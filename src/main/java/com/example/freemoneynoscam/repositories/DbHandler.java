package com.example.freemoneynoscam.repositories;
import java.sql.*;
import java.util.Scanner;

public class DbHandler
{
    static Connection con;
    static Statement stmt;
    static ResultSet rs;
    static String sqlString;

    public static void connect()
    {

        //on the localhost with the default port number 3306.
        String url = "jdbc:mysql://localhost:3306/free_money";

        //Get a connection to the database for a user named root with password admin
        try {
            con = DriverManager.getConnection(url, "root", "Skovdiget73");
        } catch (Exception e) {
            System.out.println("shit pommes frit, min DB connection virker ikke!!!");
        }


        //Display the URL and connection information
        System.out.println("URL: " + url);
        System.out.println("Connection: " + con);
    }


    public static void select()
    {
        try {
            //Get another statement object initialized as shown.
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            //Query the database, storing the result in an object of type ResultSet
            sqlString = "SELECT * from new_table";
            rs = stmt.executeQuery(sqlString);

            //Use the methods of class ResultSet in a loop
            // to display all of the data in the result.
            System.out.println("Display all results:");
            while (rs.next()) {
                String col1 = rs.getString("id");
                String col2 = rs.getString("email");

                System.out.println("\tid = " + col1 + "\temail = " + col2);
            }//end while loop

        }
        catch (Exception e){
            e.printStackTrace();
        }
        {
            System.out.println("shit pommes frit, select gik galt");
        }//end catch
    }

    public static void insertData()
    {
        String først = "dummy";
        String anden = "dummy";
        String tredje = "dummy";

        Scanner input = new Scanner(System.in);
        System.out.println("SKriv første");
        først = input.nextLine();

        try {
            //Get another statement object initialized as shown.
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            sqlString = "INSERT INTO tabel" + "(id, email)" +
                    "VALUES (" + først + "," + anden + ")";


            //Query the database, storing the result in an object of type ResultSet
            sqlString = "SELECT * from new_table";
            rs = stmt.executeQuery(sqlString);

            //Use the methods of class ResultSet in a loop
            // to display all of the data in the result.
            System.out.println("Display all results:");
            while (rs.next()) {
                String col1 = rs.getString("id");
                String col2 = rs.getString("email");

                System.out.println("\tid = " + col1 + "\temail = " + col2);
            }//end while loop
        } catch (Exception e) {
        }
    }

    public static void main(String[] args)
    {
        connect();
        insertData();
        select();
    }
}








