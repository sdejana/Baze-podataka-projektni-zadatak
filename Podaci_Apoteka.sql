use apoteka;

-- -----------------------
-- MJESTO
-- -----------------------
 insert into sjediste values ('78000', 'Banja Luka'), ('71000', 'Sarajevo');

-- -----------------------
-- BANKA
-- -----------------------
insert into banka (IdBanke, Naziv, Adresa, BrojPoste) values (1, 'Nasa banka', 'Ivana Franje Jukica 1', '78000');
insert into banka (IdBanke, Naziv, Adresa, BrojPoste) values (2, 'NLB banka', 'Milana Tepića 4', '78000');

-- -----------------------
-- PROIZVODJAC
-- -----------------------
insert into proizvodjac (IdProizvodjac, Naziv, Adresa, Telefon, BrojPoste) values (8,'Vitalis','Stojana Novakovica бб','051/445‐650','78000');
insert into proizvodjac (IdProizvodjac, Naziv, Adresa, Telefon, BrojPoste) values (9,'Ecopharm','Jukićeva б3','033/254-400','71000');
insert into proizvodjac (IdProizvodjac, Naziv, Adresa, Telefon, BrojPoste) values (10, 'Apoteka Malina','Džemala Bijedića 125a','033/720-550','71000');
insert into proizvodjac (IdProizvodjac, Naziv, Adresa, Telefon, BrojPoste) values (11, 'Apoteka Biljana','Ugljese Kojadinovica 1','051/721-555','71000');


-- -----------------------
-- APOTEKA
-- -----------------------
insert into apoteka (IdApoteke, NazivApoteke, Adresa, Email, BrojPoste) values
(1, 'Apoteka', 'Karadjordjeva 45', 'apoteka@gmail.com', '78000');

-- -----------------------
-- TELEFON_APOTEKE
-- -----------------------
insert into telefon_apoteke (Telefon, IdApoteke) values ('051/212-214', 1), ('051/215-323', 1);

-- -----------------------
-- ZAPOSLENI
-- -----------------------
insert into zaposlenik (IdZaposlenika, KorisnickoIme, Lozinka, Ime, Prezime, JMBG, Email, DatumZaposlenja, Plata, AdresaStanovanja, RadnoVrijeme, BrojPoste, IdApoteke) values
(9, 'Nevena', 'nevena@nevena', 'Nevena', 'Ninkovic', '0101980100008', 'nevenan@gmail.com', '2024-09-03', '1400', 'Stepe Stepanovica 64', '8 sati' , '78000', 1);
insert into zaposlenik (IdZaposlenika, KorisnickoIme, Lozinka, Ime, Prezime, JMBG, Email, DatumZaposlenja, Plata, AdresaStanovanja, RadnoVrijeme, BrojPoste, IdApoteke) values
(8, 'marko', 'marko', 'Marko', 'Markovic', '0512983100068', 'markom@gmail.com', '2024-09-03','1200', 'Majke Knezopoljke 3', '8 sati', '78000', 1);
insert into zaposlenik (IdZaposlenika, KorisnickoIme, Lozinka, Ime, Prezime, JMBG, Email, DatumZaposlenja, Plata, AdresaStanovanja, RadnoVrijeme, BrojPoste, IdApoteke) values
(7, 'marija', 'mara', 'Mara', 'Markovic', '0308983126118', 'maram@gmail.com', '2024-09-04', '1200', 'Jug Bogdana 8', '8 sati' , '78000', 1);


insert into inzenjer_farmacije (BrojLicence, RokVazenja, IdZaposlenika) values ('1235-78/21','2027-09-15',9);

insert into farmaceutski_tehnicar(IdZaposlenika) values (8);
insert into farmaceutski_tehnicar(IdZaposlenika) values (7);

