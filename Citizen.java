package Assignment_1;

import java.util.ArrayList;

public class Citizen extends Cowin{

    private String name;
    private int age;
    private String uniqueID;
    private int vaccinationStatus;
    private Vaccine a_vac;
    //0=Registered
    //-ve int < 0 = Fully vaccinated here magnitude of number is the no. of doses
    //+ve int > 0 = partially vaccinated here magnitude of number is the no. of doses
    private int due_date;
    //REGISTERED/PARTIALLY VACCINATED/FULLY VACCINATED


    //GETTERS
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public int getVaccinationStatus() {
        return vaccinationStatus;
    }

    public Vaccine getA_vac() {
        return a_vac;
    }

    public int getDue_date() {
        return due_date;
    }

    //SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public void setVaccinationStatus(int vaccinationStatus) {
        this.vaccinationStatus = vaccinationStatus;
    }

    public void setA_vac(Vaccine a_vac) {
        this.a_vac = a_vac;
    }

    public void setDue_date(int due_date) {
        this.due_date = due_date;
    }


    //funs
    public Citizen(String name, int age, String uniqueID) {//constructor
        this.name = name;
        this.age = age;
        this.uniqueID = uniqueID;
        this.vaccinationStatus = 0;
        this.a_vac = null;
        this.due_date = 0;//not booked first = 0, partial = +ve int, -1 fully vaccinated
    }

    public void print_rec() {
        System.out.print("Citizen Name: " + this.name + ", ");
        System.out.print("Age: " + this.age + ", ");
        System.out.println("Unique ID: " + this.uniqueID);
    }

    public void book_slot() {
        if(vaccinationStatus < 0) {
            System.out.println("You are Fully Vaccinated!!");
            return;
        }
        int ch = 0;
        while(true) {
            System.out.println("1. Search by Area");
            System.out.println("2. Search by Vaccine");
            System.out.println("3. Exit ");
            System.out.print("Enter option: ");
            ch = input.nextInt();
            switch(ch) {
                case 1: area_search();
                    ch = -1;
                    break;
                case 2: vac_search();
                    ch = -1;
                    break;
                case 3: return;
                default: System.out.println("Invalid Input!!");
                    break;
            }
            if(ch == -1){
                return;
            }
        }
    }


    private void area_search() {
        System.out.print("Enter PinCode: ");
        String pin = input.next();
        System.out.println("Available hospitals: " );
        int f = 0;
        for(int i = 0; i < Cowin.hlist.size(); i++) {
            if(Cowin.hlist.get(i).getPincode().equals(pin)) {
                f = 1;
                System.out.println( "Hospital Unique ID: " + Cowin.hlist.get(i).getHuID() + "| Hospital Name: " + Cowin.hlist.get(i).getName());
            }
        }
        if(f == 0){
            System.out.print("No Hospital available in the area.");
            return;
        }
        System.out.print("Enter Hospital Unique ID: ");//select hospital
        String huid = input.next();
        ArrayList<Integer> s = new ArrayList<Integer>();
        int fh = 0;
        f = 0;
        for(int i = 0; i < Cowin.hlist.size(); i++) {
            if(Cowin.hlist.get(i).getHuID().equals(huid) && Cowin.hlist.get(i).getPincode().equals(pin)) {
                fh = 1;
                for(int j = 0; j < Cowin.hlist.get(i).getH_slot_list().size(); j++) {
                    if(this.due_date <= Cowin.hlist.get(i).getH_slot_list().get(j).getDay()) {
                        f = 1;
                        System.out.print("Slot No.: " + j + "-> ");
                        Cowin.hlist.get(i).getH_slot_list().get(j).print_rec();
                        s.add(j);
                    }
                }
            }
        }
        if(fh == 0) {
            System.out.println("No Hospital with given Unique ID. avaible in the given pincode!!");
            return;
        }
        if(f == 0){
            System.out.println("No slots available");
            return;
        }
        int ch;
        while(true){
            System.out.print("Choose Slot: ");//select slot
            ch = input.nextInt();
            int fc = 0;
            for(int i = 0; i < s.size() ; i++){
                if(s.get(i) == ch){
                    fc = 1;
                }
            }
            if(fc == 0){
                System.out.print("Invalid slot choice!! Enter again!!");
            }
            else{
                break;
            }
        }

        for(int i = 0; i < Cowin.hlist.size(); i++) {
            if(Cowin.hlist.get(i).getHuID().equals(huid)) {
                if(!(vaccinationStatus == 0) && !a_vac.getName().equals(Cowin.hlist.get(i).getH_slot_list().get(ch).getV().getName())) {
                    System.out.println("Vaccine in selected slot is not matching previous dose!!");
                    return;
                }
                else {
                    a_vac = Cowin.hlist.get(i).getH_slot_list().get(ch).getV();
                }
                Cowin.hlist.get(i).getH_slot_list().get(ch).setQuantity(Cowin.hlist.get(i).getH_slot_list().get(ch).getQuantity()-1);
                this.due_date = Cowin.hlist.get(i).getH_slot_list().get(ch).getDay();
                if(Cowin.hlist.get(i).getH_slot_list().get(ch).getQuantity() <= 0){
                    Cowin.hlist.get(i).getH_slot_list().remove(ch);
                }
            }
        }

        System.out.println(this.name + " vaccinated by " + a_vac.getName());

        if(vaccinationStatus >= 0) {//increase doses recieved
            vaccinationStatus++;
        }
        if(vaccinationStatus == a_vac.getDosesReq()){//make vaccination status -ve in case of fully vaccinated
            vaccinationStatus = -1*vaccinationStatus;
        }
        else{
            this.due_date = this.due_date + a_vac.getGap();
        }
    }

    private void vac_search() {
        System.out.print("Enter Vaccine name: ");
        String vn = input.next();
        int f = 0;
        for(int i = 0; i < Cowin.vlist.size(); i++) {
            if(Cowin.vlist.get(i).getName().equals(vn)) {
                f = 1;
                break;
            }
        }
        if(f == 0){
            System.out.println("No Vaccine having entered name!!");
            return;
        }
        f = 0;
        if(!(vaccinationStatus == 0) && !a_vac.getName().equals(vn)) {
            System.out.println("Vaccine in selected slot is not matching previous dose!!");
            return;
        }
        else {
            for(int i = 0; i < Cowin.hlist.size(); i++) {
                for(int j = 0; j < Cowin.hlist.get(i).getH_slot_list().size(); j++) {
                    if((this.due_date <= Cowin.hlist.get(i).getH_slot_list().get(j).getDay()) && (Cowin.hlist.get(i).getH_slot_list().get(j).getV().getName().equals(vn))) {
                        f = 1;
                        System.out.println( "Hospital Unique ID: " + Cowin.hlist.get(i).getHuID() + " | Hospital Name: " + Cowin.hlist.get(i).getName());
                        break;//check
                    }
                }
            }
        }
        if(f == 0){
            System.out.println("No Hospital available with given vaccine!!");
            return;
        }
        int fh = 0;
        f = 0;
        ArrayList<Integer> s = new ArrayList<Integer>();
        System.out.print("Enter Hospital Unique ID: ");//select hospital
        String huid = input.next();
        for(int i = 0; i < Cowin.hlist.size(); i++) {
            if(Cowin.hlist.get(i).getHuID().equals(huid)) {
                fh = 1;
                for(int j = 0; j < Cowin.hlist.get(i).getH_slot_list().size(); j++) {
                    if(this.due_date <= Cowin.hlist.get(i).getH_slot_list().get(j).getDay() && (Cowin.hlist.get(i).getH_slot_list().get(j).getV().getName().equals(vn))) {
                        f = 1;
                        System.out.print("Slot No.: " + j + "-> ");
                        Cowin.hlist.get(i).getH_slot_list().get(j).print_rec();
                        s.add(j);
                    }
                }
            }
        }
        if(fh == 0) {
            System.out.println("No Hospital with given Unique ID.");
            return;
        }
        if(f == 0){
            System.out.println("No slots available");
            return;
        }

        int ch;
        while(true){
            System.out.print("Choose Slot: ");//select slot
            ch = input.nextInt();
            int fc = 0;
            for(int i = 0; i < s.size() ; i++){
                if(s.get(i) == ch){
                    fc = 1;
                }
            }
            if(fc == 0){
                System.out.print("Invalid slot choice!! Enter again!!");
                continue;
            }
            else{
                break;
            }
        }
        for(int i = 0; i < Cowin.hlist.size(); i++) {
            if(Cowin.hlist.get(i).getHuID().equals(huid)) {
                if(!(vaccinationStatus == 0) && !a_vac.getName().equals(Cowin.hlist.get(i).getH_slot_list().get(ch).getV().getName())) {
                    System.out.println("Vaccine in selected slot is not matching previous dose!!");
                    return;
                }
                else {
                    a_vac = Cowin.hlist.get(i).getH_slot_list().get(ch).getV();
                }
                //Cowin.hlist.get(i).getH_slot_list().get(ch).getQuantity()--;
                Cowin.hlist.get(i).getH_slot_list().get(ch).setQuantity(Cowin.hlist.get(i).getH_slot_list().get(ch).getQuantity()-1);
                this.due_date = Cowin.hlist.get(i).getH_slot_list().get(ch).getDay();
                if(Cowin.hlist.get(i).getH_slot_list().get(ch).getQuantity() <= 0){
                    Cowin.hlist.get(i).getH_slot_list().remove(ch);
                }
            }
        }

        System.out.println(this.name + " vaccinated by " + a_vac.getName());

        if(vaccinationStatus >= 0) {//increase doses recieved
            vaccinationStatus++;
        }
        if(vaccinationStatus == a_vac.getDosesReq()){//make vaccination status -ve in case of fully vaccinated
            vaccinationStatus = -1*vaccinationStatus;
            this.due_date = -1*this.due_date;
        }
        else{
            this.due_date = this.due_date + a_vac.getGap();
        }
    }

    public void vac_status() {
        if(this.vaccinationStatus == 0){
            System.out.println("Vaccination Status: REGISTERED");
            System.out.println("Number of Doses Administered: "+ this.vaccinationStatus);
        }
        else if(this.vaccinationStatus < 0){
            System.out.print("Vaccination Status: FULLY VACCINATED | ");
            System.out.println("Vaccine Given: " + a_vac.getName());
            System.out.println("Number of Doses Administered: "+ (-1)*this.vaccinationStatus);
        }
        else if(this.vaccinationStatus > 0){
            System.out.println("Vaccination Status: PARTIALLY VACCINATED");
            System.out.println("Number of Doses Administered: "+ this.vaccinationStatus);
            System.out.print("Vaccine Given: " + a_vac.getName());
            System.out.println(" | Due Date: " + this.due_date);
        }
    }
}


//Author Bhagesh Gaur 2020558