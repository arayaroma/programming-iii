package code;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/*
* Done:
* 1. filter(Predicate)
* 2. allMatch(Predicate)
* 3. anyMatch(Predicate)
* 4. min(Comparator)
* 5. max(Comparator)
* 
 * To do:
 * 6. map(Function)
 * 7. reduce(T, BiFunction)
 * 8. forEach(Consumer)
 * 9. sorted(Comparator)
 * 10. collect(Collector)
 * 11. toList
 * 12. toSet
 * 13. groupingBy(Function)
 * 14. partitioningBy(Predicate)
 * 15. groupingBy(Function, Collector)
 * 16. groupingBy(Function, Supplier, Collector)
 */

public class Streams {
    public static void main(String[] args) {
        Driver();
    }

    public static void Driver() {
        MockedFlight();
    }

    public static void MockedFlight() {
        List<Flight> flights = createFlights();
        System.out.println("Total flights: ");
        flights.forEach(flight -> System.out.println(flight.toString()));

        List<Flight> filteredFlights = getFlightWhereCapacityIsMoreThan300(flights);

        System.out.println("Filtered flights: ");
        filteredFlights.forEach(flight -> System.out.println(flight.toString()));

        System.out.println("Filtered flights where price is lower than 300: ");
        getFlightsWherePriceIsLowerThan300(flights).forEach(flight -> System.out.println(flight.toString()));

        System.out.println("All flights where year is 2020: " + allFlightsWhereYearIs2020(flights));

        System.out.println("Exists flight with number 1 and destination London: " +
                existsFlight(flights, 1, "London"));

        System.out.println("Cheapest flight to London: " + getCheapestFlight(flights, "London"));

        System.out.println("Most capacity flight to Helsinski: " + getMostCapacityFlight(flights, "Helsinski"));

        System.out.println("Sum of prices: " + getSumOfPrices(flights));

    }

    public static List<Flight> createFlights() {
        List<Flight> flights = new java.util.ArrayList<>();
        Flight flight1 = new Flight(1, "London", 100, 200, LocalDate.of(2020, 1, 30));
        Flight flight2 = new Flight(2, "New Jersey", 200, 250, LocalDate.of(2020, 1, 15));
        Flight flight3 = new Flight(3, "Alajuela", 250, 300, LocalDate.of(2020, 2, 3));
        Flight flight4 = new Flight(4, "Helsinski", 300, 350, LocalDate.of(2020, 2, 5));
        Flight flight5 = new Flight(5, "Tokyo", 350, 400, LocalDate.of(2020, 2, 10));
        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);
        flights.add(flight5);
        return flights;
    }

    public static List<Flight> getFlightWhereCapacityIsMoreThan300(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getCapacity() > 300)
                .collect(java.util.stream.Collectors.toList());
    }

    public static Predicate<Flight> PriceLowerThan300(List<Flight> flights) {
        return flight -> flight.getPrice() < 300;
    }

    public static List<Flight> getFlightsWherePriceIsLowerThan300(List<Flight> flights) {
        return flights.stream()
                .filter(PriceLowerThan300(flights))
                .collect(java.util.stream.Collectors.toList());
    }

    public static Boolean allFlightsWhereYearIs2020(List<Flight> flights) {
        return flights.stream()
                .allMatch(flight -> flight.getDate().getYear() == 2020);
    }

    public static Boolean existsFlight(List<Flight> flights, int number, String destination) {
        return flights.stream()
                .anyMatch(flight -> flight.getNumber() == number
                        && flight.getDestination().equals(destination));
    }

    public static Flight getCheapestFlight(List<Flight> flights, String destination) {
        return flights.stream()
                .filter(flight -> flight.getDestination().equals(destination))
                .min(Comparator.comparing(Flight::getPrice))
                .get();
    }

    public static Flight getMostCapacityFlight(List<Flight> flights, String destination) {
        return flights.stream()
                .filter(flight -> flight.getDestination().equals(destination))
                .max(Comparator.comparing(Flight::getCapacity))
                .get();
    }

    public static int getSumOfPrices(List<Flight> flights) {
        return flights.stream()
                .mapToInt(Flight::getPrice)
                .sum();
    }

}

class Flight {
    private int number;
    private String destination;
    private int price;
    private int capacity;
    private LocalDate date;

    public Flight(int number, String destination, int price, int capacity, LocalDate date) {
        this.number = number;
        this.destination = destination;
        this.price = price;
        this.capacity = capacity;
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public String getDestination() {
        return destination;
    }

    public int getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }

    public LocalDate getDate() {
        return date;
    }

    public String toString() {
        return "Flight number: " + number + ", destination: " + destination + ", price: " + price + ", capacity: "
                + capacity + ", date: " + date;
    }

}
