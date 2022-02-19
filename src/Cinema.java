import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    public static Scanner scanner = new Scanner(System.in);
    public static String[][] seats;
    public static int numberOfRows;
    public static int seatsInEachRow;
    public static int totalIncome;
    public static int currentIncome;
    public static int purchasedTickets;


    public static void main(String[] args) {
        // Write your code here
        createSeats();
        totalIncome = calculateTotalIncome(numberOfRows, seatsInEachRow);
        showMenu();
        // System.out.println("Total income:\n$" + calculateTotalIncome(numberOfRows, seatsInEachRow));
        //printSeats(seats);
        //buyTicket(numberOfRows, seatsInEachRow);
        //printSeats(seats);
    }

    public static String[][] createSeats() {
        System.out.println("Enter the number of rows:");
        numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatsInEachRow = scanner.nextInt();
        seats = new String[numberOfRows][seatsInEachRow];

        for (int i = 0; i < seats.length; i++) {
            Arrays.fill(seats[i], "S");
        }
        return seats;
    }

    public static void printSeats(String[][] seats) {
        System.out.println("\nCinema:");
        System.out.print("  ");
        for (int i = 1; i <= seats[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < seats.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static int calculateTotalIncome(int numberOfRows, int seatsInEachRow) {
        int totalSeats = numberOfRows * seatsInEachRow;

        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else {
            int frontHalf = numberOfRows / 2;
            int backHalf = numberOfRows - frontHalf;
            totalIncome = frontHalf * seatsInEachRow * 10 + backHalf * seatsInEachRow * 8;
        }
        return totalIncome;
    }

    public static void buyTicket(int numberOfRows, int seatsInEachRow) {
        while (true) {
            System.out.println("\nEnter a row number:");
            int rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatNumber = scanner.nextInt();

            if (rowNumber < 1 || rowNumber > numberOfRows || seatNumber < 1 || seatNumber > seatsInEachRow) {
                System.out.println("Wrong input!");
                continue;
            }

            if (seats[rowNumber - 1][seatNumber - 1] == "B") {
                System.out.println("That ticket has already been purchased!");
                continue;
            }

            seats[rowNumber - 1][seatNumber - 1] = "B";
            purchasedTickets++;

            int totalSeats = numberOfRows * seatsInEachRow;

            if (totalSeats <= 60) {
                currentIncome += 10;
                System.out.println("\nTicket price: $10");
            } else {
                if (rowNumber <= numberOfRows / 2) {
                    currentIncome += 10;
                    System.out.println("\nTicket price: $10");
                } else {
                    currentIncome += 8;
                    System.out.println("\nTicket price: $8");
                }
            }
            break;
        }
    }

    public static void showMenu() {
        while (true) {
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    printSeats(seats);
                    break;
                case 2:
                    buyTicket(numberOfRows, seatsInEachRow);
                    break;
                case 3:
                    showStatistics();
                    break;
                case 0:
                    return;
            }
        }
    }

    public static void showStatistics() {
        System.out.println("\nNumber of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", purchasedTickets * 100.0 / (numberOfRows * seatsInEachRow));
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }
}