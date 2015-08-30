package hnzevn.android.grocerylist.data;

import java.util.ArrayList;

import hnzevn.android.grocerylist.interfaces.DataAccess;
import hnzevn.android.grocerylist.models.Grocery;

public class DataAccessStub implements DataAccess {
    private String dbName;
    private ArrayList<Grocery> groceryList;

    public DataAccessStub() {
        groceryList = new ArrayList<>();
    }

    public void open(String dbName) {
        this.dbName = dbName;
        initDB();
    }

    public ArrayList<Grocery> getGroceries() {
        return new ArrayList<>(groceryList);
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
        groceryList.add(new Grocery("Apples", "Produce"));
        groceryList.add(new Grocery("Bananas", "Produce"));
        groceryList.add(new Grocery("Carrots", "Produce"));
        groceryList.add(new Grocery("Eggs", "Dairy"));
        groceryList.add(new Grocery("Milk", "Dairy"));
        groceryList.add(new Grocery("Sour Cream", "Dairy"));
        groceryList.add(new Grocery("Pizza", "Frozen"));
        groceryList.add(new Grocery("Green Beans", "Frozen"));
        groceryList.add(new Grocery("Bread", "Bakery"));
        groceryList.add(new Grocery("Bagels", "Bakery"));
    }
}
