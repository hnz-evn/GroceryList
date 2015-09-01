package hnzevn.android.grocerylist.fragments;

import android.app.ListFragment;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import hnzevn.android.grocerylist.adapters.GroceryListAdapter;
import hnzevn.android.grocerylist.data.DataAccessStub;
import hnzevn.android.grocerylist.interfaces.DataAccess;
import hnzevn.android.grocerylist.models.Grocery;

public class GroceryListFragment extends ListFragment {

    private static final String TAG = "GroceryListFragment";

    private DataAccess db;
    private HashMap<String, ArrayList<Grocery>> groceryList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GroceryListAdapter adapter = new GroceryListAdapter(this.getActivity());

        db = DataAccessStub.getInstance();
        db.open("GroceryList");
        groceryList = db.getGroceriesByDepartment();

        Set<String> departments = groceryList.keySet();
        ArrayList<Grocery> departmentGroceries;

        for (String department : departments) {
            adapter.addHeaderItem(department);

            departmentGroceries = groceryList.get(department);

            for (Grocery grocery : departmentGroceries) {
                adapter.addGroceryItem(grocery.getName());
            }
        }

        setListAdapter(adapter);
    }
}
