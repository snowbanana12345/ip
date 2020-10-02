# User Guide
duke is a chat bot that keep tracks of your tasks.
It uses a command line interface. 

## Features 
* List out tasks : list
* Add a to-do : todo
* Add a event : event
* Add a deadline : deadline
* Mark a task as done : done
* Delete a task : delete
* save the current task list to file : save
* load a saved file : load
* list tasks on a certain take : list-by-late
* find all task items containing a certain phrase : find
* Exit the programme : exit

### Feature 1 
Listing out recorded tasks:

## Usage
list
### `Keyword` - list

Example of usage: 
list

Expected outcome:
1. [E][not done] eat book (at: October 20 1990 1159 PM)
2. [E][not done] drink book(at: September 9 2020 1000 AM)
3. [T][not done] eat blocks
4. [E][not done] dump carrots into the ocean (at: October 20 1990 1159 PM)
5. [E][not done] dump some tentacle porn(at: September 9 2020 1000 AM)
6. [T][not done] dump some random stuff
`

### Feature 2 
Adding a Todo:
## Usage
todo /name {Name of Todo}
### `Keyword` - todo
name : name of the todo

## Examples
Example of usage: 
todo /name read book

Expected outcome:
Added a todo to the task list!
        [T][not done] read book
        Now you have 7 tasks on your list!
        
Example of usage: 
todo /name shower

Expected outcome:
Added a todo to the task list!
        [T][not done] shower
        Now you have 8 tasks on your list!

### Feature 3 
Adds a event to the task list

## Usage
event /name {Name of event} /time {time}
### `Keyword` - event
name : name of the event
time : time of the event, format YYYY-MM-DD-TTTT

## Examples
Example of usage: 
event /name book reading session /time 2020-08-16-1000

Expected outcome:
Added a event to the task list!
        [E][not done] book reading session (at: August 16 2020 1000 AM)
        Now you have 10 tasks on your list!

Example of usage: 
event /name burn some witches at the stake /time 1067-09-15-0800

Expected outcome:
Added a event to the task list!
        [E][not done] burn some witches at the stake (at: September 15 1067 0800 AM)
        Now you have 11 tasks on your list!
        
### Feature 4 
add a new deadline to the task list

## Usage
deadline /name {Name of DeadLine} /time {time}
### `Keyword` - Describe action
name : name of the deadline
time : date due of the deadline, format YYYY-MM-DD-TTTT

Example of usage: 
deadline /name finish reading macbeth /time 2020-03-10-1230

Expected outcome:
Added a deadline to the task list!
        [E][not done] finish reading macbeth(at: March 10 2020 0030 PM)
        Now you have 12 tasks on your list!
        
Example of usage:  
deadline /name die /time 2010-05-27-1655
   
Expected outcome:  
Added a deadline to the task list!
        [E][not done] die(at: May 27 2010 0455 PM)
        Now you have 14 tasks on your list!

### Feature 5 
Marking a task as done:

## Usage
done /number {task number} 

### `Keyword` - Describe action
task number : the task number on the list that is to be set to done


Example of usage: 
done /number 5

Expected outcome:
Task 5 is now marked as done!

Example of usage: 
done /number 8

Expected outcome:
Task 8 is now marked as done!


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
     [E][not done] drink book(at: September 9 2020 1000 AM)
     Now you have 13 tasks on your list!
        
 Example of usage:
 delete /number 5
 
 Expected outcome:
Noted! i've removed this task:
    [T][not done] dump some random stuff
    Now you have 12 tasks on your list!

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

Excepted outcome:
Loading task list from file :my saved file
File :my saved file is loaded successfully!

### feature 9
listing the tasks by date

## Usage 
list-by-date /time {date time}

### `Keyword` - Describe action
date time : a date time in the valid format YYYY-MM-DD-TTTT
The time input must be given, although any time will work.

Example of usage:
list-by-date /time 2020-09-09-2359

Expected outcome:
1. [E][done :)] dump some tentacle porn(at: September 9 2020 1000 AM)
2. [E][not done] some event (at: September 9 2020 0100 PM)

Example of usage:
list-by-date /time 1990-10-20-1544

Expected outcome:
1. [E][not done] eat book (at: October 20 1990 1159 PM)
2. [E][not done] dump carrots into the ocean (at: October 20 1990 1159 PM)
3. [E][not done] dead by this line(at: October 20 1990 1145 AM)
4. [E][not done] eventful event (at: October 20 1990 0645 PM)

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
3. [E][not done] dump carrots into the ocean (at: October 20 1990 1159 PM)
4. [E][done :)] dump some tentacle porn(at: September 9 2020 1000 AM)

Example of usage:
find /name book

Expected outcome:
Printing only tasks that contains: book
1. [E][not done] eat book (at: October 20 1990 1159 PM)
5. [T][not done] read book
7. [E][not done] book reading session (at: tomorrow afternoo)
8. [E][not done] book reading session (at: August 16 2020 1000 AM)

## Feature 11
exit the programme

## Usage
exit

Excepted outcome:
GoodBye, Hope to see you again