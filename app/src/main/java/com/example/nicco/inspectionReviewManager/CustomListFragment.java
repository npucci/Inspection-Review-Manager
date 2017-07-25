package com.example.nicco.inspectionReviewManager;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomListFragment extends ListFragment implements OnItemClickListener {
    private Button finishedButton;
    private Model model;
    private boolean finished = false;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_list_fragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListView().setOnItemClickListener(this);
        model = (Model) getActivity().getApplicationContext();

        model.checkDateActivityStatus();
        model.checkProjectActivityStatus();
        model.checkConcreteActivityStatus();
        model.checkFramingActivityStatus();
        model.checkConclusionActivityStatus();

        finishedButton = (Button) getActivity().findViewById(R.id.buttonFinished);
        finishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // model.insertDatabase();
               // Log.v("PUCCI", "TEST CLICK");
            }
        });

        ListView list = (ListView) getListView();
        String[] arr = getResources().getStringArray(R.array.arr);
        CustomArrayAdapter adapter = new CustomArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, arr);

        list.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), getResources().getStringArray(R.array.arr)[position],
                Toast.LENGTH_SHORT).show();
       if (getResources().getStringArray(R.array.arr)[position].equals(getString(R.string.date))) {
            Intent intent = new Intent(this.getActivity(), DateActivity.class);
            startActivity(intent);
        } else if (getResources().getStringArray(R.array.arr)[position].equals(getString(R.string.project))) {
            Intent intent = new Intent(this.getActivity(), ProjectActivity.class);
            startActivity(intent);
        } else if (getResources().getStringArray(R.array.arr)[position].equals(getString(R.string.concrete))) {
            Intent intent = new Intent(this.getActivity(), ConcreteActivity.class);
            startActivity(intent);
        } else if (getResources().getStringArray(R.array.arr)[position].equals(getString(R.string.framing))) {
            Intent intent = new Intent(this.getActivity(), FramingActivity.class);
            startActivity(intent);
        }  else if (getResources().getStringArray(R.array.arr)[position].equals(getString(R.string.conclusion))) {
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
            //this.convertView = convertView;
            this.parent = parent;
            TextView textView = (TextView) super.getView(position, convertView, parent);
            textView.setTextColor(model.getTextColor(textView.getText().toString()));
            textView.setBackgroundColor(model.getBackgroundColor(textView.getText().toString()));
            return textView;
        }

        public void setTextColor(TextView textView, int color) {

        }

        public void setBackgroundColor(int position, View convertView, int color) {
            TextView textView = (TextView) super.getView(position, convertView, parent);
            textView.setBackgroundColor(color);
        }
    }
}