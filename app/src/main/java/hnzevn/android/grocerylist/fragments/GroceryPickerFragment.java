package hnzevn.android.grocerylist.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import hnzevn.android.grocerylist.R;
import hnzevn.android.grocerylist.data.DataAccessStub;
import hnzevn.android.grocerylist.interfaces.DataAccess;
import hnzevn.android.grocerylist.models.Grocery;

public class GroceryPickerFragment extends ListFragment {

    private static final String TAG = "GroceryPickerFragment";
    private DataAccess db;
    private ArrayList<Grocery> groceryList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DataAccessStub();
        db.open("GroceryList");
        groceryList = db.getGroceries();

        ArrayAdapter<Grocery> adapter = new GroceryAdapter(groceryList);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Grocery grocery = ((GroceryAdapter) getListAdapter()).getItem(position);
        grocery.setToBuy(!grocery.willBuy());
        ((GroceryAdapter) getListAdapter()).notifyDataSetChanged();

    }

    private class GroceryAdapter extends ArrayAdapter<Grocery> {
        public GroceryAdapter(ArrayList<Grocery> groceries) {
            super(getActivity(), 0, groceries);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView  = getActivity()
                        .getLayoutInflater()
                        .inflate(R.layout.list_item_grocery_picker, null);
            }

            Grocery grocery = getItem(position);

            TextView nameTextView = (TextView) convertView.findViewById(R.id.grocery_list_item_name);
            nameTextView.setText(grocery.getName());

            CheckBox solvedCheckBox = (CheckBox) convertView.findViewById(R.id.grocery_list_item_buy);
            solvedCheckBox.setChecked(grocery.willBuy());

            return convertView;
        }
    }
}
