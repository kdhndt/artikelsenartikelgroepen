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
                .extracting(Artikel::getPrijs)
                .allSatisfy(prijs -> assertThat(prijs).isBetween(min, max));
    }
    @Test
    void findHoogstePrijs() {
        assertThat(repository.findHoogstePrijs())
                .isEqualByComparingTo(jdbcTemplate.queryForObject("select max(prijs) from artikels", BigDecimal.class));
    }

    @Test
    void findByArtikelGroepNaam() {
        var artikelGroep = "testArtikelGroep1";
        assertThat(repository.findByArtikelGroepNaam(artikelGroep))
                .hasSize(countRowsInTableWhere(ARTIKELS,
                        "artikelGroepId = (select id from artikelgroepen where naam = 'testArtikelGroep1')"))
                .first()
                .extracting(Artikel::getArtikelGroep)
                .extracting(ArtikelGroep::getNaam)
                .isEqualTo(artikelGroep)
        ;
    }
}