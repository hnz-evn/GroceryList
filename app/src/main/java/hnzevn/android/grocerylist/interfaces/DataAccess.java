package hnzevn.android.grocerylist.interfaces;

import java.util.ArrayList;

import hnzevn.android.grocerylist.models.Grocery;

public interface DataAccess {
    void open(String dbPath);
    ArrayList<Grocery> getGroceries();
    void addGrocery(Grocery grocery);
    void close();
}
