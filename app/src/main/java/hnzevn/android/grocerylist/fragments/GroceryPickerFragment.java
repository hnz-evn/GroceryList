package hnzevn.android.grocerylist.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

import hnzevn.android.grocerylist.R;
import hnzevn.android.grocerylist.data.DataAccessStub;
import hnzevn.android.grocerylist.interfaces.DataAccess;
import hnzevn.android.grocerylist.models.Grocery;

public class GroceryPickerFragment extends ListFragment {

    private static final String TAG = "GroceryPickerFragment";

    private OnActionButtonListener callback;
    private DataAccess db;
    private ArrayList<Grocery> groceryList;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            callback = (OnActionButtonListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnActionButtonListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = DataAccessStub.getInstance();
        db.open("GroceryList");
        groceryList = db.getGroceries();

        ArrayAdapter<Grocery> adapter = new GroceryAdapter(groceryList);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grocery_picker, container, false);

        ListView listView = (ListView) view.findViewById(android.R.id.list);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onActionButtonClick();
            }
        });
        fab.attachToListView(listView);

        return view;
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

    public interface OnActionButtonListener {
        void onActionButtonClick();
    }
}
