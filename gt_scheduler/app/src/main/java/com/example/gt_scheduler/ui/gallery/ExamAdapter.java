package com.example.gt_scheduler.ui.gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gt_scheduler.R;

import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamViewHolder> {
    private List<Exam> exams;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public ExamAdapter(List<Exam> exams, OnItemClickListener listener) {
        this.exams = exams;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam, parent, false);
        return new ExamViewHolder(view);
    }

    @Override
    public int getItemCount() { return exams.size(); }

    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {
        Exam exam = exams.get(position);
        holder.bind(exam);
    }

    class ExamViewHolder extends RecyclerView.ViewHolder {
        private TextView examClassTextView;
        private TextView dateTextView;
        private View examEditButton;
        private View deleteButton;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            examClassTextView = itemView.findViewById(R.id.examClassTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            examEditButton = itemView.findViewById(R.id.examEditButton);
            deleteButton = itemView.findViewById(R.id.examDeleteButton);


            examEditButton.setOnClickListener(new View.OnClickListener() {
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

        public void bind(Exam exam) {
            examClassTextView.setText(exam.getClassName());
            dateTextView.setText(exam.getDate().toString());
        }
    }

}
