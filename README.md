# Simple Task List Manager

A simple console-based task management application built using Java. It allows users to create, view, organize, complete, and delete tasks through a menu-driven interface.

## Features

- Add new tasks
- Display all tasks
- Sort tasks by priority
- Mark tasks as completed
- Delete tasks
- Automatically generate task IDs
- Save and load tasks using file handling

## Data Structure

This project uses a **Singly Linked List** implemented manually using nodes.

Each node stores a task and a reference to the next node. This allows the program to manage tasks through linked nodes.

## Priority Levels

| Priority | Level |
|---|---|
| High | 1 |
| Medium | 2 |
| Low | 3 |

Tasks are sorted from High to Low priority using Bubble Sort.

## Technologies Used

- Java
- Data Structures and Algorithms
- Singly Linked List
- Bubble Sort
- File Handling

## How to Run

1. Clone or download this repository.
2. Open the project folder in your terminal.
3. Compile the program:

   `javac SimpleTaskListManager.java`

4. Run the program:

   `java SimpleTaskListManager`

## Storage

Tasks are saved in `tasks.txt` so that previously saved tasks can be loaded when the program is started again.

## Project Type

Academic DSA project — Problem Statement No. 50.