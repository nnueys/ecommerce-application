package ku.cs.services;


import ku.cs.models.MemberData;

import java.util.Comparator;

public class MemberDataSort implements Comparator<MemberData> {

    @Override
    public int compare(MemberData o1, MemberData o2) {
        if (o1.getLocalDateTime().compareTo( o2.getLocalDateTime()) > 0) return -1;
        else if ( (o1.getLocalDateTime().compareTo( o2.getLocalDateTime()) < 0) ) {
            return 1;
        }else {
            return 0;
        }
    }
}
