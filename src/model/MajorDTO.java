package model;

public class MajorDTO {
    private int id;
    private int korean;
    private int english;
    private int math;
    private String classScore;
    private String majorName;
    private String professor;
    
    
    public String getMajorName() {
        return majorName;
    }
    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
    public String getProfessor() {
        return professor;
    }
    public void setProfessor(String professor) {
        this.professor = professor;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getKorean() {
        return korean;
    }

    public void setKorean(int korean) {
        this.korean = korean;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public String getClassScore() {
        return classScore;
    }

    public void setClassScore(String classScore) {
        this.classScore = classScore;
    }
    
    public boolean equals(Object o ) {
        if(o instanceof MajorDTO) {
            MajorDTO m = (MajorDTO)o;
            return id == m.id;
        }
        return false;
    }

}
