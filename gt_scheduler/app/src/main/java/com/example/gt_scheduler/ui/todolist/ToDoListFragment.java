package com.example.gt_scheduler.ui.todolist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gt_scheduler.databinding.FragmentTodolistBinding;

public class ToDoListFragment extends Fragment {

    private FragmentTodolistBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ToDoListViewModel toDoListViewModelViewModel =
                new ViewModelProvider(this).get(ToDoListViewModel.class);

        binding = FragmentTodolistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTodolist;
        toDoListViewModelViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}