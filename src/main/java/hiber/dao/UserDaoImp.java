package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserFromCar(String model, int series) {
      String HQL = "SELECT u FROM User u WHERE u.car.model = :model AND u.car.series = :series";
      Session session = sessionFactory.getCurrentSession();
      Query query = session.createQuery(HQL, User.class).setParameter("model", model).setParameter("series", series);
      return (User) query.getSingleResult();
   }
}
