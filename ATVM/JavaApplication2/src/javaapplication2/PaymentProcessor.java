package javaapplication2;

import java.util.Random;
import java.util.Scanner;

interface UPI {

    void processPayment(double amount);
}

interface PaymentProcessor extends UPI {

    boolean amount(double baseFare);

    String generateTransactionId();
}

class Gpay implements PaymentProcessor, UPI {

    @Override
    public boolean amount(double baseFare) {
        boolean paymentStatus = false;
        System.out.println("******************************************");
        System.out.println("            Gpay Payment Gateway           ");
        System.out.println("******************************************");
        System.out.println("Processing payment of " + baseFare + " INR...");

        Scanner sc = new Scanner(System.in);
        System.out.println("Press 'Y' to proceed with payment or 'N' to cancel: ");
        String choice = sc.next();

        choice = choice.toLowerCase();
        if (choice.equals("y") || choice.equals("yes")) {
            System.out.println("Payment successful!");

            System.out.println("Transaction ID: " + generateTransactionId());
            System.out.println("Thank you for choosing Gpay.");
            paymentStatus = true;

        } else if (choice.equals('n') || choice.equals("no")) {
            System.out.println("Payment canceled.");
            paymentStatus = false;

        } else {
            System.out.println("Invalid choice. Payment canceled.");
            paymentStatus = false;
        }
        return paymentStatus;

    }

    @Override
    public void processPayment(double amount) {
        // Implementation for processing UPI payment using Gpay
        System.out.println("Processing UPI payment of " + amount + " INR using Gpay...");
    }

    @Override
    public String generateTransactionId() {
        Random rand = new Random();
        int transactionNumber = rand.nextInt(100000);
        String transactionId = "Gpay@" + transactionNumber;
        return transactionId;

    }
}

class PhonePe implements PaymentProcessor, UPI {

    private String transactionId;

    @Override
    public boolean amount(double baseFare) {
        boolean paymentStatus = false;
        System.out.println("************************************");
        System.out.println("        PhonePe Payment Gateway       ");
        System.out.println("************************************\n\n\n");
        System.out.println("Processing payment of " + baseFare + " INR using PhonePe...");

        // Simulate user interaction for PhonePe
        System.out.println("\nPlease enter your PhonePe UPI PIN: ");
        Scanner sc = new Scanner(System.in);
        String upiPin = sc.nextLine();

        if (isValidUPIPin(upiPin)) {
            this.transactionId = generateTransactionId();
            System.out.println("Payment successful!");
            paymentStatus = true;
            System.out.println("Transaction ID: " + transactionId);
        } else {
            System.out.println("Payment failed due to incorrect UPI PIN.");
            this.transactionId = "Failed";
            paymentStatus = false;
        }
        return paymentStatus;
    }

    private boolean isValidUPIPin(String upiPin) {
        boolean status = false;
        if (!upiPin.equals("") && upiPin.length() == 6) {
            status = true;
        } else {
            status = false;
        }
        return status;
    }

    public String generateTransactionId() {
        Random rand = new Random();
        int transactionNumber = rand.nextInt(100000);
        String transactionId = "PhonePe@" + transactionNumber;
        return transactionId;
    }

    @Override
    public void processPayment(double amount) {
        // Implementation for processing UPI payment using PhonePe
        System.out.println("\n\nProcessing UPI payment of " + amount + " INR using PhonePe...");
    }
}
