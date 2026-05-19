package com.example.usergym;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {

    private final List<Calendar> days;
    private final OnDateClickListener listener;
    private int selectedPosition = -1;

    public interface OnDateClickListener {
        void onDateClick(Calendar date);
    }

    public CalendarAdapter(List<Calendar> days, OnDateClickListener listener) {
        this.days = days;
        this.listener = listener;
        
        Calendar today = Calendar.getInstance();
        for (int i = 0; i < days.size(); i++) {
            if (isSameDay(days.get(i), today)) {
                selectedPosition = i;
                break;
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar_day, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Calendar date = days.get(position);
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEE", Locale.getDefault());
        SimpleDateFormat numberFormat = new SimpleDateFormat("dd", Locale.getDefault());

        holder.tvDayName.setText(dayFormat.format(date.getTime()));
        holder.tvDayNumber.setText(numberFormat.format(date.getTime()));

        if (position == selectedPosition) {
            holder.itemView.setBackgroundResource(R.drawable.bg_calendar_selected);
            holder.tvDayName.setTextColor(0xFFFFFFFF);
            holder.tvDayNumber.setTextColor(0xFFFFFFFF);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.bg_target_card);
            holder.tvDayName.setTextColor(0xFF7B6F72);
            holder.tvDayNumber.setTextColor(0xFF1D1617);
        }

        holder.itemView.setOnClickListener(v -> {
            int previousSelected = selectedPosition;
            selectedPosition = holder.getAdapterPosition();
            notifyItemChanged(previousSelected);
            notifyItemChanged(selectedPosition);
            if (listener != null) {
                listener.onDateClick(date);
            }
        });
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    private boolean isSameDay(Calendar cal1, Calendar cal2) {
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDayName, tvDayNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDayName = itemView.findViewById(R.id.tv_day_name);
            tvDayNumber = itemView.findViewById(R.id.tv_day_number);
        }
    }
}
