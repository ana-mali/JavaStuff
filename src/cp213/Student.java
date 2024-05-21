package cp213;

import java.io.FileWriter;
import java.io.PrintStream;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Student class definition.
 *
 * @author Anastasia Malinovski 200694700 mali4700@mylaurier.ca
 * @version 2021-06-07
 */
public class Student implements Comparable<Student> {

    // Constants
    /**
     * List of string descriptions of student majors.
     */
    public static final String[] MAJORS = { "Computer Science", "Psychology", "Chemistry", "Math", "Business", "Music",
	    "English", "Basket Weaving", "Women's Studies", "History", "Archaeology" };

    /**
     * Returns a string of all majors in the Student.MAJORS list. Use for input
     * menus. Formatted as " 3: Math"
     *
     * @return the majors.
     */
    public static String majorsMenu() {
    	String result="";
    	for (int i=0; i<MAJORS.length;i++) {
    		result=result+(i)+":"+MAJORS[i]+",";
    	}
    	
    	return result;
    }

    /**
     * Converts a string of the form "2,3,6" to an array of Integer objects, [2, 3,
     * 6]. Used when reading Student objects from a file.
     *
     * @param string The string to convert to an array.
     * @return The array version of string.
     */
    public static Integer[] majorsStringToList(final String string) {
    
    	ArrayList<String> majorsList=new ArrayList<String>();
    	string.split(",");
    	majorsList=(ArrayList<String>) Arrays.asList(string);

	// Convert arraylist to an array of Integer.
    	return majorsList.toArray(new Integer[1]);
    }

    // Attributes
    private LocalDate birthDate = null;
    private String forename = "";
    private int id = 0;
    private Integer[] majors = null;
    private String surname = "";

    /**
     * Instantiates a Student object.
     *
     * @param id        student ID number
     * @param surname   student surname
     * @param forename  name of forename
     * @param birthDate birthDate in 'YYYY-MM-DD' format
     * @param majors    integers representing student majors list
     */
    public Student(final int id, final String surname, final String forename, final LocalDate birthDate,
	    final Integer[] majors) {
    	Student student = new Student(id,surname,forename,birthDate,majors);
    	return ;
    }

    /**
     * Converts a majors list of the form [2,3,7] to a string "2,3,7" for writing
     * Student data to a file.
     *
     * @return the majors list string
     */
    private String majorsListToString() {
    	String result="";
    	for (int x: this.majors) {
    		result=result+x+",";
    	}
    	return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final Student target) {	
    	return target.compareTo(this);
    }

    /**
     * birthDate getter.
     *
     * @return the birthDate
     */
    public LocalDate getBirthDate() {
	return this.birthDate;
    }

    /**
     * forename getter.
     *
     * @return the forename
     */
    public String getForename() {
	return this.forename;
    }

    /**
     * id getter
     *
     * @return the id
     */
    public int getId() {
	return id;
    }

    /**
     * majors getter.
     *
     * @return the majors list
     */
    public Integer[] getMajors() {
	return this.majors;
    }

    /**
     * surname getter.
     *
     * @return the surname
     */
    public String getSurname() {
	return this.surname;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	return this.id;
    }

    /**
     * Creates a formatted string of Student key data in the format "surname,
     * forename, id". Ex: "Brown, David, 123456".
     *
     * @return a Student key as a string
     */
    public String key() {
	return String.format("%s, %s, %d", this.surname, this.forename, this.id);
    }

    /**
     * Converts a list of major integers to a string of major names.
     *
     * @return comma delimited string of major names based upon the current student
     *         object's integer majors list. e.g.: [0, 2] returns "science fiction,
     *         drama"
     */
    public String majorsListToNames() {
    	String result="";
    
    	for (int x: this.majors) {
    		result=result+MAJORS[x]; 
    	}
    	return result;
    }

    /**
     * forename setter
     *
     * @param forename the new forename
     */
    public void setForename(String forename) {
	this.forename = forename;
    }

    /**
     * majors setter.
     *
     * @param majors the new list of numeric majors
     */
    public void setMajors(final Integer[] majors) {
	this.majors = majors;
    }

    /**
     * surname setter.
     *
     * @param surname the new surname
     */
    public void setSurname(final String surname) {
	this.surname = surname;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString() Creates a formatted string of student data.
     */
    @Override
    public String toString() {
    	return this.toString();

    }

    /**
     * Writes a single line of student data to an open file in the format
     * id|surname|forename|birthDate|majorCodes
     *
     * @param ps output PrintStream to print to
     */
    public void write(final PrintStream ps)throws Exception {
    	FileWriter myWriter = new FileWriter("ps.txt");
    	String st="";
    	st=st+this.id+"|"+this.surname+"|"+this.birthDate+"|"+this.majors;
    	myWriter.append(st);
    	myWriter.close();
    	return;
    }

}
