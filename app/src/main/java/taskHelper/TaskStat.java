package taskHelper;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_statistics",
        foreignKeys = @ForeignKey(entity = Task.class,
                parentColumns = "task_ID", childColumns = "owner_ID", onDelete = ForeignKey.CASCADE))
public class TaskStat {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "owner_ID")
    private long ownerId;
    private boolean status;

    public TaskStat(long ownerId) {
        this.ownerId = ownerId;
        this.status = false; /*Initiate as not done*/
    }

    public int getId() {
        return id;
    }
    public long getOwnerId() {
        return ownerId;
    }
    public boolean isStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}
