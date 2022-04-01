package com.example.freemoneynoscam.repositories;

import com.example.freemoneynoscam.services.Email;

import java.sql.*;
import java.util.ArrayList;

public class DbHandler
{
    private String sqlString;
    private Connection con;
    private Statement stmt;

    public void updateDb(String email)
    {
        connectDB();
        makeTableEmails();
        addEmail(email);
    }

    public void connectDB()
    {
        try
        {
            String url = "jdbc:mysql://localhost:3306/emails_db";
            con = DriverManager.getConnection(url, "root", "ekch22Lmysql");
            stmt = con.createStatement();
            System.out.println("Connected.");
        } catch (Exception e)
        {
            System.out.println("Unable to connect");
        }
    }

    public void closeConnection()
    {
        try
        {
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void makeTableEmails()
    {
        try
        {

            sqlString = "CREATE TABLE IF NOT EXISTS `emails_db`.`emails` (" +
                    "  `Email_id` INT NOT NULL AUTO_INCREMENT," +
                    "  `Email_address` VARCHAR(45) NOT NULL," +
                    "  PRIMARY KEY (`Email_id`));";
            stmt.executeUpdate(sqlString);
        } catch (Exception e)
        {
            System.out.println("DB error.");
        }
    }

    public void addEmail(String email)
    {
        try
        {
            sqlString = "INSERT INTO emails (`Email_address`) VALUES(?)";
            PreparedStatement statement = con.prepareStatement(sqlString);
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (Exception e)
        {
            System.out.println("DB error.");
        }
        closeConnection();
    }

    public ArrayList<Email> loadAddresses()
    {
        ArrayList<Email> emails = new ArrayList<>();
        try
        {
            String sqlString = "SELECT Email_address FROM emails";

            connectDB();

            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sqlString);

            while (rs.next())
            {
                emails.add(new Email(rs.getString("Email_address")));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        closeConnection();
        return emails;
    }
}








