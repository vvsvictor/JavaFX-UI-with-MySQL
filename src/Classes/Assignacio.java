
package Classes;

/**
 * Classe Assignacio
 * @author Víctor Vivancos Serrano
 * (Tot String per afegir-ho com a text al textField)
 */
public class Assignacio {
    private String id;
    private String any;
    private String id_professor;
    private String id_Assignatura;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAny() {
        return any;
    }

    public void setAny(String any) {
        this.any = any;
    }

    public String getId_professor() {
        return id_professor;
    }

    public void setId_professor(String id_professor) {
        this.id_professor = id_professor;
    }

    public String getId_Assignatura() {
        return id_Assignatura;
    }

    public void setId_Assignatura(String id_Assignatura) {
        this.id_Assignatura = id_Assignatura;
    }

    public Assignacio(String id, String any, String id_professor, String id_Assignatura) {
        this.id = id;
        this.any = any;
        this.id_professor = id_professor;
        this.id_Assignatura = id_Assignatura;
    }
    
}
