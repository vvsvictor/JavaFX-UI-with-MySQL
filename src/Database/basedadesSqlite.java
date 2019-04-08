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
public class basedadesSqlite {

    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:sqlite:sqlite.db";

    /**
     * Mètode per comprovar si la base de dades existeix
     *
     * @return
     */
    public static boolean baseDadesExisteix() {
        boolean existeix = false;

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.sqlite.JDBC");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'DBClass'";
            //STEP 5: Extract data from result set
            try (ResultSet rs = stmt.executeQuery(sql)) {
                //STEP 5: Extract data from result set
                while (rs.next()) {
                    String name = rs.getString("SCHEMA_NAME");
                    existeix = true;
                    //Retrieve by column name
                }
            }
        } catch (SQLException | ClassNotFoundException se) {
            //Handle errors for JDBC

        } //Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try {
                assert conn!=null;
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
        return existeix;
    }

    /**
     * Mètode per executar una sentència SQL
     *
     * @param query
     * @return
     * @throws java.sql.SQLException
     */
    public static boolean executarQuery(String query) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.sqlite.JDBC");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            return true;

        } catch (SQLException | ClassNotFoundException se) {
            //Handle errors for JDBC
            return false;
        } //Handle errors for Class.forName
        finally {
            //finally block used to close resources
            assert stmt!=null;
            assert conn!=null;
            stmt.close();
            conn.commit();
            conn.close();
        }//end try
    }

    /**
     * Mètode per afegir estudiants
     *
     * @param nom
     * @param dni
     * @param adreca
     * @return
     */
    public static boolean afegirEstudiant(String nom, String dni, String adreca) {
        try {
            return executarQuery("INSERT INTO estudiant (nom, dni, adreca) VALUES ('" + nom + "','" + dni + "','" + adreca + "');");
        } catch (SQLException e) {
            return false;
        }

    }

    /**
     * Mètode per afegir una assignació
     *
     * @param idProfessor
     * @param curs
     * @param assignatura
     * @return
     */
    public static boolean afegirAssignacio(int idProfessor, int curs, int assignatura) {
        try {
            return executarQuery("INSERT INTO curs (any, id_professor, id_assignatura) VALUES ('" + curs + "','" + idProfessor + "','" + assignatura + "');");

        } catch (SQLException e) {
            return false;
        }

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
            Class.forName("org.sqlite.JDBC");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
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
            Class.forName("org.sqlite.JDBC");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
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
            Class.forName("org.sqlite.JDBC");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
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
            Class.forName("org.sqlite.JDBC");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
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
            Class.forName("org.sqlite.JDBC");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT id, nom, departament FROM professor";
            //STEP 5: Extract data from result set
            try (ResultSet rs = stmt.executeQuery(sql)) {
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
     * Mètode per afegir una avaluació
     *
     * @param DNIestudiant
     * @param idAssignatura
     * @param any
     * @param dNota
     * @return
     */
    public static boolean afegirAvaluacio(String DNIestudiant, int idAssignatura, double dNota, int any) {
        try {
            int idEstudiant = obtenirIDEstudiant(DNIestudiant);
            return executarQuery("INSERT INTO avaluacio (id_assignatura, id_estudiant, nota, any) VALUES (" + idAssignatura + "," + idEstudiant + "," + dNota + "," + any + ");");
        } catch (SQLException e) {
            return false;
        }

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
            Class.forName("org.sqlite.JDBC");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT id FROM estudiant WHERE dni='" + DNI + "'";
            //STEP 5: Extract data from result set
            try (ResultSet rs = stmt.executeQuery(sql)) {
                //STEP 5: Extract data from result set
                while (rs.next()) {

                    id = rs.getInt("id");

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
        return id;
    }

    /**
     * Mètode per afegir una Assignatura
     *
     * @param nom
     * @param credits
     * @param descripcio
     * @return
     */
    public static boolean afegirAssignatura(String nom, String credits, String descripcio) {
        try {
            return executarQuery("INSERT INTO assignatura (nom, credits, descripcio) VALUES ('" + nom + "'," + credits + ",'" + descripcio + "');");
        } catch (SQLException e) {
            return false;
        }

    }

    /**
     * Mètode per afegir un professor
     *
     * @param nom
     * @param departament
     * @return
     */
    public static boolean afegirProfessor(String nom, String departament) {
        try {
            return executarQuery("INSERT INTO professor (nom, departament) VALUES ('" + nom + "','" + departament + "');");
        } catch (SQLException e) {
            return false;
        }

    }

    /**
     * Mètode principal per crear la base de dades
     *
     * @param args
     */
    public static void main(String[] args) {
        List test = obtenirProfessors();
    }

}
