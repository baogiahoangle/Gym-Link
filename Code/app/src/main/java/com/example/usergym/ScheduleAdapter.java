package com.example.usergym;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private final List<String> timeSlots;
    private final List<WorkoutTask> tasks;
    private final OnTaskClickListener listener;

    public interface OnTaskClickListener {
        void onTaskClick(WorkoutTask task);
    }

    public ScheduleAdapter(List<String> timeSlots, List<WorkoutTask> tasks, OnTaskClickListener listener) {
        this.timeSlots = timeSlots;
        this.tasks = tasks;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule_time, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String time = timeSlots.get(position);
        holder.tvTime.setText(time);
        
        WorkoutTask task = findTaskForTime(time);
        if (task != null) {
            holder.cardTask.setVisibility(View.VISIBLE);
            TextView tvTaskName = holder.cardTask.findViewById(R.id.tv_task_title);
            TextView tvTaskDuration = holder.cardTask.findViewById(R.id.tv_task_time);
            if (tvTaskName != null) tvTaskName.setText(task.getName());
            if (tvTaskDuration != null) tvTaskDuration.setText(task.getDuration());
            
            holder.itemView.setOnClickListener(v -> listener.onTaskClick(task));
        } else {
            holder.cardTask.setVisibility(View.GONE);
            holder.itemView.setOnClickListener(null);
        }
    }

    private WorkoutTask findTaskForTime(String time) {
        for (WorkoutTask task : tasks) {
            if (task.getTime().equalsIgnoreCase(time)) {
                return task;
            }
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return timeSlots.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTime;
        View cardTask;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tv_time);
            cardTask = itemView.findViewById(R.id.card_task);
        }
    }
}