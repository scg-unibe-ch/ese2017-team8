# Specification ESE Project HS 2017

## Introduction
### Project Description
The goal is to create a webtool to manage logistics in order to optimize the workflow and communication between logisticians and drivers.

### Stakeholders
#### Product Owners
- Silas Berger

#### Clients/Users
- Logisticians
- Drivers

#### Project Team
- Samuel Schwegler
- Travis Petit
- Alessandro Esposito
- Jonas Furrer
- Alain Stulz

### Definitions
Customer: Delivery target, including Name, Address, Contact Data
User: Either the logistician or the driver
Parcel: Delivery item, including dimensions, weight, additional info
Delivery: Delivery consisting of a destination (customer address), one or more parcels, a scheduled date, an actual date and a status
Tour: Sum of all deliveries that a specific driver has to deliver in one day
Unscheduled: Status of a delivery meaning that it was captured in the system but not yet allocated to a driver
Scheduled: Status of a delivery meaning that it was allocated to a driver
Attempted: Status of a delivery meaning that it could not be delivered (since the customer was not at home for example)
Delivered: Status of a delivery meaning that the delivery was successful
Cancelled: Status of a delivery after it was marked as attempted twice
Archived: Status of successful delivery changes to archived when the driver finishes the tour 

### System Overview
The System contains a login page where the logisticians and the drivers are able to login with the correts username and password.
After loging in correctly there is the logistician backend and the driver backend, respectively. In the particular backend there are
the required features for the logistican/driver (see Use Cases and Requirements). The users can log out.
In order to store the data (parcels, customer, etc.) we use Hibernate and an online database (Maria DB) provided by Hosttech (see <https://www.hosttech.ch/>
for further information). In addition the web application is available using the following url: <https://logisticswebapp.cfapps.io/>.

### References
Meeting Notes: https://docs.google.com/document/d/14YOSGv_hTwon7aVOoag-TymgAiYwRcQLe8z8clhV6DE/edit?usp=sharing

## Overall Description
### Use Cases
![Use case diagram](docs\UseCaseDiagram.jpg)

#### Logisticians
##### Parcel and Delivery Overview
- As a logistician, I want to see a list of pending deliveries
- As a logistician, I want to see a list of terminated deliveries
- As a logistician, I want to see a list of cancelled deliveries
- As a logistician, I want to see some additional information for every parcel (e.g. dimensions, destination, comments etc.)

##### Tour Management
- As a logistician, I want to allocate a parcel to a specific driver

##### Analytics/Statistics
- As a logistician, I want to see the statistics for every delivery (who changed the status of the delivery and when)
- As a logistician, I want to see the statistics for every driver (number of scheduled/attempted/delivered parcels)

##### Delivery/Parcel Management
- As a logistician, I want to add new parcels to the existing list
- As a logistician, I want to have the possibility to edit existing parcels

##### User Management
- As a logistician, I want to log in with my credentials

#### Drivers
##### Tour Overview
- As a driver, I want to see an overview of my tour for the actual day
- As a driver, I want to 

##### Delivery Tracking
- As a driver, I want to mark a delivery as successful (delivered) or unsuccessful (attemted)
- As a driver, I want to finish the tour at the end of the day
- As a driver, I want to be able to change the order of the deliveries

##### Display Route 
- As a driver, I want to see the route in Google Maps
- As a driver, I want Google Maps to calculate the best route
- As a driver, I want to see a navigation 

##### User Management
- As a driver, I want to log in with my credentials

## Requirements
### Functional
 - Logistician registers a new parcel via the new order site
 - Address, Length, height, width, weight and information about dangers and fragility, plus the ability to write a commentary are its properties
 - Logistician has the possibility to overview all parcels 
 - Logistician can edit the parcels
 - Logistician distributes the deliveries to the drivers (done by hand)
 
 - Driver can modify the status of a delivery
 - Driver has access to the tour infos e.g. a map or description of way
 
 - The System should measure when the status of the deliveries is changed
 
### Non-Functional

- Only users with the correct login information can access the system
