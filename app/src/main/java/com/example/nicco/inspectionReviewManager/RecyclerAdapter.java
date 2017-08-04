package com.example.nicco.inspectionReviewManager;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Nicco on 2017-08-02.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context context;
    private CursorAdapter cursorAdapter;
    private static RecyclerViewClickListener itemListener;
    private String selectedItem = "";

    public RecyclerAdapter(Context context, RecyclerViewClickListener itemListener, Cursor cursor) {
        this.context = context;
        this.itemListener = itemListener;

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
        Cursor cursor = cursorAdapter.getCursor();
        holder.setDate(cursor.getString(cursor.getColumnIndex(DatabaseWriter.UIComponentInputValue.DATE.getValue())));
        holder.setTime(cursor.getString(cursor.getColumnIndex(DatabaseWriter.UIComponentInputValue.TIME.getValue())));
        holder.setAddress(cursor.getString(cursor.getColumnIndex(DatabaseWriter.UIComponentInputValue.ADDRESS.getValue())) + ", ");
        holder.setCity(cursor.getString(cursor.getColumnIndex(DatabaseWriter.UIComponentInputValue.CITY.getValue())) + ", ");
        holder.setProvince(cursor.getString(cursor.getColumnIndex(DatabaseWriter.UIComponentInputValue.PROVINCE.getValue())));
        holder.setProjectNumber(cursor.getString(cursor.getColumnIndex(DatabaseWriter.UIComponentInputValue.PROJECT_NUMBER.getValue())));

        final Model model = (Model) context.getApplicationContext();
        String reviewType = "Review Type(s):";
        String footings = cursor.getString(cursor.getColumnIndex(DatabaseWriter.UIComponentInputValue.FOOTINGS_REVIEW.getValue()));
        String foundationWalls = cursor.getString(cursor.getColumnIndex(DatabaseWriter.UIComponentInputValue.FOUNDATION_WALLS_REVIEW.getValue()));
        String sheathing = cursor.getString(cursor.getColumnIndex(DatabaseWriter.UIComponentInputValue.SHEATHING_REVIEW.getValue()));
        String framing = cursor.getString(cursor.getColumnIndex(DatabaseWriter.UIComponentInputValue.FRAMING_REVIEW.getValue()));
        String other = cursor.getString(cursor.getColumnIndex(DatabaseWriter.UIComponentInputValue.OTHER_REVIEW.getValue()));
        if(model.isChecked(footings)) reviewType += " FT";
        if(model.isChecked(foundationWalls)) reviewType += " FW";
        if(model.isChecked(sheathing)) reviewType += " SHE";
        if(model.isChecked(framing)) reviewType += " FRA";
        if(model.isChecked(other)) reviewType += " OTh";
        Log.v("PUCCI", "reviewType = " + reviewType);

        holder.setReviewType(reviewType);
        holder.setReviewStatus(cursor.getString(cursor.getColumnIndex(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS.getValue())));
        holder.setReviewBy(cursor.getString(cursor.getColumnIndex(DatabaseWriter.UIComponentInputValue.REVIEWED_BY.getValue())));
        cursorAdapter.bindView(holder.itemView, context, cursorAdapter.getCursor());
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
          R.layout.recyclerview_item_row, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public int getItemCount() {
        return cursorAdapter.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private LinearLayout linearLayout;
        private TextView date;
        private TextView time;
        private TextView address;
        private TextView city;
        private TextView province;
        private TextView projectNumber;
        private TextView reviewType;
        private TextView reviewStatus;
        private TextView reviewBy;


        public ViewHolder(View view, Context context) {
            super(view);

            linearLayout = (LinearLayout) view.findViewById(R.id.linearLayoutRecycleViewItem);
            linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.black));

            // DATE
            date = (TextView) view.findViewById(R.id.textViewDate);
            date.setTextColor(ContextCompat.getColor(context, R.color.white));
            // TIME
            time = (TextView) view.findViewById(R.id.textViewTime);
            time.setTextColor(ContextCompat.getColor(context, R.color.white));
            // ADDRESS
            address = (TextView) view.findViewById(R.id.textViewAddress);
            address.setTextColor(ContextCompat.getColor(context, R.color.white));
            // CITY
            city = (TextView) view.findViewById(R.id.textViewCity);
            city.setTextColor(ContextCompat.getColor(context, R.color.white));
            // PROVINCE
            province = (TextView) view.findViewById(R.id.textViewProvince);
            province.setTextColor(ContextCompat.getColor(context, R.color.white));
            // PROJECT NUMBER
            projectNumber = (TextView) view.findViewById(R.id.textViewProjectNumber);
            projectNumber.setTextColor(ContextCompat.getColor(context, R.color.white));
            // REVIEW TYPE
            reviewType = (TextView) view.findViewById(R.id.textViewReviewType);
            reviewType.setTextColor(ContextCompat.getColor(context, R.color.white));
            // REVIEW STATUS
            reviewStatus = (TextView) view.findViewById(R.id.textViewReviewStatus);
            reviewStatus.setTextColor(ContextCompat.getColor(context, R.color.white));
            // REVIEWED BY
            reviewBy = (TextView) view.findViewById(R.id.textViewReviewBy);
            reviewBy.setTextColor(ContextCompat.getColor(context, R.color.white));

            view.setOnClickListener(this);
        }

        public void setDate(String text) { date.setText(text); }
        public void setTime(String text) { time.setText(text); }
        public void setAddress(String text) { address.setText(text); }
        public void setCity(String text) { city.setText(text); }
        public void setProvince(String text) { province.setText(text); }
        public void setProjectNumber(String text) { projectNumber.setText(text); }
        public void setReviewType(String text) { reviewType.setText(text); }
        public void setReviewStatus(String text) { reviewStatus.setText(text); }
        public void setReviewBy(String text) { reviewBy.setText(text); }

        @Override
        public void onClick(View view) {
            itemListener.recyclerViewListClicked(view, getLayoutPosition());
        }
    }
}
