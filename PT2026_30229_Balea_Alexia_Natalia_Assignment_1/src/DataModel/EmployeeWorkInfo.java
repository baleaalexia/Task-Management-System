package DataModel;

public class EmployeeWorkInfo implements Comparable<EmployeeWorkInfo>{
    public Employee employee;
    public int time;

    public EmployeeWorkInfo(Employee employee, int time) {
        this.employee = employee;
        this.time = time;
    }

    @Override
    public int compareTo(EmployeeWorkInfo o) {
        return this.time - o.time;
    }
}
