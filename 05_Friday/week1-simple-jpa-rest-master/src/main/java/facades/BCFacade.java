package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class BCFacade {

    private static BCFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private BCFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BCFacade getBCFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BCFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public BankCustomer getCustomerByID(long id) {
        
        EntityManager em = emf.createEntityManager();
        
        try{
            BankCustomer customer = em.find(BankCustomer.class, id);
            return customer;
        }finally{
            em.close();
        }
            
    }
    
    public List<BankCustomer> getCustomerByName(String name) {
        
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<BankCustomer> query = em.createQuery("SELECT c FROM CustomerDTO c WHERE c.firstname = :firstname", BankCustomer.class)
                    .setParameter("firstname", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public BankCustomer addCustomer(BankCustomer bc) {
        
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(bc);
            em.getTransaction().commit();
            return bc;
        } finally {
            em.close();
        }
    }
    
    public List<BankCustomer> getAllBankCustomers() {
        
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<BankCustomer> query
                    = em.createQuery("Select c from BankCustomer c", BankCustomer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
