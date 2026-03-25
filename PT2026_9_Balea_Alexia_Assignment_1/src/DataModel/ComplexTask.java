package DataModel;

import java.util.ArrayList;
import java.util.List;

public final class ComplexTask extends Task {
    List<Task> tasks = new ArrayList<>();

    public ComplexTask(int idTask, String statusTask, List<Task> tasks) {
        super(idTask, statusTask);
        this.tasks = tasks;
    }
    public ComplexTask(int idTask, String statusTask) {
        super(idTask, statusTask);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
    public void deleteTask(Task task){
        tasks.remove(task);
    }
    public List<Task> getTasks(){
        return tasks;
    }

    @Override
    public int estimateDuration() {
        int time = 0;

        for(Task t : tasks){
            time += t.estimateDuration();
        }

        return time;
    }
}
