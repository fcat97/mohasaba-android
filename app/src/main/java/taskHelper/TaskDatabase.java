package taskHelper;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Task.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {
    private static TaskDatabase databaseInstance;

    public abstract TaskDao taskDao();

    public static synchronized TaskDatabase/*is the return type*/ getInstance(Context context){
        if(databaseInstance == null){
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    TaskDatabase.class, "task_database")
                    .fallbackToDestructiveMigration()
                    /*.addCallback(roomCallback)*/
                    .build();
        }
        return databaseInstance;
    }

    /*private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(databaseInstance).execute();
        }
    };


    private static class PopulateAsyncTask extends AsyncTask<Void, Void, Void> {
        private TaskDao taskDao;
        private PopulateAsyncTask(TaskDatabase db) {
            taskDao = db.taskDao();*//*As the 'NoteDatabase instance' is a static object,*//*
            *//*we don't need to create a instance to use it,
            we can directly use the object*//*
        }
        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.insert(new Task("title 1", "Description 1"));
            taskDao.insert(new Task("title 2", "Description 2"));
            taskDao.insert(new Task("title 3", "Description 3"));
            return null;
        }
    }*/
}
