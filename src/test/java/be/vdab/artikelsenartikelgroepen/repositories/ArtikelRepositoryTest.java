package be.vdab.artikelsenartikelgroepen.repositories;

import be.vdab.artikelsenartikelgroepen.domain.Artikel;
import be.vdab.artikelsenartikelgroepen.domain.ArtikelGroep;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@Sql({"/insertArtikelgroepen.sql", "/insertArtikels.sql"})
class ArtikelRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String ARTIKELS = "artikels";
    private final ArtikelRepository repository;

    ArtikelRepositoryTest(ArtikelRepository repository) {
        this.repository = repository;
    }

    @Test
    void findByPrijsBetween() {
        var min = BigDecimal.ONE;
        var max = BigDecimal.TEN;
        assertThat(repository.findByPrijsBetween(min, max))
                .hasSize(countRowsInTableWhere(ARTIKELS, "prijs between 1 and 10"))
                .allSatisfy(artikel -> assertThat(artikel.getPrijs()).isBetween(min, max));
    }

    @Test
    void findMetHoogsteArtikelprijs() {
        assertThat(repository.findMetHoogsteArtikelPrijs())
                .hasSize(countRowsInTableWhere(ARTIKELS, "prijs = (select max(prijs) from artikels)"))
                .first().extracting(Artikel::getNaam).isEqualTo(
                        jdbcTemplate.queryForObject("select naam from artikels where prijs = (select max(prijs) from artikels)",
                                String.class)
                );
    }

/*    @Test
    void findByArtikelGroepNaam() {
        var artikelGroep = "testArtikelgroep";
        assertThat(repository.findByArtikelGroepNaam(artikelGroep))
                .hasSize(countRowsInTableWhere(ARTIKELS,
                        "artikelgroepId = (select id from artikelgroepen where naam = 'testArtikelgroep'"))
                .first()
                .extracting(Artikel::getArtikelGroep)
                .extracting(ArtikelGroep::getNaam)
                .isEqualTo(artikelGroep);
    }*/
}