- -----------------------
-- ARTIKLI
-- -----------------------
insert into artikal(Barkod, NazivArtikla, Zaliha, IdProizvodjac) values
('2111', 'Soft Creme Balea',  50, '10');
insert into artikal(Barkod, NazivArtikla, Zaliha, IdProizvodjac) values
('2112', 'Sinedol', 250, '9');
insert into artikal(Barkod, NazivArtikla, Zaliha, IdProizvodjac) values
('2113', 'Vitamin C', 500, '8');
insert into artikal(Barkod, NazivArtikla, Zaliha, IdProizvodjac) values
('2114', 'Bebilac', 200, '11');
insert into artikal(Barkod, NazivArtikla, Zaliha, IdProizvodjac) values
('2115', 'Katopil', 1500, '10');
insert into artikal(Barkod, NazivArtikla, Zaliha, IdProizvodjac) values
('2116', 'Cefaleksin', 700, '9');
insert into artikal(Barkod, NazivArtikla, Zaliha, IdProizvodjac) values
('2117', 'Seretide pumpica', 50, '8');
insert into artikal(Barkod, NazivArtikla, Zaliha, IdProizvodjac) values
('2119', 'Stetoskop', 5, '11');
insert into artikal(Barkod, NazivArtikla, Zaliha, IdProizvodjac) values
('2120', 'Naocare dioptrijske', 50, '10');
insert into artikal(Barkod, NazivArtikla, Zaliha, IdProizvodjac) values
('2121', 'Vlazne maramice Baby', 100, '9');
insert into artikal(Barkod, NazivArtikla, Zaliha, IdProizvodjac) values
('2123', 'Uvin caj', 10, '8');
insert into artikal(Barkod, NazivArtikla, Zaliha, IdProizvodjac) values
('2124', 'Melem', 70, '11');
insert into artikal(Barkod, NazivArtikla, Zaliha, IdProizvodjac) values
('2125', 'Panklav',  500, '10');
-- -----------------------
-- TIP_ARTIKLA
-- -----------------------
insert into tip_artikla (IdTipArtikla, NazivTipa) values (1, 'Izdaje se na recept'), (2, 'Izdaje se bez recepta'), (3, 'Ostalo');

-- -----------------------
-- ARTIKAL_TIP_ARTIKLA
-- -----------------------
 insert into artikal_tip_artikla(Barkod, IdTipArtikla) values (2111, 2);
 insert into artikal_tip_artikla(Barkod, IdTipArtikla) values (2112, 2);
 insert into artikal_tip_artikla(Barkod, IdTipArtikla) values (2113, 2);
 insert into artikal_tip_artikla(Barkod, IdTipArtikla) values (2114, 3);
 insert into artikal_tip_artikla(Barkod, IdTipArtikla) values (2115, 2);
 insert into artikal_tip_artikla(Barkod, IdTipArtikla) values (2116, 1);
 insert into artikal_tip_artikla(Barkod, IdTipArtikla) values (2117, 1);
 insert into artikal_tip_artikla(Barkod, IdTipArtikla) values (2119, 3);
 insert into artikal_tip_artikla(Barkod, IdTipArtikla) values (2120, 3);
 insert into artikal_tip_artikla(Barkod, IdTipArtikla) values (2121, 3);
 insert into artikal_tip_artikla(Barkod, IdTipArtikla) values (2123, 2);
 insert into artikal_tip_artikla(Barkod, IdTipArtikla) values (2124, 2);
 insert into artikal_tip_artikla(Barkod, IdTipArtikla) values (2125, 1);



