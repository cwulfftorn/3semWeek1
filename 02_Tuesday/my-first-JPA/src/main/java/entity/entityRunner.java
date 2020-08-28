package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author christianwulff
 */
public class entityRunner {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");       
        EntityManager em = emf.createEntityManager();
        Book b1 = new Book("Learn JPA");
        Book b2 = new Book("Learn JPA!");
        Book b3 = new Book("Learn JPA!!");
        try{
            em.getTransaction().begin();
            em.persist(b1);
            em.persist(b2);
            em.persist(b3);
            em.getTransaction().commit();
            
            System.out.println(b1.getId());
            System.out.println(b2.getId());
            System.out.println(b3.getId());
        }finally{
            em.close();
        }
        
    }
    
}
