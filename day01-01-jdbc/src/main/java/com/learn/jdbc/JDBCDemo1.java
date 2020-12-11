package com.learn.jdbc;

import java.sql.*;

public class JDBCDemo1 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost/spring?useSSL=false&serverTimezone=UTC",
                        "root",
                        "root");
        PreparedStatement ps = connection.prepareStatement("select * from account");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt("id")
                    + " " + rs.getString("name")
                    + " " + rs.getFloat("money"));
        }

        rs.close();
        ps.close();
        connection.close();
    }
}
