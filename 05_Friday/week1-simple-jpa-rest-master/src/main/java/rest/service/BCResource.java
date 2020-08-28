package rest.service;

import com.google.gson.Gson;
import dto.CustomerDTO;
import entities.BankCustomer;
import facades.BCFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("bankcustomer")
public class BCResource {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static BCFacade facade = BCFacade.getBCFacade(emf);
    private static Gson gson = new Gson();

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        List<BankCustomer> emp = facade.getAllBankCustomers();
        List<CustomerDTO> edto = new ArrayList();

        for (BankCustomer e : emp) {
            edto.add(new CustomerDTO(e));
        }
        return gson.toJson(edto);
    }

    @GET
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public String create(@PathParam("id") int id) {
        return gson.toJson(new CustomerDTO(facade.getCustomerByID(id)));
    }
    
    @GET
    @Path("/populate")
    @Consumes({MediaType.APPLICATION_JSON})
    public String update(BankCustomer entity, @PathParam("id") int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            BankCustomer c1 = new BankCustomer("Borger", "Borgersen", "4527-5599", 30578, 9, "Oprettet 17-10-2009");
            BankCustomer c2 = new BankCustomer("Morten","Mortesen","6799-1234",23644, 4, "Oprettet 01-01-1991");
            BankCustomer c3 = new BankCustomer("Mads","Madsen","2345-5012", 14317, 7, "Oprettet 25-07-2000");
            BankCustomer c4 = new BankCustomer("Inger","Ingeberg","5391-9987", 5111, 1, "Oprettet 02-12-1994");
            em.getTransaction().begin();
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.persist(c4);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return "Populated the Database successfully. Don't run this again.";
    }
    
}
