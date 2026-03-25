import BusinessLogic.TasksManagement;
import DataAccess.Serialization;
import DataModel.ComplexTask;
import DataModel.Employee;
import DataModel.SimpleTask;

public class Main {
    public static void main(String[] args) {
       BusinessLogic.TasksManagement manager = new BusinessLogic.TasksManagement();

        //TasksManagement manager = Serialization.loadData();
        if(manager == null) manager = new TasksManagement();

        int empId = 101;
        if(manager.getEmployeeTasks().isEmpty()) {
            Employee dev = new Employee(empId, "Ana");

            SimpleTask research = new SimpleTask(1, "Completed", 9, 11);
            SimpleTask docs = new SimpleTask(2, "Uncompleted", 14, 15);

            ComplexTask projectAlpha = new ComplexTask(3, "Uncompleted");
            ComplexTask projectOmega = new ComplexTask(4, "Uncompleted");
            projectOmega.addTask(new SimpleTask(33, "Uncompleted", 11, 15));
            projectAlpha.addTask(new SimpleTask(31, "Uncompleted", 10, 13));
            projectAlpha.addTask(new SimpleTask(32, "Completed", 15, 17));
            projectAlpha.addTask(projectOmega);


            manager.assignTaskToEmployee(empId, research);
            manager.assignTaskToEmployee(empId, docs);
            manager.assignTaskToEmployee(empId, projectAlpha);

             System.out.println("Og duration: " + dev.getName() + ": " + manager.calculateEmployeeWorkDuration(empId) + " hours");

            manager.modifyTaskStatus(empId, 2);
            manager.modifyTaskStatus(empId, 3);

            System.out.println("Modified duration: " + manager.calculateEmployeeWorkDuration(empId) + " hours");

        } else {
            System.out.println("Datele au fost deja incarcate");
        }
        //SERIALIZARE
//        System.out.println("Og duration: " + manager.calculateEmployeeWorkDuration(empId));
//        System.out.println("Loaded duration: " + manager.calculateEmployeeWorkDuration(empId));
//
//        Serialization.saveData(manager);
    }
}