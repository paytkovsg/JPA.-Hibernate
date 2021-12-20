package hibernate.dao;

import hibernate.Service.SessionService;
import hibernate.model.Customer;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerDao {
    private final SessionService sessionService;

    public CustomerDao(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public Customer findBuId(Long id){
        Session session = sessionService.getSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        session.getTransaction().commit();
        session.close();
        return customer;
    }

    public List<Customer> findAll() {
        Session session = sessionService.getSession();
        session.beginTransaction();
        List<Customer> customerList = session
                .createQuery("select c from Customer c", Customer.class)
                .getResultList();
        session.getTransaction().commit();
        session.close();

        return customerList;
    }

    public void deleteById(Long id) {

        Session session = sessionService.getSession();
        session.beginTransaction();
        session.createQuery("delete from Customer c where c.id =:id")
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public Customer saveOrUpdate(Customer customer) {

        Session session = sessionService.getSession();
        session.beginTransaction();
        session.saveOrUpdate(customer);
        session.getTransaction().commit();
        session.close();
        return customer;
    }

}
