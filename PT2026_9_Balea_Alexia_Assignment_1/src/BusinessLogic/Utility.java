package BusinessLogic;

import DataModel.ComplexTask;
import DataModel.Employee;
import DataModel.EmployeeWorkInfo;
import DataModel.Task;

import java.util.*;

public class Utility {
    //subpc a)
    public List<Employee> employeesThatWorkMoreThan40h(TasksManagement manager){
        List<EmployeeWorkInfo> res = new ArrayList<>();

        for(Employee e : manager.getMap().keySet()){
            int time = manager.calculateEmployeeWorkDuration(e.getIdEmployee());
            System.out.println("EMP: " + e.getName() + " TIME=" + time);
            if(time > 40){
                res.add(new EmployeeWorkInfo(e, time));
            }
        }

        Collections.sort(res);

        List<Employee> empList = new ArrayList<>();

        for(EmployeeWorkInfo emp : res){
            empList.add(emp.employee);
        }

        return empList;
    }

    //subpc b)
    private void countTasksRec(Task task, Map<String, Integer> stats){
        if(task.getStatusTask().equals("Completed")){
            stats.put("Completed", stats.get("Completed") + 1);
        } else {
            stats.put("Uncompleted", stats.get("Uncompleted") + 1);
        }

        if(task instanceof ComplexTask){
            for(Task sub : ((ComplexTask) task).getTasks()){
                countTasksRec(sub, stats);
            }
        }
    }

    public Map<String, Map<String, Integer>> emplTaskStatistic(TasksManagement manager){
        Map<String, Map<String, Integer>> res = new HashMap<>();

        for(Employee e : manager.getMap().keySet()){
            Map<String, Integer> stats = new HashMap<>();
            stats.put("Completed", 0);
            stats.put("Uncompleted", 0);

            for(Task t : manager.getMap().get(e)){
                countTasksRec(t, stats);
            }

            res.put(e.getName(), stats);
        }
        return res;
    }
}