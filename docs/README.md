# User Guide
duke is a chat bot that keep tracks of your tasks.
It uses a command line interface. 

## Features 
List out tasks : list
Add a to-do : todo
Add a event : event
Add a deadline : deadline
Mark a task as done : done
Delete a task : delete
Exit the programme : exit

### Feature 1 
Listing out recorded tasks:


## Usage
list
### `Keyword` - Describe action

Example of usage: 
list

Expected outcome:
1. [E][✗] book reading session (at: tomorrow afternoon)
1. [D][✓] finish reading book (by: tomorrow afternoon)
`

### Feature 2 
Adding a Todo:
## Usage
todo /name {Name of Todo}
### `Keyword` - Describe action
name : name of the todo

Example of usage: 
todo /name read book

Expected outcome:
Added todo read book as a task! :)

### Feature 3 
Adds a event to the task list

## Usage
event /name {Name of event} /time {time}
### `Keyword` - Describe action
name : name of the event
time : time of the event

Example of usage: 
event /name book reading session /time tomorrow afternoon

Expected outcome:
added event book reading session scheduled at time tomorrow afternoon! :)


### Feature 4 
add a new deadline to the task list

## Usage
deadline /name {Name of DeadLine} /time {time}
### `Keyword` - Describe action
name : name of the deadline
time : date due of the deadline

Example of usage: 
deadline /name finish reading macbeth /time NOW

Expected outcome:
added a deadline finish reading macbeth due NOW to the task list :)


### Feature 5 
Marking a task as done:

## Usage
done /number {task number} 

### `Keyword` - Describe action
task number : the task number on the list that is to be set to done


Example of usage: 
done /number 3

Expected outcome:
deadline finish reading macbeth due NOW is marked as done! :)



### Feature 6 
Deleting a task fom the task list:

## Usage
done /number {task number} 

### `Keyword` - Describe action
task number : the task number on the list of the task that is to be deleted

Example of usage:
delete /number 2

Expected outcome:
Noted! i've removed this task:
	[D][✗] mydeadline (by: NOW)
	Now you have 4 tasks on your list!
todo read book is marked as done! :)
todo read book is mark as undone. :(

### Feature 6
saving the task list

## Usage 
save /name {file name}

### `Keyword` - Describe action
name : the file name to be saved to

Example of usage:
save /name my first save

Expected outcome:
