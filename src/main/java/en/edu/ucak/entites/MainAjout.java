package en.edu.ucak.entites;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

public class MainAjout {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloJpaPU");
        EntityManager em = emf.createEntityManager();

        //  1. Créer et enregistrer le marteau
        Produit marteau = new Produit();
        marteau.setNom("Marteau");
        marteau.setCode("MAT");
        marteau.setDescription("marteau pour macon");

        LocalDateTime dateCreation = LocalDateTime.now();
        marteau.setDateCreation(dateCreation);
        marteau.setDateModification(dateCreation);

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(marteau);
        tx.commit();

        //  2. Modifier le marteau
        tx.begin();
        marteau = em.find(Produit.class, marteau.getId());
        marteau.setNom("Marteau de fer");
        marteau.setDateModification(LocalDateTime.now());
        em.merge(marteau);

        //  3. Ajouter un nouveau produit
        Produit fil = new Produit();
        fil.setNom("fil de fer");
        fil.setCode("FI");
        fil.setDescription("fil de diametre");
        fil.setDateCreation(LocalDateTime.now());
        fil.setDateModification(LocalDateTime.now());
        em.persist(fil);
        tx.commit();

        System.out.println("Produit modifie, id = " + marteau.getId());
        System.out.println("Produit ajoute, id = " + fil.getId());

        //  4. Supprimer un produit
        tx.begin();
        Produit toDelete = em.find(Produit.class, 2);
        if (toDelete != null) {
            em.remove(toDelete);
            System.out.println("Produit supprime, id = 2");
        } else {
            System.out.println("Aucun produit avec l'id = 2 a supprimer");
        }
        tx.commit();

        em.close();
        emf.close();
    }
}
