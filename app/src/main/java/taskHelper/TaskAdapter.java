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
            if(oldItem.getMaxProgress() != null && newItem.getMaxProgress() != null) {
                return oldItem.getTitle().equals(newItem.getTitle()) &&
                        oldItem.getDescription().equals(newItem.getDescription()) &&
                        oldItem.getMaxProgress().equals(newItem.getMaxProgress());
            } else if(oldItem.getMaxProgress() == null && newItem.getMaxProgress() == null) {
                return oldItem.getTitle().equals(newItem.getTitle()) &&
                        oldItem.getDescription().equals(newItem.getDescription());
            } else {
                return false;
            }
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
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Task task);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
