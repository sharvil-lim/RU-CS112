## RU Kindergarten – 100 course points

The purpose of this assignment is to practice your understanding of  linked list structures.

**This assignment will take longer to complete than the first assignment**.

**Start your assignment early!** You will need time to understand this assignment and the many questions it may present.

Refer to our Programming [Assignments FAQ](https://ds.cs.rutgers.edu/assignment-faq/) for instructions on how to install VSCode, how to use the command line and how to submit your assignments.

### Overview

In this assignment you will simulate activities in a kindergarten classroom.

You will simulate the students standing in a line, the students on their seats, and the students playing [musical chairs](https://en.wikipedia.org/wiki/Musical_chairs).

The assignment uses:

-   a **Singly Linked List** to simulate student standing in a line.
-   a **2D array** to simulate students at their seats.
-   a **Circular Linked List** to simulate students sitting on chairs to play the musical chairs game.

### Implementation

### **Overview of files provided**

-   **Student** class which holds a student’s information.
-   **SNode** class represents the node object to be used in the linked structures. It contains a reference to a Student object and a reference to the next node in the list.
-   **Classroom class** holds all of the methods to be written and that will be tested when running the game. Edit the empty methods with you solution, but **DO NOT edit the provided ones or the methods signatures of any method**. This is the file you submit. 
-   **Driver** class, which is used to test your methods interactively. The classroom state will be printed after every method is used. Feel free to edit this file, as it is provided only to help you test your code. It is not submitted and it is not used to grade your code.
-   **StdRandom** class contains the _StdRandom.uniform(n)_ method that you will use in the method that plays the musical chairs game.
-   StdIn and StdOut, which are used by the driver. **Do not edit these classes.**.
-   Multiple text files. Feel free to edit them or even make new ones to help test your code. They are not submitted.
    -   Files containing student information.
    -   Files containing seat location in the classroom.

### Classroom.java

-   DO NOT add new import statements.
-   DO NOT change any of the method’s signatures.
-   You are encouraged to write helper methods as you see fit. Just make sure that they are private.

The class contains:

-   **studentInLine** which refers to the first student in the singly linked list.
-   **musicalChairs** which refers to the LAST student in the circularly linked list when the game is being played.
-   **seatingLocation** which is a 2D boolean array that shows which seats exist for students to seat at.
-   **studentsSitting** which is a 2D Student array that contains the students when they are sitting.
-   NOTE: seatingLocation and studentsSitting are parallel arrays meaning that **seatingLocation\[i\]\[j\]** also refers to the same seat in **studentsSitting\[i\]\[j\]**
-   provided methods that are used by the driver and empty methods you are expected to write your code in.
-   the printClassroom() function can be used to show the state of the classroom (eg. the states of students in line, seating, and musical chairs) at any time in your code.

**Methods to be implemented by you:**

### **1\. enterClassroom**

This method simulates students standing in line and coming into the classroom (not leaving the line).

To complete this method do:

1.  Read a student from the input file.
2.  Create a **Student** object with the information read from file.
3.  Create a **SNode** object that holds the Student object from step 2.
4.  Insert the SNode from step 3 to the front of the singularly linked list **studentsInLine**. 

You have been provided some input files to test this method (students1.in, students2.in, students3.in, students4.in). The format is as follows:

-   One line containing an integer representing the number of students in the file (one per line)
-   Several lines (one for student) containing students first name, last name, and height, space separated

Use the **StdIn** library to read from a file:`   `

-   **`StdIn.setFile(filename)`** opens a file to be read
-   **`StdIn.readInt()`** reads the next integer value from the opened file (weather the value is in the current line or in the next line)
-   `**StdIn.readString()**` read the next String value from the opened file

To read one student do:  
`**String** firstName = StdIn.readString();`  
`**String** lastName = StdIn.readString();`  
`**int** height = StdIn.readInt();`

The input file has Students in reverse alphabetical order; so at the end of this method the list will have all students in alphabetical order. **DO NOT implement a sorting algorithm, there is no need**.

-   Submit Classroom.java with this method completed under **Early Submission to receive extra credit**.

This is the expected output for students1.in

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2023/02/Screen-Shot-2023-02-09-at-5.46.09-PM.png)

### **2\. setupSeats**

This method creates the classroom seats. Imagine that someone is putting down seats on a rectangular or square room.

-   We will use a 2D array to simulate a room where each cell of the array can have a seat or not.
-   The instance variable **seatingLocations** is the 2D boolean array that shows which seats exist for students to seat at.
-   The instance variable **studentsSitting** is the 2D array that contains the students when they are seating.

To complete complete this method do:

1.  Read the input file to create the seating arrangement for 2D boolean array **seatingLocations** 
2.  Initialize the 2D Student array **studentsSitting** to be of the same size.

The input file format is as follows:

-   The first line contains an integer representing the number of rows in the classroom, say r
-   The second line contains an integer representing the number of columns in the classroom, say c
-   Number of r lines, each containing c true or false values (true denotes a seat exists, false denotes that there is no seat at that position in the classroom)
    

Use the **StdIn** library to read from a file:`   `

-   **`StdIn.setFile(filename)`** opens a file to be read
-   **`StdIn.readInt()`** reads the next integer value from the opened file (weather the value is in the current line or in the next line)
-   `**StdIn.readBoolean()**` read the next boolean value from the opened file (weather the value is in the current line or in the next line)

This method DOES NOT seat students on the seats.

This is the expected output for students1.in and seating1.in:

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2023/02/Screen-Shot-2023-02-09-at-8.53.07-PM-1024x407.png)

