package taskHelper;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository taskRepository;
    private LiveData<List<Task>> allMainTasks;
    private LiveData<List<Task>> allInCompletedTasks;
    private LiveData<List<Task>> allCompletedTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskRepository = new TaskRepository(application);
        allMainTasks = taskRepository.getAllMainTasks();
        allInCompletedTasks = taskRepository.getAllInCompletedTasks();
        allCompletedTasks = taskRepository.getAllCompletedTasks();
    }

    public void insert(Task task) {
        taskRepository.insert(task);
        taskRepository.insertStat(new TaskStat(task.getTaskId()));
    }
    public void update(Task task) {
        taskRepository.update(task);
    }
    public void delete(Task task) {
        taskRepository.delete(task);
    }
    public void deleteAllTasks() {
        taskRepository.deleteAllTasks();
    }
    public LiveData<List<Task>> getSubTasksOf(Task task) {
        return taskRepository.getSubTasksOf(task);
    }

    public LiveData<List<Task>> getAllMainTasks() {
        return allMainTasks;
    }
    public LiveData<List<Task>> getAllInCompletedTasks() {
        return allInCompletedTasks;
    }
    public LiveData<List<Task>> getAllCompletedTasks() {
        return allCompletedTasks;
    }

}
