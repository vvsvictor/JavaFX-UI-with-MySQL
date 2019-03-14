package Database;

import Classes.Professor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class basedades {
    

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public static boolean baseDadesExisteix() {
        boolean existeix = false;

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'DBClass'";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                String name = rs.getString("SCHEMA_NAME");
                existeix = true;
                //Retrieve by column name
            }
            rs.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return existeix;
    }

    public static void crearBD() {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating database...");
            stmt = conn.createStatement();

            String sql = "CREATE DATABASE DBClass";
            stmt.executeUpdate(sql);

            String usesql = "USE DBClass";
            stmt.executeUpdate(usesql);

            String sqlestudiant = "CREATE TABLE estudiant( id int not null auto_increment PRIMARY KEY, nom varchar(255), dni varchar(20), adreca varchar(255) )";
            stmt.executeUpdate(sqlestudiant);

            String sqlassignatura = "CREATE TABLE assignatura( id int not null auto_increment PRIMARY KEY, nom varchar(255), credits int, descripcio varchar(255) )";
            stmt.executeUpdate(sqlassignatura);

            String sqlprofessor = "CREATE TABLE professor( id int not null auto_increment PRIMARY KEY, nom varchar(255), departament varchar(255) )";
            stmt.executeUpdate(sqlprofessor);

            String sqlavaluacio = "CREATE TABLE avaluacio( id int not null auto_increment PRIMARY KEY, id_assignatura int NOT NULL, id_estudiant int NOT NULL, nota float, any int, FOREIGN KEY (id_assignatura) REFERENCES assignatura(id), FOREIGN KEY (id_estudiant) REFERENCES estudiant(id) )";
            stmt.executeUpdate(sqlavaluacio);

            String sqlcurs = "CREATE TABLE curs( id int not null auto_increment PRIMARY KEY, any int, id_professor int NOT NULL, id_assignatura int NOT NULL, FOREIGN KEY (id_assignatura) REFERENCES assignatura(id), FOREIGN KEY (id_professor) REFERENCES professor(id) )";
            stmt.executeUpdate(sqlcurs);

            System.out.println("Base de dades creada");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

    }

    public static void executarQuery(String query) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate("USE DBClass");
            stmt.executeUpdate(query);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public static void afegirEstudiant(String nom, String dni, String adreca) {
 
        executarQuery("INSERT INTO estudiant (nom, dni, adreca) VALUES ('" + nom + "','" + dni + "','" + adreca + "')");
    }

    public static List obtenirEstudiants() {
        List llistaEstudiants = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String usesql = "USE DBClass";
            stmt.executeUpdate(usesql);
            String sql = "SELECT id, nom, dni, adreca FROM estudiant";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                /* 
                Posició 0: ID
                Posició 1: Nom
                Posició 2: DNI
                Posició 3: Adreça
                 */
                String[] estudiant = new String[4];
                //Retrieve by column name
                estudiant[0] = rs.getInt("id") + "";
                estudiant[1] = rs.getString("nom");
                estudiant[2] = rs.getString("dni");
                estudiant[3] = rs.getString("adreca");

                llistaEstudiants.add(estudiant);
            }
            rs.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try      

        return llistaEstudiants;

    }
    
    public static List obtenirProfessors() {
        List llistaEstudiants = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String usesql = "USE DBClass";
            stmt.executeUpdate(usesql);
            String sql = "SELECT id, nom, departament FROM professor";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                /* 
                Posició 0: ID
                Posició 1: Nom
                Posició 2: Departament
                 */
                String id = rs.getInt("id") + "";
                String nom = rs.getString("nom");
                String departament = rs.getString("departament");
                
                Professor professor = new Professor(id, nom, departament);

                llistaEstudiants.add(professor);
            }
            rs.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try      

        return llistaEstudiants;

    }

    public static void afegirAssignatura(String nom, String credits, String descripcio) {
        executarQuery("INSERT INTO assignatura (nom, credits, descripcio) VALUES ('" + nom + "','" + credits + "','" + descripcio + "'");
    }

    public static void afegirProfessor(String nom, String departament) {
        executarQuery("INSERT INTO professor (nom, departament) VALUES ('" + nom + "','" + departament + "'");
    }

    public static void main(String[] args) {
        if (baseDadesExisteix()) {
            System.out.println("Existeix");
        } else {
            System.out.println("No existeix");
            crearBD();
        }
    }
}
