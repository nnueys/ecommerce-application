package ku.cs.services;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class ProductShopListFileDataSource {
    public String writeDataProductShop(String nameProductShop, String descriptionProductShop, String priceProductShop, String quantityProductShop) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("dataAddProductShop.csv", true);
            BufferedWriter out = new BufferedWriter(fileWriter);
            out.write(nameProductShop + "," + descriptionProductShop + "," + priceProductShop + "," + quantityProductShop);
            out.newLine();
            out.flush();
            return "pass";
        } catch (FileNotFoundException e) {
            System.err.println("เปิดไฟล์ไม่ได้");
        } catch (IOException e) {
            System.err.println("เกิดข้อผิดพลาด");
        } finally {
            try {
                if (fileWriter != null)
                    fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "บันทึกไม่สำเร็จ";
    }



}
