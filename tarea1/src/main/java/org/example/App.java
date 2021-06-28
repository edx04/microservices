package org.example;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Title of the movie");
        String movie = myObj.nextLine();


        Connection conn = new Connection("spiderman");
        conn.obtain_titles();





    }
}
