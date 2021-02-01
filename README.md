# Hobby Web Application

The Hobby Web Application (Apex Registration System) project allows Creating, Viewing, Updating and Deleting of a Player's and Team's details using a web interface

### Technologies Used
* Java (Java Version 14) - [Link](https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html)

* Maven - [Link](https://maven.apache.org/)

* Spring Boot - [Link](https://spring.io/tools)

* Bootstrap (CSS Framework) - [Link](https://getbootstrap.com/)

* Git (not mandatory but makes it easier) - [Link](https://git-scm.com)

To verify that java has been installed properly you can type "java -version" on the command line likewise with maven by typing "mvn -version" on the command line

### Installing

To start off, navigate to the directory that you want to place the folder (in my case its C:\Users\User\ProjectLocation) using git or the Command Prompt
If you don't have git installed then just download the zip file and extract it in the folder

If you have git installed
```
-Open Command Prompt-
cd Documents/ProjectLocation
OR
Right Click on ProgramLocation folder then press 'Git Bash here'
```

Copy the repository link and use 'git clone -insert link here-' 

```
C:\Users\User\ProjectLocation> git clone https://github.com/QA-CarlA/IMS-Starter.git
```

To run the Jar file, open to project folder, navigate to the JAR folder in documentation then run the jar file.

```
C:\Users\User\ProjectLocation> cd HobbyWebApplicatioon/Documentation/JAR
C:\Users\User\ProjectLocation\IMS-Starter> java -jar HobbyWebApp-0.0.1-SNAPSHOT.jar 
```

This will run it in localhost:8080 (to access the web page) and localhost:8080/h2 (to access the DB directly)

## Testing

To be able to run the tests, open Command Prompt on the project folder then type 'mvn test'
```
C:\Users\User\ProjectLocation\HobbyWebApp> mvn test
```

Currently the project has over 88.9% coverage

<a href="https://imgur.com/YXQxRbh"><img src="https://i.imgur.com/YXQxRbh.png" title="source: imgur.com" /></a>

### Unit Testing

I have used JUnit to test the CRUD functions of the Player/Team service classes to make sure that it is interacting with the database correctly

Located in the ***com/qa/services*** test folder.

### Integration Testing

I have used Mockito to test the Player/Team controller classes, Testing these classes does not require connecting to the database as the results are mocked. This allows me to tests if the controller class is able to handle the input that it receives via HTTP requests.

Located in the ***com/qa/controllers*** test folder.

### Selenium Testing

I have used Selenium which is a automation tool that allowed me to test the functionality of my webpage.

Located in the ***com/qa/selenium*** test folder.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors
* **Carl Angeles** - [QA-CarlA](https://github.com/QA-CarlA) - [Jira](https://test20novsoft2.atlassian.net/secure/RapidBoard.jspa?rapidView=5&projectKey=HP&selectedIssue=HP-8&atlOrigin=eyJpIjoiNjFmZGM2ZTlhZWJlNDc3YzgxYmMwMjc4MDQyOGZkNGQiLCJwIjoiaiJ9)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments
The project wouldn't be possible without the help of the QA Trainers that have taught me what I needed for this project.
