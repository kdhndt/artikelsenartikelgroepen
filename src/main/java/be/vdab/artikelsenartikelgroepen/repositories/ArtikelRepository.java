package be.vdab.artikelsenartikelgroepen.repositories;

import be.vdab.artikelsenartikelgroepen.domain.Artikel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ArtikelRepository extends JpaRepository<Artikel, Long> {

    List<Artikel> findByPrijsBetween(BigDecimal min, BigDecimal max);
    //find HOOGSTE prijs, not MET HOOGSTE prijs
    @Query("select max(a.prijs) from Artikel a")
    BigDecimal findHoogstePrijs();
    List<Artikel> findByArtikelGroepNaam(String naam);
}
