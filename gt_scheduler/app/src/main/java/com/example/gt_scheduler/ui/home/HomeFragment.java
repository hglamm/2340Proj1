package com.example.gt_scheduler.ui.home;

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
import com.example.gt_scheduler.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements AssignmentAdapter.OnItemClickListener {

    private FragmentHomeBinding binding;
    private List<Assignment> assignments = new ArrayList<>(); // List to store assignments
    private AssignmentAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(view -> showAddAssignmentDialog());

        RecyclerView recyclerView = binding.recyclerView;
        adapter = new AssignmentAdapter(assignments, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    private void showAddAssignmentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_assignment, null);

        // Initialize input fields
        EditText titleEditText = dialogView.findViewById(R.id.titleEditText);
        EditText dueDateEditText = dialogView.findViewById(R.id.dueDateEditText);
        EditText classEditText = dialogView.findViewById(R.id.classEditText);

        builder.setView(dialogView)
                .setTitle("Add Assignment")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Get user input from the dialog
                        String title = titleEditText.getText().toString();
                        String dueDate = dueDateEditText.getText().toString();
                        String className = classEditText.getText().toString();

                        // Create a new assignment object
                        Assignment assignment = new Assignment(title, dueDate, className);

                        // Add the assignment to the list
                        assignments.add(assignment);

                        // Update the UI to display the added assignment
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
        Assignment assignment = assignments.get(position);
        showEditAssignmentDialog(assignment, position);
    }

    @Override
    public void onDeleteClick(int position) {
        // Handle item click for removing assignment
        assignments.remove(position);
        updateUI();
    }

    private void showEditAssignmentDialog(Assignment assignment, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_assignment, null);

        EditText titleEditText = dialogView.findViewById(R.id.titleEditText);
        EditText dueDateEditText = dialogView.findViewById(R.id.dueDateEditText);
        EditText classEditText = dialogView.findViewById(R.id.classEditText);

        // Pre-populate the EditText fields with existing assignment details
        titleEditText.setText(assignment.getTitle());
        dueDateEditText.setText(assignment.getDueDate());
        classEditText.setText(assignment.getClassName());

        builder.setView(dialogView)
                .setTitle("Edit Assignment")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Update the assignment with new details
                        assignment.setTitle(titleEditText.getText().toString());
                        assignment.setDueDate(dueDateEditText.getText().toString());
                        assignment.setClassName(classEditText.getText().toString());

                        // Update the UI to reflect the changes
                        assignments.set(position, assignment);
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


//package com.example.gt_scheduler.ui.home;
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
//import com.example.gt_scheduler.databinding.FragmentHomeBinding;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class HomeFragment extends Fragment {
//
//    private FragmentHomeBinding binding;
//    private List<Assignment> assignments = new ArrayList<>(); // List to store assignments
//    private AssignmentAdapter adapter;
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
////        HomeViewModel homeViewModel =
////                new ViewModelProvider(this).get(HomeViewModel.class);
//
//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
////        final TextView textView = binding.textHome;
////        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//
//        // Find and set up the FloatingActionButton click listener
//        FloatingActionButton fab = root.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showAddAssignmentDialog();
//            }
//        });
//
//        return root;
//    }
//
//    private void showAddAssignmentDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        LayoutInflater inflater = requireActivity().getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_add_assignment, null);
//
//        // Initialize input fields
//        EditText titleEditText = dialogView.findViewById(R.id.titleEditText);
//        EditText dueDateEditText = dialogView.findViewById(R.id.dueDateEditText);
//        EditText classEditText = dialogView.findViewById(R.id.classEditText);
//
//        builder.setView(dialogView)
//                .setTitle("Add Assignment")
//                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        // Get user input from the dialog
//                        String title = titleEditText.getText().toString();
//                        String dueDate = dueDateEditText.getText().toString();
//                        String className = classEditText.getText().toString();
//
//                        // Create a new assignment object
//                        Assignment assignment = new Assignment(title, dueDate, className);
//
//                        // Add the assignment to the list
//                        assignments.add(assignment);
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
//    }
//
//    private void updateUI() {
//        // Update the RecyclerView or TextView to display the list of assignments
//        // For example, if using RecyclerView:
//        RecyclerView recyclerView = binding.recyclerView;
//        AssignmentAdapter adapter = new AssignmentAdapter(assignments);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//    }
//
//
//
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
//}