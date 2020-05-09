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

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("DELETE FROM task_table")
    void deleteAllTasks();

    @Query("SELECT * FROM task_table WHERE creator_ID IS NULL ORDER BY task_ID ASC")
    LiveData<List<Task>> getAllMainTasks();

    @Query("SELECT * FROM task_table WHERE creator_ID IS :creatorId ORDER BY task_ID ASC")
    LiveData<List<Task>> getSubTasksOf(Long creatorId);
}
