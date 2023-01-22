Entities
1. User --> user_id, name, , type=USER, email, address, phoneNo
2. Admin --> user_id, name, type=ADMIN
3. Vehicle -> id, name, type, state
4. BookedVehicle -> Vehicle, List<Utility>
4. Booking --> id, user_id, BookedVehicle. pickDate, pickup, bookDate, mode
5. Utility --> name, type=[SERVICE, DEVICE]
6. BookingMode --> ONLINE, SCANNED

State
1. inventory -> List<Vehicle>
2. TimeSearch -> Map<Date, List<BookedVehicle>>
3. BookedHistory -> Map<user_id, List<BookedVehicle>>

API's
1. Admin --> addVehicle() --> update[Inventory]

2. User --> searchVehicle() --> list of available vehicles
6. User -> searchVehicles(user_id) --> List<BookedVehicle>
6. User -> searchVehicles(timePeriod) --> List<BookedVehicle>   -- PENDING

3. User --> selectVehicle(Vehicle) --> BookedVehicle

4. User --> addUtility(List<Utility>) --> BookedVehicle

4. User --> book(BookedVehicle)
   a. online booking
   b. Scanned booking  --> Booking

5. User --> cancel(Booking) --> update [Inventory[Vehicle]]

6. User --> return(Booking) --> invoiceGeneration --> update [Inventory[Vehicle]]

NotificationService --> Booking --> sendNotification to 3rd party with notify Date of +1 day of bookDate



Assumption --> 1 booking contains 1 booked Vehicle


Controllers

1. InventoryController --> InventoryMangerService [updateInventory]

2 SearchController --> SearchVehicleService

3. SelectController --> SelectVehicleService

4. UtilityController --> UtilityService

5. BookingController --> BookingService -- [book, cancel & return]

6. NotifcationService
7. InvoiceGenerationService