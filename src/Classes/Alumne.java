package Classes;

/**
 * Classe Alumne
 * @author Víctor Vivancos
 * (Tot String per afegir-ho com a text al textField)
 */
public class Alumne {

    private String id;
    private String nom;
    private String dni;
    private String adreca;

    /**
     * Mètode Constructor
     *
     * @param id
     * @param nom
     * @param dni
     * @param adreca
     */
    public Alumne(String id, String nom, String dni, String adreca) {
        this.id = id;
        this.nom = nom;
        this.dni = dni;
        this.adreca = adreca;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

}
