package taskHelper;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DataConverter {
    public static String fromTaskStat(TaskStat taskStat) {
        if (taskStat == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<TaskStat>(){}.getType();
        return gson.toJson(taskStat,type);
    }

    @TypeConverter
    public static TaskStat toTaskStat(String json) {
        if (json == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<TaskStat>(){}.getType();
        return gson.fromJson(json, type);
    }

    @TypeConverter
    public static String fromTag(List<String> tags) {
        if (tags == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>(){}.getType();
        return gson.toJson(tags,type);
    }

    @TypeConverter
    public static List<String> toTag(String json) {
        if (json == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>(){}.getType();
        return gson.fromJson(json, type);
    }
}
