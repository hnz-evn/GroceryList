package hnzevn.android.grocerylist.data;

import java.util.ArrayList;
import java.util.HashMap;

import hnzevn.android.grocerylist.interfaces.DataAccess;
import hnzevn.android.grocerylist.models.Grocery;

public class DataAccessStub implements DataAccess {
    private static DataAccessStub instance;

    private String dbName;
    private ArrayList<Grocery> groceryList;

    private DataAccessStub() {
        groceryList = new ArrayList<>();
    }

    public static DataAccessStub getInstance() {
        if (instance == null) {
            instance = new DataAccessStub();
        }

        return instance;
    }

    public void open(String dbName) {
        this.dbName = dbName;
        initDB();
    }

    public ArrayList<Grocery> getGroceries() {
        return new ArrayList<>(groceryList);
    }

    public HashMap<String, ArrayList<Grocery>> getGroceriesByDepartment() {
        HashMap<String, ArrayList<Grocery>> groceriesByDepartment = new HashMap<>();

        for (Grocery grocery : groceryList) {
            if (grocery.willBuy()) {
                ArrayList<Grocery> departmentGroceries;
                String department = grocery.getDepartment();

                if (groceriesByDepartment.containsKey(department)) {
                    departmentGroceries = groceriesByDepartment.get(department);

                    if (!departmentGroceries.contains(grocery)) {
                        departmentGroceries.add(grocery);
                    }
                }
                else {
                    departmentGroceries = new ArrayList<>();
                    departmentGroceries.add(grocery);
                    groceriesByDepartment.put(department, departmentGroceries);
                }
            }
        }

        return groceriesByDepartment;
    }

    public void addGrocery(Grocery grocery) {
        if (!groceryList.contains(grocery)) {
            groceryList.add(grocery);
        }
    }

    public void close() {
        groceryList.clear();
        groceryList = null;
    }

    private void initDB() {
        if (groceryList.isEmpty()) {
            groceryList.add(new Grocery("Apples", "Produce"));
            groceryList.add(new Grocery("Bananas", "Produce"));
            groceryList.add(new Grocery("Carrots", "Produce"));
            groceryList.add(new Grocery("Lettuce", "Produce"));
            groceryList.add(new Grocery("Cucumber", "Produce"));
            groceryList.add(new Grocery("Red Onion", "Produce"));
            groceryList.add(new Grocery("Bacon", "Meats"));
            groceryList.add(new Grocery("Eggs", "Dairy"));
            groceryList.add(new Grocery("Milk", "Dairy"));
            groceryList.add(new Grocery("Sour Cream", "Dairy"));
            groceryList.add(new Grocery("Cheddar Cheese", "Dairy"));
            groceryList.add(new Grocery("Parmesan Cheese", "Dairy"));
            groceryList.add(new Grocery("Greek Yogurt", "Dairy"));
            groceryList.add(new Grocery("Pizza", "Frozen"));
            groceryList.add(new Grocery("Green Beans", "Frozen"));
            groceryList.add(new Grocery("Frozen Fruit", "Frozen"));
            groceryList.add(new Grocery("Stuffed Chicken", "Frozen"));
            groceryList.add(new Grocery("Rice", "Dried Goods"));
            groceryList.add(new Grocery("Clif Bars", "Dried Goods"));
            groceryList.add(new Grocery("Fruit Bars", "Dried Goods"));
            groceryList.add(new Grocery("Bread", "Bakery"));
            groceryList.add(new Grocery("Bagels", "Bakery"));
            groceryList.add(new Grocery("Hand Soap", "Miscellaneous"));
            groceryList.add(new Grocery("Garbage Bags", "Miscellaneous"));
        }
    }
}
