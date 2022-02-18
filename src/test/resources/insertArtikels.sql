insert into artikels(naam, prijs, artikelGroepId) VALUES
('testArtikel1', 8, (select id from artikelgroepen where naam = 'testArtikelGroep1')),
('testArtikel2', 50, (select id from artikelgroepen where naam = 'testArtikelGroep1')),
('testArtikel3', 100, (select id from artikelgroepen where naam = 'testArtikelGroep2'));