package GraphicalUserInterface;

import BusinessLogic.TasksManagement;
import DataAccess.Serialization;

import javax.swing.*;

public class TestGUI {
    public static void main(String[] args) {
        TasksManagement manager = Serialization.loadData();

        if(manager == null){
            manager = new TasksManagement();
        }

        TasksManagement finalManager = manager;

        SwingUtilities.invokeLater(() -> new TasksManagementGUI(finalManager));
    }
}