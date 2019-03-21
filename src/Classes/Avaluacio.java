package Classes;

public class Avaluacio {

    private String id;
    private String assignatura;
    private String estudiant;
    private String nota;
    private String any;

    public Avaluacio(String id, String assignatura, String estudiant, String nota, String any) {
        this.id = id;
        this.assignatura = assignatura;
        this.estudiant = estudiant;
        this.nota = nota;
        this.any = any;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssignatura() {
        return assignatura;
    }

    public void setAssignatura(String assignatura) {
        this.assignatura = assignatura;
    }

    public String getEstudiant() {
        return estudiant;
    }

    public void setEstudiant(String estudiant) {
        this.estudiant = estudiant;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getAny() {
        return any;
    }

    public void setAny(String any) {
        this.any = any;
    }
    
    

}
