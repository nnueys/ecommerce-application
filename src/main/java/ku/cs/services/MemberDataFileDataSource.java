package ku.cs.services;

import ku.cs.models.AdminData;
import ku.cs.models.MemberData;
import ku.cs.models.MemberDatalist;

import java.io.*;

public class MemberDataFileDataSource implements DataSource<MemberDatalist> {
    private String directoryName;
    private String filename;

    public MemberDataFileDataSource() {
        this("member", "member.csv");
    }


    public MemberDataFileDataSource(String directoryName, String filename) { // test use
        this.directoryName = directoryName;
        this.filename = filename;
        initialFileIfNotExist();
    }

    private void initialFileIfNotExist() {
        File file = new File(directoryName);
        if (!file.exists()) {
            file.mkdir();
        }
        String path = directoryName + File.separator + filename;
        file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public MemberDatalist readData() {
        MemberDatalist memberDatalist = new MemberDatalist();
        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                String type = data[0].trim();
                if (type.equals("Customer")) {
                    memberDatalist.addMember(new MemberData(
                            data[1].trim(), // name
                            data[2].trim(), // username
                            data[3].trim(), // password
                            data[4].trim(), // shop name
                            data[5].trim(),// local date time
                            data[6].trim(), //image path
                            Integer.parseInt(data[7].trim()),//limit
                            Boolean.parseBoolean(data[8].trim()),// banUser
                            Integer.parseInt(data[9].trim()) //try To Login
                    ));
                } else if (type.equals("Admin")) {
                    memberDatalist.addMember(new AdminData(
                            data[1].trim(),// name
                            data[2].trim(), // username
                            data[3].trim()// password

                    ));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return memberDatalist;
        }
    }

    @Override
    public void writeData(MemberDatalist memberDatalist) {
        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            buffer.write(memberDatalist.toCSV());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
