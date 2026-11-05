# Task Management System

### Organize tasks. Track progress. Understand workloads.

A Java desktop application for managing employees and their tasks through a Swing interface. Build task hierarchies, track completion, and explore employee workload statistics in one place.

**Java 17+ · Swing · Object-Oriented Design · Local Persistence**

---

## Overview

Task Management System brings together employee management, recursive task structures, and workload reporting. Tasks can be simple activities with a start and end hour, or complex activities containing multiple subtasks—including other complex tasks.

The project demonstrates a layered architecture, the Composite design pattern, recursive algorithms, and Java object serialization without external dependencies.

## Features

| Feature | What you can do |
| --- | --- |
| Employee management | Add employees, view their assignments, and delete employees with their assignment entries. |
| Simple and complex tasks | Create individual tasks or organize work into nested task hierarchies. |
| Task assignment | Assign new tasks or add an existing task to an employee's list. |
| Progress tracking | Toggle tasks between `Completed` and `Uncompleted`. Changing a complex task's status updates all its descendants. |
| Duration calculations | View estimated task durations and calculate completed work hours per employee. |
| Workload reporting | List employees with more than 40 completed work hours, sorted by duration in ascending order. |
| Completion statistics | View completed and uncompleted task counts for each employee. |
| Local persistence | Save application data and reload it on the next GUI launch. |

## Technology

- **Java 17 or newer** — required for the sealed task hierarchy and pattern matching used in the source.
- **Java Swing and AWT** — desktop windows, dialogs, and tables.
- **Java Collections Framework** — employee-to-task mappings and task lists.
- **Java Object Serialization** — local storage of the application state.
- **IntelliJ IDEA** — project metadata is included; any Java IDE can be used.

No Maven, Gradle, database, or third-party libraries are required.

## Using the Application

1. Select **Add Employee** and enter an employee ID and name.
2. Select **Add Task**, choose a simple or complex task, and enter its ID. Simple tasks also require integer start and end hours.
3. Add the task to an existing complex task, or assign it directly to an employee.
4. Use **Show Tasks** or **Show Employess** to inspect the current data.
5. Select **Change Status** and enter the employee and task IDs to toggle completion.
6. Use **Calculate Duration**, **Employee work > 40**, or **Employee Work Statistic** to inspect progress and workload.
7. Click **Save** before closing to persist your changes.
