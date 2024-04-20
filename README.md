                Courier Management Web Application

This project is a RESTful web application developed in Java that manages streaming geolocations of couriers and provides functionalities to track their movements and interactions with Migros stores.

		Design Patterns Used
Builder Pattern: Utilized for creating instances of complex objects such as CourierInfo and StoreInfo.
Singleton Pattern: Implemented for managing repository instances to ensure only one instance is created and shared across the application.

               Request Example
url: http://localhost:8080/courierInfo
Request Body : {
  "time": 1714513033,
  "courier": "Akin kaya",
  "lat":40.96324,
  "lng": 29.0630908
}
