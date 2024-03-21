# Instruction for running an application

## Prerequisites
IntelliJ Idea, Maven and Tomcat 10 or higher

To be able to run the app successfully you should follow next steps

1. Clone the github repository
2. Import application in Intellij Idea
3. Build the application by using command mvn clean package and after building you will get war file of application in target folder
4. Download and run Tomcat server (on windows you should run startup.bat script from terminal in bin directory)
5. Deploy web application on Tomcat server by copying war file in webapps folder or by using tomcat manager(in case of the manager you will have to set certain permissions and credentials)

After all these steps you should be able to access the application and GraphiQL UI on the next URL: http://localhost:8080/TestApp/graphiql
