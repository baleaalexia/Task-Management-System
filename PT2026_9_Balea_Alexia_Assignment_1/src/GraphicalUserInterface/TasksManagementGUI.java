package GraphicalUserInterface;

import BusinessLogic.TasksManagement;
import BusinessLogic.Utility;
import DataModel.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class TasksManagementGUI extends JFrame {
    private TasksManagement manager;
    private JTable table;
    private DefaultTableModel model;

    public TasksManagementGUI(TasksManagement manager){
        this.manager = manager;

        setTitle("App for TASK MANAGEMENT");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        //SP SUS
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(134, 30, 30));
        topPanel.setBorder(new EmptyBorder(15, 10, 15, 10));


        JLabel title = new JLabel("Task Management System");
        title.setForeground(Color.white);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        topPanel.add(title);

        add(topPanel, BorderLayout.NORTH);

        //MENIU STG
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(15, 1, 10, 10));
        leftPanel.setBackground(new Color(69, 40, 40));

        JButton btnShowTasks = new JButton("Show Tasks");
        btnShowTasks.setBackground(new Color(134, 30, 30));
        btnShowTasks.setForeground(Color.white);

        JButton btnShowEmployees = new JButton("Show Employess");
        btnShowEmployees.setBackground(new Color(134, 30, 30));
        btnShowEmployees.setForeground(Color.white);

        JButton btnAddEmployee = new JButton("Add Employee");
        btnAddEmployee.setBackground(new Color(134, 30, 30));
        btnAddEmployee.setForeground(Color.white);

        JButton btnAddTask = new JButton("Add Task");
        btnAddTask.setBackground(new Color(134, 30, 30));
        btnAddTask.setForeground(Color.white);

        JButton btnAssignTask = new JButton("Assign Task to Employee");
        btnAssignTask.setBackground(new Color(134, 30, 30));
        btnAssignTask.setForeground(Color.white);

        JButton btnChangeStatus = new JButton("Change Status");
        btnChangeStatus.setBackground(new Color(134, 30, 30));
        btnChangeStatus.setForeground(Color.white);

        JButton btnDuration = new JButton("Calculate Duration");
        btnDuration.setBackground(new Color(134, 30, 30));
        btnDuration.setForeground(Color.white);

        JButton btnSave = new JButton("Save");
        btnSave.setBackground(new Color(134, 30, 30));
        btnSave.setForeground(Color.white);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBackground(new Color(134, 30, 30));
        btnDelete.setForeground(Color.white);

        JButton btnWorkDurationGreaterThan40 = new JButton("Employee work > 40 ");
        btnWorkDurationGreaterThan40.setBackground(new Color(81, 19, 19));
        btnWorkDurationGreaterThan40.setForeground(Color.WHITE);

        JButton btnEmployeeWorkStatistics = new JButton("Employee Work Statistic");
        btnEmployeeWorkStatistics.setBackground(new Color(81, 19, 19));
        btnEmployeeWorkStatistics.setForeground(Color.WHITE);

        leftPanel.add(btnShowTasks);
        leftPanel.add(btnShowEmployees);
        leftPanel.add(btnAddEmployee);
        leftPanel.add(btnAddTask);
        leftPanel.add(btnAssignTask);
        leftPanel.add(btnChangeStatus);
        leftPanel.add(btnDuration);
        leftPanel.add(btnSave);
        leftPanel.add(btnDelete);
        leftPanel.add(btnWorkDurationGreaterThan40);
        leftPanel.add(btnEmployeeWorkStatistics);

        add(leftPanel, BorderLayout.WEST);

        //PARTEA DIN DR
        JPanel rightPanel = new JPanel(new BorderLayout());

        model = new DefaultTableModel();
        table = new JTable(model);
        table.setBackground(Color.WHITE);
        table.setGridColor(Color.BLACK);
        table.setFillsViewportHeight(true);

        JScrollPane scroll = new JScrollPane(table);

        rightPanel.add(scroll, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.CENTER);

        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(28);
        table.setGridColor(new Color(120, 120, 120));

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(134, 30, 30));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setReorderingAllowed(false);
        header.setBorder(BorderFactory.createEmptyBorder());

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if(isSelected){
                    c.setBackground(new Color(155, 130, 130));
                    c.setForeground(Color.white);
                } else {
                    c.setBackground(row % 2 == 0 ? new Color(245, 235, 230) : Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });

        table.setFillsViewportHeight(true);

        //ACTIUNI BTN
        btnShowTasks.addActionListener(e -> loadTasks());
        btnShowEmployees.addActionListener(e -> loadEmployees());
        btnAddEmployee.addActionListener(e ->{
            UIManager.put("OptionPane.background", new Color(68, 40, 40));
            UIManager.put("Panel.background", new Color(68, 40, 40));
            UIManager.put("OptionPane.messageForeground", Color.WHITE);
            UIManager.put("Button.background", new Color(134, 30, 30));
            UIManager.put("Button.foreground", Color.WHITE);

            int empId = readInt("Enter Employee ID");
            String empName =  JOptionPane.showInputDialog("Enter Employee Name");

            Employee emp = new Employee(empId, empName);

            manager.addEmployee(emp);

            loadEmployees();
        });
        btnAddTask.addActionListener(e -> {
            UIManager.put("OptionPane.background", new Color(68, 40, 40));
            UIManager.put("Panel.background", new Color(68, 40, 40));
            UIManager.put("OptionPane.messageForeground", Color.WHITE);
            UIManager.put("Button.background", new Color(134, 30, 30));
            UIManager.put("Button.foreground", Color.WHITE);

            String[] options = {"Simple Task", "Complex Task"};
            int taskType = JOptionPane.showOptionDialog(null, "Select Task Type", "Task Type",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if(taskType == -1)
                return;

            int id = readInt("Task ID");
            String status = "Uncompleted";

            Task taskToAssign;

            if(taskType == 0){
                int start = Integer.parseInt(JOptionPane.showInputDialog("Start Time"));
                int end = Integer.parseInt(JOptionPane.showInputDialog("End Time"));
                taskToAssign = new SimpleTask(id, status, start, end);
            } else {
                taskToAssign = new ComplexTask(id, status);
            }

            int partOf = JOptionPane.showConfirmDialog(null, "Is it a part of an existing complex task?", "Apartenenta", JOptionPane.YES_NO_OPTION);

            if(partOf == JOptionPane.YES_OPTION){
                int parentId = Integer.parseInt(JOptionPane.showInputDialog("Parent Task ID: "));
                ComplexTask parent = manager.findComplexTask(parentId);

                if(parent != null){
                    parent.addTask(taskToAssign);
                    JOptionPane.showMessageDialog(null, "Task added successfully in " + parentId);
                } else{
                    JOptionPane.showMessageDialog(null, "Task " + parentId + " not found");
                    return;
                }
            } else {
                int empId = Integer.parseInt(JOptionPane.showInputDialog("Employee ID: "));

                manager.assignTaskToEmployee(empId, taskToAssign);
            }

            loadTasks();
        });
        btnAssignTask.addActionListener(e -> {
            UIManager.put("OptionPane.background", new Color(68, 40, 40));
            UIManager.put("Panel.background", new Color(68, 40, 40));
            UIManager.put("OptionPane.messageForeground", Color.WHITE);
            UIManager.put("Button.background", new Color(134, 30, 30));
            UIManager.put("Button.foreground", Color.WHITE);

            Integer taskId = readInt("Enter Task ID");
            if(taskId == null)
                return;

            Integer empId = readInt("Enter Employee ID");
            if(empId == null)
                return;

            Task toAssign = null;

            for(List<Task> tasks : manager.getEmployeeTasks().values()){
                for(Task t : tasks){
                    toAssign = findTaskRec(t, taskId);
                    if(toAssign != null)
                        break;
                }
                if(toAssign != null)
                    break;
            }

            if(toAssign == null){
                JOptionPane.showMessageDialog(null, "Task " + taskId + " not found");
                return;
            }

            try{
                manager.assignTaskToEmployee(empId, toAssign);
                JOptionPane.showMessageDialog(null, "Task added successfully in " + empId);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Task " + empId + " not found");
            }

            loadTasks();
        });
        btnChangeStatus.addActionListener(e -> {
            UIManager.put("OptionPane.background", new Color(68, 40, 40));
            UIManager.put("Panel.background", new Color(68, 40, 40));
            UIManager.put("OptionPane.messageForeground", Color.WHITE);
            UIManager.put("Button.background", new Color(134, 30, 30));
            UIManager.put("Button.foreground", Color.WHITE);

            int empId = Integer.parseInt(JOptionPane.showInputDialog("Employee ID"));
            int taskId = Integer.parseInt(JOptionPane.showInputDialog("Task ID"));

            manager.modifyTaskStatus(empId, taskId);

            loadTasks();
        });
        btnDuration.addActionListener(e -> {
            UIManager.put("OptionPane.background", new Color(68, 40, 40));
            UIManager.put("Panel.background", new Color(68, 40, 40));
            UIManager.put("OptionPane.messageForeground", Color.WHITE);
            UIManager.put("Button.background", new Color(134, 30, 30));
            UIManager.put("Button.foreground", Color.WHITE);

            int empId = Integer.parseInt(JOptionPane.showInputDialog("Employee ID"));
            int duration = manager.calculateEmployeeWorkDuration(empId);

            JOptionPane.showMessageDialog(null, "Total duration: " + duration);
        });
        btnSave.addActionListener(e -> {
           manager.serializationSaveData(manager);
        });
        btnDelete.addActionListener(e -> {
            UIManager.put("OptionPane.background", new Color(68, 40, 40));
            UIManager.put("Panel.background", new Color(68, 40, 40));
            UIManager.put("OptionPane.messageForeground", Color.WHITE);
            UIManager.put("Button.background", new Color(134, 30, 30));
            UIManager.put("Button.foreground", Color.WHITE);

            String[] options = {"Delete Employee", "Delete Task"};

            int choice = JOptionPane.showOptionDialog(null, "What do you want to delete?",
                    "Delete", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

            if(choice == -1)
                return;

            if(choice == 0){
                int empId = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee ID"));

                boolean ok = manager.deleteEmployee(empId);

                if(ok){
                    JOptionPane.showMessageDialog(null, "Employee deleted!");
                } else {
                    JOptionPane.showMessageDialog(null, "Employee not found!");
                }
            } else {
                int taskId = Integer.parseInt(JOptionPane.showInputDialog("Enter Task ID"));

                boolean ok = manager.deleteTask(taskId);

                if(ok){
                    JOptionPane.showMessageDialog(null, "Task deleted!");
                } else {
                    JOptionPane.showMessageDialog(null, "Task not found!");
                }
            }
        });
        btnWorkDurationGreaterThan40.addActionListener(e -> {
            model.setColumnIdentifiers(new String[]{"Employee Name", "Duration"});
            model.setRowCount(0);

            Utility ut = new Utility();
            List<Employee> employeesInfo = ut.employeesThatWorkMoreThan40h(manager);

            for(Employee info : employeesInfo){
                int duration = manager.calculateEmployeeWorkDuration(info.getIdEmployee());
                model.addRow(new Object[]{
                        info.getName(),
                        duration
                });
            }
        });
        btnEmployeeWorkStatistics.addActionListener(e -> {
            UIManager.put("OptionPane.background", new Color(68, 40, 40));
            UIManager.put("Panel.background", new Color(68, 40, 40));
            UIManager.put("OptionPane.messageForeground", Color.WHITE);
            UIManager.put("Button.background", new Color(134, 30, 30));
            UIManager.put("Button.foreground", Color.WHITE);

            model.setColumnIdentifiers(new String[]{"Employee Name", "Completed", "Uncompleted"});
            model.setRowCount(0);

            Utility ut = new Utility();
            Map<String, Map<String, Integer>> stats = ut.emplTaskStatistic(manager);

            for(String name : stats.keySet()){
               Map<String, Integer> stat = stats.get(name);
               model.addRow(new Object[]{
                       name,
                       stat.get("Completed"),
                       stat.get("Uncompleted")
               });
            }
        });

        setVisible(true);
    }

    private void loadTasks() {
        model.setColumnIdentifiers(new String[]{
                "Task ID", "Status", "Duration", "Type", "Employee ID"
        });

        model.setRowCount(0);

        Map<Employee, List<Task>> data = manager.getEmployeeTasks();

        for (Employee e : data.keySet()) {
            for (Task t : data.get(e)) {
                addTaskToTableModel(t, "", e.getIdEmployee());
            }
        }
    }

    private void loadEmployees(){
        model.setColumnIdentifiers(new String[]{
                "Employee ID", "Employee Name", "Task ID", "Type"
        });
        model.setRowCount(0);

        Map<Employee, List<Task>> data = manager.getEmployeeTasks();

        for(Employee e : data.keySet()){
            List<Task> tasks = data.get(e);

            if(tasks.isEmpty()){
                model.addRow(new Object[]{
                        e.getIdEmployee(),
                        e.getName(),
                        "No task",
                        "-"
                });
            }

            for(Task t : tasks){
                String type = (t instanceof ComplexTask) ? "Complex" : "Simple";

                model.addRow(new Object[]{
                        e.getIdEmployee(),
                        e.getName(),
                        t.getIdTask(),
                        type
                });
            }
        }
    }

    private void addTaskToTableModel(Task task, String identare, int empId) {
        String type = (task instanceof ComplexTask) ? "Complex" : "Simple";

        model.addRow(new Object[]{
                identare + task.getIdTask(),
                task.getStatusTask(),
                task.estimateDuration(),
                type,
                empId
        });

        if(task instanceof ComplexTask complex){
            for(Task subT : complex.getTasks()){
                addTaskToTableModel(subT, identare + "   ", empId);
            }
        }
    }

    private Integer readInt(String message){
        String input = JOptionPane.showInputDialog(message);

        if(input == null)
            return null;

        try{
            return Integer.parseInt(input);
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Invalid input!");
            return null;
        }
    }

    private Task findTaskRec(Task task, int id){
        if(task.getIdTask() == id){
            return task;
        }

        if(task instanceof ComplexTask complex){
            for(Task sub : complex.getTasks()){
                Task found = findTaskRec(sub, id);
                if(found != null)
                    return found;
            }
        }
        return null;
    }
}