package taskHelper;

public class TaskManager {
    public boolean areTheseSame(Task oldItem, Task newItem) {
        if(oldItem.getMaxProgress() != null && newItem.getMaxProgress() != null) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getMaxProgress().equals(newItem.getMaxProgress()) &&
                    oldItem.getProgress() == newItem.getProgress();
        } else if(oldItem.getMaxProgress() == null && newItem.getMaxProgress() == null) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription());
        } else {
            return false;
        }
    }

    public static void incrementProgress(Task task) {
        if (task.getMaxProgress() != null) {
            if (task.getProgress() < task.getMaxProgress()) {
                task.setProgress(task.getProgress() + 1);
            }
        }
    }
}
