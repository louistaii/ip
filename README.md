# **Casio**

## **💬 Overview**
Casio is a **task manager chatbot** designed to help you efficiently **add, edit, and manage your tasks** all in one application!

### **🔹Key Features**
1. **Task Organization** – Easily **add, delete, mark, and unmark tasks**, including **To-Dos, Deadlines, and Events**.
2. **User-Friendly Interface** – Lightweight and intuitive chatbot experience.
3. **Persistent Storage** – Your tasks are automatically saved locally, so nothing gets lost when you exit the app.
4. **Flexible Sorting** - Arrange your task list by time or in alphabetical order.
5. **Search and Filters** – Quickly find tasks by keywords or filter them by task type.



## **🚀 Quick Start**

### **📦 Prerequisites**
Make sure you have the following installed:
- **Java Development Kit (JDK 17)**
- **Latest version of IntelliJ IDEA**

### **🔧  Setting up in Intellij**
1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
    ██████╗  █████╗ ███████╗██╗ ██████╗  
   ██╔════╝ ██╔══██╗██╔════╝██║██╔═══██╗
   ██║      ███████║███████╗██║██║   ██║
   ██║      ██╔══██║╚════██║██║██║   ██║
   ╚██████╗ ██║  ██║███████║██║╚██████╔╝
   ╚═════╝ ╚═╝  ╚═╝╚══════╝╚═╝ ╚═════╝  
   ★彡★彡★彡★彡★彡★彡★彡★★彡★彡★彡★彡★彡★彡★
   
   Casio:
   Hello!! I'm Casio, your personal chat bot. ⊂(◉‿◉)つ
   What can I do for you?
   ```

**⚠️ Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.

## **📖 User Guide**
For detailed instructions on commands and features, check out the [User Guide](./docs/README.md).