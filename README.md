# Movie Search

Author: [Paweł Dobrzański](https://www.linkedin.com/in/pawel-dobrzanski/)  
Created: July 2020  
Technology stack: Spring Boot, Groovy, MongoDB, JSON, Thymeleaf, HTML, Bootstrap, CSS, Font Awesome, jQuery.

## Description

Simple search mechanism that finds movie titles by actors who starred in them.

## Installation guide

### Prerequisites

- [Java 8+](https://www.java.com/pl/download/)
- [MongoDB 4.2](https://docs.mongodb.com/manual/installation/) (also include MongoDB Compass client)
- [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git) (optional)

Please install the above software by following appropriate links. Leave Mongo settings by default.

### Installation steps

1. Clone git repository: `git clone https://github.com/YaeriusFCM/SimpleSearch.git`  
   (Alternatively you can download a ZIP file and unpack it if you don't want to install Git)
1. Run MongoDB Compass. Create new database named "test" with collection named "movie" (singular!)
1. Select "movie" collection and click on "ADD DATA" button
1. Choose "Import file" and browse for `SimpleSearch/src/main/resources/db-input.json`
1. After the import, the collection should consist of 500 documents
1. Enter the **SimpleSearch** folder and compile the project by running: `gradlew build` (gradlew.bat if on Windows)
1. Start the application by running: `java -jar build/libs/SimpleSearch-0.0.1.jar`
1. Open your browser and enter: `localhost:8080`
1. Enjoy :)
