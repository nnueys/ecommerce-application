package ku.cs.models;

public class AdminData extends MemberData{

    public AdminData(String name, String username, String password){
        super(name, username, password);
    }

    public String toCSV() {
        return "Admin," +
                getName() + "," +
                getUsername()+ "," +
                getPassword();
    }
}
