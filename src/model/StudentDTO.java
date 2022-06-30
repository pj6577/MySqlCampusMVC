package model;

public class StudentDTO {
    private int id;
    private String StudentName;
    private String StudentClass;
    private String ClassScore;
    
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getStudentName() {
        return StudentName;
    }
    public void setStudentName(String studentName) {
        StudentName = studentName;
    }
    public String getStudentClass() {
        return StudentClass;
    }
    public void setStudentClass(String studentClass) {
        StudentClass = studentClass;
    }
    public String getClassScore() {
        return ClassScore;
    }
    public void setClassScore(String classScore) {
        ClassScore = classScore;
    }
    
    public boolean equals(Object o ) {
        if(o instanceof StudentDTO) {
            StudentDTO st = (StudentDTO) o;
            return id == st.id;
        }
        return false;
    }
     
    
}
