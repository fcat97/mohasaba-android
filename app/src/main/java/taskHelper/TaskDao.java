package taskHelper;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void insert(Task task);

    @Insert
    void insertStat(TaskStat taskStat);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("DELETE FROM task_table")
    void deleteAllTasks();

    @Query("SELECT * FROM task_table WHERE creator_ID IS NULL ORDER BY task_ID ASC")
    LiveData<List<Task>> getAllMainTasks();

    @Query("SELECT * FROM task_table WHERE task_ID in(" +
            "SELECT owner_ID FROM task_statistics WHERE status IS 0) ORDER BY task_ID ASC")
    LiveData<List<Task>> getAllInCompletedTasks();

    @Query("SELECT * FROM task_table WHERE task_ID in(" +
            "SELECT owner_ID FROM task_statistics WHERE status IS 1) ORDER BY task_ID ASC")
    LiveData<List<Task>> getAllCompletedTasks();


    @Query("SELECT * FROM task_table WHERE creator_ID IS :creatorId ORDER BY task_ID ASC")
    LiveData<List<Task>> getSubTasksOf(Long creatorId);
}
