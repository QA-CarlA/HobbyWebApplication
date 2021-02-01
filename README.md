# Apex Registration System

The Apex Registration System allows Creating, Viewing, Updating and Deleting of a Player's and Team's details using a website

### Technologies Used
* Java (Java Version 14) - [Link](https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html)

* Maven - [Link](https://maven.apache.org/)

* Spring Boot - [Link](https://spring.io/tools)

* Bootstrap (CSS Framework) - [Link](https://getbootstrap.com/)

* Git (not mandatory but makes it easier) - [Link](https://git-scm.com)

To verify that java has been installed properly you can type "java -version" on the command line likewise with maven by typing "mvn -version" on the command line

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
