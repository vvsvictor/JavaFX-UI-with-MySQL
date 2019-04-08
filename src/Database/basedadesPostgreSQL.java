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

public class basedadesPostgreSQL {
    // JDBC driver name and database URL

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/dbclass";

    //  Database credentials
    static final String USER = "postgres";
    static final String PASS = "bemen3";

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
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
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
                assert conn != null;
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
     * Mètode per crear la base de dades
     */
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

            String sqlavaluacio = "CREATE TABLE avaluacio( id int not null auto_increment PRIMARY KEY, id_assignatura int NOT NULL, id_estudiant int NOT NULL, nota float, year int, FOREIGN KEY (id_assignatura) REFERENCES assignatura(id), FOREIGN KEY (id_estudiant) REFERENCES estudiant(id) )";
            stmt.executeUpdate(sqlavaluacio);

            String sqlcurs = "CREATE TABLE curs( id int not null auto_increment PRIMARY KEY, year int, id_professor int NOT NULL, id_assignatura int NOT NULL, FOREIGN KEY (id_assignatura) REFERENCES assignatura(id), FOREIGN KEY (id_professor) REFERENCES professor(id) )";
            stmt.executeUpdate(sqlcurs);

            System.out.println("Base de dades creada");
        } catch (SQLException | ClassNotFoundException se) {
            //Handle errors for JDBC

        } //Handle errors for Class.forName
        finally {
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
     * Mètode per executar una sentència SQL
     *
     * @param query
     * @return Comprovació d'execució correcte
     */
    public static boolean executarQuery(String query) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            return true;

        } catch (SQLException | ClassNotFoundException se) {
            //Handle errors for JDBC
            return false;

        } //Handle errors for Class.forName
        finally {
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
     * @return
     */
    public static boolean afegirEstudiant(String nom, String dni, String adreca) {

        return executarQuery("INSERT INTO estudiant (nom, dni, adreca) VALUES ('" + nom + "','" + dni + "','" + adreca + "');");
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
        return executarQuery("INSERT INTO curs (year, id_professor, id_assignatura) VALUES ('" + curs + "','" + idProfessor + "','" + assignatura + "');");
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
                assert conn != null;
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
            String sql = "SELECT c.id, c.year, p.nom as professor, a.nom as assignatura FROM curs c, professor p, assignatura a WHERE c.id_professor = p.id and c.id_assignatura = a.id";
            //STEP 5: Extract data from result set
            try (ResultSet rs = stmt.executeQuery(sql)) {
                //STEP 5: Extract data from result set
                while (rs.next()) {
                    String id = rs.getInt("id") + "";
                    String year = rs.getString("year");
                    String professor = rs.getString("professor");
                    String assignatura = rs.getString("assignatura");
                    Assignacio assignacio = new Assignacio(id, year, professor, assignatura);

                    llistaAssignacions.add(assignacio);
                }
            }
        } catch (SQLException | ClassNotFoundException se) {
            //Handle errors for JDBC

        } //Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try {
                assert conn != null;
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
            String sql = "SELECT av.id, ass.nom as assnom, es.nom as esnom, av.nota, av.year FROM avaluacio av, assignatura ass, estudiant es WHERE av.id_assignatura = ass.id and av.id_estudiant = es.id";
            //STEP 5: Extract data from result set
            try (ResultSet rs = stmt.executeQuery(sql)) {
                //STEP 5: Extract data from result set
                while (rs.next()) {
                    String id = rs.getInt("id") + "";
                    String nomAssignatura = rs.getString("assnom");
                    String nomEstudiant = rs.getString("esnom");
                    String nota = rs.getString("nota");
                    String year = rs.getString("year");

                    Avaluacio avaluacio = new Avaluacio(id, nomAssignatura, nomEstudiant, nota, year);

                    llistaAssignatures.add(avaluacio);
                }
            }
        } catch (SQLException | ClassNotFoundException se) {
            //Handle errors for JDBC

        } //Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try {
                assert conn != null;
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
                assert conn != null;
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
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
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
                assert conn != null;
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
     * @param year
     * @param dNota
     * @return
     */
    public static boolean afegirAvaluacio(String DNIestudiant, int idAssignatura, double dNota, int year) {
        int idEstudiant = obtenirIDEstudiant(DNIestudiant);
        System.out.println("INSERT INTO avaluacio (id_assignatura, id_estudiant, nota, year) VALUES (" + idAssignatura + "," + idEstudiant + "," + dNota + "," + year + ");");
        return executarQuery("INSERT INTO avaluacio (id_assignatura, id_estudiant, nota, year) VALUES (" + idAssignatura + "," + idEstudiant + "," + dNota + "," + year + ");");
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
                assert conn != null;
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
        return executarQuery("INSERT INTO assignatura (nom, credits, descripcio) VALUES ('" + nom + "'," + credits + ",'" + descripcio + "');");
    }

    /**
     * Mètode per afegir un professor
     *
     * @param nom
     * @param departament
     * @return
     */
    public static boolean afegirProfessor(String nom, String departament) {
        return executarQuery("INSERT INTO professor (nom, departament) VALUES ('" + nom + "','" + departament + "');");
    }

    /**
     * Mètode principal per crear la base de dades
     *
     * @param args
     */
    public static void main(String[] args) {
        if (baseDadesExisteix()) {
            System.out.println("Existeix");
        } else {
            System.out.println("No existeix");
            crearBD();
        }
    }
}
