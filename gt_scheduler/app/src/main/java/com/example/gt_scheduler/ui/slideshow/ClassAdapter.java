package com.example.gt_scheduler.ui.slideshow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gt_scheduler.R;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {
    private List<Class> classes;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public ClassAdapter(List<Class> classes, OnItemClickListener listener) {
        this.classes = classes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class, parent, false);
        return new ClassViewHolder(view);
    }

    @Override
    public int getItemCount() { return classes.size(); }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        Class course = classes.get(position);
        holder.bind(course);
    }

    class ClassViewHolder extends RecyclerView.ViewHolder {
        private TextView classNameTextView;
        private TextView instructorTextView;
        private TextView daysTextView;
        private TextView timeTextView;
        private TextView locationTextView;
        private View editButton;
        private View deleteButton;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            classNameTextView = itemView.findViewById(R.id.classNameTextView);
            instructorTextView = itemView.findViewById(R.id.instructorTextView);
            daysTextView = itemView.findViewById(R.id.dayTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
            editButton = itemView.findViewById(R.id.classEditButton);
            deleteButton = itemView.findViewById(R.id.classDeleteButton);


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

        public void bind(Class course) {
            classNameTextView.setText(course.getClassName());
            instructorTextView.setText(course.getInstructor());
            daysTextView.setText(course.getDays() + " ");
            timeTextView.setText(course.getTime());
            locationTextView.setText(course.getLocation());
        }
    }

}