### **3\. seatStudents**

This method simulates students taking their seats in the Kindergarten classroom. Assume that the students are currently in **studentsInLine** and that there are enough available seats to seat all students in the line.

-   This method will populate the 2D array **studentsSitting**.
-   Students will be seated starting at the first row, first seat (studentsSitting\[0\]\[0\] if a seat is present at that location). Once the first row is filled, continues into the next row.
-   It uses the **seatingLocations** array to determine if a seat exists at a location.
    -   A student can sit at seat **studentsSitting\[i\]\[j\]** only if **seatingLocations\[i\]\[j\]** is true (seat exists). 
-   Then seat the first student in **studentsInLine** and moves on to the second, third and so on.

NOTES

-   A student is only in one place (musical chairs, seating chairs, or in line) at a time. At the end of this method **studentsInLine** is empty.
-   This method depends on **enterClassroom** and **setupSeats**. Test those individually on the driver prior to testing this method.

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2023/02/Screen-Shot-2023-02-09-at-9.02.26-PM-1024x453.png)

### **4\. insertMusicalChairs**

This method represents students preparing to start musical chairs! Assume that the students are in **studentsSitting**.

-   Imagine this as a circle of chairs.
-   This method will take students from the **studentsSitting** array and add them to the **musicalChairs** circular linked list.
-   Students are to be inserted at the end of the linked list by traversing row-wise, then column-wise in the studentsSitting array.
-   REMEMBER: the pointer to a circular linked list points to the LAST item in the list, and that item points to the front.

NOTES

-   At the end of this method **studentsInLine** and **studentsSitting** have no students.
-   This method depends on the previous methods from parts 1, 2, and 3. Test those before testing insertMusicalChairs.

This is the expected output using students1.in and seating1.in:

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2023/02/Screen-Shot-2023-02-09-at-9.04.41-PM-1024x381.png)

### **playMusicalChairs**

This method simulates the game of musical chairs!

-   This method is implemented for you.
-   Write the following methods to make it work.

This is the expected output using students1.in and seating1.in:

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2023/02/Screen-Shot-2023-02-17-at-10.36.50-AM-1024x407.png)

