package com.example.mohasaba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Objects;

public class AddTaskActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.example.mohasaba.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.mohasaba.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.mohasaba.EXTRA_DESCRIPTION";

    private EditText titleEditText;
    private EditText descriptionEditText;

    private ImageButton saveButton;
    private ImageButton cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Objects.requireNonNull(getSupportActionBar()).hide();

        titleEditText = findViewById(R.id.addTaskTitleID);
        descriptionEditText = findViewById(R.id.addTaskDescriptionId);
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

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            titleEditText.setText(intent.getStringExtra(EXTRA_TITLE));
            descriptionEditText.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
        }
    }

    public void addTask() {
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Please Insert a Title", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);

        int id = getIntent().getIntExtra(EXTRA_ID,-1);
        if(id != -1) {
            data.putExtra(EXTRA_ID,id);
        }

        setResult(RESULT_OK,data);
        finish();
    }

}
