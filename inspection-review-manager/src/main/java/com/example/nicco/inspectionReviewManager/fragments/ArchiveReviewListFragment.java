package com.example.nicco.inspectionReviewManager.fragments;

import android.app.ListFragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicco.inspectionReviewManager.R;

public class ArchiveReviewListFragment extends ListFragment implements OnItemClickListener {
    private boolean finished = false;
    private String reportKey = "";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.archive_list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView listView = getListView();
        listView.setOnItemClickListener(this);

        String[] arr = new String[200];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = "Inspection Review " + (i + 1);
        }
        CustomArrayAdapter adapter = new CustomArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, arr);
        listView.setAdapter(adapter);
    }

    public void setSelected(int position) {
       // if(position < 0 || position >= getListView().getAdapter().getCount()) return;
        String selected = getListView().getAdapter().getItem(position).toString();
        reportKey = selected;
        ((View) getListView().getAdapter().getItem(position)).setSelected(true);
        ((ArrayAdapter<String>) getListView().getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListView listView = getListView();
        if(listView.getAdapter().getItem(position) == null) return;
        String selected = listView.getAdapter().getItem(position).toString();
        Toast.makeText(getActivity(), selected,
                Toast.LENGTH_SHORT).show();
        reportKey = selected;
        view.setSelected(true);
        ((ArrayAdapter<String>) listView.getAdapter()).notifyDataSetChanged();
    }

    private class CustomArrayAdapter extends ArrayAdapter<String> {
        private ViewGroup parent;

        public CustomArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull String[] objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            this.parent = parent;
            TextView textView = (TextView) super.getView(position, convertView, parent);
            if(textView.isSelected()) {
                textView.setTextColor(Color.WHITE);
                textView.setBackgroundColor(Color.DKGRAY);
            } else {
                textView.setTextColor(Color.WHITE);
                textView.setBackgroundColor(Color.rgb(12, 12, 12));
            }
            return textView;
        }
    }
}