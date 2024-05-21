package cp213;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Utilities for working with Student objects.
 *
 * @author Anastasia Malinovski 200694700 mali4700@mylaurier.ca
 * @version 2021-06-10
 */
public class StudentUtilities {
	public static final String[] MAJORS = { "Computer Science", "Psychology", "Chemistry", "Math", "Business", "Music",
			"English", "Basket Weaving", "Women's Studies", "History", "Archaeology" };

	/**
	 * Creates a list of Students whose birthDates are equal to or later than
	 * birthDate.
	 *
	 * @param students  list of students
	 * @param birthDate to compare against
	 * @return list of students for birthDate
	 */
	public static ArrayList<Student> getByBirthDate(final ArrayList<Student> students, final LocalDate birthDate) {
		final ArrayList<Student> bStudents = new ArrayList<>();

		return bStudents;
	}

	/**
	 * Creates a list of Students whose list of majors include major.
	 *
	 * @param students list of students
	 * @param major    major to compare against
	 * @return list of students for major
	 */
	public static ArrayList<Student> getByMajor(final ArrayList<Student> students, final int major) {
		final ArrayList<Student> mStudents = new ArrayList<>();
		for (Student x : students) {
			Integer[] m = x.getMajors();
			for (int i : m) {
				if (i == major) {
					mStudents.add(x);
				}
			}
		}

		return mStudents;
	}

	/**
     * Creates a list of Students whose list of majors include all the major codes
     * in majors.
     *
     * @param students list of students
     * @param majors   majors list to compare against
     * @return list of students for majors
     */
    public static ArrayList<Student> getByMajors(final ArrayList<Student> students, final Integer[] majors) {
	final ArrayList<Student> gStudents = new ArrayList<>();
	for (Student x: students) {
		Student x;
		Integer[] m=x.getMajors();
		for (Integer i: m) {
			for (int y: majors) {
				if (y==i) {
					gStudents.add(x);
				}
			
			}

	return gStudents;
    }

	/**
	 * Creates a list of Students from a particular birth year.
	 *
	 * @param students list of students
	 * @param year     birth date year of students
	 * @return list of students for birthDate
	 */
	public static ArrayList<Student> getByYear(final ArrayList<Student> students1, final int year) {
		final ArrayList<Student> yStudents = new ArrayList<>();

		for (Student x1 : students1) {
			LocalDate date = x1.getBirthDate();
			if (year == date.getYear()) {
				yStudents.add(x1);
			}
		}

		return yStudents;
	}

	/**
     * Creates a Student object by requesting data from a user.
     *
     * @param keyboard a keyboard Scanner
     * @return a Student object
     */
    public static Student getStudent(final Scanner keyboard) {

	private static Scanner scanner = new Scanner(System.in);

	String ident = scanner.nextLine();
	int id = Integer.parseInt(ident);

	String surname = scanner.nextLine();

	String forename = scanner.nextLine();

	String birth = scanner.nextLine();
	LocalDate birthDate = LocalDate.parse(birth);
	int[] majors= new int[0];
	while(scanner.hasNext())
	{

		if (scanner.next() != "") {
			String num = scanner.nextLine();
			int maj = Integer.parseInt(num);
			majors.add(maj);
		}
	}
	Student student = new Student(id, surname, forename, birthDate, majors);
	return student;
    }}
	/**
	 * Counts the number of students in each major given in Student.GENRES.
	 *
	 * @param students list of students
	 * @return Number of majors across all Students.
	 */
	public static int[] majorCounts (final ArrayList<Student> students) {
		int[] result=new int[students.size()];
		int num=0;
		for (Student x: students) {
			num++;
			for (Integer i: x.getMajors()) {
				result[num]=i;
			}
		}
		return result;
	}

	/**
	 * Asks a user to select majors from a list of majors and returns an integer
	 * list of the majors chosen.
	 *
	 * @param keyboard Keyboard input.
	 * @return An integer list of major codes.
	 */
	public static Integer[] readMajors(final Scanner keyboard) {
		final ArrayList<Integer> majors = new ArrayList<Integer>();
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter major according to list of majors:");
		while (scan.hasNext()){
			String maj=scan.next();
			int num=0;
			for (String x: MAJORS) {
				if (maj==x) {
					majors.add(num);
				}
				num++;
			}
		}
		

		return majors.toArray(new Integer[1]);
	}

	/**
	 * Creates and returns a Student object from a line of formatted string data.
	 *
	 * @param line a vertical bar-delimited line of student data in the format
	 *             id|surname|forename|birthDate|majorCodes
	 * @return the data from line as a Student object
	 */
	public static Student readStudent(final String line) {
		String[] thing=line.split("|");
		String ide=thing[0];
		String surname=thing[1];
		String forename=thing[2];
		String birth=thing[3];
		String majorCodes=thing[4];
		int id=Integer.parseInt(ide);
		LocalDate birthDate=LocalDate.parse(birth);
		Student student = new Student(id,surname,forename,birthDate,majors);
		
	}

	}

	/**
	 * Reads a list of Students from a file.
	 *
	 * @param fileScanner The file to read.
	 * @return A list of Student objects.
	 * @throws FileNotFoundException file not found
	 */
	public static ArrayList<Student> readStudents(final Scanner fileScanner) throws FileNotFoundException {
		final ArrayList<Student> students = new ArrayList<>();
		File myobj=new File("students.txt");
		Scanner reader=new Scanner(myobj);
		while (reader.hasNextLine()){
			String stud=reader.next();
//			Student stud.readStudent();
//			students.add(stud);
		}
		

		return students;
	}

	/**
	 * Writes the contents of students to a file. Overwrites or creates a new file
	 * of Student objects converted to strings.
	 *
	 * @param students List of Students.
	 * @param ps       PrintStream to write Student data to.
	 */
	public static void writeStudents(final ArrayList<Student> students, PrintStream ps) {
		for (Student x: students) {
			
		}
		return;

	}

}
