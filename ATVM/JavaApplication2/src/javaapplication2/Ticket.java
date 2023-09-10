package javaapplication2;

import java.util.Scanner;

class Ticket {

    static String[] JourneyType = new String[2];
    static String[] classType = new String[2];

    int noOfAdults;
    int noOfChilds;
    int jrny;
    int classUser;

    double baseFare;
    double childPerFare = 5;

//    inside this non static global variable we are trying to store address of object
    UserInterface user;
    Ticket t1;

    static {
        JourneyType[0] = "Single";
        JourneyType[1] = "Return";
        classType[0] = "I-Class";
        classType[1] = "II-Class";
    }

    Ticket() {
    }

    Ticket(UserInterface user) {
        this.user = user;
        displayMenu(user);
    }

    private void displayPaymentOptions() {
        System.out.println("1. Using UPI");
        
        System.out.println("2. Cancel Transaction");
    }

    private void displayMenu(UserInterface user) {
        // Assume default values if the user didn't change journey details
        if (noOfAdults == 0) {
            noOfAdults = 1;
            noOfChilds = 0;
            jrny = 1; // Assume single journey
            classUser = 0; // Assume first class
            calculateFare(noOfAdults, noOfChilds, jrny, classUser, user.stationNumber);
        }

        System.out.println("Choose Following:: ");

        System.out.println("1. Change Journey Details");
        System.out.println("2. Proceed For Payment");
        System.out.println("3. Back ");

        Scanner sc = new Scanner(System.in);
        switch (sc.nextInt()) {
            case 1:
                changeJourneyDetails(user);
                break;
            case 2:
                // Display payment options
                displayPaymentOptions();
                int paymentMode = sc.nextInt();

                if (paymentMode == 1) {
                    System.out.println("************************************");
                    System.out.println("        Select Payment Method       ");
                    System.out.println("************************************");
                    System.out.println("1. Gpay");
                    System.out.println("2. PhonePe");
                    System.out.println("\n");
                    int paymentChoice = sc.nextInt();

                    if (paymentChoice == 1) {
                        PaymentProcessor paymentProcessor = new Gpay();

                        if (paymentProcessor.amount(baseFare)) {
                            printPreview();
                        } else {
                            System.out.println("OOps !! Transaction got cancelled");
                        }

                    } else if (paymentChoice == 2) {
                        PaymentProcessor paymentProcessor = new PhonePe();

                        if (paymentProcessor.amount(baseFare)) {
                            printPreview();
                        } else {
                            System.out.println("OOps !! Transaction got cancelled");
                        }
                    }
                }
                else if(paymentMode==3){
                return;
                }
                break;
            case 3:
                return;

            default:
                System.out.println("Enter Valid Choice - between [1-3] ");
        }
    }

    private int takeInputAgain(int noOfAdults) {
        if (noOfAdults < 1 || noOfAdults > 5) {
            System.out.println("\nSelect No Of adults between[1-5]");

            noOfAdults = new Scanner(System.in).nextInt();
            takeInputAgain(noOfAdults);
        }
        return noOfAdults;
    }

    private int takeInputAgainForChilds(int noOfAdults) {
        if (noOfAdults < 0 || noOfAdults > 5) {
            System.out.println("\nSelect No Of Childs between[1-5]");

            noOfAdults = new Scanner(System.in).nextInt();
            takeInputAgainForChilds(noOfAdults);
        }
        return noOfAdults;
    }

    private void changeJourneyDetails(UserInterface user) {

//        System.out.println("inside changeJourneyDetails :: " + this);
        System.out.println("\nSelect No Of adults");
        noOfAdults = new Scanner(System.in).nextInt();
        noOfAdults = takeInputAgain(noOfAdults);

        System.out.println("\nSelect No Of Childs");
        noOfChilds = new Scanner(System.in).nextInt();
        noOfChilds = takeInputAgainForChilds(noOfChilds);

        System.out.println("\nSelect Journey Type");
        displayOptions(JourneyType);

        jrny = new Scanner(System.in).nextInt();

        System.out.println("\nSelect Class Type");
        displayOptions(classType);
        classUser = new Scanner(System.in).nextInt();
        calculateFare(noOfAdults, noOfChilds, jrny, classUser, user.stationNumber);

        displayMenu(user);
    }

