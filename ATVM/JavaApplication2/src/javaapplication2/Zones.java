package javaapplication2;

import java.util.Scanner;

class Zones {

    static String Zones[][] = new String[9][];
    static String[] z1, z2, z3, z4, z5, z6, z7, z8, z9;
    int zoneNo;

    static {
//         Now we Store the stations in order of their ticket fare  20 ,25,30,35          
        z1 = new String[]{
            "Vaitarna", "Virar", "Vasai Road", "Dahisar", "Borivali", "Kandivali", "Malad",
            "Naigaon", "Bhayandar", "Mira Road",
            "Vangaon", "Palghar", "Saphale", "Nalla Sopara",
            "Dahanu Road", "Kelva Road"
        //vaitarna to malad(0-6)    ==      20
        //maigaon to mira road(7-9) ==      25
        //vangaon to nalla sopara(10-13)    30 
        //dahani to nalla sopara(14-15) =   35
        //                western -1                                                        
        //                18 stations                                                       
        };
        z2 = new String[]{"Thane",
            "Mulund", "Nahur", "Bhandup", "Kanjurmarg", "Ghatkopar", "Vikhroli",
            "Vidyavihar", "Kurla", "Sion", "Chembur", "Mankhurd", "TilakNagar", "Juchandra", "Kaman Road", "Kharbao", //                16 stations
    };
//        z2_price = new int[]{ 10, 10, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,30, 30, 30,};

        z3 = new String[]{
            "Shahad", "Vitthalwadi",
            "Ulhasnagar", "Kalyan", "Khadavli", "Titwala", "Ambivali", "Thakurli", "Dombivali",
            "Kopar", "Diva", "Atgaon", "vasind", "Asagaon", "Kasara", "Khardi", //16 stations
        };
        //z3_price = new int[]{5,5,5,5,10,10,10,10,10,10,10,15,15,15,20,20};

        z4 = new String[]{"Badlapur", "Vangani", "Shelu", "Neral", "Dativali",
            "Nilje", "Taloja", "Navade Road"
//                8 stations
//       z4_price = new int[]{5,10,10,10,20,20,20,20}         
        };
        z5 = new String[]{"Mumbra", "Kalwa", "Airoli", "Rabada", "Ghansoli",
            "KoparKhairna", "Turbhe", "Vashi", "Sanpada", "Juinagar", "Nerul",
            "Seawoods Darve", "Belapur CBD", "Kharghar"
//                14 stations

//                0-1 10 2-6 15 7-13 20
        };
        z6 = new String[]{
            "Manasarover", "Khandeshwar", "Panvel", "Bhivpuri Road", "Karjat",
            "Palasdhari", "Kelavali", "Kalamboli", "Dolavali", "Lowjee",
            "Khopoli"
//                11 stations
        };
        z7 = new String[]{
            "Chunabhatti", "GTB Nagar", "King's Circle", "Vadala Road", "Sewri",
            "Chinchpokli", "Currey Road",
            "Cotton Green", "Reay Road", "Dock-Yard Road", "Sandhurst Road",
            "Churchgate", "CSMT", "Masjid", "Byculla"
//                15 stations
        };

        z8 = new String[]{
            "Matunga", "Dadar", "Prabhadevi", "Lower Parel", "Parel",
            "Mahalaxmi", "Grant Road", "Churney Road", "Marine Lines",
            "Churchgate"
//                10 stations
//                0-4 15  5-9 20
        };

        z9 = new String[]{"Goregaon", "Ram Mandir", "Andheri", "Jogeshwari",
            "Vile Parle", "Santacruz", "Khar Road", "Bandra", "Matunga Road",
            "Mahim"
//                matunga road , mahim jn 15 remaining all 20 rs ticket
//        10stations
        };

        assignZones();
    }

    public static void assignZones() {
        Zones[0] = z1;
        Zones[1] = z2;
        Zones[2] = z3;
        Zones[3] = z4;
        Zones[4] = z5;
        Zones[5] = z6;
        Zones[6] = z7;
        Zones[7] = z8;
        Zones[8] = z9;
    }

    public static String[] displayZone() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Zones Available Are:");
        for (int i = 0; i < Zones.length; i++) {
            System.out.println((i + 1) + ". Zone " + (i + 1));
        }
        System.out.println("Enter the zone number: ");
        Zones ref = new Zones();
        ref.zoneNo = sc.nextInt();
        ref.zoneNo = validate(ref.zoneNo);
//display stations will return station number and zone no and station name in user selected zone
//thats why we are passing zoneNo as an argument 
        String[] userDestinationInfo = displayStations(ref.zoneNo);
//        String zoneNo_destination[]={Integer.toString(ref.zoneNo),userDestination};
        return userDestinationInfo;
    }

    public static int validate(int zoneNo) {
        int temp_zone = zoneNo;
//        System.out.println(temp_zone);
        if (zoneNo < 1 || zoneNo > 9) {
            System.out.println("Enter valid zoneNo:");
            Scanner sc = new Scanner(System.in);
            zoneNo = sc.nextInt();
            temp_zone = validate(zoneNo);
        }

        return temp_zone;
    }

    public static int validate(int zoneNo, int stationNumber) {
        int temp_stn_no = stationNumber;
        if (stationNumber < 0 || stationNumber > Zones[zoneNo - 1].length) {
            System.out.println("Enter valid station-Number:");
            Scanner sc = new Scanner(System.in);
            stationNumber = sc.nextInt();
            temp_stn_no = validate(zoneNo, stationNumber);
        }
        return temp_stn_no;
    }

    public static String[] displayStations(int zoneNo) {
        System.out.println("Destination in Selected Zone :( Zone- (" + zoneNo + ")");
        System.out.println("=============================================");

        for (int j = 0; j < Zones[zoneNo - 1].length; j++) {
            //All stations inside userSelected ZoneNO will be displayed
            System.out.println("\t" + (j + 1) + ". " + Zones[zoneNo - 1][j] + "\t");
        }
        System.out.println("=============================================");

        System.out.print("Enter the station number: ");
        Scanner sc = new Scanner(System.in);
        int stationNo = sc.nextInt();
        stationNo = validate(zoneNo, stationNo);
        String[] stationInfo = {Integer.toString(stationNo), Integer.toString(zoneNo), Zones[zoneNo - 1][stationNo - 1]};
//        THis stationInfo array willl store station number,zone No  station name ,
        return stationInfo;
    }
}
