package model;

public class StaffDTO {
    private int id;
    private String staffName;
    
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getStaffName() {
        return staffName;
    }
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    
    public boolean equals(Object o ) {
        if(o instanceof StaffDTO) {
            StaffDTO s = (StaffDTO)o;
            return id == s.id;
        }
        return false;
    }
    
    
}
