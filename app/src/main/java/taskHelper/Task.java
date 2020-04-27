package taskHelper;

import android.graphics.Color;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_table")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String description;
    private int currentProgress;

    /*Constructor for this task object
    * Note that here is no constructor for id variable*/
    public Task(String title, String description ) {
        this.title = title;
        this.description = description;
        this.currentProgress = -1;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
    }

    public void setId(int id) {
        this.id = id;
    }
}
