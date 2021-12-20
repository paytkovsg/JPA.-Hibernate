package hibernate.Service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service
public class SessionService {
    private final SessionFactory sessionFactory;

    public SessionService(){
        this.sessionFactory = new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }
    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @PreDestroy
    public void preDestroy(){
        sessionFactory.close();
    }
}
