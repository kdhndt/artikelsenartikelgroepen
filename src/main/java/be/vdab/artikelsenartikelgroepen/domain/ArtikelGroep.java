package be.vdab.artikelsenartikelgroepen.domain;

import javax.persistence.*;

@Entity
@Table(name = "artikelgroepen")
public class ArtikelGroep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;

    public ArtikelGroep(String naam) {
        this.naam = naam;
    }

    public ArtikelGroep() {
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
