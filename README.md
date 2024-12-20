# Baze podataka - Projektni zadatak  

Ovo je projektni zadatak za predmet **Baze podataka** na Elektrotehničkom fakultetu u Banjoj Luci. Cilj projekta je kreiranje baze podataka sa odgovarajućim modelom i funkcionalnostima za desktop aplikaciju koja koristi MySQL bazu podataka.  

## Kontakt informacije

e-mail: smiljanicdejana02@gmail.com, dejana.smiljanic@student.etf.unibl.org

LinkedIn profil: https://www.linkedin.com/in/dejana-smiljanic-7129b931a/

---

## 📋 Opis projekta  
Projekat obuhvata:  
- Dizajn konceptualnog modela baze podataka (ER model).  
- Generisanje DDL skripti za kreiranje baze u MySQL-u.  
- Kreiranje pogleda, procedura, trigera i inicijalnog seta podataka.  
- Implementaciju desktop aplikacije u **Java** ili **C#**, sa podrškom za rad sa bazom podataka.  

### Ključne funkcionalnosti:  
- Upravljanje podacima (unos, izmjena, brisanje, pregled).  
- Prikaz relevantnih izveštaja.  
- Implementacija složenih upita i logike kroz procedure i trigere.  

---

## 🛠️ Tehnologije  
- **MySQL** – baza podataka.  
- **Java** ili **C#** – za desktop aplikaciju.  
- **SQL** – za rad sa DDL, DML i složenim upitima.  

---

## 📂 Struktura projekta  
- **/DDL/** – Skripte za kreiranje baze podataka.  
- **/ERD/** – Dijagrami baze podataka.  
- **/Data/** – Skripte za inicijalne podatke.  
- **/Procedures/** – SQL procedure i pogledi.  
- **/Triggers/** – Trigeri implementirani u MySQL-u.  
- **/Application/** – Kod desktop aplikacije.  

---

## 🧑‍💻 Pokretanje projekta  
### 1. Priprema baze podataka  
1. Pokrenite MySQL server.  
2. Učitajte DDL skripte.  
3. Učitajte inicijalne podatke.  

### 2. Pokretanje aplikacije  
1. Otvorite projekat u IntelliJ IDEA (za Java implementaciju).  
2. Podesite konekcione parametre ka bazi podataka.  
3. Pokrenite aplikaciju.  

---

## 📊 Sadržaj baze  
- **Entiteti:** 15-20 entiteta sa različitim relacijama (1:N, N:M).  
- **Pogledi i procedure:** Definisani za složene upite i agregaciju podataka.  
- **Trigeri:** Implementacija logike u bazi podataka za automatske radnje.  

---

## 🗂️ Primjer upita  
```sql
-- Primjer procedure za dodavanje zaposlenika

delimiter $$
create procedure dodaj_zaposlenika(in pIme varchar(45), in pPrezime varchar(45), in pJMBG char(13), in pEmail varchar(50), in pDatumZaposlenja date, in pPlata double,
in pAdresaStanovanja varchar(50), in pRadnoVrijeme varchar(20), in pBrojPoste int, in pIdApoteke int)
begin
    insert into zaposlenik(IdZaposlenika, Ime, Prezime, JMBG, Email, DatumZaposlenja, Plata, AdresaStanovanja, RadnoVrijeme, BrojPoste, IdApoteke) values (pIme, pPrezime, pJMBG, pEmail, pDatumZaposlenja, pPlata, pAdresaStanovanja, pRadnoVrijeme, pBrojPoste, pIdApoteke);
end$$
delimiter ;


