import controllers.BookingController;
import controllers.HotelOnboardingController;
import controllers.SearchHotelsController;
import enums.UserType;
import lombok.val;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.*;

import java.util.Date;
import java.util.Set;


public class ClearTripTest {

    private  HotelOnboardingController hotelOnboardingController;
    private BookingController bookingController;
    private SearchHotelsController searchHotelsController;
    @BeforeEach
    public void setup(){
        val hotelOnboardingService = new HotelOnboardingService();
        val inventoryManagerService = new InventoryManagerService();
        val availabiltyService = new AvailabilityService();
        val searchmanagerService = new SearchManagerService(availabiltyService);
        val bookingManagerService = new BookingManagerService(availabiltyService);
        hotelOnboardingController = new HotelOnboardingController(hotelOnboardingService, inventoryManagerService);
        bookingController = new BookingController(bookingManagerService);
        searchHotelsController = new SearchHotelsController(searchmanagerService);

    }

    @Test
    public void test(){
        val admin1 = new User("tej", UserType.ADMIN);
        val user1 = new User("raj", UserType.CUSTOMER);
        val hotel1= hotelOnboardingController.onboardProperty(admin1, "Hotel My Place", "Bangalore", 3,
                Set.of("Deluxe", "Luxury"));
        // Add inventory for hotel1
        hotelOnboardingController.addInventory(admin1, hotel1.getTitle(), "Deluxe", 3000.00, 3);
        val hotels = searchHotelsController.searchHotels(new Date(2022, 12, 01), new Date(2022, 12, 03));
        searchHotelsController.searchHotels(new Date(2022, 12, 01), new Date(2022, 12, 03), "Bangalore");
        searchHotelsController.searchHotels(new Date(2022, 12, 01), new Date(2022, 12, 03), 1000.00, 4000.00);
        searchHotelsController.searchHotels(new Date(2022, 12, 01), new Date(2022, 12, 03),  "Bangalore", 1000.00, 4000.00);



        val booking = bookingController.book(user1, hotel1.getTitle(), "Deluxe",
                new Date(2022, 12, 01), new Date(2022, 12, 03));
        //show user bookings
        val userBookings = bookingController.showBooking(user1);
        val adminBooking = bookingController.showBooking(admin1);
        bookingController.cancelBooking(booking);

    }
}
