package en.edu.ucak.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.Objects;
import en.edu.ucak.entites.Marque;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Produit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 7, nullable = false)
    private String code;

    @Column(length = 500)
    private String description;

    @Column(nullable = false , length = 100)
    private String nom;

    @Column(nullable = false)
    private LocalDateTime dateCreation;

    @Column(nullable = false)
    private LocalDateTime dateModification;

    @ManyToOne
    @JoinColumn(name = "marque_id")
    private Marque marque;

    @PrePersist
    public void prePersist(){
        System.out.println("mis a jour date avant creation");
        System.out.println("id="+id);
        dateCreation = LocalDateTime.now();
        dateModification = dateCreation;
    }

    @PreUpdate
    public void preUpdate(){

    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "marque_id", nullable = false)
    private Marque marque;

    @Column(nullable = false, precision = 13, scale = 2)
    private BigDecimal prix;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produit produit = (Produit) o;
        return Objects.equals(id, produit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Produit(String code, String description, String nom, BigDecimal prix, Marque marque) {
        this.code = code;
        this.description = description;
        this.nom = nom;
        this.prix = prix;
        this.marque = marque;
    }

    public Produit(String code, String nom, BigDecimal prix) {
        this.code = code;
        this.nom = nom;
        this.prix = prix;
    }
}
