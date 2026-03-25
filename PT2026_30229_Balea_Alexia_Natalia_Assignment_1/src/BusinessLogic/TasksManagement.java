package BusinessLogic;

import DataAccess.Serialization;
import DataModel.ComplexTask;
import DataModel.Employee;
import DataModel.SimpleTask;
import DataModel.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TasksManagement implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<Employee, List<Task>> employeeTasks = new HashMap<>();

    public void assignTaskToEmployee(int idEmployee, Task task) {
        for (Employee e : employeeTasks.keySet()) {
            if (e.getIdEmployee() == idEmployee){
                employeeTasks.get(e).add(task);
            return;
            }
        }

        Employee newEmployee =  new  Employee(idEmployee, "New DataModel.Employee");

        List<Task> tasks = new ArrayList<>();
        tasks.add(task);

        employeeTasks.put(newEmployee, tasks);
    }

    public int workDurationComplex(Task task){
        if(task instanceof SimpleTask){
            if(task.getStatusTask().equals("Completed")){
                return task.estimateDuration();
            }
            return 0;
        }

        if(task instanceof ComplexTask){
            int time = 0;
            for(Task sub : ((ComplexTask) task).getTasks()){
                time += workDurationComplex(sub);
            }
            return time;
        }
        System.out.println("Task " + task.getIdTask() + " status=" + task.getStatusTask());

        return 0;
    }

    public int calculateEmployeeWorkDuration(int idEmployee){
       int totalTime = 0;
       for(Employee e : employeeTasks.keySet()){
           if(e.getIdEmployee() == idEmployee){
               for(Task t :  employeeTasks.get(e)){
                   totalTime+= workDurationComplex(t);
               }
           }
       }
       return totalTime;
    }

    public void modifyTaskStatus(int idEmployee, int idTask){
        for(Employee e : employeeTasks.keySet()){
            if(e.getIdEmployee() == idEmployee){
                for(Task t :  employeeTasks.get(e)){
                    modifyTaskStatusRec(t, idTask);
                }
            }
        }
    }

    public void setStatus(Task task, String status){
        task.setStatusTask(status);

        if(task instanceof ComplexTask complexT) {
            for (Task sub : complexT.getTasks()) {
                setStatus(sub, status);
            }
        }
    }

    public void modifyTaskStatusRec(Task currentTask, int idTask){
        if(currentTask.getIdTask() == idTask){
            String newStatus;

            if(currentTask.getStatusTask().equals("Completed"))
                newStatus = "Uncompleted";
            else
                newStatus = "Completed";

            setStatus(currentTask, newStatus);
            return;
        }

        if(currentTask instanceof ComplexTask complexTask){
            for(Task subTask : complexTask.getTasks()){
                modifyTaskStatusRec(subTask, idTask);
            }
        }

    }

    public Map<Employee, List<Task>> getEmployeeTasks() {
        return employeeTasks;
    }

    public ComplexTask findComplexTask(int idSearched){
        for(List<Task> tasks : employeeTasks.values()){
            for(Task t : tasks){
                ComplexTask found = findComplexTaskRec(t, idSearched);
                if(found != null)
                    return found;
            }
        }
        return null;
    }

    private ComplexTask findComplexTaskRec(Task task, int idSearched){
        if(task instanceof ComplexTask complexTask){
            if(complexTask.getIdTask() == idSearched){
                return complexTask;
            }
            for(Task sub : complexTask.getTasks()){
                ComplexTask found = findComplexTaskRec(sub, idSearched);
                if(found != null)
                    return found;
            }
        }
        return null;
    }

    public void addEmployee(Employee e){
        employeeTasks.put(e, new ArrayList<>());
    }

    public boolean deleteEmployee(int id){
        Employee toDelete = null;

        for(Employee e : employeeTasks.keySet()){
            if(e.getIdEmployee() == id){
                toDelete = e;
                break;
            }
        }

        if(toDelete != null){
            employeeTasks.remove(toDelete);
            return true;
        }

        return false;
    }

    public boolean deleteTask(int id){
        for(List<Task> tasks : employeeTasks.values()){
            if(removeTaskFromList(tasks, id))
                return true;
        }

        return false;
    }

    private boolean removeTaskFromList(List<Task> tasks, int id){
        boolean removed = tasks.removeIf(t -> t.getIdTask() == id);

        if(removed)
            return true;

        for(Task t : tasks){
            if(t instanceof ComplexTask complexTask){
                if(removeTaskFromList(complexTask.getTasks(), id))
                    return true;
            }
        }

        return false;
    }

    protected Map<Employee, List<Task>> getMap(){
        return employeeTasks;
    }

    public void serializationSaveData(TasksManagement manager){
        Serialization.saveData(manager);
    }
}