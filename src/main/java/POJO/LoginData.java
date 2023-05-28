package POJO;

public class LoginData {
    private String username;
    private String password;
    private String invalidUsername;
    private String invalidPass;

    public LoginData(String _username, String _password, String _invalidUsername, String _invalidPass){
        this.username = _username;
        this.password = _password;
        this.invalidUsername = _invalidUsername;
        this.invalidPass = _invalidPass;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getInvalidUsername() { return invalidUsername; }
    public String getInvalidPass() { return invalidPass; }
}
