package hnzevn.android.grocerylist.fragments;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeSet;

import hnzevn.android.grocerylist.R;

public class GroceryListFragment extends ListFragment {

    class GroceryListAdapter extends BaseAdapter {
        private static final int TYPE_ITEM = 0;
        private static final int TYPE_HEADER = 1;

        private ArrayList<String> listData;
        private TreeSet<Integer> sectionHeader;

        private LayoutInflater inflater;

        public GroceryListAdapter(Context context) {
            listData = new ArrayList<>();
            sectionHeader = new TreeSet<>();
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void addGroceryItem(final String groceryName) {
            listData.add(groceryName);
        }

        public void addHeaderItem(final String headerName) {
            listData.add(headerName);
            sectionHeader.add(listData.size() - 1);
        }

        @Override
        public int getItemViewType(int position) {
            return sectionHeader.contains(position) ? TYPE_HEADER : TYPE_ITEM;
        }

        @Override
        public int getCount() {
            return listData.size();
        }

        @Override
        public String getItem(int position) {
            return listData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = null;
            int rowType = getItemViewType(position);

            if (convertView == null) {
                switch(rowType) {
                    case TYPE_ITEM:
                        convertView = inflater.inflate(R.layout.list_item_grocery, null);
                        textView = (TextView) convertView.findViewById(R.id.grocery_name);
                        break;
                    case TYPE_HEADER:
                        convertView = inflater.inflate(R.layout.list_item_grocery_header, null);
                        textView = (TextView) convertView.findViewById(R.id.grocery_department);
                        break;
                }
            }

            textView.setText(listData.get(position));

            return convertView;
        }
    }
}
