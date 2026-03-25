package DataAccess;

import BusinessLogic.TasksManagement;

import java.io.*;

public class Serialization {
    public static void saveData(TasksManagement app) {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("task.txt"))) {
            out.writeObject(app);
            System.out.println("Datele au fost salvate in fisier!");
        } catch (IOException e){
            System.out.println("Eroare la salvare >:(");
        }
    }

    public static TasksManagement loadData() {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("task.txt"))) {
            return (TasksManagement) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Eroare la incarcare >:(");
            return null;
        }
    }
}
