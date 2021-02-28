
## Laboratory System
This application to creates, updates and read different categories, items and experiments.
You can create the experiments with item used while performing the experiment of different categories.

## Features

 - Creates, update and read a category
 - Creates, update and read an Item
 - Creates, update and read a Experiment details
 
## Tech
 - Spring boot
 - maven
 - Java 10
 - H2
 
## Run the application
 To run the application 
 - run mvn clean install
 - run as the spring boot application
 - Application will start on 8181 port, in order to change the port go to the application.properties file and replace the port value under the property : server.port:8181
 - Integrated swagger for better visibility. Once the application is started open the chrome and hit : http://localhost:8181/swagger-ui.html
 - It will show all the controller classes present in the application. 
 - Have used in-memory H2 database, to open the database goto the link: http://localhost:8181/h2-console and insert username/password as labforward/labforward. 
 - It will show all the tables created and used in the application with some default value. 
 
## Enhancement in scope
 Following items can be enhanced 
  - Could have implemented UI also, as I have basic understanding of creating an UI application.
  - As of now capturing only basic details, could have inserted more detailed items of different categories. 
  - Categories could have further divided into sub-categories. 
  - As of now only experiment details, with item used in that experiment is captured. would have created experiment phases also. So we can capture in which phase what was the result. 
  
## Sample Test Data

- Create Category

[
  {
    "categoryName": "Sample"
  },
  {
	"categoryName": "Device"
   }
]

- Create Item

[
  {
    "name": "Sodium",
    "quantity": "0.5gm",
    "category": {
      "categoryName": "Chemicals"
    }
  },
  {
    "name": "Potassium",
    "quantity": "0.5gm",
    "category": {
      "categoryName": "Chemicals"
    }
  }
]

- Create Experiment

{
  "experimentName": "ABC Test",
  "experimentPhase": "initial",
  "experimentDescription": "simple test experiment",
  "item": [
    {
      "category": {
        "categoryName": "Sample"
      },
      "name": "chloride",
      "quantity": ".9gm"
    }
  ]
}

