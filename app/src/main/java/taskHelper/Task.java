package taskHelper;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Entity(tableName = "task_table",
        foreignKeys = @ForeignKey(entity = Task.class, parentColumns = "task_ID",
                childColumns = "creator_ID",
                onDelete = ForeignKey.CASCADE))

public class Task {

    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>VARIABLE DECLARATION ZONE*/
    @PrimaryKey
    @ColumnInfo(name = "task_ID")
    private Long taskId;

    @ColumnInfo(name = "creator_ID")
    private Long creatorId;
    private String title;
    private String description;
    private int progress;
    private Integer maxProgress;
    private String progressUnit;
    private int targetTime;
    private String targetUnit;

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<VARIABLE DECLARATION ZONE*/

    public Task(String title){
        this.taskId = IdGenerator.generate();
        this.title = title;
    }

    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>ALL THE GETTERS*/
    public Long getTaskId() {
        return taskId;
    }
    public Long getCreatorId() {
        return creatorId;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public int getProgress() {
        return progress;
    }
    public Integer getMaxProgress() {
        return maxProgress;
    }
    public String getProgressUnit() {
        return progressUnit;
    }
    public int getTargetTime() {
        return targetTime;
    }
    public String getTargetUnit() {
        return targetUnit;
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<ALL THE GETTERS*/

    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>ALL THE SETTERS*/
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setProgress(int progress) {
        this.progress = progress;
    }
    public void setMaxProgress(Integer maxProgress) {
        this.maxProgress = maxProgress;
    }
    public void setProgressUnit(String progressUnit) {
        this.progressUnit = progressUnit;
    }
    public void setTargetTime(int targetTime) {
        this.targetTime = targetTime;
    }
    public void setTargetUnit(String targetUnit) {
        this.targetUnit = targetUnit;
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<ALL THE SETTERS*/

    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>ADDITIONAL METHODS ZONE*/
    public Task createSubTask(String title){
        Task newTask = new Task(title);
        newTask.setCreatorId(this.getTaskId());
        return newTask;
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<ADDITIONAL METHODS ZONE*/
}