    private void calculateFare(int noOfAdults, int noOfChilds, int jrny, int classUser, int stationNumber) {
//        System.out.println("noOFadults +"+noOfAdults +"noOFChilds  "+noOfChilds+ "jrny "+jrny+"classUser "+classUser+" stationNumber"+stationNumber);
        stationNumber -= 1;
        boolean isClassII = false;
        if (classUser == 2) {
            isClassII = true;
        }
        switch (user.ZoneNO) {
            case 1:
                if (stationNumber >= 0 && stationNumber <= 6) {
                    baseFare = 20 * (noOfAdults) + (noOfChilds) * childPerFare;
                } else if (stationNumber >= 7 && stationNumber <= 9) {
                    baseFare = 25 * (noOfAdults) + (noOfChilds) * childPerFare;
                } else if (stationNumber >= 10 && stationNumber <= 13) {
                    baseFare = 30 * (noOfAdults) + (noOfChilds) * childPerFare;
                } else if (stationNumber >= 14 && stationNumber <= 15) {
                    baseFare = 35 * (noOfAdults) + (noOfChilds) * childPerFare;
                }
                if (isClassII) {
                    baseFare *= 2;
                }
//                System.out.println("Your fare is " + baseFare);
                break;
            case 2:
                if (stationNumber >= 0 && stationNumber <= 1) {
                    baseFare = 10 * (noOfAdults) + (noOfChilds) * childPerFare;
                } else if (stationNumber >= 2 && stationNumber <= 12) {
                    baseFare = 15 * (noOfAdults) + (noOfChilds) * childPerFare;
                } else if (stationNumber >= 13 && stationNumber <= 15) {
                    baseFare = 30 * (noOfAdults) + (noOfChilds) * childPerFare;
                }
                if (isClassII) {
                    baseFare *= 2;
                }
                break;

            case 3:

                if (stationNumber >= 0 && stationNumber <= 3) {
                    baseFare = 5 * (noOfAdults) + (noOfChilds) * childPerFare;
                } else if (stationNumber >= 4 && stationNumber <= 10) {
                    baseFare = 10 * (noOfAdults) + (noOfChilds) * childPerFare;
                } else if (stationNumber >= 11 && stationNumber <= 13) {
                    baseFare = 15 * (noOfAdults) + (noOfChilds) * childPerFare;
                } else if (stationNumber >= 14 && stationNumber <= 15) {
                    baseFare = 20 * (noOfAdults) + (noOfChilds) * childPerFare;
                }
                if (isClassII) {
                    baseFare *= 2;
                }
//                System.out.println("Your fare is " + baseFare);
                break;
            case 4:
                if (stationNumber == 0) {
                    baseFare = 5 * (noOfAdults) + (noOfChilds) * childPerFare;
                } else if (stationNumber >= 1 && stationNumber <= 3) {
                    baseFare = 10 * (noOfAdults) + (noOfChilds) * childPerFare;
                } else if (stationNumber >= 4 && stationNumber <= 7) {
                    baseFare = 20 * (noOfAdults) + (noOfChilds) * childPerFare;
                }
                if (isClassII) {
                    baseFare *= 2;
                }
                break;

            case 5:
                if (stationNumber >= 0 && stationNumber <= 1) {
                    baseFare = 10 * (noOfAdults) + (noOfChilds) * childPerFare;
                } else if (stationNumber >= 2 && stationNumber <= 6) {
                    baseFare = 15 * (noOfAdults) + (noOfChilds) * childPerFare;
                } else if (stationNumber >= 7 && stationNumber <= 13) {
                    baseFare = 20 * (noOfAdults) + (noOfChilds) * childPerFare;
                }
                if (isClassII) {
                    baseFare *= 2;
                }
                break;
            case 6:
                if (stationNumber >= 0 && stationNumber <= 1) {
                    baseFare = 20 * (noOfAdults) + (noOfChilds) * childPerFare;
                } else {
                    baseFare = 15 * (noOfAdults) + (noOfChilds) * childPerFare;

                }
                if (isClassII) {
                    baseFare *= 2;
                }
                break;
            case 7:
                if (stationNumber >= 0 && stationNumber <= 6) {
                    baseFare = 15 * (noOfAdults) + (noOfChilds) * childPerFare;
                } else if (stationNumber >= 7 && stationNumber <= 14) {
                    baseFare = 20 * (noOfAdults) + (noOfChilds) * childPerFare;

                }
                if (isClassII) {
                    baseFare *= 2;
                }
                break;
            case 8:
                if (stationNumber >= 0 && stationNumber <= 4) {
                    baseFare = 15 * (noOfAdults) + (noOfChilds) * childPerFare;
                } else if (stationNumber >= 5 && stationNumber <= 9) {
                    baseFare = 20 * (noOfAdults) + (noOfChilds) * childPerFare;
                }
                if (isClassII) {
                    baseFare *= 2;
                }
                break;

            case 9:
                if (stationNumber >= 0 && stationNumber <= 7) {
                    baseFare = 20 * (noOfAdults) + (noOfChilds) * childPerFare;
                } else if (stationNumber >= 8 && stationNumber <= 9) {
                    baseFare = 15 * (noOfAdults) + (noOfChilds) * childPerFare;
                }
                if (isClassII) {
                    baseFare *= 2;
                }
                break;
            default:
                System.out.println("Invalid Zone Number");
                break;

        }
    }
    //    This will display options of array Journey Type[Single,Return] and Class Type[First class,Second Class]

    public void displayOptions(String[] arr) {
        int count = 0;
        for (String cType : arr) {
            System.out.println(count + 1 + ". " + cType);
            count++;
        }
    }

    public void printPreview() {

        System.out.println("\n\n===============================================");
        System.out.println("             TICKET PREVIEW");
        System.out.println("===============================================");
        System.out.println("\nSource          : " + user.getSource());
        System.out.println("\nDestination     : " + user.getDestination());
        System.out.println("\nNo. of Adults   : " + noOfAdults);
        System.out.println("\nNo. of Children : " + noOfChilds);
        System.out.println("\nClass           : " + classType[classUser]);
        System.out.println("\nJourney Type    : " + JourneyType[jrny]);
        System.out.println("\n===============================================");
        System.out.println("            THANK YOU FOR CHOOSING US");
        System.out.println("\n===============================================");
    }

    public void printPreview(String destination, int journeyChoice, double fare) {
        System.out.println("\n\n\n\n\n===============================================");
        System.out.println("             TICKET PREVIEW");
        System.out.println("===============================================");
        System.out.println("\nDestination     : " + destination);
        System.out.println("\nJourney Type    : " + ((journeyChoice == 1) ? "Single" : "Return"));
        System.out.println("\nFare            : " + fare + " INR");
        System.out.println("\n===============================================");
        System.out.println("            THANK YOU FOR CHOOSING US");
        System.out.println("\n===============================================");
        
    }
}
