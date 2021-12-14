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
            session.getTransaction().commit();
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products;
        try (Session session = factory.getCurrentSession()){
            session.getTransaction().begin();
            products = session
                    .createQuery("select p from Product p", Product.class)
                    .getResultList();
            session.getTransaction().commit();
        }
        return products;
    }

    @Override
    public void deleteById(Long id) {

        try (Session session = factory.getCurrentSession()){
            session.getTransaction().begin();
            session.createQuery("delete from Product p where p.id =:id")
                    .setParameter("id", id)
                    .executeUpdate();
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
