package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.util.List;


public class ProductDaoCrud implements ProductDao{

    SessionFactory factory;


    public ProductDaoCrud(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Product findById(Long id) {
        Product product;
        try (Session session = factory.getCurrentSession()){
            session.getTransaction().begin();

            product = session.get(Product.class, id);
            System.out.println(product);

            session.getTransaction().commit();
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        try (Session session = factory.getCurrentSession()){
            session.getTransaction().begin();
            var products = session
                    .createQuery("select p from Product p", Product.class)
                    .getResultList();
            products.forEach(System.out::println);
            session.getTransaction().commit();
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = factory.getCurrentSession()){
            session.getTransaction().begin();
            Product product = session.get(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();

        }

    }

    @Override
    public Product saveOrUpdate(Product p) {

        try (Session session = factory.getCurrentSession()){
            session.getTransaction().begin();
            session.saveOrUpdate(p);
            session.getTransaction().commit();
        }
        return p;
    }

}
