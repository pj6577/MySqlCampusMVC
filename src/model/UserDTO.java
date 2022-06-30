package model;

public class UserDTO {
    private int id;
    private String userName;
    private String passWord;
    private int userClass;
    
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
    public int getUserClass() {
        return userClass;
    }
    public void setUserClass(int userClass) {
        this.userClass = userClass;
    }
    
    public boolean equals(Object o ) {
        if(o instanceof UserDTO) {
            UserDTO u = (UserDTO)o;
            return id == u.id;
        }
        return false;
    }
    
}
