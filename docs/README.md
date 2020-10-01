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
save the current task list to file : save
load a saved file : load
list tasks on a certain take : list-by-late
find all task items containing a certain phrase : find
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
delete /number {task number} 

### `Keyword` - Describe action
task number : the task number on the list of the task that is to be deleted

Example of usage:
delete /number 2

Expected outcome:
Noted! i've removed this task:
	[D][✗] mydeadline (by: NOW)
	Now you have 4 tasks on your list!

### Feature 7
saving the task list

## Usage 
save /name {file name}

### `Keyword` - Describe action
file name : the file name to be saved to

Example of usage:
save /name my first save

Expected outcome:
Current task list is saved as :my first save!

### Feature 8
load a saved task list

## Usage
load /name {file name}

### `Keyword` - Describe action
name : the file name containing the task list to be loaded

Example of usage:
load /name my saved file

### feature 9
listing the tasks by date

## Usage 
list-by-date /time {date time}

### `Keyword` - Describe action
date time : a date time in the valid format YYYY-MM-DD-TTTT
The time input must be given, although any time will work.

Example of usage:
list-by-date /time 2020-10-09-1030

Expected outcome:
1. [E][✗] eat book(at: October 20 1990 1159 PM)
2. [E][✗] dump carrots into the ocean(at: October 20 1990 1159 PM)

## Feature 10
finding only the tasks that contains a certain name

## Usage

find /name {task name}

### `Keyword` - Describe action
task name : the task just has to contain the name to be displayed

Example of usage:
find /name dump

Expected outcome:
Printing only tasks that contains: dump
3. [E][✗] dump carrots into the ocean (at: October 20 1990 1159 PM)
4. [E][✓] dump some tentacle porn(at: September 9 2020 1000 AM)
5. [T][✗] dump some random stuff

## Feature 11
exit the programme

## Usage
exit

Excepted outcome:
GoodBye, Hope to see you again