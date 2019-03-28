/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Víctor
 */
public class basedadesH2 {
    // db parameters
    public static final String DB_URL = "jdbc:h2:./h2db;MV_STORE=FALSE;MVCC=FALSE";
    public static final String DB_USER = "sa";
    public static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        DBConnect();
    }

    public static void DBConnect() {
        Connection conn = null;
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            System.out.println("Connection to H2 has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
