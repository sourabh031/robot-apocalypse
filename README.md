                       Robot Apocalypse


Steps to run application:

1)Build application in local

   mvn package -DskipTests

2) Run the application

Run RobotApocalypseApplication.java class in ant IDE or run from command prompt with below command

mvn spring-boot:run

Rest endpoint details:

Database Tables: a) Survivers b)surviver_resources

A)	Add survivors to the database

Resource Payload: 
URL- POST: http://localhost:8080/robot/surviver/create
Body- {
    "name": "Rahul",
    "age": 31,
    "gender": "male",
    "location": "27.2046° N, 77.4977° E",
    "resources": [
        "water",
        "Maditation"
    ]
}


B)	Update survivor location

Resource Payload: 
URL- PATCH: http://localhost:8080/robot/surviver/updateLocation/{id} 
Body- 
     {
    "location": "27.2046° S, 77.4977° S"
  }

C)	 Flag survivor as infected

Resource Payload: 
URL- PUT: http://localhost:8080/robot/surviver/markInfected/{id} 



D)	 Connect to the Robot CPU system

Data is sorted by category and model name humans to understand and process.

Resource Payload: 
URL- GET: http://localhost:8080/robot/surviver/robots 


E)	Percentage of infected and non-infected survivors. 

Resource Payload: 
URL- GET: http://localhost:8080/robot/surviver/percentage 


F)	List of infected and survivors. 

Resource Payload: 
URL- GET: http://localhost:8080/robot/surviver/infected 


G)	List of non-infected survivors. 

Resource Payload: 
URL- GET: http://localhost:8080/robot/surviver/non-infected 


H)	List of robots 

Resource Payload: 
URL- GET: http://localhost:8080/robot/surviver/robots 
