package facade;

import entity.Book;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author christianwulff
 */
public class FacadeRunner {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        BookFacade facade = BookFacade.getBookFacade(emf);
        Book b1 = facade.addBook("Learn JavaScript");
        facade.addBook("Learn JavaScript2");
        facade.addBook("Learn JavaScript3");
        List<Book> books = facade.getAllBooks();
        for(Book b : books) {
            System.out.println(b.toString());
        }
        
    }
}
