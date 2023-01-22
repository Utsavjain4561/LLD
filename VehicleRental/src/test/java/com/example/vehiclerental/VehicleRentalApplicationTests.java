package com.example.vehiclerental;

import com.example.vehiclerental.controllers.BookingController;
import com.example.vehiclerental.controllers.InventoryController;
import com.example.vehiclerental.controllers.SearchController;
import com.example.vehiclerental.controllers.SelectionController;
import com.example.vehiclerental.controllers.UtilityController;
import com.example.vehiclerental.enums.BookingMode;
import com.example.vehiclerental.enums.UserEnum;
import com.example.vehiclerental.enums.UtilityTypeEnum;
import com.example.vehiclerental.enums.VehicleStateEnum;
import com.example.vehiclerental.enums.VehicleTypeEnum;
import com.example.vehiclerental.factories.UserFactory;
import com.example.vehiclerental.model.Admin;
import com.example.vehiclerental.model.Buyer;
import com.example.vehiclerental.model.User;
import com.example.vehiclerental.model.Utility;
import com.example.vehiclerental.model.Vehicle;
import com.example.vehiclerental.services.BookingManagerService;
import com.example.vehiclerental.services.InventoryManagerService;
import com.example.vehiclerental.services.InvoiceGenerationService;
import com.example.vehiclerental.services.SearchInventoryService;
import com.example.vehiclerental.services.SelectionService;
import com.example.vehiclerental.services.UtilityManagerService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@SpringBootTest
class VehicleRentalApplicationTests {
    private InventoryController inventoryController;
    private SearchController searchController;
    private SelectionController selectionController;
    private BookingController bookingController;
    private UtilityController utilityController;
    @BeforeEach
    void setup(){
        val inventoryManagerService = new InventoryManagerService();
        val selectionService = new SelectionService();
        val bookingManagerService = new BookingManagerService(inventoryManagerService);
        val searchInventoryService = new SearchInventoryService(inventoryManagerService, bookingManagerService);
        val invoiceGenerationService = new InvoiceGenerationService();
        val utilityManagerService = new UtilityManagerService();
        inventoryController = new InventoryController(inventoryManagerService);
        searchController = new SearchController(searchInventoryService);
        selectionController = new SelectionController(selectionService);
        bookingController = new BookingController(bookingManagerService, invoiceGenerationService);
        utilityController = new UtilityController(utilityManagerService);

    }

    @Test
    void contextLoads() throws InterruptedException {
        // create an admin user and a buyer user
        val admin = UserFactory.createUser(UserEnum.ADMIN, "admin123", "utsav", User.UserProps.builder().build());
        val buyer = UserFactory.createUser(UserEnum.BUYER, "vin123", "vinayak", User.UserProps.builder()
                .address("Agra")
                .email("vinayak@gmail.com")
                .phoneNo(547583434L)
                .build());
        addInventory(admin);
        log.info("Admin details : {} ", admin);log.info("Buyer details : {} ", buyer);
        Assertions.assertInstanceOf(Admin.class, admin);
        Assertions.assertInstanceOf(Buyer.class, buyer);

        // Get all Available vehicles
        val availableVehicles = searchController.searchVehicles();
        availableVehicles.forEach(vehicle -> Assertions.assertEquals(VehicleStateEnum.AVAILABLE, vehicle.getState()));

        // Select vehicle 1 from the list
        var selectedVehicle = selectionController.selectVehicle(availableVehicles.get(0));
        log.info("Selected Vehicle : {}", selectedVehicle);
        Assertions.assertEquals(availableVehicles.get(0), selectedVehicle.getVehicle());

        // Add 2 utilities
        val service = Utility.builder().name("ALS-Braking").type(UtilityTypeEnum.SERVICES).build();
        val device = Utility.builder().name("Music-player").type(UtilityTypeEnum.DEVICES).build();
        selectedVehicle = utilityController.addUtility(selectedVehicle, List.of(service, device));
        Assertions.assertEquals(selectedVehicle.getUtilities(), List.of(service, device));

        // book this vehicle
        val booking = bookingController.book(selectedVehicle, buyer.getUserId(), BookingMode.ONLINE);
        Assertions.assertEquals(VehicleStateEnum.UNAVAILABLE, selectedVehicle.getVehicle().getState());
        Assertions.assertEquals(buyer.getUserId(), booking.getUserId());
        Assertions.assertEquals(BookingMode.ONLINE, booking.getBookingMode());

        // search for the vehicles booked by a user
        val vehiclesBookedByUser = searchController.searchVehicles(buyer.getUserId());
        Assertions.assertEquals(availableVehicles.get(0), vehiclesBookedByUser.get(0));

        Thread.sleep(2000);

        // cancel this booking
//        bookingController.cancel(booking);
//        Assertions.assertEquals(VehicleStateEnum.AVAILABLE, selectedVehicle.getVehicle().getState());

        // return the vehicle
        val invoice = bookingController.returnVehicle(booking);
        Assertions.assertEquals(VehicleStateEnum.AVAILABLE, selectedVehicle.getVehicle().getState());
        Assert.notNull(invoice, "Invoice is present");
        log.info("Invoice is :{} ", invoice);

    }

    private void addInventory(final User adminUser) {
        val vehicle1 = Vehicle.builder().id("vehicle1").name("Bolero").type(VehicleTypeEnum.HATCHBACK).build();
        val vehicle2 = Vehicle.builder().id("vehicle2").name("Harley-Davidson").type(VehicleTypeEnum.MOTORCYCLE).build();
        val vehicle3 = Vehicle.builder().id("vehicle3").name("Fortuner").type(VehicleTypeEnum.SUV).build();
        val vehicle4 = Vehicle.builder().id("vehicle4").name("Atlas").type(VehicleTypeEnum.BICYCLE).build();
        val vehicles = List.of(vehicle1, vehicle2, vehicle3, vehicle4);
        vehicles.forEach(vehicle -> {
            try {
                inventoryController.addVehicle(adminUser, vehicle);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

}
