package com.example.nicco.inspectionReviewManager;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
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
    private int selectedPos = -1;
    private CursorAdapter cursorAdapter;
    private float textSize;
    private static RecyclerViewClickListener itemListener;

    public RecyclerAdapter(Context context, RecyclerViewClickListener itemListener, Cursor cursor, int textSize) {
        this.context = context;
        this.itemListener = itemListener;
        this.textSize = textSize;

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

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        notifyDataSetChanged();
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
        if(model.isChecked(other)) reviewType += " OTH";

        holder.setReviewType(reviewType);
        holder.setReviewStatus(cursor.getString(cursor.getColumnIndex(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS.getValue())));
        holder.setReviewBy(cursor.getString(cursor.getColumnIndex(DatabaseWriter.UIComponentInputValue.REVIEWED_BY.getValue())));
        cursorAdapter.bindView(holder.itemView, context, cursorAdapter.getCursor());

        holder.itemView.setSelected(position == selectedPos);

        holder.setbackgroundColors(context, holder.itemView.isSelected());
        holder.setTextColors(context, holder.itemView.isSelected());
        holder.setTextUnderline(holder.itemView.isSelected());
        holder.updateTextSize(textSize);
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

    public int getSelectedPosition() { return selectedPos; }

    public void setSelectedPosition(int selectedPos) { this.selectedPos = selectedPos; }

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

        public void setbackgroundColors(Context context, Boolean selected) {
            int color = 0;
            if(selected) color = ContextCompat.getColor(context, R.color.selectedBackground);
            else color = ContextCompat.getColor(context, R.color.black);
            linearLayout.setBackgroundColor(color);
        }

        public void setTextColors(Context context, Boolean selected) {
            int color = 0;
            if(selected) color = ContextCompat.getColor(context, R.color.selectedText);
            else color = ContextCompat.getColor(context, R.color.white);

            date.setTextColor(color);
            time.setTextColor(color);
            address.setTextColor(color);
            city.setTextColor(color);
            province.setTextColor(color);
            projectNumber.setTextColor(color);
            reviewType.setTextColor(color);
            reviewStatus.setTextColor(color);
            reviewBy.setTextColor(color);
        }

        public void setTextUnderline(boolean selected) {
            if(selected) {
                date.setPaintFlags(date.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                time.setPaintFlags(time.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                address.setPaintFlags(address.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                city.setPaintFlags(city.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                province.setPaintFlags(province.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                projectNumber.setPaintFlags(projectNumber.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                reviewType.setPaintFlags(reviewType.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                reviewStatus.setPaintFlags(reviewStatus.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                reviewBy.setPaintFlags(reviewBy.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            } else {
                date.setPaintFlags(0);
                time.setPaintFlags(0);
                address.setPaintFlags(0);
                city.setPaintFlags(0);
                province.setPaintFlags(0);
                projectNumber.setPaintFlags(0);
                reviewType.setPaintFlags(0);
                reviewStatus.setPaintFlags(0);
                reviewBy.setPaintFlags(0);
            }
        }

        public void updateTextSize(float textSize) {
            date.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
            time.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
            address.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
            city.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
            province.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
            projectNumber.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
            reviewType.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
            reviewStatus.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
            reviewBy.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        }

        @Override
        public void onClick(View view) {
            itemListener.recyclerViewListClicked(view, getLayoutPosition());
        }
    }
}
