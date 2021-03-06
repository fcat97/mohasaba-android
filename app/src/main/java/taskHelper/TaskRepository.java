package taskHelper;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {
    private TaskDao taskDao;

    public TaskRepository (Application/*is similar to Context*/application){
        TaskDatabase database = TaskDatabase.getInstance(application);
        taskDao = database.taskDao();
    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    public void insert(Task task) {
        new InsertTaskAsyncTask(taskDao).execute(task);
    }
    public void update(Task task) {
        new UpdateTaskAsyncTask(taskDao).execute(task);
    }
    public void delete(Task task) {
        new DeleteTaskAsyncTask(taskDao).execute(task);
    }
    public void deleteAllTasks() {
        new DeleteAllTasksAsyncTask(taskDao).execute();
    }

    private static class InsertTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao taskDao;
        private InsertTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }
        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.insert(tasks[0]);
            return null;
        }
    }
    private static class DeleteTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao taskDao;
        private DeleteTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }
        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.delete(tasks[0]);
            return null;
        }
    }
    private static class UpdateTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao taskDao;
        private UpdateTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }
        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.update(tasks[0]);
            return null;
        }
    }
    private static class DeleteAllTasksAsyncTask extends AsyncTask<Void, Void, Void> {
        private TaskDao taskDao;
        private DeleteAllTasksAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.deleteAllTasks();
            return null;
        }
    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    public void insertStat(TaskStat taskStat) {
        new InsertTaskStatAsyncTask(taskDao).execute(taskStat);
    }
    public void updateStat(TaskStat taskStat) {
        new UpdateStatAsyncTask(taskDao).execute(taskStat);
    }
    public TaskStat getTaskStatOf(Task task) {
        return taskDao.getTaskStatOf(task.getTaskId());
    }

    private static class InsertTaskStatAsyncTask extends AsyncTask<TaskStat, Void, Void> {
        private TaskDao taskDao;
        private InsertTaskStatAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }
        @Override
        protected Void doInBackground(TaskStat... taskStats) {
            taskDao.insertStat(taskStats[0]);
            return null;
        }
    }
    private static class UpdateStatAsyncTask extends AsyncTask<TaskStat, Void, Void> {
        private TaskDao taskDao;
        private UpdateStatAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }
        @Override
        protected Void doInBackground(TaskStat... taskStats) {
            taskDao.updateStat(taskStats[0]);
            return null;
        }
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    public LiveData<List<Task>> getAllMainTasks() {
        return taskDao.getAllMainTasks();
    }
    public LiveData<List<Task>> getAllInCompletedTasks() {
        return taskDao.getAllInCompletedTasks();
    }
    public LiveData<List<Task>> getAllCompletedTasks() {
        return taskDao.getAllCompletedTasks();
    }
    public LiveData<List<Task>> getSubTasksOf(Task task) {
        return taskDao.getSubTasksOf(task.getTaskId());
    }
}
