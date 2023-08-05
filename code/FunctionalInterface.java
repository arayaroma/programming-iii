package code;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

/*
 * Functional Interface
 * 
 * Added:
 * - Predicate
 * - BiPredicate
 * 
 * Left to add:
 * 
 * - Function
 * - BiFunction
 * - Consumer
 * - BiConsumer
 * - Supplier
 * - UnaryOperator
 * - BinaryOperator
 */

public class FunctionalInterface {

    public static void main(String[] args) {
        Driver();
    }

    public static void Driver() {
        MockedHotel();
    }

    public static void MockedHotel() {
        Hotel hotel = new Hotel("Hotel California", setFixedRooms());

        System.out.println("Hotel name: " + hotel.getName());
        System.out.println("Total rooms: " + hotel.getTotalRooms().size());

        System.out.println("Available rooms: " + hotel.getAvailableRooms().size());

        System.out.println("Available rooms with capacity 2: " +
                hotel.getAvailableRooms()
                        .stream()
                        .filter(hotel.getRoomsBasedInCapacity(2))
                        .count());

        System.out.println("Available rooms with price 100: " +
                hotel.getRoomsBasedInPrice(100).size());

        System.out.println("Available rooms with capacity 6: " +
                hotel.getRoomsAvailableBasedInCapacity(6).size());

        System.out.println("Room numbers: " + hotel.getRoomNumbers());

    }

    public static List<Room> setFixedRooms() {
        List<Room> rooms = new java.util.ArrayList<>();
        rooms.add(new Room(105, 2, true, 100));
        rooms.add(new Room(203, 2, false, 100));
        rooms.add(new Room(302, 4, true, 200));
        rooms.add(new Room(404, 4, false, 200));
        rooms.add(new Room(500, 6, true, 300));
        rooms.add(new Room(604, 6, false, 300));
        return rooms;
    }

}

class Hotel {
    private String name;
    private List<Room> rooms;

    public Hotel(String name, List<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public List<Room> getTotalRooms() {
        return rooms;
    }

    public List<Room> getAvailableRooms() {
        Predicate<Room> availableRoom = room -> room.isAvailable();
        return rooms.stream()
                .filter(availableRoom)
                .collect(java.util.stream.Collectors.toList());
    }

    public Predicate<Room> getRoomsBasedInCapacity(int capacity) {
        return room -> room.getCapacity() == capacity;
    }

    public List<Room> getRoomsBasedInPrice(int price) {
        return getAvailableRooms()
                .stream()
                .filter(room -> room.getPrice() == price)
                .collect(java.util.stream.Collectors.toList());
    }

    public List<Room> getRoomsAvailableBasedInCapacity(int capacity) {
        BiPredicate<Room, Integer> availableRoomWithCapacity = (room, roomCapacity) -> room.isAvailable()
                && room.getCapacity() == roomCapacity;

        return getAvailableRooms()
                .stream()
                .filter(room -> availableRoomWithCapacity.test(room, capacity))
                .collect(java.util.stream.Collectors.toList());
    }

    public List<Integer> getRoomNumbers() {
        Function<Room, Integer> getRoomNumber = room -> room.getNumber();
        return rooms.stream()
                .map(getRoomNumber)
                .collect(java.util.stream.Collectors.toList());
    }
}

class Room {
    private int number;
    private int capacity;
    private boolean isAvailable;
    private int price;

    public Room(int number, int capacity, boolean isAvailable, int price) {
        this.number = number;
        this.capacity = capacity;
        this.isAvailable = isAvailable;
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getPrice() {
        return price;
    }

}