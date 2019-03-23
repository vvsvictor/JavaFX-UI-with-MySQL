
package Classes;
/**
 * Classe Professor
 * @author Víctor Vivancos Serrano
 * (Tot String per afegir-ho com a text al textField)
 */
public class Professor {
    private String id;
    private String nom;
    private String departament;

    public Professor(String id, String nom, String departament) {
        this.id = id;
        this.nom = nom;
        this.departament = departament;
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

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }
    
}
