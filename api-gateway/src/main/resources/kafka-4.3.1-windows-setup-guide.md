# Kafka 4.3.1 Setup Guide for Windows (with Spring Boot)

Kafka 4.x no longer needs Zookeeper — it uses built-in **KRaft mode**. This guide covers a clean single-node dev setup on Windows, including a fix for a common Windows 11 issue.

---

## Prerequisites

- Java 11+ installed (Java 17 recommended). Check with:
  ```
  java -version
  ```
- Download Kafka 4.3.1 binary (Scala 2.13 build) and extract it, e.g. to `C:\kafka_2.13-4.3.1`

---

## Step 1: Generate a Cluster UUID

Open `cmd` inside the Kafka folder:

```
.\bin\windows\kafka-storage.bat random-uuid
```

Copy the printed UUID (you'll reuse it in Step 2). Ignore any `Reconfiguration failed: No configuration found...` line — that's harmless log4j noise.

## Step 2: Format Storage (Single-Node / Standalone)

```
.\bin\windows\kafka-storage.bat format -t <YOUR_UUID> -c .\config\server.properties --standalone
```

`--standalone` tells KRaft this is a single-node controller+broker setup (no `controller.quorum.voters` needed).

## Step 3: Fix the `wmic` Issue (Windows 11 24H2+)

Recent Windows 11 builds removed `wmic.exe` entirely. Kafka's `kafka-server-start.bat` uses it only to detect 32-bit vs 64-bit OS for setting heap size — safe to hardcode instead.

Open `bin\windows\kafka-server-start.bat` in a text editor and find this block:

```bat
IF ["%KAFKA_HEAP_OPTS%"] EQU [""] (
    rem detect OS architecture
    wmic os get osarchitecture | find /i "32-bit" >nul 2>&1
    IF NOT ERRORLEVEL 1 (
        rem 32-bit OS
        set KAFKA_HEAP_OPTS=-Xmx512M -Xms512M
    ) ELSE (
        rem 64-bit OS
        set KAFKA_HEAP_OPTS=-Xmx1G -Xms1G
    )
)
```

Replace it with:

```bat
IF ["%KAFKA_HEAP_OPTS%"] EQU [""] (
    rem hardcoded 64-bit heap size (wmic removed on newer Windows builds)
    set KAFKA_HEAP_OPTS=-Xmx1G -Xms1G
)
```

Save the file, keeping the `.bat` extension (use "Save As" > "All Files" in Notepad).

> **Alternative fix:** try running `Add-WindowsCapability -Online -Name "WMIC~~~~"` in an admin PowerShell first. On some builds this restores `wmic` and you can skip patching the script. On newer builds (like 24H2+) the capability install reports success but `wmic.exe` still doesn't exist — in that case, use the patch above.

## Step 4: Start the Broker

```
.\bin\windows\kafka-server-start.bat .\config\server.properties
```

Keep this window open — it's your running Kafka broker on `localhost:9092`.

## Step 5: Create a Test Topic

In a **new** cmd window:

```
.\bin\windows\kafka-topics.bat --create --topic test-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```

## Step 6: Verify with Producer/Consumer

Producer:
```
.\bin\windows\kafka-console-producer.bat --topic test-topic --bootstrap-server localhost:9092
```
Type a message, press Enter.

Consumer (another new window):
```
.\bin\windows\kafka-console-consumer.bat --topic test-topic --bootstrap-server localhost:9092 --from-beginning
```
You should see the message appear.

---

## Step 7: Spring Boot Integration

**pom.xml** — add dependency:
```xml
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>
```

**application.properties:**
```properties
spring.kafka.bootstrap-servers=localhost:9092

# Consumer
spring.kafka.consumer.group-id=my-group
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
spring.kafka.consumer.properties.spring.json.trusted.packages=*

# Producer
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer
```

**Producer example:**
```java
@Autowired
private KafkaTemplate<String, String> kafkaTemplate;

public void sendMessage(String message) {
    kafkaTemplate.send("test-topic", message);
}
```

**Consumer example:**
```java
@KafkaListener(topics = "test-topic", groupId = "my-group")
public void listen(String message) {
    System.out.println("Received: " + message);
}
```

---

## Troubleshooting Quick Reference

| Error | Cause | Fix |
|---|---|---|
| `Reconfiguration failed: No configuration found...` | Log4j startup noise | Ignore — not fatal |
| `controller.quorum.voters is not set` | Missing standalone flag on format | Add `--standalone` |
| `'wmic' is not recognized` | Removed on newer Windows 11 builds | Patch `kafka-server-start.bat` (Step 3) |
