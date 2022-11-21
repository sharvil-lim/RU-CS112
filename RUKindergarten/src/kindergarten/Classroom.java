package kindergarten;
/**
 * This class represents a Classroom, with:
 * - an SNode instance variable for students in line,
 * - an SNode instance variable for musical chairs, pointing to the last student in the list,
 * - a boolean array for seating availability (eg. can a student sit in a given seat), and
 * - a Student array parallel to seatingAvailability to show students filed into seats 
 * --- (more formally, seatingAvailability[i][j] also refers to the same seat in studentsSitting[i][j])
 * 
 * @author Ethan Chou
 * @author Kal Pandit
 * @author Maksims Kurjanovics Kravcenko
 */
public class Classroom {
    private SNode studentsInLine;             // when students are in line: references the FIRST student in the LL
    private SNode musicalChairs;              // when students are in musical chairs: references the LAST student in the CLL
    private boolean[][] seatingAvailability;  // represents the classroom seats that are available to students
    private Student[][] studentsSitting;      // when students are sitting in the classroom: contains the students

    /**
     * Constructor for classrooms. Do not edit.
     * @param l passes in students in line
     * @param m passes in musical chairs
     * @param a passes in availability
     * @param s passes in students sitting
     */
    public Classroom ( SNode l, SNode m, boolean[][] a, Student[][] s ) {
		studentsInLine      = l;
        musicalChairs       = m;
		seatingAvailability = a;
        studentsSitting     = s;
	}
    /**
     * Default constructor starts an empty classroom. Do not edit.
     */
    public Classroom() {
        this(null, null, null, null);
    }

    /**
     * This method simulates students coming into the classroom and standing in line.
     * 
     * Reads students from input file and inserts these students in alphabetical 
     * order to studentsInLine singly linked list.
     * 
     * Input file has:
     * 1) one line containing an integer representing the number of students in the file, say x
     * 2) x lines containing one student per line. Each line has the following student 
     * information separated by spaces: FirstName LastName Height
     * 
     * @param filename the student information input file
     */
    public void makeClassroom ( String filename ) {
        StdIn.setFile(filename);
        int numStudents = StdIn.readInt();

        studentsInLine = null;
        SNode prevNode = null;

        for(int i = 0; i < numStudents; i++) {
            String firstName = StdIn.readString();
            String lastName = StdIn.readString();
            int height = StdIn.readInt();
            Student student = new Student(firstName, lastName, height);

            studentsInLine = new SNode();
            studentsInLine.setStudent(student);

            if(prevNode == null) {
                prevNode = studentsInLine;
            } else {
                studentsInLine.setNext(prevNode);
                prevNode = studentsInLine;
            }
        }

        alphabeticalSort();   
    } 

    /**
     * 
     * This method creates and initializes the seatingAvailability (2D array) of 
     * available seats inside the classroom. Imagine that unavailable seats are broken and cannot be used.
     * 
     * Reads seating chart input file with the format:
     * An integer representing the number of rows in the classroom, say r
     * An integer representing the number of columns in the classroom, say c
     * Number of r lines, each containing c true or false values (true denotes an available seat)
     *  
     * This method also creates the studentsSitting array with the same number of
     * rows and columns as the seatingAvailability array
     * 
     * This method does not seat students on the seats.
     * 
     * @param seatingChart the seating chart input file
     */
    public void setupSeats(String seatingChart) {
        StdIn.setFile(seatingChart);
        int r = StdIn.readInt();
        int c = StdIn.readInt();
        seatingAvailability = new boolean[r][c];
        studentsSitting = new Student[r][c];

        for(int row = 0; row < r; row++) {
            for(int col = 0; col < c; col++) {
                seatingAvailability[row][col] = StdIn.readBoolean();
            }
        }
    }

