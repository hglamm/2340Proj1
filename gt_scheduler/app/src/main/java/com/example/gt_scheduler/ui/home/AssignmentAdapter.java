package com.example.gt_scheduler.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gt_scheduler.R;

import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder> {

    private List<Assignment> assignments;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public AssignmentAdapter(List<Assignment> assignments, OnItemClickListener listener) {
        this.assignments = assignments;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_assignment, parent, false);
        return new AssignmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, int position) {
        Assignment assignment = assignments.get(position);
        holder.bind(assignment);
    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }

    class AssignmentViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView dueDateTextView;
        private TextView classTextView;
        private View editButton;
        private View deleteButton;


        public AssignmentViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            dueDateTextView = itemView.findViewById(R.id.dueDateTextView);
            classTextView = itemView.findViewById(R.id.classTextView);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.removeButton);

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }

        public void bind(Assignment assignment) {
            titleTextView.setText(assignment.getTitle());
            dueDateTextView.setText(assignment.getDueDate());
            classTextView.setText(assignment.getClassName());
        }
    }
}


//package com.example.gt_scheduler.ui.home;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.gt_scheduler.R;
//
//import java.util.List;
//
//public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder> {
//
//    private List<Assignment> assignments;
//
//    public AssignmentAdapter(List<Assignment> assignments) {
//        this.assignments = assignments;
//    }
//
//    @NonNull
//    @Override
//    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_assignment, parent, false);
//        return new AssignmentViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, int position) {
//        Assignment assignment = assignments.get(position);
//        holder.bind(assignment);
//    }
//
//    @Override
//    public int getItemCount() {
//        return assignments.size();
//    }
//
//    static class AssignmentViewHolder extends RecyclerView.ViewHolder {
//        private TextView titleTextView;
//        private TextView dueDateTextView;
//        private TextView classTextView;
//
//        public AssignmentViewHolder(@NonNull View itemView) {
//            super(itemView);
//            titleTextView = itemView.findViewById(R.id.titleTextView);
//            dueDateTextView = itemView.findViewById(R.id.dueDateTextView);
//            classTextView = itemView.findViewById(R.id.classTextView);
//        }
//
//        public void bind(Assignment assignment) {
//            titleTextView.setText(assignment.getTitle());
//            dueDateTextView.setText(assignment.getDueDate());
//            classTextView.setText(assignment.getClassName());
//        }
//    }
//}
