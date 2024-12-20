-- ---------------------------
-- -----------------------
-- POGLEDI
-- -----------------------
-- ---------------------------

-- -----------------------
-- recept_pogled
-- -----------------------
create view recept_pogled as
select r.DatumIzdavanja, r.BrojKnjizice, r.JMBG, r.SifraDoktora, a.Barkod, a.NazivArtikla, rs.Kolicina, c.ProdajnaCijena
from recept r
inner join racun_stavka rs on rs.IdRecept = r.IdRecept
inner join artikal a on a.Barkod = rs.Barkod
inner join cijena c on a.Barkod= c.Barkod
where IdTipArtikla=1;


-- -----------------------
-- artikal_pogled
-- -----------------------
create view artikal_pogled as
select a.Barkod, a.NazivArtikla, a.Zaliha, p.Naziv 
from artikal a
inner join proizvodjac p on a.IdProizvodjac = p.IdProizvodjac
order by Barkod;

-- --------------------------
-- -----------------------
-- PROCEDURE
-- -----------------------
-- --------------------------

-- -----------------------
-- dodaj_racun
-- -----------------------
delimiter $$
create procedure dodaj_racun(in pIdZaposlenika int(11))
begin
	insert into racun(IdRacuna, IdZaposlenika) values (null, pIdZaposlenika);
end$$
delimiter ;
-- -----------------------
-- dodaj_zaposlenog
-- -----------------------
delimiter $$
create procedure dodaj_zaposlenika(in pIme varchar(45), in pPrezime varchar(45), in pJMBG char(13), in pEmail varchar(50), in pDatumZaposlenja date, in pPlata double,
in pAdresaStanovanja varchar(50), in pRadnoVrijeme varchar(20), in pBrojPoste int, in pIdApoteke int)
begin
    insert into zaposlenik(IdZaposlenika, Ime, Prezime, JMBG, Email, DatumZaposlenja, Plata, AdresaStanovanja, RadnoVrijeme, BrojPoste, IdApoteke) values (pIme, pPrezime, pJMBG, pEmail, pDatumZaposlenja, pPlata, pAdresaStanovanja, pRadnoVrijeme, pBrojPoste, pIdApoteke);
end$$
delimiter ;

-- -----------------------
-- azuriraj_kozmetiku
-- -----------------------
delimiter $$ 
create procedure azuriraj_kozmetiku(in pBarkod int, in pNazivArtikla varchar(50), in pIdProizvodjac int, pJedinicaMjere varchar(10))
begin
	update artikal set NazivArtikla = pNazivArtikla, IdProizvodjac = pIdProizvodjac where Barkod = pBarkod;
    update kozmetika set JedinicaMjere = pJedinicaMjere where Barkod = pBarkod;
end$$
delimiter ;


-- --------------------
-- azuriraj_lijek
-- --------------------
delimiter $$
create procedure azuriraj_lijek(
									in pBarkod int, 
                                    in pNazivArtikla varchar(50), 
                                    in pIdProizvodjac int, 							
							        in pGenerickiNaziv varchar(45), 
                                    in pDoza varchar(20), 
                                    in pOblik varchar(30),
                                    in pNazivListe char(2))
begin
	
	update artikal set NazivArtikla = pNazivArtikla, IdProizvodjac = pIdProizvodjac where Barkod = pBarkod;
    update lijek set GenerickiNaziv = pGenerickiNaziv, Doza = pDoza, Oblik = pOblik, NazivListe = pNazivListe where Barkod = pBarkod;
    
end$$
delimiter ;


-- --------------------------
-- azuriraj_medicinski_aparat
-- --------------------------
delimiter $$ 
create procedure azuriraj_medicinski_aparat(in pBarkod int, in pNazivArtikla varchar(50), in pIdProizvodjac int)
begin
	update artikal set NazivArtikla=pNazivArtikla, IdProizvodjac=pIdProizvodjac where Barkod=pBarkod;
end$$
delimiter ;


-- -----------------------
-- azuriraj_proizvodjaca
-- -----------------------
delimiter $$ 
create procedure azuriraj_proizvodjaca(in pNaziv varchar(45), in pAdresa varchar(50), in pBrojPoste int, in pTelefon varchar(20))
begin
	update proizvodjac set Naziv = pNaziv, Adresa = pAdresa, BrojPoste=pBrojPoste, Telefon = pTelefon where Naziv = pNaziv;
end$$
delimiter ;

