package ku.cs.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    public static void main(String[] args) {
        //dates
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        //date time formatter
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd / MM / yyyy  HH:mm:ss");
        String formatDateTime = now.format(format);
        System.out.println(formatDateTime);
    }
}
