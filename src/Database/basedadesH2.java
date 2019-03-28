/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Classes.Assignacio;
import Classes.Assignatura;
import Classes.Avaluacio;
import Classes.Professor;
import static Database.basedadesMysql.DB_URL;
import static Database.basedadesMysql.executarQuery;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Víctor
 */
public class basedadesH2 {

    // db parameters
    public static final String DB_URL = "jdbc:h2:./h2db;MV_STORE=FALSE;MVCC=FALSE";
    public static final String USER = "sa";
    public static final String PASS = "";

    /**
     * Mètode per executar una sentència SQL
     *
     * @param query
     */
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

        } catch (ClassNotFoundException e) {
            //Handle errors for Class.forName

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
            }//end finally try
        }//end try
    }

    /**
     * Mètode per afegir estudiants
     *
     * @param nom
     * @param dni
     * @param adreca
     */
    public static void afegirEstudiant(String nom, String dni, String adreca) {

        executarQuery("INSERT INTO estudiant (nom, dni, adreca) VALUES ('" + nom + "','" + dni + "','" + adreca + "')");
    }

    /**
     * Mètode per afegir una assignació
     *
     * @param idProfessor
     * @param curs
     * @param assignatura
     */
    public static void afegirAssignacio(int idProfessor, int curs, int assignatura) {
        executarQuery("INSERT INTO curs (any, id_professor, id_assignatura) VALUES ('" + curs + "','" + idProfessor + "','" + assignatura + "')");
    }

    /**
     * Mètode per obtenir tots els estudiants en objectes Estudiant
     *
     * @return
     */
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
            //STEP 5: Extract data from result set
            try (ResultSet rs = stmt.executeQuery(sql)) {
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
            }
        } catch (SQLException | ClassNotFoundException se) {
            //Handle errors for JDBC

        } //Handle errors for Class.forName
        finally {
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
            }//end finally try
        }//end try      

        return llistaEstudiants;

    }

    /**
     * Mètode per obtenir totes les assignacions en un objecte Assignacions
     *
     * @return
     */
    public static List obtenirAssignacions() {
        List llistaAssignacions = new ArrayList();
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
            String sql = "SELECT c.id, c.any, p.nom as professor, a.nom as assignatura FROM curs c, professor p, assignatura a WHERE c.id_professor = p.id and c.id_assignatura = a.id";
            //STEP 5: Extract data from result set
            try (ResultSet rs = stmt.executeQuery(sql)) {
                //STEP 5: Extract data from result set
                while (rs.next()) {
                    String id = rs.getInt("id") + "";
                    String any = rs.getString("any");
                    String professor = rs.getString("professor");
                    String assignatura = rs.getString("assignatura");
                    Assignacio assignacio = new Assignacio(id, any, professor, assignatura);

                    llistaAssignacions.add(assignacio);
                }
            }
        } catch (SQLException se) {
            //Handle errors for JDBC

        } catch (ClassNotFoundException e) {
            //Handle errors for Class.forName

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
            }//end finally try
        }//end try      

        return llistaAssignacions;

    }

    /**
     * Mètode per obtenir totes les avaluacions en un objecte Avaluacions
     *
     * @return
     */
    public static List obtenirAvaluacions() {
        List llistaAssignatures = new ArrayList();
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
            String sql = "SELECT av.id, ass.nom as assnom, es.nom as esnom, av.nota, av.any FROM avaluacio av, assignatura ass, estudiant es WHERE av.id_assignatura = ass.id and av.id_estudiant = es.id";
            //STEP 5: Extract data from result set
            try (ResultSet rs = stmt.executeQuery(sql)) {
                //STEP 5: Extract data from result set
                while (rs.next()) {
                    String id = rs.getInt("id") + "";
                    String nomAssignatura = rs.getString("assnom");
                    String nomEstudiant = rs.getString("esnom");
                    String nota = rs.getString("nota");
                    String any = rs.getString("any");

                    Avaluacio avaluacio = new Avaluacio(id, nomAssignatura, nomEstudiant, nota, any);

                    llistaAssignatures.add(avaluacio);
                }
            }
        } catch (SQLException se) {
            //Handle errors for JDBC

        } catch (ClassNotFoundException e) {
            //Handle errors for Class.forName

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
            }//end finally try
        }//end try      

        return llistaAssignatures;

    }

    /**
     * Mètode per obtenir una List amb totes les assignatures en el objecte
     * Assignatura
     *
     * @return
     */
    public static List obtenirAssignatures() {
        List llistaAssignatures = new ArrayList();
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
            String sql = "SELECT id, nom, credits, descripcio FROM assignatura";
            //STEP 5: Extract data from result set
            try (ResultSet rs = stmt.executeQuery(sql)) {
                //STEP 5: Extract data from result set
                while (rs.next()) {
                    String id = rs.getInt("id") + "";
                    String nom = rs.getString("nom");
                    String credits = rs.getString("credits");
                    String descripcio = rs.getString("descripcio");

                    Assignatura assignatura = new Assignatura(nom, credits, descripcio, id);

                    llistaAssignatures.add(assignatura);
                }
            }
        } catch (SQLException se) {
            //Handle errors for JDBC

        } catch (ClassNotFoundException e) {
            //Handle errors for Class.forName

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

        return llistaAssignatures;

    }

    /**
     * Mètode per obtenir una List amb tots els objectes Professor
     *
     * @return
     */
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

    /**
     * Mètode per afegir una avaluació
     *
     * @param DNIestudiant
     * @param curs
     * @param dNota
     */
    public static void afegirAvaluacio(String DNIestudiant, int idAssignatura, double dNota, int any) {
        int idEstudiant = obtenirIDEstudiant(DNIestudiant);
        executarQuery("INSERT INTO avaluacio (id_assignatura, id_estudiant, nota, any) VALUES (" + idAssignatura + "," + idEstudiant + "," + dNota + "," + any + ")");
    }

    /**
     * Mètode per obtenir el ID de l'alumne segons el seu DNI
     *
     * @param DNI
     * @return
     */
    private static int obtenirIDEstudiant(String DNI) {
        int id = 1;
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
            String sql = "SELECT id FROM estudiant WHERE dni='" + DNI + "'";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while (rs.next()) {

                id = rs.getInt("id");

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
        return id;
    }

    /**
     * Mètode per afegir una Assignatura
     *
     * @param nom
     * @param credits
     * @param descripcio
     */
    public static void afegirAssignatura(String nom, String credits, String descripcio) {
        executarQuery("INSERT INTO assignatura (nom, credits, descripcio) VALUES ('" + nom + "'," + credits + ",'" + descripcio + "')");
    }

    /**
     * Mètode per afegir un professor
     *
     * @param nom
     * @param departament
     */
    public static void afegirProfessor(String nom, String departament) {
        executarQuery("INSERT INTO professor (nom, departament) VALUES ('" + nom + "','" + departament + "')");
    }

}
