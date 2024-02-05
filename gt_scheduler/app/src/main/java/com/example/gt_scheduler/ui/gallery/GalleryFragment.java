package com.example.gt_scheduler.ui.gallery;

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
import com.example.gt_scheduler.databinding.FragmentGalleryBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment implements ExamAdapter.OnItemClickListener { // exams

    private FragmentGalleryBinding binding;
    private List<Exam> exams = new ArrayList<>(); // list of exams
    private ExamAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FloatingActionButton examFab = root.findViewById(R.id.examFab);
        examFab.setOnClickListener(view -> showAddExamDialog());

        RecyclerView recyclerView = binding.recyclerView;

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    private void showAddExamDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_exam, null);

        // Initialize input fields
        EditText examClassEditText = dialogView.findViewById(R.id.examClassEditText);
        EditText examDateEditText = dialogView.findViewById(R.id.examDateEditText);

        builder.setView(dialogView)
                .setTitle("Add Exam")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int id) {
                    // get user input
                    String examClass = examClassEditText.getText().toString();
                    String examDate = examDateEditText.getText().toString();

                    // create new exam
                    Exam exam = new Exam(examClass, examDate);

                    exams.add(exam);
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
        Exam exam = exams.get(position);
        showEditExamDialog(exam, position);
    }

    @Override
    public void onDeleteClick(int position) {
        exams.remove(position);
        updateUI();
    }

    private void showEditExamDialog(Exam exam, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_exam, null);

        EditText examClassEditText = dialogView.findViewById(R.id.examClassEditText);
        EditText examDateEditText = dialogView.findViewById(R.id.examDateEditText);

        examClassEditText.setText(exam.getClassName());
        examDateEditText.setText(exam.getDate());

        builder.setView(dialogView)
                .setTitle("Edit Exam")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // update exam
                        exam.setDate(examDateEditText.getText().toString());
                        exam.setClassName(examClassEditText.getText().toString());

                        exams.set(position, exam);
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