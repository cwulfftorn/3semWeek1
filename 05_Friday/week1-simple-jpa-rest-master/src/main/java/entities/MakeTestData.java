/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class MakeTestData {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        try{
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
        }finally{
            em.close();
        }
    }
}
