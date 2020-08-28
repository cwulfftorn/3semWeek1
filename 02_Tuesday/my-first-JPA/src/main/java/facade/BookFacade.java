package facade;

import entity.Book;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;


public class BookFacade {
    private static EntityManagerFactory emf;
    private static BookFacade instance;

    private BookFacade() {}

    public static BookFacade getBookFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BookFacade();
        }
        return instance;
    }
    
    public Book addBook(String author){
        Book book = new Book(author);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
            return book;
        }finally {
            em.close();
        }
    }
    
    public List<Book> findBookByTitle(String title){
    EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.title = :title",Book.class)
                    .setParameter("title", title);
            return query.getResultList();
        }finally {
            em.close();
        }
       
    }
    

    public List<Book> getAllBooks(){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Book> query = 
                       em.createQuery("Select book from Book book",Book.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }

}