### **5\. moveStudentFromChairsToLine**

This method simulates a student being eliminated during the musical chairs game. 

-   This method randomly chooses a student to be eliminated from **musicalChairs**. 
-   Use _StdRandom.uniform(x)_, where x is the number of students currently in **musicalChairs**, to get a number _**n**_ between 0 (inclusive) and _x (_exclusive).
-   Remove the student at position **_n_** from **musicalChairs**. Position 0 is the first student.
    -   Notice that the driver is setting a seed for the random number generator. The seed value is 2022.
-   Once the student is removed from the chairs call **insertByHeight** to insert the student into **studentsInLine**.
-   [Watch this video](https://drive.google.com/file/d/1tROVN7Ll1eH2n--UrqDaTMESVq-0jv7Z/view?usp=sharing) for more information.

Note: this method depends on **insertMusicalChairs** and **insertByHeight**.

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2023/02/Screen-Shot-2023-02-09-at-9.32.34-PM-1024x286.png)

### **6\. insertByHeight**

Each student that is eliminated from the musical chairs game is put back in the line (**studentsInLine** linked list) by height order.

-   The eliminated student is given as as parameter
-   Iterate through **studentsInLine** searching for a student that is taller than the eliminated student. You will have 3 cases:
    -   eliminated student is the shortest, insert at the front of the list.
    -   eliminated student is the tallest, insert at the end of the list.
    -   eliminated student is not the shortest or tallest, insert in the middle of the list.
-   When inserting into the list, a student will be insert AFTER all students of the same height.
-   [Watch this video](https://drive.google.com/file/d/1e9VW7DipSE3ADAX2aVNY3DXi_ksBisZl/view?usp=share_link) for more information.
-   This method is called from **moveStudentFromChairsToLine()** to insert a student that was eliminated from the musical chairs game. In the driver, you can test this method independently using moveStudentFromChairsToLine.

### **7\. eliminateLosingStudents**

This method eliminates all students from **musicalChairs** except the winner.

1.  Obtain the number of students currently in the **musicalChairs**, call it size.
2.  Repeatedly call **moveStudentFromChairsToLine** with the size until one player is left.
3.  Don’t forget to update size after each call as the number of students in the chairs decreases by 1.

At the end of this method one student will remain in musicalChairs, this is the winner! **The winner doesn’t leave the musical chairs to enter the line.**

All other students will be standing on the line in ascending order by height.

NOTE: this method depends on all previous methods.

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2023/02/Screen-Shot-2023-02-13-at-3.06.12-PM-1024x166.png)

### **8\. seatMusicalChairsWinner**

This method will seat the winner of the musical chairs game!

After the game of playMusicalChairs, you must seat the winner first!

-   If musical **musicalChairs** contains only one node (that is, the winner), delete the node. The list will be empty.
-   Insert the student object into the first empty spot in the classroom (**seatingStudents**).

![](https://ds.cs.rutgers.edu/wp-content/uploads/sites/82/2023/02/Screen-Shot-2023-02-09-at-10.28.54-PM.png)

### Implementation Notes

-   YOU MAY only update the methods with the WRITE YOUR CODE HERE line. 
-   DO NOT add any instance variables to the Classroom class.
-   DO NOT add any public methods to the Classroom class.
-   YOU MAY add private methods to the Classroom class. 
-   **DO NOT** use System.exit()

### Executing and Debugging

-   You can run your program through VSCode or you can use the Terminal to compile and execute. We suggest running through VSCode because it will give you the option to debug.
-   [How to debug your code](https://code.visualstudio.com/docs/java/java-debugging)
-   If you choose the Terminal:
    -   first navigate to **RUKindergarten** directory/folder
        -   to compile:  **javac -d bin src/kindergarten/\*.java**
        -   to execute: **java -cp bin kindergarten.Driver**

Problem by Ethan Chou, Maksims Kurjanovics Kravcenko, and Kal Pandit