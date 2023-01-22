import db.Storage;
import functions.AddBuilding;
import functions.AddFloor;
import functions.AddRoom;
import lombok.val;
import models.User;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.AvailabilityService;
import services.BookManagerService;
import services.SearchRoomService;

public class RoomManagementTest {
    private final AddBuilding addBuilding = new AddBuilding();
    private final AddFloor addFloor = new AddFloor();
    private final AddRoom addRoom = new AddRoom();
    private AvailabilityService availabilityService;
    private SearchRoomService searchRoomService;
    private BookManagerService bookManagerService;

    @BeforeEach
    public void setup() {
        this.availabilityService = new AvailabilityService(Storage.getBookings());
        this.searchRoomService = new SearchRoomService(availabilityService);
        this.bookManagerService = new BookManagerService(Storage.getBookings(), Storage.getUserBookings(), availabilityService);

    }

    @Test
    public void testBooking() {
        // create a user
        val user1 = new User("user1");
        // create a building
        val building1 = addBuilding.apply("building1");
        // create 2 floors
        addFloor.apply(Pair.of(building1, 1));
        addFloor.apply(Pair.of(building1, 2));
        // create 2 rooms in floor1 and 1 room on floor2
        val room11 = addRoom.apply(Pair.of("room1_1", Pair.of(building1, 1)));
        val room12 = addRoom.apply(Pair.of("room2_1", Pair.of(building1, 1)));
        val room21 = addRoom.apply(Pair.of("room1_2", Pair.of(building1, 2)));

        val rooms = searchRoomService.listRooms(user1.getId(), building1);
        Assertions.assertEquals(rooms.size(), 3);

        var availableRooms = searchRoomService.listRooms(user1.getId(), building1, 13, 18);
        Assertions.assertEquals(rooms.size(), 3);

        val booking1 = bookManagerService.bookRoom(user1.getId(), room11, 12, 13);
        val booking2 = bookManagerService.bookRoom(user1.getId(), room21, 1,6);

        Assertions.assertEquals(booking1.getBookedRoom(), room11);
        Assertions.assertEquals(booking2.getBookedRoom(), room21);
        Assertions.assertFalse(availabilityService.isAvailable(room11.getId(), 11, 13));
        Assertions.assertTrue(availabilityService.isAvailable(room21.getId(), 11, 13));

        availableRooms = searchRoomService.listRooms(user1.getId(), building1, 13, 18);
        Assertions.assertEquals(availableRooms.size(), 2);

        val userBookings = bookManagerService.showBookings(user1.getId());
        Assertions.assertEquals(userBookings.size(), 2);
        Assertions.assertEquals(userBookings.get(0), booking1);
        Assertions.assertEquals(userBookings.get(1), booking2);

        bookManagerService.cancelBooking(user1.getId(), booking1);
        availableRooms = searchRoomService.listRooms(user1.getId(), building1, 13, 18);
        Assertions.assertEquals(availableRooms.size(), 3);

    }
}
