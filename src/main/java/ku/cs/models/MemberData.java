package ku.cs.models;

public class MemberData {

    private String name;
    private String username;
    private String password;
    private String shopName;
    private String localDateTime;
    private String imagePath;
    private int limit;
    private boolean userBan;
    private int tryToLogin;

    public MemberData (String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public MemberData(String name, String username, String password, String localDateTime) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.localDateTime = localDateTime;
        this.imagePath = "images/default_pfp.JPG";
    }

    public MemberData(String name, String username, String password, String localDateTime, String imagePath) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.localDateTime = localDateTime;
        this.imagePath = imagePath;
    }

    public MemberData(String name, String username, String password, String shopName, String localDateTime, String imagePath, int limit, boolean userBan, int tryToLogin) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.shopName = shopName;
        this.localDateTime = localDateTime;
        this.imagePath = imagePath;
        this.limit = limit;
        this.userBan = userBan;
        this.tryToLogin = tryToLogin;
        System.out.println(imagePath);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getShopName() {
        return shopName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public boolean isUserBan() {
        return userBan;
    }

    public void setUserBan(boolean userBan) {
        this.userBan = userBan;
    }

    public void setTryToLogin(int tryToLogin) {
        this.tryToLogin = tryToLogin;
    }

    public int getTryToLogin() {
        return tryToLogin;
    }

    //พยายามเข้าสู่ระบบ
    public void setTryToLogin() {
        this.tryToLogin++;
    }

    //banUser
    public void forUserBan() {
        if (userBan) {
            setUserBan(false); //เเบน
            this.tryToLogin = 0;
        }
        else {
            setUserBan(true); //ยกเลิก
        }
    }

    public String toCSV() {
        return "Customer," +
                name + "," +
                username + "," +
                password + "," +
                shopName + "," +
                localDateTime+ "," +
                imagePath + "," +
                limit+ "," +
                userBan + "," +
                tryToLogin;
    }

    @Override
    public String toString() {
        String ban = "not ban";
        if (userBan) {
            ban = "ban";
        }
        return "name : " + name + "\n" + "username : " + username + "\n" + "date : " + localDateTime + "\n" + "status : " + ban;

    }

}
