package en.edu.ucak.entites;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloJpaPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        LocalDateTime maintenant = LocalDateTime.now();

        // ----- Ajouter une marque -----
        Marque marque = new Marque();
        marque.setNom("Générique");
        marque.setDescription("Marque par défaut");
        em.persist(marque);

        // ----- Ajouter des produits -----
        Produit p1 = new Produit();
        p1.setCode("P001");
        p1.setNom("Clavier");
        p1.setPrix(new java.math.BigDecimal("49.99"));
        p1.setMarque(marque);
        p1.setDateCreation(maintenant);
        p1.setDateModification(maintenant);
        em.persist(p1);

        Produit p2 = new Produit();
        p2.setCode("P002");
        p2.setNom("Souris");
        p2.setPrix(new java.math.BigDecimal("19.99"));
        p2.setMarque(marque);
        p2.setDateCreation(maintenant);
        p2.setDateModification(maintenant);
        em.persist(p2);

        Produit p3 = new Produit();
        p3.setCode("P003");
        p3.setNom("Ecran");
        p3.setPrix(new java.math.BigDecimal("199.99"));
        p3.setMarque(marque);
        p3.setDateCreation(maintenant);
        p3.setDateModification(maintenant);
        em.persist(p3);

        // ----- Ajouter un client -----
        Client c1 = new Client();
        c1.setNom("Diop");
        c1.setPrenom("Fallou");
        c1.setEmail("fallou.bousso@ucak.edu");
        c1.setAdresseLivraison("Touba, Guede Bousso");
        c1.setNumeroTelephone("770970906");
        em.persist(c1);

        em.getTransaction().commit();

        System.out.println("Produit 1 enregistré avec id = " + p1.getId());
        System.out.println("Produit 2 enregistré avec id = " + p2.getId());
        System.out.println("Produit 3 enregistré avec id = " + p3.getId());
        System.out.println("Client enregistré avec id = " + c1.getId());

        em.close();
        emf.close();
    }
}
