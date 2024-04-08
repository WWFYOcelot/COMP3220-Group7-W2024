# Windsor Biking Data
# COMP-3220 Winter 2024 Group 7 

Bicycling Infrastructure Dashboard for Windsor, Ontario This project aims to enhance the safety and convenience of cycling in Windsor, Ontario, through a bicycling infrastructure dashboard. The dashboard provides various features, including displaying hazardous areas, viewing local bike routes, finding optimal cycling routes, and integrating with public transit for a seamless biking experience. The project utilizes data from Open Data Windsor and the Google Maps API to provide valuable insights and improve biking conditions in the city.

Javadoc can be found at this link:
https://wwfyocelot.github.io/COMP3220-Group7-W2024/JavaDoc/com/example/googletest/package-summary.html

Files:
Pothole_2022.csv - raw data acquired from the Windsor open data portal   
Pothole_2022.json - raw data converted to JSON format   
convert.py - Python script to convert data from .csv to .json    

(dir) GoogleTest - contains application code   
CustomMapImage.java - class that generates map images to display output of various use cases   
GeocodingParser.java - object to parse & store results from the Geocoding API   
HelloApplication.java - front-end user interface application    
HelloController.java - program to initiate front-end application    
PotholeData.json - JSON file containing data on potholes in Windsor, acquired from the Windsor open data portal, for use in our application    
PotholeDataParser.java - object to parse & store data concerning potholes, which was originally in JSON format    
PotholeReader.java - class to read pothole data, with methods to make that data accessible    
