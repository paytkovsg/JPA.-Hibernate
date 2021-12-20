import hibernate.configuration.ContextConfig;
import hibernate.dao.CustomerDao;
import hibernate.dao.ProductDao;
import hibernate.model.Cart;
import hibernate.model.Customer;
import hibernate.model.Product;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ContextConfig.class);

        ProductDao productDao = context.getBean(ProductDao.class);
        CustomerDao customerDao = context.getBean(CustomerDao.class);

        System.out.println("Получаем клиента с id 1");
        Customer customer = customerDao.findBuId(1L);
        System.out.println(customer);
        System.out.println("----------");


        System.out.println("Добавляем клиенту продукт 2 по начальной цене");
        Product product2 = productDao.findById(2L);

        Cart cart1 = new Cart();
        cart1.setProduct(product2);

        customer.addCart(cart1);
        customerDao.saveOrUpdate(customer);
        System.out.println(customer);
        System.out.println("--------");

        System.out.println("Повышаем цену продукта 2");
        product2.setPrice(10000);
        productDao.saveOrUpdate(product2);

        Cart cart2 = new Cart();
        cart2.setProduct(product2);
        customer.addCart(cart2);
        customerDao.saveOrUpdate(customer);

        System.out.println("Для проверки вытягиваем клиента из базы");
        System.out.println(customerDao.findBuId(1L));

        context.close();
    }
}

