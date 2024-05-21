package cp213;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * @author Anastasia Malinovski 200694700 mali4700@mylaurier.ca
 * @version 2021-06-07
 */
public class A02_Main {
    // Constants
    public static final String SEP = "-".repeat(40);

    public static void main(String[] args) {

    	System.out.println("Testing majorsMenu");
    	String result=Student.majorsMenu();
    	System.out.println(result);
		System.out.println(SEP);
		
//		System.out.println("Testing majorsStringToList");
//		String st="1,2,5";
//		Integer[] a_list=Student.majorsStringToList(st);
//		for (int x:a_list) {
//			System.out.println(x);
//		}
		System.out.println(SEP);
		
		System.out.println("Testing Student");
		String id="200694700";
		String surname="Malinovski";
		String forename="Anastasia";
		String birthDate = "07/08/2002";
		String majors = "Computer Science";
	
		System.out.println(SEP);
		System.out.println("Testing majorsListToNames");
		
   }
}