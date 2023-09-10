package javaapplication2;

import java.util.InputMismatchException;
import java.util.Scanner;

class UserInterface {
    private static String source = "AMBERNATH";
    private String destination;
    int ZoneNO;
    int stationNumber;

    public static void InterfaceUI() {
        
        System.out.println("===================================================================================================================================");
        System.out.println("                    Welcome To ATVM ");
        System.out.println("===================================================================================================================================\n");
        while (true) {

            Scanner sc = new Scanner(System.in);
            System.out.println("                           Main Menu");
            System.out.println("===================================================================================================================================");
            System.out.println("  1. Book Ticket Using Map");
            System.out.println("  2. Fast Booking");
            System.out.println("  3. Exit");
            System.out.println("===================================================================================================================================\n");
            try {
                int input = sc.nextInt();
                switch (input) {
                    case 1:
                        UserInterface user = new UserInterface();
                        user.UsingMap();
                        break;
                    case 2:
                        FastBooking ref1 = new FastBooking();
                        ref1.display();

                        break;
                    case 3:
                        System.out.println("Do You Really Wanna Exit ;Press 'Y or N");
                        String exit_ask = sc.next();
                        exit_ask = exit_ask.toLowerCase();
                        if (exit_ask.equals("y") || exit_ask.equals("yes")) {
                            System.out.println("Thank you for using ATVM. Have a great day!");

                            return;
                        } else if (exit_ask.equals("n") || exit_ask.equals("no")) {
                            continue;
                        } else {
                            System.out.println("Enter valid choice");
                        }

                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                        break;
                }
            } catch (InputMismatchException inex) {
                System.out.println("Enter Value Between 1-4");
            } catch (Exception e) {
                System.out.println(e + "Something Went Wrong !!!");

            }
//            System.out.println("AFter cach");
        }
    }

//    Getter and setter method for Accessing and manipulating destination private field
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    public String getSource() {
        return source;
    }
//station no zone no station name
//Non static method for displaying zones options;

    public void UsingMap() {

        System.out.println("    Using Map - Choose Destination:");

        String zoneNo_destination[] = Zones.displayZone();
//        userDestination will store station name

        stationNumber = Integer.parseInt(zoneNo_destination[0]);
        ZoneNO = Integer.parseInt(zoneNo_destination[1]);
        String userDestination = zoneNo_destination[2];

        setDestination(userDestination);

        System.out.println("\n=======================================================");
        System.out.println("Source      :: " + this.source);
        System.out.println("Destination :: " + userDestination);

        System.out.println("\n=======================================================");
        System.out.println("1. Back \t\t\t 2.Next \t\t\t  3.Home");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        if (input == 1) {
            UsingMap();
        } else if (input == 3) {
            InterfaceUI();
        } else if (input == 2) {
            Ticket tkt = new Ticket(this);
        }
    }
}
