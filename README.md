# QASpringToDoListProject

## Description

A simple web-browser todo list app which will enable you to keep track of your ever-growing list of uncompleted tasks

## Installation Requirements
<details>
<summary>Click to expand</summary>
  
In order to run the .jar file that contains the application you will need the following installed on your computer:
  * [Java](https://www.oracle.com/java/technologies/downloads/#jdk17-windows)
  * [MySQL](https://dev.mysql.com/downloads/)
  * Any modern web browser
  
</details>

## Configuration Details
<details>
<summary>Click to expand</summary>
  
When setting the password for your MySQL server, you will need to ensure it is set to "thanks4allthefish!" (without the quotation marks) for the application to connect successfully.
  
</details>

## Running The App (Windows 10)
<details>
<summary>Click to expand</summary>

Ensure MySQL Server is running on your machine by going into the services tab in Task Manager. If it is showing as "stopped" right click on it and select "Start"
![Task Manager](https://github.com/DanSandvig/QASpringToDoListProject/blob/dev/Documentation/Images/TaskManagerServices.png)  
  
Open your favourite terminal in the same folder as the .jar file:
![Right click menu](https://github.com/DanSandvig/QASpringToDoListProject/blob/dev/Documentation/Images/OpenInTerminal.png)  
  
Then run the command "java -jar QASpringToDoListProject-0.0.1-SNAPSHOT.jar" (without the quotation marks)
![Command line](https://github.com/DanSandvig/QASpringToDoListProject/blob/dev/Documentation/Images/RunFromCommandLine.png)
  
Many things will happen. Fear not. You can stop the app running by pressing Ctrl-c if you wish.
![Terminal Output](https://github.com/DanSandvig/QASpringToDoListProject/blob/dev/Documentation/Images/TerminalOutput.png)
  
In your web browser, navigate to "http://localhost:8080/index.html" to access the application. You can now ToDo to your heart's content. All fields other than ID can be edited simply by clicking on them and entering a new value. If you have any issues please contact your IT helpdesk who will be happy to assist you.
![Browser Example](https://github.com/DanSandvig/QASpringToDoListProject/blob/dev/Documentation/Images/BrowserExample.png)
</details>
