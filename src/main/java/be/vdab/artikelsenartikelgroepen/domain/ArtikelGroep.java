package be.vdab.artikelsenartikelgroepen.domain;

import javax.persistence.*;

@Entity
@Table(name = "artikelgroepen")
public class ArtikelGroep {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
