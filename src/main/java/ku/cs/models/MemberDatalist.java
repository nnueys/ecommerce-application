package ku.cs.models;

import ku.cs.services.MemberDataSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MemberDatalist {
    private ArrayList<MemberData> newData;

    public MemberDatalist() {
        newData = new ArrayList<>();
    }

    public void addMember(MemberData data) {
        this.newData.add(data);
    }

    public ArrayList<MemberData> getMemberData() {
        return this.newData;
    }

    public ArrayList<MemberData> getAllMembers() {
        return newData;
    }

    public ArrayList<MemberData> sort(ArrayList<MemberData> member) {
        Comparator<MemberData> memberDataComparator = new MemberDataSort();
        Collections.sort(member, memberDataComparator);
        return member;
    }

//    public void remove(String username) {
//        MemberData member = null;
//        for (MemberData memberData: newData) {
//            if (memberData.getUsername().equals(username)) {
//                member = memberData;
//            }
//        } newData.remove(member);
//    }

    public boolean checkUsername(String username) {
        for (MemberData memberData : newData) {
            if (memberData.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    //public int countData() {
//        return this.newData.size();
//    }

    public String toCSV() {
        String result = "";
        for (MemberData data : this.newData) {
            result += data.toCSV() + "\n";
        }
        return result;
    }

    public String recordChangePassword(String name, String username, String password, String oldPassword, String newPassword, String confirmNewPassword) {
        if (oldPassword.equals("")) {
            return "please enter old password";
        } else if (newPassword.equals("")) {
            return "please enter new password";
        } else if (confirmNewPassword.equals("")) {
            return "plead confirm order";
        } else if ((newPassword.equals(oldPassword)) || (confirmNewPassword.equals(oldPassword))) {
            return "new password can't be as old password";
        } else if (!newPassword.equals(confirmNewPassword)) {
            return "new password and confirm password are not match";
        } else if (!password.equals(oldPassword)) {
            return "please enter correct old password";
        }
        for (MemberData member : newData) {
            if (member.getUsername().equals(username)) {
                member.setPassword(newPassword);
                return "pass";
            }
        }
        return "false";
    }

    public String recordShopName(String name, String username, String password,String oldShopName, String shopName) {
        for (MemberData member : newData) {
            if (member.getUsername().equals(username)) {
                member.setShopName(shopName);
                return "pass";
            }
        }
        return "false";
    }

    public String recordLimit(String name, String username, String password, String shopName, int limit) {
        for (MemberData member : newData) {
            if (member.getUsername().equals(username)) {
                member.setLimit(limit);

                return "pass";
            }
        }
        return "false";
    }

    public String recordTryToLogin (String username, int tryToLogin) {
        for (MemberData member : newData) {
            if (member.getUsername().equals(username)) {
                member.setTryToLogin(tryToLogin);
                return "pass";
            }
        }
        return "false";
    }



}
