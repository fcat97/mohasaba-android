package com.example.mohasaba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE =
            "com.example.mohasaba.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.mohasaba.EXTRA_DESCRIPTION";

    private TextView textViewTitle;
    private TextView textViewDescription;

    private ImageButton saveButton;
    private ImageButton cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        textViewTitle = findViewById(R.id.addTaskTitleID);
        textViewDescription = findViewById(R.id.addTaskDescriptionId);
        saveButton = findViewById(R.id.addTaskSaveButtonId);
        cancelButton = findViewById(R.id.addTaskCancelButtonId);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddTaskActivity.this, "Task not added!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    public void addTask() {
        String title = textViewTitle.getText().toString();
        String description = textViewDescription.getText().toString();

        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Please Insert a Title", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);

        setResult(RESULT_OK,data);
        finish();
    }

}