    /**
     * 
     * This method simulates students taking their seats in the classroom.
     * 
     * 1. seats any remaining students from the musicalChairs starting from the front of the list
     * 2. starting from the front of the studentsInLine singly linked list
     * 3. removes one student at a time from the list and inserts them into studentsSitting according to
     *    seatingAvailability
     * 
     * studentsInLine will then be empty
     */
    public void seatStudents () {
        for(int row = 0; row < seatingAvailability.length; row++) {
            for(int col = 0; col < seatingAvailability[row].length; col++) {
                if(seatingAvailability[row][col]) {
                    if(lengthOfMusicalChairs() > 0) {
                        studentsSitting[row][col] = musicalChairs.getStudent();
                        musicalChairs = null;
                    } else {
                        if(lengthOfStudentsInLine(studentsInLine) > 0) {
                            studentsSitting[row][col] = studentsInLine.getStudent();
                            studentsInLine = studentsInLine.getNext();
                        }
                    } 
                }
            }
        }
    }

    /**
     * Traverses studentsSitting row-wise (starting at row 0) removing a seated
     * student and adding that student to the end of the musicalChairs list.
     * 
     * row-wise: starts at index [0][0] traverses the entire first row and then moves
     * into second row.
     */
    public void insertMusicalChairs () {
        SNode head = null;
        musicalChairs = null; // tail

        for(int row = 0; row < studentsSitting.length; row++) {
            for(int col = 0; col < studentsSitting[row].length; col++) {
                Student student = studentsSitting[row][col];
                studentsSitting[row][col] = null;
                
                if(student != null) {
                    SNode newNode = new SNode();
                    newNode.setStudent(student);

                    if (head == null) {
                        head = newNode;
                    } else {
                        musicalChairs.setNext(newNode);
                    }
                
                    musicalChairs = newNode;
                    musicalChairs.setNext(head);
                }
            }
        }
     }

    /**
     * 
     * This method repeatedly removes students from the musicalChairs until there is only one
     * student (the winner).
     * 
     * Choose a student to be elimnated from the musicalChairs using StdRandom.uniform(int b),
     * where b is the number of students in the musicalChairs. 0 is the first student in the 
     * list, b-1 is the last.
     * 
     * Removes eliminated student from the list and inserts students back in studentsInLine 
     * in ascending height order (shortest to tallest).
     * 
     * The last line of this method calls the seatStudents() method so that students can be seated.
     */
    public void playMusicalChairs() {
        while(lengthOfMusicalChairs() > 1) {
            printMusicalChairs();
            int studentsPlaying = lengthOfMusicalChairs();
            int eliminatedStudentToken = StdRandom.uniform(studentsPlaying);

            SNode prevNode = musicalChairs; // prior value
            SNode node = prevNode.getNext(); // current value (to be eliminated)
            SNode nextNode = node.getNext(); // next value

            for(int i = 0; i <= studentsPlaying; i++) {

                if (i == eliminatedStudentToken) {
                    prevNode.setNext(nextNode);
                    if (node == musicalChairs) {
                        musicalChairs = prevNode;
                    }
                    addStudentInLine(node);                 
                    break;
                }

                if (prevNode != null || node != null || nextNode != null) {
                    prevNode = prevNode.getNext();
                    node = node.getNext();
                    nextNode = nextNode.getNext();
                }
            }
        }

        heightSort();
        seatStudents();
    } 

    /**
     * Insert a student to wherever the students are at (ie. whatever activity is not empty)
     * Note: adds to the end of either linked list or the next available empty seat
     * @param firstName the first name
     * @param lastName the last name
     * @param height the height of the student
     */
    public void addLateStudent ( String firstName, String lastName, int height ) {
        Student lateStudent = new Student(firstName, lastName, height);
        int lengthOfMusicalChairs = lengthOfMusicalChairs();
        int lengthOfStudentsInLine = lengthOfStudentsInLine(studentsInLine);
        boolean added = false;

        if (lengthOfStudentsInLine > 0) {
            for(SNode node = studentsInLine; node != null; node = node.getNext()) {
                if(node.getNext() == null && !added) {
                    SNode newNode = new SNode();
                    newNode.setStudent(lateStudent);
                    node.setNext(newNode);
                    added = true;
                }
            }
        } else if (lengthOfMusicalChairs > 0) {
            SNode node = musicalChairs.getNext();
            SNode nextNode = node.getNext();

            for(int i = 0; i < lengthOfMusicalChairs; i++) {
                if(node == musicalChairs && !added) {
                    SNode newNode = new SNode();
                    newNode.setStudent(lateStudent);
                    node.setNext(newNode);
                    newNode.setNext(nextNode);
                    musicalChairs = newNode;
                    added = true;
                }
                node = node.getNext();
                nextNode = node.getNext();
            }
        } else {
            for(int row = 0; row < studentsSitting.length; row++) {
                for(int col = 0; col < studentsSitting[row].length; col++) {
                    if(studentsSitting[row][col] == null && !added && seatingAvailability[row][col]) {
                        studentsSitting[row][col] = lateStudent;
                        added = true;
                    }
                }
            }
        }
    }

