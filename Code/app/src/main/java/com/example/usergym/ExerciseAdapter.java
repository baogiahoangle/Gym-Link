package com.example.usergym;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {
    private List<Exercise> exerciseList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Exercise exercise);
    }

    public ExerciseAdapter(List<Exercise> exerciseList, OnItemClickListener listener) {
        this.exerciseList = exerciseList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Exercise exercise = exerciseList.get(position);
        holder.tvName.setText(exercise.getName());
        holder.tvDuration.setText(exercise.getDuration());
        holder.ivExercise.setImageResource(exercise.getImageResId());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(exercise));
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivExercise;
        TextView tvName, tvDuration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivExercise = itemView.findViewById(R.id.iv_exercise);
            tvName = itemView.findViewById(R.id.tv_exercise_name);
            tvDuration = itemView.findViewById(R.id.tv_exercise_duration);
        }
    }
}
