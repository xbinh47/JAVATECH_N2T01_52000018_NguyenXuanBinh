import dao.ManufactureDAO;
import dao.PhoneDAO;
import domain.Phone;
import domain.Manufacture;
import org.hibernate.Session;
import utils.HibernateUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Program {
    static Session session = HibernateUtils.getFactory().openSession();
    static PhoneDAO phoneDAO = new PhoneDAO(session);
    static ManufactureDAO manufactureDAO = new ManufactureDAO(session);
    public static void init(){
        Phone p1 = new Phone("Samsung Galaxy S21", 7990000, "Phantom Black", "South Korea", 50);
        Phone p2 = new Phone("Apple iPhone 12", 99900000, "White", "United States", 40);
        Phone p3 = new Phone("Xiaomi Mi 11", 79900000, "Horizon Pink", "China", 30);
        Phone p4 = new Phone("Nokia 8.3 5G", 69900000, "Polar Night", "Finland", 20);
        Phone p5 = new Phone("Oppo Find X3 Pro", 11990000, "Gloss Black", "China", 10);
        session.save(p1);
        session.save(p2);
        session.save(p3);
        session.save(p4);
        session.save(p5);
        Manufacture m1 = new Manufacture("Samsung Electronics", "South Korea", 320671);
        Set<Phone> s1 = new HashSet<>();
        s1.add(p1);
        m1.setItems(s1);
        session.save(m1);
        Manufacture m2 = new Manufacture("Apple Inc.", "United States", 147000);
        Set<Phone> s2 = new HashSet<>();
        s2.add(p2);
        m2.setItems(s2);
        session.save(m2);
        Manufacture m3 = new Manufacture("Xiaomi Corporation", "China", 20000);
        Set<Phone> s3 = new HashSet<>();
        s3.add(p3);
        m3.setItems(s3);
        session.save(m3);
        Manufacture m4 = new Manufacture("Nokia Corporation", "Finland", 102000);
        Set<Phone> s4 = new HashSet<>();
        s4.add(p4);
        m4.setItems(s4);
        session.save(m4);
        Manufacture m5 = new Manufacture("Guangdong Oppo Mobile Telecommunications Corp., Ltd.", "China", 40000);
        Set<Phone> s5 = new HashSet<>();
        s5.add(p5);
        m5.setItems(s5);
        session.save(m5);
    }

    public static void main(String[] args) {
        session.beginTransaction();


        init();

        //
        System.out.println(phoneDAO.getHighestPrice());
        printList(phoneDAO.sortByNameAndPrice());
        System.out.println(phoneDAO.getAbove50mili());
        System.out.println(phoneDAO.getPinkAndOver15());

        System.out.println(manufactureDAO.checkAbove100E());
        System.out.println(manufactureDAO.sumAllE());
        System.out.println(manufactureDAO.getLastManufacturerBasedInUS());

        session.getTransaction().commit();
        session.close();

    }

    public static void printList(List<Phone> list){
        for (Phone phone: list) {
            System.out.println(phone.toString());
        }
    }
}
