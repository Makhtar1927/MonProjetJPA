package en.edu.ucak.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LigneArticle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer quantite;

    @Column(name = "prix_unitaire", nullable = false)
    private Double prixUnitaire;

    @ManyToOne
    @JoinColumn(name = "facture_id", nullable = false)
    private Facture facture;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;
}
