package en.edu.ucak.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

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
}
