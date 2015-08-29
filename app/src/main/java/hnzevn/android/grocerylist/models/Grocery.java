package hnzevn.android.grocerylist.models;

public class Grocery {
    private String name;
    private String department;

    public Grocery(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
}
