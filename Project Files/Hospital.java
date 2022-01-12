package Assignment_1;

import java.util.*;

public class Hospital extends Cowin{
    private String name;
    private String huID;
    private String pincode;
    private ArrayList<Slots> h_slot_list = new ArrayList<Slots>();

    //GETTERS & SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHuID() {
        return huID;
    }

    public void setHuID(String huID) {
        this.huID = huID;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public ArrayList<Slots> getH_slot_list() {
        return h_slot_list;
    }

    public void setH_slot_list(ArrayList<Slots> h_slot_list) {
        this.h_slot_list = h_slot_list;
    }


    //Functions
    public Hospital(String name, String huID, String pincode) {//constructors
        this.name = name;
        this.huID = huID;
        this.pincode = pincode;
    }

    public void print_rec() {
        System.out.println("Hospital added...");
        System.out.print("Hospital Name: " + this.name);
        System.out.print(", PinCode: " + this.pincode);
        System.out.println(", Unique ID: " + this.huID);
    }

    public void CreateSlots() {
		Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of Slots to be added: ");
        int numSlots = sc.nextInt();
        for(int count = 1; count <= numSlots; count++) {
            System.out.println("For Slot "+count);
		    //taking in day & quantity.
            System.out.print("Enter Day: ");
            int day = sc.nextInt();
            System.out.println("Select the vaccine");//print Vaccine menu
            for(int i = 0; i < Cowin.vlist.size(); i++) {
                System.out.println(i + ". " + Cowin.vlist.get(i).getName());
            }
            System.out.print("Enter your Choice: ");
            int choice = sc.nextInt();
            for(int i = 0; i < h_slot_list.size(); i++){
                if(h_slot_list.get(i).getV().getName().equals(Cowin.vlist.get(choice).getName()) && day == h_slot_list.get(i).getDay()){
                    System.out.println("Already having slot/(s) for this vaccine in this hospital for the given day ");
                    return;
                }
            }
            Vaccine v = Cowin.vlist.get(choice);//select vaccine

            System.out.print("Enter Quantity: ");
            int quantity = sc.nextInt();//get vaccine in the vaccine list(access through cowin)
            if(quantity <= 0){
                System.out.println("Invalid Qty for the slot. Hence, Slot NOT Created!!!!");
                return;
            }

            Slots s = new Slots(this.huID, this.pincode, day, quantity, v);//make slot

            h_slot_list.add(s);//adding slot in hospital list
            System.out.println("Slot added by Hospital " + this.huID + " for Day: " + day);//print it
            System.out.println("Available Quantity: " + quantity + " of Vaccine " + v.getName());
        }
    }

    public void slotlist() {
        if(h_slot_list.size() == 0){
            System.out.println("No slots Available in " + this.name + "at the moment!!!");
            return;
        }
        System.out.println("Slots available in " + this.name + ": ");
        System.out.println("*****************************************************");
        for(int i = 0; i < h_slot_list.size(); i++) {
            h_slot_list.get(i).print_rec();
            System.out.println("*****************************************************");
        }
    }
}

//Author Bhagesh Gaur 2020558