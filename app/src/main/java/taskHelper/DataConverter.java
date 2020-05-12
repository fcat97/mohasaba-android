package taskHelper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class DataConverter {
    public static String fromTaskStat(TaskStat taskStat) {
        if (taskStat == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<TaskStat>(){}.getType();
        return gson.toJson(taskStat,type);
    }

    public static TaskStat toTaskStat(String json) {
        if (json == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<TaskStat>(){}.getType();
        return gson.fromJson(json, type);
    }
}
