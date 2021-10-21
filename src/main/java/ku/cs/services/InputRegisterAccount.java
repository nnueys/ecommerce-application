package ku.cs.services;

import ku.cs.models.MemberData;
import ku.cs.models.MemberDatalist;

import java.time.format.DateTimeFormatter;

public class InputRegisterAccount implements DataSource<MemberDatalist> {

    private String name;
    private String username;
    private String password;
    private String confirmPassword;
    private String shopName;
    private String localDateTime;

    private MemberDatalist memberList;
    private MemberData memberData;

    public String memberData(String name, String username, String password, String confirmPassword,String localDateTime, String imagePath) {
        if (name.equals("")) {
            return "please enter name";
        }else if (username.equals("")) {
            return "please enter username";
        }else if (password.equals("")) {
            return ("please enter password");
        } else if (confirmPassword.equals("")) {
            return("please confirm password");
        } else if (!password.equals(confirmPassword)) {
            return("password and confirm password are not match");
        }

        DataSource<MemberDatalist> dataSource;
        String directory = "member";
        String filename = "member.csv";

        dataSource = new MemberDataFileDataSource(directory, filename);
        MemberDatalist memberDatalist = dataSource.readData();
        if (memberDatalist.checkUsername(username)) {
            return "this username has already used";
        }

        if (imagePath == null) {
            memberDatalist.addMember(new MemberData(name,username,password,localDateTime));
            dataSource.writeData(memberDatalist);
            return"pass";
        }
        memberDatalist.addMember(new MemberData(name,username,password,localDateTime,imagePath));
        dataSource.writeData(memberDatalist);
        return"pass";
    }

    public boolean userCheck(String username, String password) {
        DataSource<MemberDatalist> dataSource;
        String directory = "member";
        String filename = "member.csv";

        dataSource = new MemberDataFileDataSource(directory, filename);
        MemberDatalist memberDatalist = dataSource.readData();

        for(MemberData member: memberDatalist.getMemberData() ) {
            if (member.getUsername().equals(username)) {
                if (member.getPassword().equals(password)) {
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd / MM / yyyy  HH:mm:ss ");
                    member.setLocalDateTime(java.time.LocalDateTime.now().format(format));
                    return true;
                }
            }
        }
        return false;
    }

    public MemberData callUser(String username, String password) {
        DataSource<MemberDatalist> dataSource;
        String directory = "member";
        String filename = "member.csv";

        dataSource = new MemberDataFileDataSource(directory, filename);
        MemberDatalist memberDatalist = dataSource.readData();

        for(MemberData member: memberDatalist.getMemberData() ) {
            if (member.getUsername().equals(username)) {
                if (member.getPassword().equals(password)) {
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd / MM / yyyy  HH:mm:ss ");
                    member.setLocalDateTime(java.time.LocalDateTime.now().format(format));
                    dataSource.writeData(memberDatalist);
                    return member;
                }
            }
        }
        return null;
    }

    public boolean register(String username, String password, String confirmPassword) {
        if (password.equals("")) {
            System.out.println("ยังไม่ได้กรอกรหัสผ่าน");
            return false;
        } else if (confirmPassword.equals("")) {
            System.out.println("ยังไม่ได้ confirm รหัสผ่าน");
            return false;
        } else if (!password.equals(confirmPassword)) {
            System.out.println("รหัสผ่านไม่ตรงกัน");
            return false;
        }else {
            return true;
        }
    }

    @Override
    public MemberDatalist readData() {
        return memberList;
    }

    @Override
    public void writeData(MemberDatalist memberList) {
        this.memberList = memberList;
    }
}