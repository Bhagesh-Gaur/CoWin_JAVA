package Assignment_1;

public class Vaccine {
    private String name;
    private int ndoses, gap;

    public Vaccine(String name, int ndoses, int gap) {
        this.name = name;
        this.ndoses = ndoses;
        this.gap = gap;
    }

    public String getName() {
        return name;
    }

    public int getDosesReq() {
        return ndoses;
    }

    public int getGap() {
        return gap;
    }

    public void print_rec() {
        System.out.print("Vaccine Name: ");
        System.out.print(this.name);
        System.out.print(", Number of Doses: ");
        System.out.print(this.ndoses);
        System.out.print(", Gap Between Doses: ");
        System.out.println(this.gap);
    }
}

//Author Bhagesh Gaur 2020558