#To Run the project 
## Setting Up the Environment

### Setting JAVA_HOME in IntelliJ IDEA

1. **Open your Project in IntelliJ IDEA:**
   - Launch IntelliJ IDEA and open your project.

2. **Open Run/Debug Configurations:**
   - Click on `Run` in the top menu bar.
   - Select `Edit Configurations...` from the dropdown.

3. **Edit the Configuration:**
   - In the `Run/Debug Configurations` dialog, select the application configuration for which you want to set the environment variables. If you don't have a configuration, you can create a new one by clicking the `+` icon and selecting `Application`.
   - In the `Configuration` tab, find the `Environment` section.

4. **Set Environment Variables:**
   - In the `Environment variables` field, click the `...` button (or `Edit` button) next to it.
   - In the `Environment Variables` dialog, click the `+` icon to add a new environment variable.
   - Set `Name` to `JAVA_HOME` and `Value` to your JDK path (e.g., `C:\Program Files\Java\jdk-<version>` on Windows or `/Library/Java/JavaVirtualMachines/jdk-<version>.jdk/Contents/Home` on macOS, `/usr/lib/jvm/java-<version>-openjdk` on Linux).
   - Click `OK` to save the variable.

5. **Apply the Changes:**
   - Click `Apply` and then `OK` to save your changes.

### Verifying Environment Variables

To verify that the environment variables are set correctly, you can run a simple Java application that prints the environment variables:

```java
public class Main {
    public static void main(String[] args) {
        String javaHome = System.getenv("JAVA_HOME");
        System.out.println("JAVA_HOME: " + javaHome);
    }
}

- Main.java: Entry point of the application.
- Message.java: Represents a message in the queue.
- MessageQueue.java: Implements a thread-safe message queue.
- Producer.java: Simulates a producer that generates messages.
- Consumer.java: Simulates a consumer that processes messages and logs successes and errors.
- ProducerConsumerTest.java: Unit tests for the producer-consumer scenario.


6. **To run the project:**
- mvn clean install
- java -cp target/message-driven-app-1.0-SNAPSHOT.jar com.example.Main
- mvn test
