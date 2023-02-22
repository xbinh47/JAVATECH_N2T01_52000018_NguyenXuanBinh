package dao;

import domain.Manufacture;
import domain.Phone;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.Repository;

import javax.persistence.criteria.*;
import java.awt.dnd.InvalidDnDOperationException;
import java.util.List;

public class ManufactureDAO implements Repository<Manufacture,Integer> {
    private Session session = null;

    public ManufactureDAO(Session session) {
        this.session = session;
    }

    @Override
    public Integer add(Manufacture item) {
        session.save(item);
        return 1;
    }

    @Override
    public List<Manufacture> getAll() {
        try{
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Manufacture> cr = cb.createQuery(Manufacture.class);
            Root<Manufacture> root = cr.from(Manufacture.class);
            cr.select(root);
            Query<Manufacture> query = session.createQuery(cr);
            List<Manufacture> results = query.getResultList();
            return results;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Manufacture get(Integer id) {
        try{
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Manufacture> cr = cb.createQuery(Manufacture.class);
            Root<Manufacture> root = cr.from(Manufacture.class);
            cr.select(root);
            cr.where(cb.equal(root.get("id"),id));
            Query<Manufacture> query = session.createQuery(cr);
            List<Manufacture> results = query.getResultList();
            return results.get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Manufacture item) {
        try{
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaUpdate<Manufacture> update = cb.createCriteriaUpdate(Manufacture.class);
            Root<Manufacture> root = update.from(Manufacture.class);
            update.set("name",item.getName());
            update.set("location",item.getLocation());
            update.set("employee",item.getEmployee());
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
            CriteriaDelete<Manufacture> delete = cb.createCriteriaDelete(Manufacture.class);
            Root<Manufacture> root = delete.from(Manufacture.class);
            delete.where(cb.equal(root.get("id"),id));
            session.createQuery(delete).executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkAbove100E(){
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Manufacture> cr = cb.createQuery(Manufacture.class);
            Root<Manufacture> root = cr.from(Manufacture.class);
            cr.select(root);
            cr.where(cb.lessThanOrEqualTo(root.get("employee"),100));
            Query<Manufacture> query = session.createQuery(cr);
            List<Manufacture> results = query.getResultList();
            if(results.size() > 0){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public Integer sumAllE(){
        try{
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Integer> cr = cb.createQuery(Integer.class);
            Root<Manufacture> root = cr.from(Manufacture.class);
            cr.select(cb.sum(root.get("employee")));
            Query<Integer> query = session.createQuery(cr);
            Integer result = query.getSingleResult();
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public Manufacture getLastManufacturerBasedInUS() {
        try{
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Manufacture> cr = cb.createQuery(Manufacture.class);
            Root<Manufacture> root = cr.from(Manufacture.class);
            cr.select(root);
            cr.where(cb.equal(root.get("location"), "United States"));
            cr.orderBy(cb.desc(root.get("id")));
            Query<Manufacture> query = session.createQuery(cr);
            query.setMaxResults(1);
            List<Manufacture> result = query.getResultList();
            if (result.isEmpty()) {
                throw new InvalidDnDOperationException("No manufacturer found based in US.");
            }
            return result.get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
