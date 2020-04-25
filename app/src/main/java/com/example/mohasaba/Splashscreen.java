package com.example.mohasaba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import java.util.Objects;

public class Splashscreen extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = findViewById(R.id.ProgressBarId);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doProgress();
                startApp();
            }
        });
        thread.start();
    }

    public void doProgress() {
        for (progress = 0; progress < 140; progress = progress + 10) {
            try {
                Thread.sleep(100);
                progressBar.setProgress(progress);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void startApp(){
        Intent intent = new Intent(Splashscreen.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
