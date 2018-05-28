# Hair Salon
This is a simple web application built with java.
## Requirements
Assuming that you have these tools installed in your machine:
* Gradle;
* Java Development Kit;
* Spark;
* Postgres SQL.
## Setup/Installation
Clone or download this repository in your local machine and run this commands inside your terminal:
* psql;
* CREATE DATABASE hair_salon
* CREATE TABLE stylists(id serial PRIMARY KEY, stylist_fname varchar, stylist_lname varchar, specialty varchar)
* CREATE TABLE clients(id serial PRIMARY KEY, client_fnmae varchar, client_lname varchar, client_style)
* Open your terminal and navigate to the application's root folder
* Run *gradle run* command
* Navigate to the link *http://localhost:4567*.
## Bugs
There are no known bug(s) yet.
## Contact
* E-mail: jakwam@gmail.com
## License
The Application is registered under the MIT copyright(c) 2018, **Joel Akwam** 
