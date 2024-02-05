package com.example.gt_scheduler.ui.slideshow;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gt_scheduler.R;
import com.example.gt_scheduler.databinding.FragmentSlideshowBinding;
import com.example.gt_scheduler.ui.gallery.Exam;
import com.example.gt_scheduler.ui.gallery.ExamAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment implements ClassAdapter.OnItemClickListener {

    private FragmentSlideshowBinding binding;
    private List<Class> classes = new ArrayList<>();
    private ClassAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FloatingActionButton classFab = root.findViewById(R.id.classFab);
        classFab.setOnClickListener(view -> showAddClassDialog());

        RecyclerView recyclerView = binding.recyclerView;
        adapter = new ClassAdapter(classes, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    private void showAddClassDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_class, null);

        // Initialize input fields
        EditText classNameEditText = dialogView.findViewById(R.id.classNameEditText);
        EditText instructorEditText = dialogView.findViewById(R.id.instructorEditText);
        EditText daysEditText = dialogView.findViewById(R.id.daysEditText);
        EditText timeEditText = dialogView.findViewById(R.id.timeEditText);
        EditText locationEditText = dialogView.findViewById(R.id.locationEditText);

        builder.setView(dialogView)
                .setTitle("Add Class")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // get user input
                        String className = classNameEditText.getText().toString();
                        String instructor = instructorEditText.getText().toString();
                        String days = daysEditText.getText().toString();
                        String time = timeEditText.getText().toString();
                        String location = locationEditText.getText().toString();

                        // create new class
                        Class course = new Class(className, instructor, days, time, location);

                        classes.add(course);
                        updateUI();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {dialog.cancel();}
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
        // handle item click for editing exam details
        Class course = classes.get(position);
        showEditClassDialog(course, position);
    }

    @Override
    public void onDeleteClick(int position) {
        classes.remove(position);
        updateUI();
    }

    private void showEditClassDialog(Class course, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_class, null);

        EditText classNameEditText = dialogView.findViewById(R.id.classNameEditText);
        EditText instructorEditText = dialogView.findViewById(R.id.instructorEditText);
        EditText daysEditText = dialogView.findViewById(R.id.daysEditText);
        EditText timeEditText = dialogView.findViewById(R.id.timeEditText);
        EditText locationEditText = dialogView.findViewById(R.id.locationEditText);

        classNameEditText.setText(course.getClassName());
        instructorEditText.setText(course.getInstructor());
        daysEditText.setText(course.getDays());
        timeEditText.setText(course.getTime());
        locationEditText.setText(course.getLocation());

        builder.setView(dialogView)
                .setTitle("Edit Class")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // update exam
                        course.setClassName(classNameEditText.getText().toString());
                        course.setInstructor(instructorEditText.getText().toString());
                        course.setDays(daysEditText.getText().toString());
                        course.setTime(timeEditText.getText().toString());
                        course.setLocation(locationEditText.getText().toString());

                        classes.set(position, course);
                        updateUI();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {dialog.cancel();}
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}