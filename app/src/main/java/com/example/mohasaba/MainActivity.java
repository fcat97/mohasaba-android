package com.example.mohasaba;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
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
import taskHelper.TaskAdapter;
import taskHelper.TaskViewModel;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;
    private TaskViewModel taskViewModel;
    FloatingActionButton floatingAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();/*Hides Action Bar*/

        final RecyclerView recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final TaskAdapter adapter = new TaskAdapter();
        recyclerView.setAdapter(adapter);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskViewModel.getAllMainTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapter.submitList(tasks);
            }
        });

        /*Listener for EditActivity*/
        adapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Task task) {
                Intent intent = new Intent(MainActivity.this,AddTaskActivity.class);
                intent.putExtra(AddTaskActivity.EXTRA_TITLE, task.getTitle());
                intent.putExtra(AddTaskActivity.EXTRA_DESCRIPTION, task.getDescription());
                intent.putExtra(AddTaskActivity.EXTRA_ID, task.getTaskId());
                if(task.getMaxProgress() != null) {
                    intent.putExtra(AddTaskActivity.EXTRA_MAX_PROGRESS, String.valueOf(task.getMaxProgress()));
                    intent.putExtra(AddTaskActivity.EXTRA_MAX_PROGRESS_UNIT,task.getProgressUnit());
                }

                startActivityForResult(intent,EDIT_NOTE_REQUEST);
            }
        });

        /*Swipe Left to Delete Feature*/
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                taskViewModel.delete(adapter.getItemAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

        /*Floating Add Button and its Listener*/
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

            Task newTask = new Task(title);
            newTask.setDescription(description);

            if(data.hasExtra(AddTaskActivity.EXTRA_MAX_PROGRESS)) {
                String maxProgress = data.getStringExtra(AddTaskActivity.EXTRA_MAX_PROGRESS);
                String maxProgressUnit = data.getStringExtra(AddTaskActivity.EXTRA_MAX_PROGRESS_UNIT);
                newTask.setMaxProgress(Integer.parseInt(maxProgress));
                newTask.setProgressUnit(maxProgressUnit);
            }

            taskViewModel.insert(newTask);
            Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show();
        } else if(requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            long id = data.getLongExtra(AddTaskActivity.EXTRA_ID,-1);
            if (id == -1) {
                Toast.makeText(this, "Data not updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String title = data.getStringExtra(AddTaskActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddTaskActivity.EXTRA_DESCRIPTION);

            Task newTask = new Task(title);
            newTask.setTaskId(id);
            newTask.setDescription(description);

            if(data.hasExtra(AddTaskActivity.EXTRA_MAX_PROGRESS)) {
                String maxProgress = data.getStringExtra(AddTaskActivity.EXTRA_MAX_PROGRESS);
                String maxProgressUnit = data.getStringExtra(AddTaskActivity.EXTRA_MAX_PROGRESS_UNIT);
                newTask.setMaxProgress(Integer.parseInt(maxProgress));
                newTask.setProgressUnit(maxProgressUnit);
            }
            taskViewModel.update(newTask);
            Toast.makeText(this, "Task Updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task not Added", Toast.LENGTH_SHORT).show();
        }
    }
}
