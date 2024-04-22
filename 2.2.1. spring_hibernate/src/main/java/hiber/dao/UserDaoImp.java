package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
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
   public void metod(String model, int series) {
      TypedQuery<User> query=sessionFactory.getCurrentSession()
              .createQuery("SELECT user from User user WHERE user.car.model=?1 and user.car.series=?2")
              .setParameter(1,model)
              .setParameter(2,series);
      List <User> list = query.getResultList();
      User user = list.get(0);
      System.out.print(user);
   }

}
