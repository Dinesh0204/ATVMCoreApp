package javaapplication2;

import java.util.Scanner;

public class FastBooking {

    static String[] stations;
    static double[] singleJourneyFares;

    static {
        stations = new String[]{
            "Shahad", "Badlapur", "NERAL", "Mulund", "Khadavli",
            "Turbhe", "Atgaon", "ChinchPokli", "Khopoli", "Mahim Jn", "Cotton Green",
            "Dahisar", "Vashi", "ChurchGate", "Kasara", "CSMT", "Panvel", "GoreGaon", "Virar"
        };

        singleJourneyFares = new double[]{5, 5, 10, 10, 10, 15, 15, 15, 15, 15, 15, 20, 20, 20, 20, 20, 20, 20, 30};
    }

    public void display() {
        System.out.println("\n====================================");
        System.out.println("Welcome to FastBooking Option");
        System.out.println("\n====================================");

        int i = 1;
        for (String station : stations) {
            System.out.println(i + ". Upto " + station);
            i++;
        }

        int destinationChoice = userChoice("\n\nChoose your destination: ");
        String destination = stations[destinationChoice - 1];

        int journeyChoice = userChoice("Select Journey Type:\n1. Single\n2. Return");

        // Calculate fare based on journey type
        double fare = (journeyChoice == 1) ? singleJourneyFares[destinationChoice - 1] : singleJourneyFares[destinationChoice - 1] * 2;

        // Display payment options
        displayPaymentOptions(destination, journeyChoice, fare);
    }

    public int userChoice(String message) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.print(message);
            choice = sc.nextInt();
        } while (choice < 1 || choice > stations.length);

        return choice;
    }

    public void displayPaymentOptions(String destination, int journeyChoice, double fare) {
        System.out.println("Payment Options:\n\n");
        System.out.println("1. Gpay");
        System.out.println("2. PhonePe");
        System.out.println("================================");
        boolean status = false;

        int paymentChoice = userChoice("Select Payment Method: ");

        PaymentProcessor paymentProcessor = null;
        if (paymentChoice == 1) {
            paymentProcessor = new Gpay();
            status = paymentProcessor.amount(fare);
        } else if (paymentChoice == 2) {
            paymentProcessor = new PhonePe();
            status = paymentProcessor.amount(fare);
        }

        Ticket ticket = new Ticket();
        // Calling the printPreview method from the Ticket class
        if (status) {
            ticket.printPreview(destination, journeyChoice, fare);
        } else {
            System.out.println(" Ticket Cannot Be Generated !!!");
        }
    }

}
