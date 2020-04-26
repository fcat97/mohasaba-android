package com.example.mohasaba;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

import taskHelper.Task;
import taskHelper.Task2RecyclerViewAdapter;
import taskHelper.TaskViewModel;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    private TaskViewModel taskViewModel;
    FloatingActionButton floatingAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final Task2RecyclerViewAdapter adapter = new Task2RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        Objects.requireNonNull(getSupportActionBar()).hide(); /*Hides Action Bar*/

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapter.setTasks(tasks);
            }
        });

        floatingAddButton = findViewById(R.id.floatingAddButtonId);
        floatingAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivityForResult(intent,ADD_NOTE_REQUEST);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddTaskActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddTaskActivity.EXTRA_DESCRIPTION);

            Task newTask = new Task(title,description);
            taskViewModel.insert(newTask);
            Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task not Added", Toast.LENGTH_SHORT).show();
        }
    }
}