-- -----------------------
-- CIJENA
-- -----------------------

 insert into cijena (IdCijena, NabavnaCijena, ProdajnaCijena, DatumOd, DatumDo, Barkod, IdTipArtikla) values
 (15, 3.75, '6.99', '2024-09-23 15:29:40', null, 2111, 2);
 insert into cijena (IdCijena, NabavnaCijena, ProdajnaCijena, DatumOd, DatumDo, Barkod, IdTipArtikla) values
 (16, 8.5, 10.00,'2024-09-14 14:29:45', null, 2112, 2);
 insert into cijena (IdCijena, NabavnaCijena, ProdajnaCijena, DatumOd, DatumDo, Barkod, IdTipArtikla) values
 (17, 4.23, '5.24', '2024-09-08 14:29:47', null, 2113, 2);
 insert into cijena (IdCijena, NabavnaCijena, ProdajnaCijena, DatumOd, DatumDo, Barkod, IdTipArtikla) values
 (18, 15.6, '18.90', '2024-09-08 15:29:48', null, 2114, 3);
 insert into cijena (IdCijena, NabavnaCijena, ProdajnaCijena, DatumOd, DatumDo, Barkod, IdTipArtikla) values
 (19, 1.23,'2.35', '2024-09-08 15:30:01', '2024-09-09 14:08:02', 2115, 2);
 insert into cijena (IdCijena, NabavnaCijena, ProdajnaCijena, DatumOd, DatumDo, Barkod, IdTipArtikla) values
 (20, 4.59, '6.80', '2024-09-08 15:31:10', null, 2116, 1);
 insert into cijena (IdCijena, NabavnaCijena, ProdajnaCijena, DatumOd, DatumDo, Barkod, IdTipArtikla) values
 (21, 56.90, '66.60', '2024-09-08 15:32:20', null, 2117, 1);
 insert into cijena (IdCijena, NabavnaCijena, ProdajnaCijena, DatumOd, DatumDo, Barkod, IdTipArtikla) values
 (22, 20.78, '22.90','2024-09-08 15:34:26', null, 2119, 3);
 insert into cijena (IdCijena, NabavnaCijena, ProdajnaCijena, DatumOd, DatumDo, Barkod, IdTipArtikla) values
 (23, 123.00, '130.00','2024-09-08 15:35:35', null, 2120, 3);
 insert into cijena (IdCijena, NabavnaCijena, ProdajnaCijena, DatumOd, DatumDo, Barkod, IdTipArtikla) values
 (24, 1.9, '2.60', '2024-09-08 15:36:45', null, 2121, 3);
 insert into cijena (IdCijena, NabavnaCijena, ProdajnaCijena, DatumOd, DatumDo, Barkod, IdTipArtikla) values
 (25, 3.00, '3.50', '2024-09-08 15:38:20', null, 2123, 2);
 insert into cijena (IdCijena, NabavnaCijena, ProdajnaCijena, DatumOd, DatumDo, Barkod, IdTipArtikla) values
 (26, 10.60, '15.80', '2024-09-08 15:29:39', null, 2124, 2);
 insert into cijena (IdCijena, NabavnaCijena, ProdajnaCijena, DatumOd, DatumDo, Barkod, IdTipArtikla) values
 (27, 4.5, '9.00', '2024-09-08 15:29:39', null, 2125, 1);


-- -----------------------
-- LIJEK
-- -----------------------
 insert into lijek (GenerickiNaziv, Doza, Oblik, NazivListe, Barkod) values
 ('katopil', '2 mg', 'kapsula','A', 2115);
 insert into lijek (GenerickiNaziv, Doza, Oblik, NazivListe, Barkod) values
 ('cefaleksin', '250 mg', 'obložena tableta', 'B', 2116);
 insert into lijek (GenerickiNaziv, Doza, Oblik, NazivListe, Barkod) values
 ('seretide pumpica', '125 mg', 'dispenzer', 'C', 2117);
 insert into lijek (GenerickiNaziv, Doza, Oblik, NazivListe, Barkod) values
 ('panklav', '500 mg', 'obložena tableta', 'A', 2125);
  insert into lijek (GenerickiNaziv, Doza, Oblik, NazivListe, Barkod) values
 ('sinedol', '500 ml', 'gel', 'G', 2112);
  insert into lijek (GenerickiNaziv, Doza, Oblik, NazivListe, Barkod) values
 ('vitamin c', '500 mg', 'šumeća tableta tuba', 'C', 2113);
  insert into lijek (GenerickiNaziv, Doza, Oblik, NazivListe, Barkod) values
 ('uvin caj', '100 g', 'rinfuza', 'C', 2123);

-- -----------------------
-- MEDICINSKI APARATI
-- -----------------------
 insert into medicinsko_pomagalo(Barkod) values (2119), (2120);

-- -----------------------
-- KOZMETIKA
-- -----------------------
insert into kozmeticki_asortiman(JedinicaMjere, Barkod) values ('300 ml', 2111), ('500', 2121), ('100 g', 2124);


-- -----------------------
-- RACUN_U_BANCI
-- -----------------------
insert into racun_u_banci (BrojRacuna, IdBanke) values ('56122336', 1), ('3459987', 2), ('12009755', 1), ('980776', 1), ('234990', 1), ('2340097', 1), ('0088877', 1);

-- -----------------------
-- RACUN_APOTEKA
-- -----------------------
insert into racun_apoteka (IdApoteke, BrojRacuna, Stanje) values (1, '56122336', '57000.60');

