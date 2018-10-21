package com.company;

import java.sql.*;
import java.util.Random;

import static java.lang.Math.abs;

public class DataBase {
    private Consts c;
    private Connection conn;
    private Random random;
    private Statement stm;

    public DataBase() throws SQLException {
        this.c = new Consts();
        this.conn = (Connection)DriverManager.getConnection(
                c.URL,
                c.USER,
                c.PASS);
        this.random = new Random();
        this.stm = conn.createStatement();
    }

    public void Insert(DataModel tmp) throws SQLException {
        String query = "INSERT INTO Users.Users" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, tmp.name);
        preparedStmt.setString (2, tmp.surname);
        preparedStmt.setString(3, tmp.secondName);
        preparedStmt.setInt(4, tmp.age);
        preparedStmt.setBoolean    (5, tmp.sex);

        preparedStmt.setString(6, tmp.dateOfBirth);
        preparedStmt.setString (7, tmp.INN);
        preparedStmt.setString(8, tmp.postIndex);
        preparedStmt.setString(9, tmp.country);
        preparedStmt.setString (10, tmp.region);

        preparedStmt.setString (11, tmp.city);
        preparedStmt.setString (12, tmp.street);
        preparedStmt.setInt(13, tmp.houseNum);
        preparedStmt.setInt(14, tmp.flatNum);

        preparedStmt.execute();
    }

    public DataModel generate() throws SQLException {
        DataModel tmp = new DataModel();
        ResultSet rs;
        int numData = 0;
        String query = "select count(*) as cnt from Users.Users";

        rs = stm.executeQuery(query);
        rs.next();
        numData = rs.getInt("cnt");

        if ( numData == 0) {
            System.out.print("No data for generation");
            System.exit(0);
        }

        query = "select Name from Users";
        rs = stm.executeQuery(query);


        shift(numData, rs);
        tmp.name = rs.getString(1);

        query = "select SecondName from Users";
        rs = stm.executeQuery(query);

        shift(numData, rs);
        tmp.surname = rs.getString(1);

        query = "select MiddleName from Users";
        rs = stm.executeQuery(query);

        shift(numData, rs);
        tmp.secondName = rs.getString(1);

        query = "select Age from Users";
        rs = stm.executeQuery(query);

        shift(numData, rs);
        tmp.age = rs.getInt(1);

        query = "select Gender from Users";
        rs = stm.executeQuery(query);

        shift(numData, rs);
        tmp.sex = rs.getBoolean(1);

        query = "select DateOfBirth from Users";
        rs = stm.executeQuery(query);

        shift(numData, rs);
        tmp.dateOfBirth = rs.getString(1);

        query = "select INN from Users";
        rs = stm.executeQuery(query);

        shift(numData, rs);
        tmp.INN = rs.getString(1);

        query = "select PostCode from Users";
        rs = stm.executeQuery(query);

        shift(numData, rs);
        tmp.postIndex = rs.getString(1);

        query = "select Country from Users";
        rs = stm.executeQuery(query);

        shift(numData, rs);
        tmp.country = rs.getString(1);

        query = "select Region from Users";
        rs = stm.executeQuery(query);

        shift(numData, rs);
        tmp.region = rs.getString(1);


        query = "select City from Users";
        rs = stm.executeQuery(query);

        shift(numData, rs);
        tmp.city = rs.getString(1);

        query = "select Street from Users";
        rs = stm.executeQuery(query);

        shift(numData, rs);
        tmp.street = rs.getString(1);

        query = "select House from Users";
        rs = stm.executeQuery(query);

        shift(numData, rs);
        tmp.houseNum = rs.getInt(1);

        query = "select Apartment from Users";
        rs = stm.executeQuery(query);

        shift(numData, rs);
        tmp.flatNum = rs.getInt(1);

        return tmp;
    }

    private void shift(int numData, ResultSet rs) throws SQLException {
        Random r = new Random();
        int times = 1 + abs(r.nextInt() % numData);
        int i = 0;
        rs.next();
        while (i < times-1) {
            rs.next();
            i++;
        }
    }

}
