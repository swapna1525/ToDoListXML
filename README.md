# ToDoListXML

A simple To-Do List Android application built using **Kotlin and XML**.

This project was created to practice Android development concepts including **RecyclerView, View Binding, MVVM architecture, Room Database, Kotlin Coroutines, Flow, StateFlow, and Git/GitHub**.

## Features

- Add new tasks
- Delete tasks
- Mark tasks as completed
- Unmark completed tasks
- Completed tasks automatically move to the bottom
- New tasks appear at the top
- Tasks are saved locally using Room Database
- Tasks remain available after closing and reopening the app
- Reactive UI updates using Kotlin Flow and StateFlow
- Lifecycle-aware Flow collection
- View Binding

## Tech Stack

- **Language:** Kotlin
- **UI:** XML
- **Architecture:** MVVM
- **Database:** Room Database
- **UI Component:** RecyclerView
- **Asynchronous Programming:** Kotlin Coroutines
- **Reactive Data:** Flow & StateFlow
- **View Binding:** Android View Binding
- **IDE:** Android Studio
- **Version Control:** Git & GitHub

## Architecture

The application follows the MVVM architecture with a Repository layer.

```text
┌──────────────────────┐
│   Presentation       │
│                      │
│   MainActivity       │
│   TaskAdapter        │
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│      ViewModel       │
│                      │
│   TaskViewModel      │
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│     Repository       │
│                      │
│   TaskRepository     │
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│        DAO           │
│                      │
│       TaskDao        │
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│    Room Database     │
│                      │
│    TaskDatabase      │
└──────────────────────┘

```

## Project Structure

```text
com.example.todolistxml
│
├── data
│   ├── TaskDao
│   ├── TaskDatabase
│   ├── TaskModel
│   └── TaskRepository
│
├── presentation
│   ├── MainActivity
│   └── TaskAdapter
│
├── viewmodel
│   └── TaskViewModel
│
└── ui.theme

```
## Room Database

Room Database is used to store tasks locally on the device.

Each task contains:

- `id` - Auto-generated unique ID
- `title` - Task title
- `isCompleted` - Completion status

The application uses Room's reactive `Flow` to observe changes in the database.

The `TaskViewModel` exposes the task list using `StateFlow`, allowing the UI to react automatically when database data changes.

## Task Ordering

The application keeps incomplete and completed tasks organized:

```text
Incomplete Tasks
       ↓
Completed Tasks
```
Newer tasks are displayed before older tasks, while completed tasks remain at the bottom.   

## Future Improvements

- Edit existing tasks
- Add push notifications
- Add task categories
- Add due dates
- Add search and filtering
- Add UI animations
- Add unit tests
- Add UI tests
- Add dark mode

## What I Learned

Through this project, I practiced:

- Kotlin
- Android XML layouts
- RecyclerView
- View Binding
- MVVM architecture
- Repository pattern
- Room Database
- DAO operations
- Kotlin Coroutines
- Flow and StateFlow
- Lifecycle-aware data collection
- Git and GitHub
- Android project/package organization

## How to Run

1. Clone this repository.
2. Open the project in Android Studio.
3. Let Gradle sync complete.
4. Connect an Android device or start an emulator.
5. Run the application.

## Author
Naga Swapna Siramdasu