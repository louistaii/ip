# Casio User Guide

![Casio Screenshot](Screenshot.png)


Welcome to Casio, your personal chatbot task manager! 
Casio helps you stay organized with a variety of task management features.

## 📋 Listing tasks

List all current tasks in the task list using `list` command.

Expected output:
```
Casio:
1. [T][✔] todo1
2. [D][ ] deadline1 (by: 12 Dec 2024 12:30)
3. [E][ ] event1 (from: 12 Dec 2024 12:30 to: 12 Dec 2024 12:40)
You have 3 task(s).
```


## ➕ Adding ToDos

Add deadlines to task list using `todo` command.

Format: `todo todo_name`

Example: `todo Assignment`

Expected output:
```
Casio:
Added todo: Assignment into the list.
You now have 1 task(s) in the list.
```

## ➕ Adding deadlines

Add deadlines to task list using `deadline` command.

Format: `deadline deadline_name /by date time`

Example: `deadline Assignment /by 12/12/2025 12:30`

Expected output:
```
Casio:
Added deadline: Assignment (by: 12 Dec 2024 12:30) into the list.
You now have 1 task(s) in the list.
```

## ➕ Adding events

Add events to task list using `event` command.

Format: `event event_name /from start_datetime /to end_datetime`

Example: `event Lecture /from 12/12/2025 12:30 /to 12/12/2025 13:30`

Expected output:
```
Casio:
Added event: Lecture (from: 12 Dec 2024 12:30 to: 12 Dec 2024 13:30) into the list.
You now have 1 task(s) in the list.
```

## 🗑️ Deleting tasks

Delete tasks from task list based off their index on the list. Use the `delete` command.
Index starts from 1.

Format: `delete task_index`

Example: `delete 1`

Expected output:
```
Casio:
Deleted 1. [D][ ] Assignment (by: 12 Dec 2024 12:30)
You now have 0 task(s) in the list.
```

## ✔️ Marking tasks as complete

Mark tasks as complete based off their index on the list.
Index starts from 1.

Use the `mark` command.

Format: `mark task_index`

Example: `mark 1`

Expected output:
```
Casio:
Assignment marked as done. Good job!!
```

## 🔄 Unmarking tasks

Unmark tasks based off their index on the list.
Index starts from 1.

Use the `unmark` command.

Format: `unmark task_index`

Example: `unmark 1`

Expected output:
```
Casio:
Assignment marked as undone.
```

## 📅 Sorting task list

Sort the list of all current tasks by date and time using `sort /by time` command.

Expected output:
```
Casio:
List is now sorted by date and time!
Enter 'list' command to see sorted list!
```

## 🔍 Finding tasks

Search for a task in the task list with a keyword using `find` command.

Format: `find keyword`

Example: `find hello`

Expected output:
```
Casio:
You have 1 search result(s):
1.[T][✔] hello
```