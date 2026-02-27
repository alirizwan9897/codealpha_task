import java.util.*;

class Room {
    int roomNumber;
    String type;
    double price;
    boolean isAvailable;

    Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isAvailable = true;
    }
}

class Booking {
    String customerName;
    int roomNumber;
    int nights;
    double totalAmount;

    Booking(String customerName, int roomNumber, int nights, double totalAmount) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.nights = nights;
        this.totalAmount = totalAmount;
    }
}

public class HotelReservationSystem {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Create Rooms
        rooms.add(new Room(101, "Standard", 1000));
        rooms.add(new Room(102, "Standard", 1000));
        rooms.add(new Room(201, "Deluxe", 2000));
        rooms.add(new Room(202, "Deluxe", 2000));
        rooms.add(new Room(301, "Suite", 3000));

        int choice;

        do {
            System.out.println("\n====== HOTEL RESERVATION SYSTEM ======");
            System.out.println("1. View All Rooms");
            System.out.println("2. Search Available Rooms");
            System.out.println("3. Book Room");
            System.out.println("4. Cancel Booking");
            System.out.println("5. View Bookings");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    viewRooms();
                    break;

                case 2:
                    searchAvailableRooms();
                    break;

                case 3:
                    bookRoom(sc);
                    break;

                case 4:
                    cancelBooking(sc);
                    break;

                case 5:
                    viewBookings();
                    break;

                case 6:
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);

        sc.close();
    }

    static void viewRooms() {
        System.out.println("\n--- All Rooms ---");
        for (Room room : rooms) {
            System.out.println("Room: " + room.roomNumber +
                    " | Type: " + room.type +
                    " | Price: ₹" + room.price +
                    " | Available: " + room.isAvailable);
        }
    }

    static void searchAvailableRooms() {
        System.out.println("\n--- Available Rooms ---");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println("Room: " + room.roomNumber +
                        " | Type: " + room.type +
                        " | Price: ₹" + room.price);
            }
        }
    }

    static void bookRoom(Scanner sc) {

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter room number: ");
        int roomNo = sc.nextInt();

        System.out.print("Enter number of nights: ");
        int nights = sc.nextInt();

        for (Room room : rooms) {
            if (room.roomNumber == roomNo && room.isAvailable) {

                double total = room.price * nights;

                System.out.println("Total Amount: ₹" + total);
                System.out.print("Proceed with payment? (yes/no): ");
                sc.nextLine();
                String payment = sc.nextLine();

                if (payment.equalsIgnoreCase("yes")) {
                    room.isAvailable = false;
                    bookings.add(new Booking(name, roomNo, nights, total));
                    System.out.println("✅ Booking Confirmed!");
                } else {
                    System.out.println("❌ Payment Cancelled!");
                }
                return;
            }
        }

        System.out.println("❌ Room not available!");
    }

    static void cancelBooking(Scanner sc) {

        System.out.print("Enter room number to cancel: ");
        int roomNo = sc.nextInt();

        Iterator<Booking> iterator = bookings.iterator();

        while (iterator.hasNext()) {
            Booking booking = iterator.next();
            if (booking.roomNumber == roomNo) {

                iterator.remove();

                for (Room room : rooms) {
                    if (room.roomNumber == roomNo) {
                        room.isAvailable = true;
                        break;
                    }
                }

                System.out.println("✅ Booking Cancelled Successfully!");
                return;
            }
        }

        System.out.println("❌ Booking not found!");
    }

    static void viewBookings() {
        System.out.println("\n--- Booking Details ---");

        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }

        for (Booking booking : bookings) {
            System.out.println("Customer: " + booking.customerName +
                    " | Room: " + booking.roomNumber +
                    " | Nights: " + booking.nights +
                    " | Paid: ₹" + booking.totalAmount);
        }
    }
}
