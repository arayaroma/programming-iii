package code;

import java.util.List;
import java.util.function.Predicate;

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

        hotel.getAvailableRooms(hotel.getTotalRooms());
        System.out.println("Available rooms: " + hotel.getAvailableRooms(hotel.getTotalRooms()).size());

    }

    public static List<Room> setFixedRooms() {
        List<Room> rooms = new java.util.ArrayList<>();
        rooms.add(new Room(1, 2, true, 100));
        rooms.add(new Room(2, 2, false, 100));
        rooms.add(new Room(3, 4, true, 200));
        rooms.add(new Room(4, 4, false, 200));
        rooms.add(new Room(5, 6, true, 300));
        rooms.add(new Room(6, 6, false, 300));
        return rooms;
    }

}

class Hotel {
    private String name;
    private List<Room> rooms;
    private Predicate<Room> availableRoom = room -> room.isAvailable();

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

    public List<Room> getAvailableRooms(List<Room> rooms) {
        return rooms.stream()
                .filter(availableRoom)
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