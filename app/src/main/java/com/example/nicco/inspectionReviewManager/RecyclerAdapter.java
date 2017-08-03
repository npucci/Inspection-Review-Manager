package com.example.nicco.inspectionReviewManager;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Nicco on 2017-08-02.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context context;
    private CursorAdapter cursorAdapter;

    public RecyclerAdapter(Context context, Cursor cursor) {
        this.context = context;

        cursorAdapter = new CursorAdapter(context, cursor, 1) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                return null;
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {

            }
        };
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        cursorAdapter.getCursor().moveToPosition(position);
        holder.setText(cursorAdapter.getCursor().getString(0));
        cursorAdapter.bindView(holder.itemView, context, cursorAdapter.getCursor());
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
          R.layout.recyclerview_item_row, parent, false);//new TextView(context);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return cursorAdapter.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textViewItemString);
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundColor(Color.BLACK);
        }

        public void setText(String text) { textView.setText(text); }
    }
}
