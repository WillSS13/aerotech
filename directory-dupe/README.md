# Directory Dupe

## Design Overview

I chose to use the Template and Stategy design patterns to help manage the different commands. The Template pattern was used to give some uniformity to the Command classes by defining which methods they have to implement. The Strategy pattern was used limit duplicate code by implementing common methods in the AbstractCommand class while allowing the Command classes to implement their own unique methods.

## Time to Complete
### **Total**: &nbsp;&nbsp;&nbsp; `3 hours`

Design:&nbsp;&nbsp;&nbsp;10 minutes

Development:&nbsp;&nbsp;&nbsp;2 hours 15 minutes

Documentation:&nbsp;&nbsp;&nbsp;35 minutes

## Testing Considerations
I could break down every single test case for each command and input, but I think there are some high-level concepts that would be more important to cover:

- **User Input**: The program should work as expected when given two directories and a valid command.
- **Directory Structure**: The program should work as expected when given directories with different structures.
- **Detecting Duplicates**: The program should only flag a file as a duplicate if the contents are exactly the same across different file types.

I've included a directory called `testing` that contains `dir1` and `dir2` which I used to test each command.

## Program

This tool helps manage duplicate files between two directories. There are three main commands:

### Display 
```ls```

Displays a list of all files from one directory (and subdirectories) that duplicate any 
files in the other directory (and subdirectories)

### Delete
```rm```

Delete all files from one directory (and subdirectories) that duplicate any files in the other directory (and subdirectories). 

### Copy
```cp```

Copy all files that are  not  duplicates from one directory  (and subdirectories) to the other directory (and subdirectories). 

## Build

If you are not in the project directory, navigate to it:
```
cd directory-dupe
```
Build the jarfile using Maven:
```
mvn clean package
```


## Run
Again, navigate to **target** directory:
```
cd target
```

Run the jarfile:
```
java -jar /directory-dupe-1.0-SNAPSHOT.jar [command] [directory1] [directory2]
```
