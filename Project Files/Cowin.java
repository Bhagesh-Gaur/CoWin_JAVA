package Assignment_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Cowin {

    static Scanner input = new Scanner(System.in);
    static ArrayList<Citizen> clist = new ArrayList<Citizen>();//citizens list
    static ArrayList<Hospital> hlist = new ArrayList<Hospital>();//hospitals list
    public static int c_hos = 0;//count for hospitals
    static ArrayList<Vaccine> vlist = new ArrayList<Vaccine>();//vaccines list


    public static void menu() {  //displaying menu for interface of cowin
            System.out.println("--------------------MENU---------------------");
            System.out.println("Please select any of the following services: ");
            System.out.println("1. Add Vaccine. ");
            System.out.println("2. Register Hospital. ");
            System.out.println("3. Register Citizen. ");
            System.out.println("4. Add Slot For Vaccination. ");
            System.out.println("5. Book Slot For Vaccination. ");
            System.out.println("6. List all slots for a hospital.  ");
            System.out.println("7. Check Vaccination status. ");
            System.out.println("8. Exit. ");
            System.out.println("---------------------------------------------");
            System.out.print("Enter Service number: ");
    }

    public static void add_vac() { //vaccine added
        //taking in name, no of doses, gap b/w doses
        System.out.print("Vaccine Name: ");
        String name = input.next();
        for(int i = 0; i < vlist.size(); i++){
            if(vlist.get(i).getName().equals(name)){
                System.out.println("Vaccine with same name already exits!!");
                return;
            }
        }
        System.out.print("Number of Doses: ");
        int ndos = input.nextInt();
        if(ndos == 0){
            System.out.println("INVALID INPUT!! ");
            return;
        }
        int gap;
        if(ndos == 1){
            gap = 0;
        }
        else{
            System.out.print("Gap between Doses: ");
            gap = input.nextInt();
        }
        if(gap < 0){
            System.out.println("INVALID INPUT!! ");
            return;
        }
        Vaccine nv = new Vaccine(name, ndos, gap);
        nv.print_rec();
        vlist.add(nv);
    }

    public static String make_code(int c_hos) {
        String id = Integer.toString(c_hos);
        String uid = "";
        for (int i = 0; i < 6 - id.length(); i++) {
            uid = uid + "0";
        }
        uid = uid + id;
        return uid;
    }

    public static void hospital_reg() { //registeration Hospital
        //taking in name, pincode, unique id
        System.out.print("Hospital Name: ");
        String name = input.next();
        System.out.print("PinCode: ");
        String pincode = input.next()	;
        String h_ID = make_code(c_hos);
        Hospital hp = new Hospital(name, h_ID, pincode);
        hp.print_rec();
        hlist.add(hp);
        c_hos++;
    }

    public static boolean isnum(String c_ID) {//check nmeric id
        if (c_ID != null) {
            try{
                double i = Double.parseDouble(c_ID);
            }
            catch (NumberFormatException nfe) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static void citizen_reg() {//register citizens
        //taking in name, age, unique id
        System.out.print("Citizen Name: ");
        String name = input.next();
        System.out.print("Age: ");
        int age = input.nextInt();
        input.nextLine();
        System.out.print("Unique ID: ");
        String c_ID = input.nextLine();
        if((c_ID.length() < 12) || !isnum(c_ID)) {
            System.out.println("Invalid Citizen Unique ID");
            return;
        }
        for(int i = 0; i < clist.size(); i++) {
            if(clist.get(i).getUniqueID().equals(c_ID)) {
                System.out.println("Already Registered!!");
                return;
            }
        }
        if(age < 18) {
            System.out.println("Only above 18 are allowed!");
            return;
        }
        Citizen ct = new Citizen(name, age, c_ID);
        System.out.println("Citizen Registered!!");
        ct.print_rec();
        clist.add(ct);
    }

    public static void add_slot() {//add slot
        //taking in hospital id, creating slots
        System.out.print("Enter the Hospital ID: ");
        String huID = input.next();
        int f = 0;
        if(huID.length() > 6){
            System.out.println("Invalid Hospital ID");
        }
        else{
            for(int i = 0; i < hlist.size(); i++) {
                if(hlist.get(i).getHuID().equals(huID)) {
                    f = 1;
                    hlist.get(i).CreateSlots();
                    break;
                }
            }
            if(f == 0){
                System.out.println("No Hospital found with given Hospital ID");
            }
        }

    }

    public static void slotlisting() {//listing slots
        //taking in hospital id, listing slots
        System.out.print("Enter Hospital Id: ");
        String huID = input.next();
        int f = 0;
        if(huID.length() > 6){
            System.out.println("Invalid Hospital ID");
        }
        else{
            for(int i = 0; i < hlist.size(); i++) {
                if(hlist.get(i).getHuID().equals(huID)) {
                    f = 1;
                    hlist.get(i).slotlist();
                    break;
                }
            }
            if(f == 0){
                System.out.println("No Hospital found with given Hospital ID");
            }
        }

    }

    public static void bookslot(){
        System.out.print("Enter Patient Unique ID: ");
        String c_ID = input.next();
        if((c_ID.length() < 12) || !isnum(c_ID)) {
            System.out.println("Invalid Patient Unique ID");
            return;
        }
        int mf = 0;
        for(int i = 0; i < clist.size(); i++) {
            if(clist.get(i).getUniqueID().equals(c_ID)) {
                mf = 1;
                clist.get(i).book_slot();
                return;
            }

        }
        if(mf == 0){
            System.out.println("Patient not registered!!");
        }
    }

    public static void cvacStat(){
        System.out.print("Enter Patient Unique ID: ");
        String c_ID = input.next();
        if((c_ID.length() < 12) || !isnum(c_ID)) {
            System.out.println("Invalid Patient Unique ID");
            return;
        }
        int mf = 0;
        for(int i = 0; i < clist.size(); i++) {
            if(clist.get(i).getUniqueID().equals(c_ID)) {
                mf = 1;
                clist.get(i).vac_status();
                return;
            }
        }
        if(mf == 0){
            System.out.println("Patient not registered!!");
        }
    }



    public static void main(String[] args) throws IOException {

            System.out.println("CoWin Portal initialized....");
            System.out.println("Welcome to CoWin Portal. ");
            int c;
            int f = 0;
            while(true){
                menu();
                try{
                    c = input.nextInt();
                }
                catch (NumberFormatException nfe) {
                    System.out.println("Invalid Input!!");
                    continue;
                }
                switch(c){
                    case 1: //add vaccine
                        add_vac();
                        break;
                    case 2: //Register Hospital
                        hospital_reg();
                        break;
                    case 3: //Register Citizen.
                        citizen_reg();
                        break;
                    case 4: //Add Slot For Vaccination.
                        add_slot();
                        break;
                    case 5: //Book Slot For Vaccination.
                        bookslot();
                        break;
                    case 6: //List all slots for a hospital.
                        slotlisting();
                        break;
                    case 7: //Check Vaccination status
                        cvacStat();
                        break;
                    case 8: System.out.println("Thanks a lot for visiting CoWin");
                            f = 1;
                        break;
                    default: System.out.println("Invalid Service number entered!! \nPlease try again!!");
                        break;
                }
                if(f == 1){
                    break;
                }
            }
    }




}


//Author Bhagesh Gaur 2020558