package hnzevn.android.grocerylist.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import hnzevn.android.grocerylist.models.Grocery;

public interface DataAccess {
    void open(String dbPath);
    ArrayList<Grocery> getGroceries();
    HashMap<String, ArrayList<Grocery>> getGroceriesByDepartment();
    void addGrocery(Grocery grocery);
    void close();
}
