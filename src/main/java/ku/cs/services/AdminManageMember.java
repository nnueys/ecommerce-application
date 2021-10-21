package ku.cs.services;

import ku.cs.models.MemberDatalist;

public class AdminManageMember {
    public void manageMember() {
        DataSource<MemberDatalist> dataSource;
        String directory = "member";
        String filename = "member.csv";

        dataSource = new MemberDataFileDataSource(directory, filename);
        MemberDatalist memberDatalist = dataSource.readData();
    }
}
