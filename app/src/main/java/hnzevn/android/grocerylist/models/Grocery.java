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

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Grocery))
            return false;

        if (other == this)
            return true;

        Grocery grocery = (Grocery) other;
        return grocery.getName().equals(this.name) && grocery.getDepartment().equals(this.department);
    }
}
