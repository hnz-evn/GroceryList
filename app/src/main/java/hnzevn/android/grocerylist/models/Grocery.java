package hnzevn.android.grocerylist.models;

public class Grocery {
    private String name;
    private String department;
    private boolean willBuy;

    public Grocery(String name, String department) {
        this.name = name;
        this.department = department;
        this.willBuy = false;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public boolean willBuy() {
        return willBuy;
    }

    public void setToBuy(boolean willBuy) {
        this.willBuy = willBuy;
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
