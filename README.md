## วิธีการติดตั้งหรือรันโปรแกรม
สำหรับระบบปฏิบัติการMacOS
1. clone git โปรเจคจาก git hub
2. เข้าไปที่ terminal
3. cd เข้าโฟลเดอร์ project-641-nonnon
4. เมื่อ terminal เข้า project-641-nonnon จากนั้นพิมพ์ cd cs211-431-project/bin
5. เมื่อ terminal เข้าสู่ bin แล้ว จากนั้นพิมพ์ ./launch.sh แล้วกด enter เพื่อเปิดแอปพลิเคชัน
6. หลังจากนั้นตัวแอปพลิเคชันจะเด้งขึ้นมาที่หน้าจอ เป็นอันใช้งานได้

สำหรับระบบปฏิบัติการwindow
1. clone git โปรเจคจาก git hub
2. กดเข้า folder ที่ clone มาจาก git hub
3. กดเปิด folder ที่ชื่อ cs211-431-project
4. กดเปิด folder ที่ชื่อ bin
5. ภายใน folder bin จะมีไฟล์ที่ชื่อว่า launch.sh
6. กดดับเบิลคลิกที่ไฟล์ launch.sh แอปพลิเคชันจะปรากฎขึ้นมา เป็นอันใช้งานได้

## การวางโครงสร้างไฟล์
* project-641-nonnon
  * cs211-431-project (execute file ของ application)
  * images (เก็บรูปภาพของผู้ใช้งาน และสินค้า)
  * member
    * member.csv (เก็บข้อมูลของผู้ใช้ระบบ และแอดมิน)
  * Order 
    * Order.csv (เก็บข้อมูลการสั่งสินค้าทั้งหมด)
  * Product 
    * Product.csv (เก็บข้อมูลของสินค้าทั้งหมด)
  * Report 
    * Report.csv (เก็บข้อมูลของการรายงานความไม่เหมาะสมของผู้ใช้งานและสินค้า)
  * ReviewProduct 
    * ReviewProduct.csv (เก็บข้อมูลของการรีวิวสินค้า)
  * src 
    * java (เป็นส่วนของโค้ด ซึ่งเก็บโฟลเดอร์ย่อย คือ controllers , models, services และ App.java เป็นไฟล์กำหนดค่าเบื้องต้นในการรันโปรแกรม)
    * resources (ประกอบไปด้วยโฟลเดอร์ image ซึ่งเก็บรุปภาพที่ใช้ใน UI และโฟลเดอร์ ku.cs ที่เป็นส่วนของ UI ทั้งหมด)
 * UML_project-641-nonnon.png (เป็นไฟล์ที่เก็บรูปภาพของ UML)
 * วิธีใช้งานแอพพลิเคชั่น.pdf (เป็นไฟล์ PDF ที่เขียนวิธีการใช้งานเบื้องต้นของ applicaton)
          

## ตัวอย่างข้อมูลผู้ใช้ระบบ
* (seller) (piggybooboo) (piggy123) (ถูกแบน)
* (seller) (cheetah) (lee)
* (seller) (mixxiw) (sahaphap227)
* (seller) (tootee) (tootee)
* (customer) (khaotungg) (kt123)
* (customer) (snowbrewww) (somporn)
* (customer) (do0_nct) (doyoung)