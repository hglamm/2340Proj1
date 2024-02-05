package com.example.gt_scheduler.ui.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gt_scheduler.R;

import java.util.List;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ToDoListViewHolder> {

    private List<ToDoList> todolists;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onDeleteClick(int position);
    }

    public ToDoListAdapter(List<ToDoList> todolists, OnItemClickListener listener) {
        this.todolists = todolists;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ToDoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_todo_list, parent, false);
        return new ToDoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoListViewHolder holder, int position) {
        ToDoList todolist = todolists.get(position);
        holder.bind(todolist);
    }

    @Override
    public int getItemCount() {
        return todolists.size();
    }

    class ToDoListViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView dueDateTextView;
        private TextView classTextView;
        private View editButton;
        private View deleteButton;

        public ToDoListViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            dueDateTextView = itemView.findViewById(R.id.dueDateTextView);
            classTextView = itemView.findViewById(R.id.classTextView);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.removeButton);

            editButton.setOnClickListener(v -> handleItemClick(getAdapterPosition()));
            deleteButton.setOnClickListener(v -> handleDeleteClick(getAdapterPosition()));
        }

        public void bind(ToDoList todolist) {
            titleTextView.setText(todolist.getTitle());
            dueDateTextView.setText(todolist.getDueDate());
            classTextView.setText(todolist.getCategoryName());
        }
    }

    private void handleItemClick(int position) {
        if (listener != null && position != RecyclerView.NO_POSITION) {
            listener.onItemClick(position);
        }
    }

    private void handleDeleteClick(int position) {
        if (listener != null && position != RecyclerView.NO_POSITION) {
            listener.onDeleteClick(position);
        }
    }
}

//VERSION 2:::
//package com.example.gt_scheduler.ui.todolist;
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
//public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ToDoListViewHolder> {
//
//    private List<ToDoList> todolists;
//    private OnItemClickListener listener;
//
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//
//        void onDeleteClick(int position);
//    }
//
//    public ToDoListAdapter(List<ToDoList> todolists, OnItemClickListener listener) {
//        this.todolists = todolists;
//        this.listener = listener;
//    }
//
//    @NonNull
//    @Override
//    public ToDoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_todolist, parent, false);
//        return new ToDoListViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ToDoListViewHolder holder, int position) {
//        ToDoList todolist = todolists.get(position);
//        holder.bind(todolist);
//    }
//
//    @Override
//    public int getItemCount() {
//        return todolists.size();
//    }
//
//    class ToDoListViewHolder extends RecyclerView.ViewHolder {
//        private TextView titleTextView;
//        private TextView dueDateTextView;
//        private TextView classTextView;
//        private View editButton;
//        private View deleteButton;
//
//        public ToDoListViewHolder(@NonNull View itemView) {
//            super(itemView);
//            titleTextView = itemView.findViewById(R.id.titleTextView);
//            dueDateTextView = itemView.findViewById(R.id.dueDateTextView);
//            classTextView = itemView.findViewById(R.id.categoryTextView);
//            editButton = itemView.findViewById(R.id.editButton);
//            deleteButton = itemView.findViewById(R.id.removeButton);
//
//            editButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            listener.onItemClick(position);
//                        }
//                    }
//                }
//            });
//
//            deleteButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            listener.onDeleteClick(position);
//                        }
//                    }
//                }
//            });
//        }
//
//        public void bind(ToDoList todolist) {
//            titleTextView.setText(todolist.getTitle());
//            dueDateTextView.setText(todolist.getDueDate());
//            classTextView.setText(todolist.getClassName());
//        }
//    }
//}




//VERSION 1:::
//package com.example.gt_scheduler.ui.todolist;
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
//import com.example.gt_scheduler.ui.home.Assignment;
//
//import java.util.List;
//
//public class AssignmentAdapter extends RecyclerView.Adapter<com.example.gt_scheduler.ui.todolist.ToDoListAdapter.ToDoListViewHolder> {
//
//    private List<ToDoList> todolists;
//    private com.example.gt_scheduler.ui.todolist.ToDoListAdapter.OnItemClickListener listener;
//
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//        void onDeleteClick(int position);
//    }
//
//    public ToDoListAdapter(List<ToDoList> todolists, com.example.gt_scheduler.ui.todolist.ToDoListAdapter.OnItemClickListener listener) {
//        this.todolists = todolists;
//        this.listener = listener;
//    }
//
//    @NonNull
//    @Override
//    public com.example.gt_scheduler.ui.todolist.ToDoListAdapter.ToDoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_todolist, parent, false);
//        return new com.example.gt_scheduler.ui.todolist.ToDoListAdapter.ToDoListViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull com.example.gt_scheduler.ui.todolist.ToDoListAdapter.ToDoListViewHolder holder, int position) {
//        ToDoList todolist = todolists.get(position);
//        holder.bind(todolist);
//    }
//
//    @Override
//    public int getItemCount() {
//        return todolists.size();
//    }
//
//    class ToDoListViewHolder extends RecyclerView.ViewHolder {
//        private TextView titleTextView;
//        private TextView dueDateTextView;
//        private TextView classTextView;
//        private View editButton;
//        private View deleteButton;
//
//        public ToDoListViewHolder(@NonNull View itemView) {
//            super(itemView);
//            titleTextView = itemView.findViewById(R.id.titleTextView);
//            dueDateTextView = itemView.findViewById(R.id.dueDateTextView);
//            categoryTextView = itemView.findViewById(R.id.categoryTextView);
//            editButton = itemView.findViewById(R.id.editButton);
//            deleteButton = itemView.findViewById(R.id.removeButton);
//
//            editButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            listener.onItemClick(position);
//                        }
//                    }
//                }
//            });
//
//            deleteButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            listener.onDeleteClick(position);
//                        }
//                    }
//                }
//            });
//        }
//
//        public void bind(TodoList todolist) {
//            titleTextView.setText(todolist.getTitle());
//            dueDateTextView.setText(todolist.getDueDate());
//            classTextView.setText(todolist.getClassName());
//        }
//    }
//}
