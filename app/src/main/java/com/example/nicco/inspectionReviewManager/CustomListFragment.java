package com.example.nicco.inspectionReviewManager;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class CustomListFragment extends ListFragment implements OnItemClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.arr, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), getResources().getStringArray(R.array.arr)[position],
                Toast.LENGTH_SHORT).show();
        if (getResources().getStringArray(R.array.arr)[position].equals("Location")) {
            Intent intent = new Intent(this.getActivity(), LocationActivity.class);
            startActivity(intent);
        } else if (getResources().getStringArray(R.array.arr)[position].equals("Date")) {
            Intent intent = new Intent(this.getActivity(), DateActivity.class);
            startActivity(intent);
        } else if (getResources().getStringArray(R.array.arr)[position].equals("Concrete")) {
            Intent intent = new Intent(this.getActivity(), ConcreteActivity.class);
            startActivity(intent);
        } else if (getResources().getStringArray(R.array.arr)[position].equals("Framing")) {
            Intent intent = new Intent(this.getActivity(), FramingActivity.class);
            startActivity(intent);
        }  else if (getResources().getStringArray(R.array.arr)[position].equals("Conclusion")) {
            Intent intent = new Intent(this.getActivity(), ConclusionActivity.class);
            startActivity(intent);
        }
    }
}