-- -----------------------
-- dodaj_faktura_stavka
-- -----------------------
delimiter $$
create procedure `dodaj_faktura_stavka`(in pBarkod int, in pKolicina int, in pCijenaStavke decimal(5,2))
begin
	insert into faktura_stavka (IdFaktura, Barkod, Kolicina, CijenaStavke) values ((SELECT LAST_INSERT_ID()), pBarkod, pKolicina, pCijenaStavke);    
end$$
delimiter ;


-- -----------------------
-- dodaj_novu_fakturu
-- -----------------------
delimiter $$
create procedure `dodaj_fakturu`(in pBrojFakture int, in pIdProizvodjac int, in pIdZaposlenika int)
begin
	insert into faktura (IdFaktura, BrojFakture, IdProizvodjac, IdZaposlenika) values (null, pBrojFakture, pIdProizvodjac, pIdZaposlenika);
end$$
delimiter ;


-- -----------------------
-- dodaj_kozmetika
-- -----------------------
delimiter $$ 
create procedure dodaj_kozmetika(in pBarkod int, in pNazivArtikla varchar(50), in pNabavnaCijena decimal(5,2), in pProdajnaCijena decimal(5,2), in pIdProizvodjac int, pJedinicaMjere varchar(10), in pIdTipArtikla int)
begin
	insert into artikal (Barkod, NazivArtikla, IdProizvodjac) values (pBarkod, pNazivArtikla, pIdProizvodjac);
	insert into kozmeticki_asortiman (JedinicaMjere, Barkod) values (pJedinicaMjere, pBarkod);
    insert into artikal_tip_artikla (Barkod, IdTipArtikla) values (pBarkod, pIdTipArtikla);
    insert into cijena (NabavnaCijena, ProdajnaCijena, Barkod, IdTipArtikla) values (pNabavnaCijena, pProdajnaCijena, pBarkod, pIdTipArtikla);
end$$
delimiter ;


-- -----------------------
-- dodaj_lijek
-- -----------------------
delimiter $$ 
create procedure dodaj_lijek(in pBarkod int, 
                             in pNazivArtikla varchar(50), 
							 in pIdTipArtikla int, 
                             in pNabavnaCijena decimal(5,2),
                             in pProdajnaCijena decimal(5,2), 
                             in pIdProizvodjac int, 
						     in pGenerickiNaziv varchar(45), 
                             in pDoza varchar(20), 
                             in pOblik varchar(30), 
                             in pNazivListe char(2))
begin	
	insert into artikal (Barkod, NazivArtikla, IdProizvodjac) values (pBarkod, pNazivArtikla, pIdProizvodjac);
	insert into lijek (GenerickiNaziv, Doza, Oblik, NazivListe, Barkod) values (pGenerickiNaziv, pDoza, pOblik, pNazivListe, pBarkod);
    insert into artikal_tip_artikla (Barkod, IdTipArtikla) values (pBarkod, pIdTipArtikla);
    insert into cijena (NabavnaCijena, ProdajnaCijena, Barkod, IdTipArtikla) values (pNabavnaCijena, pProdajnaCijena, pBarkod, pIdTipArtikla);
end$$
delimiter ;


-- -----------------------
-- dodaj_medicinski_aparat
-- -----------------------
delimiter $$ 
create procedure dodaj_medicinska_pomagala(in pBarkod int, in pNazivArtikla varchar(50), in pNabavnaCijena decimal(5,2), in pProdajnaCijena decimal(5,2), in pIdProizvodjac int, in pIdTipArtikla int)
begin
	insert into artikal (Barkod, NazivArtikla, IdProizvodjac) values (pBarkod, pNazivArtikla, pIdProizvodjac);
	insert into medicinska_pomagala(Barkod) values (pBarkod);
    insert into artikal_tip_artikla (Barkod, IdTipArtikla) values (pBarkod, pIdTipArtikla);
    insert into cijena (NabavnaCijena, ProdajnaCijena, Barkod, IdTipArtikla) values (pNabavnaCijena, pProdajnaCijena, pBarkod, pIdTipArtikla);
end$$
delimiter ;


-- -----------------------
-- dodaj_proizvodjaca
-- -----------------------
delimiter $$ 
create procedure dodaj_proizvodjaca(in pNaziv varchar(45), in pAdresa varchar(50), in pBrojPoste int, in pTelefon varchar(20))
begin
	insert into proizvodjac (IdProizvodjac, Naziv, Adresa, BrojPoste, Telefon) values (null, pNaziv, pAdresa, pBrojPoste, pTelefon);
