
import model.Product;
import model.ProductDao;
import model.ProductDaoCrud;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;




public class Main {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        ProductDao productDao = new ProductDaoCrud(sessionFactory);

        System.out.println(productDao.findById(3L));
        System.out.println("--------");

        productDao.findAll().forEach(System.out::println);

        System.out.println("--------");

        productDao.deleteById(2L);
        productDao.findAll().forEach(System.out::println);
        System.out.println("--------");
        Product p  = new Product();
        p.setId(4L);
        p.setTitle("Монитор");
        p.setPrice(30);
        productDao.saveOrUpdate(p);
        productDao.findAll().forEach(System.out::println);
        sessionFactory.close();
    }
}
