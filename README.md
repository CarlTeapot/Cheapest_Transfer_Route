# Optimal_Transfer_Route
Hello. This is a small server-side project that solves the following problem: given maximal weight and a list of routes, defined by cost and weight, calculate the maximal cost that can be achieved by combining different routes, while the total sum of the weights of the used routes does not exceed maximal weight. This is a very well known problem in data structures and algorithms, called "Knapsack" problem. the algorithm I used is a standard 0-1 knapsack variation algorithm using dynamic programming with time complexity of O(n * maxWeight), where n is the amount of travel routes.  


1) Install jdk 21:
Windows:
    Download JDK 21 from this link and run the .exe file https://www.oracle.com/java/technologies/downloads/?er=221886#jdk21-windows
Linux: 
   run this command in the terminal:
   
   sudo apt update
   sudo apt install -y openjdk-21-jdk

2) build the application

Windows:
  run this command, replace {project directory} with the actual location
    cd {project directory}
    gradlew build
    cd build/libs
    java -jar Zerobyte_Assignment-0.0.1-SNAPSHOT.jar

if the .jar file has a different name, run the dir command to get the contents of the folder and replace the file name in the command

Ubuntu: 
  


   
