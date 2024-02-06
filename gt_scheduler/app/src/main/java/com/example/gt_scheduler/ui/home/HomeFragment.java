package com.example.gt_scheduler.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

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
    private SortingOption sortByValue;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FloatingActionButton fab = root.findViewById(R.id.fab);
        FloatingActionButton sortByFab = root.findViewById(R.id.sortByFab);
        sortByFab.setOnClickListener(view -> showSortAssignmentDialog());
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
        EditText classEditText = dialogView.findViewById(R.id.classEditText);
        EditText dueDateEditText = dialogView.findViewById(R.id.dueDateEditText);

        // Pre-populate the EditText fields with existing assignment details
        titleEditText.setText(assignment.getTitle());
        classEditText.setText(assignment.getClassName());
        dueDateEditText.setText(assignment.getDueDate());


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

    private void showSortAssignmentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_sort_assignments, null);

        RadioButton sortByClass = dialogView.findViewById(R.id.sortByClass);
        RadioButton sortByDueDate = dialogView.findViewById(R.id.sortByDueDate);

        sortByClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortByValue = SortingOption.COURSE;
                updateUI();
            }
        });

        sortByDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortByValue = SortingOption.DUEDATE;
                updateUI();
            }
        });

        builder.setView(dialogView)
                .setTitle("Sort By:")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (sortByValue == SortingOption.DUEDATE) {
                            // sort assignments by due date
                            for (int i = 0; i < assignments.size(); i++) {
                                String min = assignments.get(i).getDueDate();
                                int index = i;
                                for (int j = i + 1; j < assignments.size(); j++) {
                                    if (min.compareTo(assignments.get(j).getDueDate()) > 0) {
                                        min = assignments.get(j).getDueDate();
                                        index = j;
                                    }
                                }
                                Assignment temp = assignments.get(index);
                                assignments.set(index, assignments.get(i));
                                assignments.set(i, temp);
                            }
                        } else if (sortByValue == SortingOption.COURSE) {
                            // sort assignments by class
                            for (int i = 0; i < assignments.size(); i++) {
                                String min = assignments.get(i).getClassName();
                                int index = i;
                                for (int j = i + 1; j < assignments.size(); j++) {
                                    if (min.compareTo(assignments.get(j).getClassName()) > 0) {
                                        min = assignments.get(j).getClassName();
                                        index = j;
                                    }
                                }
                                Assignment temp = assignments.get(index);
                                assignments.set(index, assignments.get(i));
                                assignments.set(i, temp);
                            }
                        }

                        updateUI();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}