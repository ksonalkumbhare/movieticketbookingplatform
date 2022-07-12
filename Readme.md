# Movie Ticket Booking Platform 

## Implementation

1. Application is developed in Spring Boot with Java 8 on Spring Tool Suite IDE. Database used is h2 in memory database.

2. You can book movie tickets using the application.

3. Mandatory entities to book a ticket are - user, movie, theater with seats, shows of configured movies in configured theater with seats.

4. Logging is done on console and file

5. Exception Handling is done using ExceptionInterceptor. 

6. JUnit implemented for ticket/book with Jacoco plugin to generate code coverage. 


## Assumptions

For the simplicity of system, I have made following assumptions while implementing the solution -

1. Single User Model - One user will use at once. No locking implemented for seat selection. 
2. Single Screen Theaters - No multiple screen handling for a theater has been done. However an option is given for future scope.
3. 10 seats for each show are configured - 5 for CLASSIC and 5 for PREMIUM. Seat numbers are kept fixed in all theaters. 
4. No Payment flow used.
5. Seat Locking API is not added using in Memory cache


## Setting up the data

1. Access the application using swagger on `http://localhost:8080/ticketBooking/swagger-ui.html`

2. Execute the API `http://localhost:8080/ticketBooking/initialize/generate` from swagger inside the heading `data-populator`. This will create a sample user, movie, theater, theater seats, shows, shows seats that will be used in booking ticket.


## Booking a ticket

1. Use the `show-controller` heading in swagger and call the `search` API inside it using `pageNo=1` and `limit=10` to see the available list of shows.

2. Select any show from the above search result and copy its id.

3. Now go to `ticket-controller` in the swagger and  execute the book ticket API using the following request body - 

{"seatType":"PREMIUM","seatsNumbers":["ROW2-A","ROW2-B","ROW2-C"],"showId":10,"userId":1}

{
  "seatType": "PREMIUM",
  "seatsNumbers": [
    "ROW2-A","ROW2-B","ROW2-C"
  ],
  "showId": 10,
  "userId": 1
}

This will book a ticket for you and you will get ticket id along with show details in response.


## Verifying the results from DB

1. Login to your h2-Console and go to `http://localhost:8080/ticketBooking/h2-ui/` DB

2. `SELECT * FROM users;` to see all registered users.

3. `SELECT * FROM movies;` to see all movies.

4. `SELECT * FROM theaters;` to see all theaters.

5. `SELECT * FROM theater_seats;` to see all theater's seats.

6. `SELECT * FROM shows;` to see all shows for movies in theaters.

7. `SELECT * FROM show_seats;` to see all show's seats by type.

8. `SELECT * FROM tickets;` to see all booked tickets.

9. `SELECT * FROM offers;` to see all booked tickets.

## Other API Details

1. `UserController` -  API to add and get user

2. `MovieController` - API to add and get movie

3. `TheaterController` - API to add and get theater

4. `ShowController` - API to add and search show


## Future Scope

1. Multiple user handling 
2. Seat locking during payment
3. Multiple Screen handling in theater
4. Seat management on the fly via integrating with Theater having existing IT system and new         	theaters and localization(movies)
5. Integration with payment gateways
6. Login and User Account Management