end$$
delimiter ;

-- -----------------------
-- dodaj_racun_stavka
-- -----------------------
delimiter $$
create procedure dodaj_racun_stavka (in pBarkod int, in pCijena decimal(5,2), in pKolicina int)
begin
   
    select @last_id_racun := MAX(IdRacuna) from racun;
    select @last_id_recept := MAX(IdRecept) from recept;

	insert into racun_stavka(IdRacunStavka, Cijena, Kolicina, IdRacuna, Barkod, IdRecept) values (null, pCijena, pKolicina, @last_id_racun, pBarkod, @last_id_recept);
	
end$$
delimiter ;


-- ------------------------------
-- dodaj_racun_stavka_bez_recepta
-- ------------------------------
delimiter $$
create procedure dodaj_racun_stavka_bez_recepta(in pBarkod int, in pCijena decimal(5,2), in pKolicina int)
begin

	select @last_id_racun := MAX(IdRacuna) from racun;
	insert into racun_stavka (IdRacunStavka, Cijena, Kolicina, IdRacuna, Barkod) values (null, pCijena, pKolicina, @last_id_racun , pBarkod);
    
end$$
delimiter ;


-- -----------------------
-- dodaj_recept
-- -----------------------
delimiter $$
create procedure dodaj_recept(in pDatumIzdavanja date, in pBrojKnjizice char(11), in pJMBG char(13), in pSifraDoktora int(11))
begin
	insert into recept(IdRecept, DatumIzdavanja, BrojKnjizice, JMBG, SifraDoktora) values (null, pDatumIzdavanja, pBrojKnjizice, pJMBG, pSifraDoktora);
end$$
delimiter ;

-- -----------------------
-- obrisi_proizvodjaca
-- -----------------------
delimiter $$
create procedure obrisi_proizvodjaca (in pNaziv varchar(45))
begin
	delete from proizvodjac where Naziv = pNaziv;
end$$
delimiter ;


-- ------------------------------------------
-- -----------------------
-- TRIGERI
-- -----------------------
-- ------------------------------------------
delimiter $$
create trigger dodaj_faktura_stavka after insert on faktura_stavka
for each row
begin
	declare kolicina int;
    declare iznosCijene decimal(5,2); 
    declare ukupno decimal(8,2);
	select Zaliha from artikal where Barkod = new.Barkod into kolicina;
    select CijenaStavke from faktura_stavka where IdFaktura=new.IdFaktura and Barkod=new.Barkod into iznosCijene;
    select IznosRacuna from faktura where IdFaktura=new.IdFaktura into ukupno;
    
    update artikal set Zaliha = kolicina + new.Kolicina where Barkod = new.Barkod;
    update faktura set IznosRacuna = ukupno + iznosCijene*new.Kolicina where IdFaktura = new.IdFaktura;
end$$
delimiter ;


-- -----------------------
-- dodaj_racun_stavka
-- -----------------------
delimiter $$
create trigger dodaj_racun_stavka after insert on racun_stavka
for each row
begin
	declare kolicina int;
	declare cijenaArtikla decimal(5,2);
	declare ukupno decimal(6,2);
	select Zaliha from artikal where Barkod = new.Barkod into kolicina;
	select Cijena from racun_stavka where IdRacunStavka = new.IdRacunStavka into cijenaArtikla;
    select UkupanIznos from racun where IdRacuna = new.IdRacuna into ukupno;
	if(new.Kolicina <= kolicina) then
		update artikal set Zaliha = kolicina - new.Kolicina where Barkod = new.Barkod;
        update racun set UkupanIznos = ukupno + cijenaArtikla*new.Kolicina where IdRacuna = new.IdRacuna;
	end if;
end$$
delimiter ;


-- -----------------------
-- ukloni_racun_stavka
-- -----------------------
delimiter $$
create trigger ukloni_racun_stavka before delete on racun_stavka
for each row 
begin	
	declare cijenaArtikla decimal(5,2);
    select Cijena from racun_stavka where IdRacunStavka = old.IdRacunStavka into cijenaArtikla;
	
    update artikal set Zaliha = Zaliha + old.Kolicina where Barkod = old.Barkod;
    update racun set UkupanIznos = UkupanIznos - cijenaArtikla*old.Kolicina where IdRacuna = old.IdRacuna;
end$$
delimiter ;
