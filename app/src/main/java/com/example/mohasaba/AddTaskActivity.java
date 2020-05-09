package com.example.mohasaba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Objects;

public class AddTaskActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.example.mohasaba.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.mohasaba.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.mohasaba.EXTRA_DESCRIPTION";
    public static final String EXTRA_MAX_PROGRESS =
            "com.example.mohasaba.EXTRA_MAX_PROGRESS";
    public static final String EXTRA_MAX_PROGRESS_UNIT =
            "com.example.mohasaba.EXTRA_MAX_PROGRESS_UNIT";

    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText maxProgressEditText;
    private EditText maxProgressUnitEditText;
    private LinearLayout targetLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Objects.requireNonNull(getSupportActionBar()).hide();

        titleEditText = findViewById(R.id.addTaskTitleID);
        descriptionEditText = findViewById(R.id.addTaskDescriptionId);
        maxProgressEditText = findViewById(R.id.addTaskTargetEditTextId);
        maxProgressUnitEditText = findViewById(R.id.addTaskTargetUnitEditTextId);
        targetLL = findViewById(R.id.addTaskTargetLLId);

        Button saveButton = findViewById(R.id.addTaskSaveButtonId);
        Button cancelButton = findViewById(R.id.addTaskCancelButtonId);
        Button addFeatureButton = findViewById(R.id.addTaskNewFeatureId);

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

        addFeatureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                targetLL.setVisibility(View.VISIBLE);
            }
        });

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            titleEditText.setText(intent.getStringExtra(EXTRA_TITLE));
            descriptionEditText.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
        }
        if(intent.hasExtra(EXTRA_MAX_PROGRESS)){
            targetLL.setVisibility(View.VISIBLE);
            maxProgressEditText.setText(intent.getStringExtra(EXTRA_MAX_PROGRESS));
            maxProgressUnitEditText.setText(intent.getStringExtra(EXTRA_MAX_PROGRESS_UNIT));
        }
    }

    public void addTask() {
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String maxProgress = maxProgressEditText.getText().toString();
        String maxProgressUnit = maxProgressUnitEditText.getText().toString();

        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Please Insert a Title", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);

        long id = getIntent().getLongExtra(EXTRA_ID,-1);
        if(id != -1) {
            data.putExtra(EXTRA_ID,id);
        }

        if(!maxProgress.trim().isEmpty() && !maxProgressUnit.trim().isEmpty()){
            data.putExtra(EXTRA_MAX_PROGRESS,maxProgress);
            data.putExtra(EXTRA_MAX_PROGRESS_UNIT,maxProgressUnit);
        }

        setResult(RESULT_OK,data);
        finish();
    }

}
