import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import static org.hibernate.Criteria.DISTINCT_ROOT_ENTITY;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;
import java.util.List;
import java.io.Serializable;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class DaoImplementation implements Dao{

    private SessionFactory sessionFactory;

    public DaoImplementation(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
    }
	
    public <T> void create(T object) {
    	Session session = sessionFactory.getCurrentSession();
      session.save(object);
    }

  	public Object getById(long id, String object) {
  		Session session = sessionFactory.getCurrentSession();
  		Object resultObject = session.get(object, id);
      return resultObject; 
    }
  	
  	public <T> void update(T object) {
  		Session session = sessionFactory.getCurrentSession();
	  	session.update(object);
  	}

  	public <T> void delete(long id, String object) {
  		Session session = sessionFactory.getCurrentSession();
      Object resultObject = session.get(object, id);
      if (resultObject != null) {
          session.delete(resultObject);
      }
  	}

  	public List getList(String object) {
  		Session session = sessionFactory.getCurrentSession();
  		List results = null;
  		try {
  			Criteria criteria = session.createCriteria(object);
  			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			results = criteria.list();
    	} catch (HibernateException e) {
    		e.printStackTrace(); 
      }
		  return results;
  	}

  	public List getOrderedList(String object, String order) {
  		Session session = sessionFactory.openSession();
  		List results = null;
  		try {
  			Criteria criteria = session.createCriteria(object);
  			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
  			criteria.addOrder(Order.asc(order));
        results = criteria.list();
  		} catch (HibernateException e) {
  			e.printStackTrace(); 
  		}
  		return results;
  	}
}