package dao;

import domain.Manufacture;
import domain.Phone;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.Repository;

import javax.persistence.criteria.*;
import java.util.List;

public class PhoneDAO implements Repository<Phone,Integer> {
    private Session session = null;

    public PhoneDAO(Session session) {
        this.session = session;
    }

    @Override
    public Integer add(Phone item) {
        session.save(item);
        return 1;
    }

    @Override
    public List<Phone> getAll() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Phone> cr = cb.createQuery(Phone.class);
        Root<Phone> root = cr.from(Phone.class);
        cr.select(root);
        Query<Phone> query = session.createQuery(cr);
        List<Phone> results = query.getResultList();
        return results;
    }

    @Override
    public Phone get(Integer id) {
        try{
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Phone> cr = cb.createQuery(Phone.class);
            Root<Phone> root = cr.from(Phone.class);
            cr.select(root);
            Query<Phone> query = session.createQuery(cr);
            List<Phone> results = query.getResultList();
            return results.get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Phone item) {
        try{
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaUpdate<Phone> update = cb.createCriteriaUpdate(Phone.class);
            Root<Phone> root = update.from(Phone.class);
            update.set("name",item.getName());
            update.set("price",item.getPrice());
            update.set("color",item.getColor());
            update.set("country",item.getCountry());
            update.set("quantity",item.getQuantity());
            update.where(cb.equal(root.get("id"),item.getId()));
            session.createQuery(update).executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        try{
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<Phone> delete = cb.createCriteriaDelete(Phone.class);
            Root<Phone> root = delete.from(Phone.class);
            delete.where(cb.equal(root.get("id"),id));
            session.createQuery(delete).executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public Phone getHighestPrice(){
        try{
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Phone> cr = cb.createQuery(Phone.class);
            Root<Phone> root = cr.from(Phone.class);
            cr.select(root);
            cr.orderBy(cb.desc(root.get("price")));
            Query<Phone> query = session.createQuery(cr);
            List<Phone> results = query.getResultList();
            return results.get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Phone> sortByNameAndPrice(){
        try{
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Phone> cr = cb.createQuery(Phone.class);
            Root<Phone> root = cr.from(Phone.class);
            cr.select(root);
            cr.orderBy(cb.asc(root.get("country")),cb.asc(root.get("price")));
            Query<Phone> query = session.createQuery(cr);
            List<Phone> results = query.getResultList();
            return results;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean getAbove50mili(){
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Phone> cr = cb.createQuery(Phone.class);
            Root<Phone> root = cr.from(Phone.class);
            cr.select(root);
            cr.where(cb.greaterThan(root.get("price"),50000000));
            Query<Phone> query = session.createQuery(cr);
            List<Phone> results = query.getResultList();
            if(results.size()==0){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public Phone getPinkAndOver15(){
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cr = cb.createQuery(Phone.class);
        Root<Phone> root = cr.from(Phone.class);
        cr.select(root);
        cr.where(cb.and(cb.like(root.get("color"),"%Pink%"),cb.greaterThan(root.get("price"),15000000)));
        Query<Phone> query = session.createQuery(cr);
        query.setMaxResults(1);
        List<Phone> results = query.getResultList();
        if(results.size()==0){
            return null;
        }
        return results.get(0);
    }
}
