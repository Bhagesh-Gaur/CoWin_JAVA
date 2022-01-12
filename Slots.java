package Assignment_1;

public class Slots {

    private String hospitalID;
    private String Pincode;
    private int day;
    private int quantity;
    private Vaccine V;

    //GETTERS & SETTERS
    public String getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(String hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Vaccine getV() {
        return V;
    }

    public void setV(Vaccine v) {
        V = v;
    }

    public Slots(String hospitalID, String pincode, int day, int quantity, Vaccine v) {
        this.hospitalID = hospitalID;
        this.Pincode = pincode;
        this.day = day;
        this.quantity = quantity;
        this.V = v;
    }

    public void print_rec() {
        System.out.print("Day: " + this.day + ", ");
        System.out.print("Qty available : " + quantity + ", ");
        System.out.println("Vaccine: " + V.getName());
    }
}

//Author Bhagesh Gaur 2020558