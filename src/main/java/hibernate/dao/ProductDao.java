package hibernate.dao;

import hibernate.Service.SessionService;
import hibernate.model.Product;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Component
public class ProductDao {
    private final SessionService sessionService;

    public ProductDao(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public Product findById(Long id) {
        Session session = sessionService.getSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.getTransaction().commit();
        session.close();
        return product;
    }

    public List<Product> findAll() {
         Session session = sessionService.getSession();
            session.beginTransaction();
            List<Product> productList = session
                    .createQuery("select p from Product p", Product.class)
                    .getResultList();
            session.getTransaction().commit();
            session.close();

        return productList;
    }

    public void deleteById(Long id) {

        Session session = sessionService.getSession();
            session.beginTransaction();
            session.createQuery("delete from Product p where p.id =:id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
            session.close();
    }

    public Product saveOrUpdate(Product product) {

        Session session = sessionService.getSession();
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            session.close();
        return product;
    }

}
