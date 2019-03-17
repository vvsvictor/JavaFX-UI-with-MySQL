
package Classes;

public class Assignatura {
    private String nom;
    private String credits;
    private String descripcio;
    private String  id;

    public Assignatura(String nom, String credits, String descripcio, String id) {
        this.nom = nom;
        this.credits = credits;
        this.descripcio = descripcio;
        this.id = id;
    }
    
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id=id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }
    
}
