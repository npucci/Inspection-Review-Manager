package com.example.nicco.inspectionReviewManager.fragments;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicco.inspectionReviewManager.customDatatypes.Model;
import com.example.nicco.inspectionReviewManager.R;
import com.example.nicco.inspectionReviewManager.activities.ConclusionActivity;
import com.example.nicco.inspectionReviewManager.activities.ConcreteActivity;
import com.example.nicco.inspectionReviewManager.activities.DateActivity;
import com.example.nicco.inspectionReviewManager.activities.FramingActivity;
import com.example.nicco.inspectionReviewManager.activities.ProjectActivity;

public class InspectionReviewListFragment extends ListFragment implements OnItemClickListener {
    private boolean finished = false;
    private Model model;
    private float textSize;

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inspection_review_list_fragment, container, false);
        model = (Model) getActivity().getApplicationContext();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView listView = getListView();
        listView.setOnItemClickListener(this);

        String[] arr = getResources().getStringArray(R.array.arr);
        CustomArrayAdapter adapter = new CustomArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, arr);
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ArrayAdapter) getListView().getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListView listView = getListView();
        view.setSelected(true);
        ((ArrayAdapter<String>) listView.getAdapter()).notifyDataSetChanged();

        String selected = listView.getAdapter().getItem(position).toString();
        Toast.makeText(getActivity(), selected,
                Toast.LENGTH_SHORT).show();
        if (selected.equals(getString(R.string.date))) {
            Intent intent = new Intent(this.getActivity(), DateActivity.class);
            startActivity(intent);
        } else if (selected.equals(getString(R.string.project))) {
            Intent intent = new Intent(this.getActivity(), ProjectActivity.class);
            startActivity(intent);
        } else if (selected.equals(getString(R.string.concrete))) {
            Intent intent = new Intent(this.getActivity(), ConcreteActivity.class);
            startActivity(intent);
        } else if (selected.equals(getString(R.string.framing))) {
            Intent intent = new Intent(this.getActivity(), FramingActivity.class);
            startActivity(intent);
        }  else if (selected.equals(getString(R.string.conclusion))) {
            Intent intent = new Intent(this.getActivity(), ConclusionActivity.class);
            startActivity(intent);
        }
    }

    private class CustomArrayAdapter extends ArrayAdapter<String> {
        //private View convertView;
        private ViewGroup parent;

        public CustomArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull String[] objects) {
            super(context, resource, objects);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            this.parent = parent;
            TextView textView = (TextView) super.getView(position, convertView, parent);
            textView.setTextColor(model.getTextColor(textView.getText().toString()));
            textView.setBackgroundColor(model.getBackgroundColor(textView.getText().toString()));
            textView.setTextSize(textSize);
            if(textView.isSelected()) {
                textView.setTextColor(ContextCompat.getColor(getContext(), R.color.selectedText));
                textView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.selectedBackground));
            }
            return textView;
        }
    }
}