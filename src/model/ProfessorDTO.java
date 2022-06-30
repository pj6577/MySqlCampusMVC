package model;


public class ProfessorDTO {
    
    
    private int id;
    private String professorName;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getProfessorName() {
        return professorName;
    }
    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }
    
    public boolean equals(Object o ) {
        if(o instanceof ProfessorDTO) {
            ProfessorDTO p = (ProfessorDTO) o;
            return id == p.id;
        }
        return false;
    }
    
}