    /**
     * A student decides to leave early
     * This method deletes an early-leaving student from wherever the students 
     * are at (ie. whatever activity is not empty)
     * 
     * Assume the student's name is unique
     * 
     * @param firstName the student's first name
     * @param lastName the student's last name
     */
    public void deleteLeavingStudent ( String firstName, String lastName ) {
        int lengthOfStudentsInLine = lengthOfStudentsInLine(studentsInLine);
        int lengthOfMusicalChairs = lengthOfMusicalChairs();

        if(lengthOfStudentsInLine > 0) {
            SNode node = studentsInLine;
            SNode prevNode = null;
            SNode nextNode = node.getNext();

            for (int i = 0; i < lengthOfStudentsInLine; i++) {
                Student student = node.getStudent();
                if (student.getFirstName().equalsIgnoreCase(firstName) && student.getLastName().equalsIgnoreCase(lastName)) {
                    if (prevNode == null) {
                        studentsInLine = nextNode;
                    } else {
                        prevNode.setNext(nextNode);
                    }
                }

                prevNode = node;
                node = node.getNext();
                if (node != null) {
                    nextNode = node.getNext();
                }
            }
        } else if (lengthOfMusicalChairs > 0) {
            SNode node = musicalChairs;
            SNode nextNode = node.getNext();
            SNode prevNode = null;

            for(SNode tempNode = musicalChairs; tempNode.getNext() != musicalChairs; tempNode = tempNode.getNext()) {
                if (tempNode.getNext().getNext() == musicalChairs) {
                    prevNode = tempNode.getNext();
                }
            }

            for (int i = 0; i < lengthOfMusicalChairs; i++) {
                Student student = node.getStudent();

                if (student.getFirstName().equalsIgnoreCase(firstName) && student.getLastName().equalsIgnoreCase(lastName)) {
                    prevNode.setNext(nextNode);

                    if (node == musicalChairs) {
                        musicalChairs = prevNode;
                    }
                }

                prevNode = prevNode.getNext();
                node = node.getNext();
                nextNode = nextNode.getNext();
            }
        } else {
            for(int row = 0; row < studentsSitting.length; row++) {
                for(int col = 0; col < studentsSitting[row].length; col++) {
                    Student student = studentsSitting[row][col];
                    if(student!= null) {
                        if(student.getFirstName().equalsIgnoreCase(firstName) && student.getLastName().equalsIgnoreCase(lastName)) {
                            studentsSitting[row][col] = null;
                        }
                    }
                }
            }
        }
    } 

    /**
     * Used by driver to display students in line
     * DO NOT edit.
     */
    public void printStudentsInLine () {

        //Print studentsInLine
        StdOut.println ( "Students in Line:" );
        if ( studentsInLine == null ) { StdOut.println("EMPTY"); }

        for ( SNode ptr = studentsInLine; ptr != null; ptr = ptr.getNext() ) {
            StdOut.print ( ptr.getStudent().print() );
            if ( ptr.getNext() != null ) { StdOut.print ( " -> " ); }
        }
        StdOut.println();
        StdOut.println();
    }

