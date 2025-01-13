# Optimal_Transfer_Route
Hello. This is a small server-side project that solves the following problem: given maximal weight and a list of routes, defined by cost and weight, calculate the maximal cost that can be achieved by combining different routes, while the total sum of the weights of the used routes does not exceed maximal weight. This is a very well known problem in data structures and algorithms, called "Knapsack" problem. the algorithm I used is a standard 0-1 knapsack variation algorithm using dynamic programming with time complexity of O(n * maxWeight), where n is the amount of travel routes.  


Install jdk 21:

Windows:
   
    Download JDK 21 from this link and run the .exe file https://www.oracle.com/java/technologies/downloads/?er=221886#jdk21-windows

Linux: 
   run the following command in the terminal:
   
   sudo apt update
   
   sudo apt install -y openjdk-21-jdk

 build and run the application

Windows:
 
  run this command, replace {project directory} with the actual location
  
    cd {project directory}
    
    gradlew build
    
    cd build/libs
    
    java -jar Zerobyte_Assignment-0.0.1-SNAPSHOT.jar


if the .jar file has a different name, run the dir command to get the contents of the folder and replace the file name in the command

Linux: 

    cd {project directory}
    
    chmod +x ./gradlew
    
    ./gradlew build
    
    cd build/libs
    
    java -jar Zerobyte_Assignment-0.0.1-SNAPSHOT.jar
    


Application listens to port 8080, so the port must not be used by any other application. here is how to free the ports:

Windows: run cmd as administrator and type the following command:

    for /f "tokens=5" %a in ('netstat -ano ^| findstr :8080') do taskkill /PID %a /F
    
Linux:
    
    sudo fuser -k 8080/tcp
    


3) in order to use the application, you need postman or a similar software.
    
The software has two features 
1) you can send a json bady containing the maxWeight and list of transfers, and the program will return the optimal route to you
2) you can send a request to the program to generate a random list of trasfers and maxWeight and then it will find the optimal route in the list

Both are POST requests

You need to use Postman or a similar software for the first option. Here is an example of a valid json body that the code accepts:

{
  "maxWeight": 50
,
  "availableTransfers":
  [
    {
      "weight":
      10
    ,
      "cost": 60
    },{
    "weight": 20
  ,
    "cost": 100
  },{
    "weight":
    30
  ,
    "cost":
    120
  }
  ]

}

url addresses:
1) http://localhost:8080/transfer/process
2) http://localhost:8080/transfer/processRandom

if you are running the jar file on ubuntu virtual machine, then the url address would be http://{ubuntu ip address}:8080/transfer/processRandom

you can find the ubuntu ip address by typing this command in the ubuntu terminal:

   ip addr show

   

   
