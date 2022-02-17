package be.vdab.artikelsenartikelgroepen.repositories;

import be.vdab.artikelsenartikelgroepen.domain.Artikel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ArtikelRepository extends JpaRepository<Artikel, Long> {

    List<Artikel> findByPrijsBetween(BigDecimal min, BigDecimal max);
    List<Artikel> findMetHoogsteArtikelPrijs();
    List<Artikel> findByArtikelGroepNaam(String naam);
}