    /**
     * Prints the seated students; can use this method to debug.
     * DO NOT edit.
     */
    public void printSeatedStudents () {

        StdOut.println("Sitting Students:");

        if ( studentsSitting != null ) {
        
            for ( int i = 0; i < studentsSitting.length; i++ ) {
                for ( int j = 0; j < studentsSitting[i].length; j++ ) {

                    String stringToPrint = "";
                    if ( studentsSitting[i][j] == null ) {

                        if (seatingAvailability[i][j] == false) {stringToPrint = "X";}
                        else { stringToPrint = "EMPTY"; }

                    } else { stringToPrint = studentsSitting[i][j].print();}

                    StdOut.print ( stringToPrint );
                    
                    for ( int o = 0; o < (10 - stringToPrint.length()); o++ ) {
                        StdOut.print (" ");
                    }
                }
                StdOut.println();
            }
        } else {
            StdOut.println("EMPTY");
        }
        StdOut.println();
    }

    /**
     * Prints the musical chairs; can use this method to debug.
     * DO NOT edit.
     */
    public void printMusicalChairs () {
        StdOut.println ( "Students in Musical Chairs:" );

        if ( musicalChairs == null ) {
            StdOut.println("EMPTY");
            StdOut.println();
            return;
        }
        SNode ptr;
        for ( ptr = musicalChairs.getNext(); ptr != musicalChairs; ptr = ptr.getNext() ) {
            StdOut.print(ptr.getStudent().print() + " -> ");
        }
        if ( ptr == musicalChairs) {
            StdOut.print(musicalChairs.getStudent().print() + " - POINTS TO FRONT");
        }
        StdOut.println();
    }

    /**
     * Prints the state of the classroom; can use this method to debug.
     * DO NOT edit.
     */
    public void printClassroom() {
        printStudentsInLine();
        printSeatedStudents();
        printMusicalChairs();
    }

    /**
     * Used to get and set objects.
     * DO NOT edit.
     */

    public SNode getStudentsInLine() { return studentsInLine; }
    public void setStudentsInLine(SNode l) { studentsInLine = l; }

    public SNode getMusicalChairs() { return musicalChairs; }
    public void setMusicalChairs(SNode m) { musicalChairs = m; }

    public boolean[][] getSeatingAvailability() { return seatingAvailability; }
    public void setSeatingAvailability(boolean[][] a) { seatingAvailability = a; }

    public Student[][] getStudentsSitting() { return studentsSitting; }
    public void setStudentsSitting(Student[][] s) { studentsSitting = s; }

    // PRIVATE METHODS ADDED HERE 

    private void alphabeticalSort() {
        boolean flip = true;
        
        while(flip) {
            flip = false;

            for (SNode node = studentsInLine; node != null; node = node.getNext()) {
                SNode nextNode = node.getNext();

                if(nextNode != null) {
                    Student student = node.getStudent();
                    Student nextStudent = nextNode.getStudent();
    
                    if (student.compareNameTo(nextStudent) > 0) {
                        node.setStudent(nextStudent);
                        nextNode.setStudent(student);
                        flip = true;
                    }
                }
            }
        }
    }

    private void heightSort() {
        boolean flip = true;

        while(flip) {
            flip = false;

            for(SNode node = studentsInLine; node != null; node = node.getNext()) {
                SNode nextNode = node.getNext();

                if(nextNode != null) {
                    Student student = node.getStudent();
                    Student nextStudent = nextNode.getStudent();

                    if (student.getHeight() > nextStudent.getHeight()) {
                        node.setStudent(nextStudent);
                        nextNode.setStudent(student);
                        flip = true;
                    }
                }
            }
        }
    }

    private int lengthOfStudentsInLine(SNode linkedList) {
        if (linkedList == null) {
            return 0;
        }

        return (lengthOfStudentsInLine(linkedList.getNext()) + 1);
    }

    private int lengthOfMusicalChairs() {
        int counter = 0;
        SNode node = musicalChairs;
        
        if (musicalChairs == null) {
            return counter;
        } else {
            do {
                node = node.getNext();
                counter++;
            } while (node != musicalChairs);
        }
        
        return counter;
    }

    private void addStudentInLine(SNode node) {
        SNode nextNode = studentsInLine;
        studentsInLine = node;
        studentsInLine.setNext(nextNode);  
    }
}