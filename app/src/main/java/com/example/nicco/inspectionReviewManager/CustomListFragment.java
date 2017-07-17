package com.example.nicco.inspectionReviewManager;

import android.app.ListFragment;
import android.content.Intent;
import android.graphics.Color;
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

//    private void changeListItemBackground() {
//        getListView().getChildAt(0).setBackgroundColor(Color.RED);
//    }
//

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), getResources().getStringArray(R.array.arr)[position],
                Toast.LENGTH_SHORT).show();
       if (getResources().getStringArray(R.array.arr)[position].equals(getString(R.string.date))) {
            Intent intent = new Intent(this.getActivity(), DateActivity.class);
            startActivity(intent);
        } else if (getResources().getStringArray(R.array.arr)[position].equals(getString(R.string.project))) {
            Intent intent = new Intent(this.getActivity(), LocationActivity.class);
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
}