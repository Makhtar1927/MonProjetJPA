package en.edu.ucak.entites;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

public class MainTestMarque {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloJpaPU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Marque marque = new Marque();
            marque.setNom("Samsung");
            marque.setDescription("Marque samsung original");
            em.persist(marque);

            Produit produit = new Produit();
            produit.setCode("S001");
            produit.setNom("Ecran 24");
            produit.setDescription("Ecran de test");
            produit.setDateCreation(LocalDateTime.now());
            produit.setDateModification(LocalDateTime.now());
            produit.setMarque(marque);
            em.persist(produit);

            em.getTransaction().commit();

            System.out.println("Marque enregistree avec id = " + marque.getId());
            System.out.println("Produit enregistre avec id = " + produit.getId());
            System.out.println("Produit lie a la marque = " + produit.getMarque().getNom());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
