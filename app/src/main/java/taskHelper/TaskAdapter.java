package taskHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mohasaba.R;

public class TaskAdapter extends ListAdapter<Task, TaskAdapter.TaskHolder> {
    private OnItemClickListener listener;
    private static TaskManager taskManager = new TaskManager();

    public TaskAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<Task> DIFF_CALLBACK = new DiffUtil.ItemCallback<Task>() {
        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getTaskId().equals(newItem.getTaskId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return taskManager.areTheseSame(oldItem, newItem);
        }
    };

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_taskview,parent,false);
        return new TaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        Task currentTask = getItem(position);
        holder.taskTitle.setText(currentTask.getTitle());
        holder.taskDescription.setText(currentTask.getDescription());

        if(currentTask.getMaxProgress() != null) {
            holder.progressBar.setVisibility(View.VISIBLE);
            holder.progressBar.setMax(currentTask.getMaxProgress());
            holder.progressBar.setProgress(currentTask.getProgress());

            String text = currentTask.getProgress() + "/" + currentTask.getMaxProgress();
            holder.currentProgressTextView.setText(text);
            holder.progressUnit.setText(currentTask.getProgressUnit());

            if(currentTask.getTargetTime() != 0) {
                holder.targetTimeTextView.setText(currentTask.getTargetTime());
                holder.targetUnitTextView.setText(currentTask.getTargetUnit());
            } else {
                holder.taskPerTextView.setVisibility(View.GONE);
                holder.targetTimeTextView.setVisibility(View.GONE);
                holder.targetUnitTextView.setVisibility(View.GONE);
            }

        } else {
            holder.progressLayout.setVisibility(View.GONE);
        }


    }

    public Task getItemAt(int position) {
        return getItem(position);
    }

    class TaskHolder extends RecyclerView.ViewHolder {
        private TextView taskTitle, taskDescription;
        private ConstraintLayout progressLayout;
        private ProgressBar progressBar;
        private TextView currentProgressTextView, progressUnit, targetTimeTextView, targetUnitTextView;
        private TextView taskPerTextView;


        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            taskTitle = itemView.findViewById(R.id.taskTitleId);
            taskDescription = itemView.findViewById(R.id.taskDescriptionId);
            progressLayout = itemView.findViewById(R.id.progressParentLayoutId);
            progressBar = itemView.findViewById(R.id.progressBarId);
            currentProgressTextView = itemView.findViewById(R.id.taskCurrentProgressTextViewId);
            progressUnit = itemView.findViewById(R.id.taskProgressUnitTextViewId);
            targetTimeTextView = itemView.findViewById(R.id.taskTargetTimeId);
            targetUnitTextView = itemView.findViewById(R.id.taskTargetUnitId);
            taskPerTextView = itemView.findViewById(R.id.taskPerTextViewId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItemAt(position));
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemLongClick(getItemAt(position));
                    }
                    return true;
                }
            });
        }
    }

    public interface OnItemClickListener {/*Interface doesn't have any body
    this is declared when it is used. As clicking on an item will take to an specific activity of
    this task where all the elements will be same as this task parameter that's why we used an interface
    Here this task means on what we clicked and will be passed to mainActivity*/
        void onItemClick(Task task);
        void onItemLongClick(Task task);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
