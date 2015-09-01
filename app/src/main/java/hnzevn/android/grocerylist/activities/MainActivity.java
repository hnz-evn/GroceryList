package hnzevn.android.grocerylist.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import hnzevn.android.grocerylist.R;
import hnzevn.android.grocerylist.fragments.GroceryListFragment;
import hnzevn.android.grocerylist.fragments.GroceryPickerFragment;


public class MainActivity extends Activity
        implements GroceryPickerFragment.OnActionButtonListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new GroceryPickerFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    public void onActionButtonClick() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = new GroceryListFragment();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
