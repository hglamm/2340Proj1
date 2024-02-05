package com.example.gt_scheduler.ui.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gt_scheduler.R;
import com.example.gt_scheduler.databinding.FragmentTodolistBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ToDoListFragment extends Fragment implements ToDoListAdapter.OnItemClickListener {

    private FragmentTodolistBinding binding;
    private List<ToDoList> todolists = new ArrayList<>();
    private ToDoListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTodolistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(view -> showAddToDoListDialog());

        RecyclerView recyclerView = binding.recyclerView;
        adapter = new ToDoListAdapter(todolists, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    private void showAddToDoListDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_todolist, null);

        // Initialize input fields
        EditText titleEditText = dialogView.findViewById(R.id.titleEditText);
        EditText dueDateEditText = dialogView.findViewById(R.id.dueDateEditText);
        EditText categoryEditText = dialogView.findViewById(R.id.categoryEditText);

        builder.setView(dialogView)
                .setTitle("Add ToDo List")
                .setPositiveButton("Add", (dialog, id) -> {
                    // Get user input from the dialog
                    String title = titleEditText.getText().toString();
                    String dueDate = dueDateEditText.getText().toString();
                    String categoryName = categoryEditText.getText().toString();

                    // Create a new ToDoList object
                    ToDoList todolist = new ToDoList(title, dueDate, categoryName);

                    // Add the ToDoList to the list
                    todolists.add(todolist);

                    // Update the UI to display the added ToDoList
                    updateUI();
                })
                .setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void updateUI() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(int position) {
        // Handle item click for editing assignment details
        ToDoList todolist = todolists.get(position);
        showEditToDoListDialog(todolist, position);
    }

    @Override
    public void onDeleteClick(int position) {
        // Handle item click for removing assignment
        todolists.remove(position);
        updateUI();
    }

    private void showEditToDoListDialog(ToDoList todolist, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_todolist, null);

        EditText titleEditText = dialogView.findViewById(R.id.titleEditText);
        EditText dueDateEditText = dialogView.findViewById(R.id.dueDateEditText);
        EditText categoryEditText = dialogView.findViewById(R.id.categoryEditText);

        // Pre-populate the EditText fields with existing assignment details
        titleEditText.setText(todolist.getTitle());
        dueDateEditText.setText(todolist.getDueDate());
        categoryEditText.setText(todolist.getCategoryName());

        builder.setView(dialogView)
                .setTitle("Edit ToDo List")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Update the assignment with new details
                        todolist.setTitle(titleEditText.getText().toString());
                        todolist.setDueDate(dueDateEditText.getText().toString());
                        todolist.setCategoryName(categoryEditText.getText().toString());

                        // Update the UI to reflect the changes
                        todolists.set(position, todolist);
                        updateUI();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

//package com.example.gt_scheduler.ui.todolist;
//
//
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.gt_scheduler.R;
//import com.example.gt_scheduler.databinding.FragmentTodolistBinding;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ToDoListFragment extends Fragment implements ToDoListAdapter.OnItemClickListener{
//
//    private FragmentTodolistBinding binding;
//    private List<ToDoList> todolists = new ArrayList<>(); // List to store assignments
//    private ToDoListAdapter adapter;
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
////        ToDoListViewModel toDoListViewModelViewModel =
////                new ViewModelProvider(this).get(ToDoListViewModel.class);
//
//        binding = FragmentTodolistBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        FloatingActionButton fab = root.findViewById(R.id.fab);
//        fab.setOnClickListener(view -> showAddToDoListDialog());
//
//        RecyclerView recyclerView = binding.recyclerView;
//        adapter = new ToDoListAdapter(todolists, this);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
////        final TextView textView = binding.textTodolist;
////        toDoListViewModelViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;
//    }
//
//    private void showAddToDoListDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        LayoutInflater inflater = requireActivity().getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_add_todolist, null);
//
//        // Initialize input fields
//        EditText titleEditText = dialogView.findViewById(R.id.titleEditText);
//        EditText dueDateEditText = dialogView.findViewById(R.id.dueDateEditText);
//        EditText categoryEditText = dialogView.findViewById(R.id.categoryEditText);
//
//        builder.setView(dialogView)
//                .setTitle("Add ToDo List")
//                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        // Get user input from the dialog
//                        String title = titleEditText.getText().toString();
//                        String dueDate = dueDateEditText.getText().toString();
//                        String className = categoryEditText.getText().toString();
//
//                        // Create a new assignment object
//                        ToDoList todolist = new ToDoList(title, dueDate, className);
//
//                        // Add the assignment to the list
//                        todolists.add(todolist);
//
//                        // Update the UI to display the added assignment
//                        updateUI();
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
//        AlertDialog dialog = builder.create();
//        dialog.show();
//
//    }
//
//    private void updateUI() {
//        adapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
//
//    @Override
//    public void onItemClick(int position) {
//        // Handle item click for editing assignment details
//        ToDoList assignment = todolists.get(position);
//        showEditToDoListDialog(todolist, position);
//    }
//
//    @Override
//    public void onDeleteClick(int position) {
//        // Handle item click for removing assignment
//        todolists.remove(position);
//        updateUI();
//    }
//
//    private void showEditToDoListDialog(ToDoList todolist, int position) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        LayoutInflater inflater = requireActivity().getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_add_todolist, null);
//
//        EditText titleEditText = dialogView.findViewById(R.id.titleEditText);
//        EditText dueDateEditText = dialogView.findViewById(R.id.dueDateEditText);
//        EditText categoryEditText = dialogView.findViewById(R.id.categoryEditText);
//
//        // Pre-populate the EditText fields with existing assignment details
//        titleEditText.setText(todolist.getTitle());
//        dueDateEditText.setText(todolist.getDueDate());
//        categoryEditText.setText(todolist.getClassName());
//
//        builder.setView(dialogView)
//                .setTitle("Edit ToDo List")
//                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        // Update the assignment with new details
//                        todolist.setTitle(titleEditText.getText().toString());
//                        todolist.setDueDate(dueDateEditText.getText().toString());
//                        todolist.setClassName(categoryEditText.getText().toString());
//
//                        // Update the UI to reflect the changes
//                        todolists.set(position, todolist);
//                        updateUI();
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }
//}