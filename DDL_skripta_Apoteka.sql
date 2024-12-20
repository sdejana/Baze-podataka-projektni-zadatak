-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema apoteka
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema apoteka
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `apoteka` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `apoteka` ;

-- -----------------------------------------------------
-- Table `apoteka`.`SJEDISTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`SJEDISTE` (
  `BrojPoste` INT(11) NOT NULL,
  `Naziv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`BrojPoste`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `apoteka`.`APOTEKA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`APOTEKA` (
  `IdApoteke` INT(11) NOT NULL,
  `NazivApoteke` VARCHAR(50) NOT NULL,
  `Adresa` VARCHAR(50) NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  `BrojPoste` INT(11) NOT NULL,
  PRIMARY KEY (`IdApoteke`),
  INDEX `fk_APOTEKA_SJEDISTE_idx` (`BrojPoste` ASC) VISIBLE,
  CONSTRAINT `fk_APOTEKA_SJEDISTE`
    FOREIGN KEY (`BrojPoste`)
    REFERENCES `apoteka`.`SJEDISTE` (`BrojPoste`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `apoteka`.`PROIZVODJAC`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`PROIZVODJAC` (
  `IdProizvodjac` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(45) NOT NULL,
  `Adresa` VARCHAR(50) NOT NULL,
  `Telefon` VARCHAR(20) NULL,
  `BrojPoste` INT(11) NOT NULL,
  PRIMARY KEY (`IdProizvodjac`),
  INDEX `fk_DOBAVLJAC_SJEDISTE1_idx` (`BrojPoste` ASC) VISIBLE,
  UNIQUE INDEX `Naziv_UNIQUE` (`Naziv` ASC) VISIBLE,
  CONSTRAINT `fk_DOBAVLJAC_SJEDISTE1`
    FOREIGN KEY (`BrojPoste`)
    REFERENCES `apoteka`.`SJEDISTE` (`BrojPoste`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `apoteka`.`ARTIKAL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`ARTIKAL` (
  `Barkod` INT NOT NULL,
  `NazivArtikla` VARCHAR(50) NOT NULL,
  `Zaliha` INT NOT NULL DEFAULT '0',
  `IdProizvodjac` INT NOT NULL,
  PRIMARY KEY (`Barkod`),
  INDEX `fk_ARTIKAL_DOBAVLJAC1_idx` (`IdProizvodjac` ASC) VISIBLE,
  CONSTRAINT `fk_ARTIKAL_DOBAVLJAC1`
    FOREIGN KEY (`IdProizvodjac`)
    REFERENCES `apoteka`.`PROIZVODJAC` (`IdProizvodjac`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `apoteka`.`BANKA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`BANKA` (
  `IdBanke` INT(11) NOT NULL,
  `Naziv` VARCHAR(50) NOT NULL,
  `Adresa` VARCHAR(50) NOT NULL,
  `BrojPoste` INT(11) NOT NULL,
  PRIMARY KEY (`IdBanke`),
  INDEX `fk_BANKA_SJEDISTE1_idx` (`BrojPoste` ASC) VISIBLE,
  CONSTRAINT `fk_BANKA_SJEDISTE1`
    FOREIGN KEY (`BrojPoste`)
    REFERENCES `apoteka`.`SJEDISTE` (`BrojPoste`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `apoteka`.`ZAPOSLENI`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`ZAPOSLENIK` (
  `IdZaposlenika` INT NOT NULL AUTO_INCREMENT,
  `KorisnickoIme` VARCHAR(20) NOT NULL,
  `Lozinka` VARCHAR(20) NOT NULL,
  `Ime` VARCHAR(20) NOT NULL,
  `Prezime` VARCHAR(20) NOT NULL,
  `JMBG` CHAR(13) NOT NULL,
  `Email` VARCHAR(50) NULL,
  `DatumZaposlenja` DATE NOT NULL,
  `Plata` DECIMAL NOT NULL DEFAULT '0.00',
  `AdresaStanovanja` VARCHAR(50) NOT NULL,
  `RadnoVrijeme` VARCHAR(20) NULL,
  `BrojPoste` INT(11) NOT NULL,
  `IdApoteke` INT(11) NOT NULL,
  PRIMARY KEY (`IdZaposlenika`),
  UNIQUE INDEX `JMBG_UNIQUE` (`JMBG` ASC) VISIBLE,
  INDEX `fk_ZAPOSLENIK_APOTEKA1_idx` (`IdApoteke` ASC) VISIBLE,
  INDEX `fk_ZAPOSLENIK_SJEDISTE1_idx` (`BrojPoste` ASC) VISIBLE,
  UNIQUE INDEX `KorisnickoIme_UNIQUE` (`KorisnickoIme` ASC) VISIBLE,
  CONSTRAINT `fk_ZAPOSLENIK_APOTEKA1`
    FOREIGN KEY (`IdApoteke`)
    REFERENCES `apoteka`.`APOTEKA` (`IdApoteke`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_ZAPOSLENIK_SJEDISTE1`
    FOREIGN KEY (`BrojPoste`)
    REFERENCES `apoteka`.`SJEDISTE` (`BrojPoste`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `apoteka`.`FAKTURA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`FAKTURA` (
  `IdFaktura` INT NOT NULL AUTO_INCREMENT,
  `BrojFakture` INT NOT NULL,
  `DatumIzdavanja` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `IznosRacuna` DECIMAL(8,2) NOT NULL DEFAULT '0.00',
  `IdProizvodjac` INT NOT NULL,
  `IdZaposlenika` INT NOT NULL,
  PRIMARY KEY (`IdFaktura`),
  INDEX `fk_FAKTURA_DOBAVLJAC1_idx` (`IdProizvodjac` ASC) VISIBLE,
  INDEX `fk_FAKTURA_ZAPOSLENIK1_idx` (`IdZaposlenika` ASC) VISIBLE,
  UNIQUE INDEX `BrojFakture_UNIQUE` (`BrojFakture` ASC) VISIBLE,
  CONSTRAINT `fk_FAKTURA_DOBAVLJAC1`
    FOREIGN KEY (`IdProizvodjac`)
    REFERENCES `apoteka`.`PROIZVODJAC` (`IdProizvodjac`),
  CONSTRAINT `fk_FAKTURA_ZAPOSLENIK1`
    FOREIGN KEY (`IdZaposlenika`)
    REFERENCES `apoteka`.`ZAPOSLENIK` (`IdZaposlenika`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `apoteka`.`FARMACEUTSKI_TEHNICAR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`FARMACEUTSKI_TEHNICAR` (
  `IdZaposlenika` INT NOT NULL,
  PRIMARY KEY (`IdZaposlenika`),
  CONSTRAINT `fk_farmaceutski_tehnicar_ZAPOSLENIK1`
    FOREIGN KEY (`IdZaposlenika`)
    REFERENCES `apoteka`.`ZAPOSLENIK` (`IdZaposlenika`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `apoteka`.`KOZMETIKA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`KOZMETICKI_ASORTIMAN` (
  `JedinicaMjere` VARCHAR(10) NOT NULL,
  `Barkod` INT NOT NULL,
  PRIMARY KEY (`Barkod`),
  CONSTRAINT `fk_kozmeticki_asortiman_artikal1`
    FOREIGN KEY (`Barkod`)
    REFERENCES `apoteka`.`ARTIKAL` (`Barkod`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `apoteka`.`LIJEK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`LIJEK` (
  `Barkod` INT NOT NULL,
  `GenerickiNaziv` VARCHAR(45) NOT NULL,
  `Doza` VARCHAR(20) NOT NULL,
  `Oblik` VARCHAR(30) NOT NULL,
  `NazivListe` CHAR(2) NOT NULL,
  PRIMARY KEY (`Barkod`),
  CONSTRAINT `fk_LIJEK_artikal1`
    FOREIGN KEY (`Barkod`)
    REFERENCES `apoteka`.`ARTIKAL` (`Barkod`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `apoteka`.`MAGISTAR_FARMACIJE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`INZENJER_FARMACIJE` (
  `BrojLicence` VARCHAR(10) NOT NULL,
  `RokVazenja` DATE NOT NULL,
  `IdZaposlenika` INT NOT NULL,
  PRIMARY KEY (`IdZaposlenika`),
  CONSTRAINT `fk_magistar_farmacije_ZAPOSLENIK1`
    FOREIGN KEY (`IdZaposlenika`)
    REFERENCES `apoteka`.`ZAPOSLENIK` (`IdZaposlenika`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `apoteka`.`MEDICINSKI_APARAT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`MEDICINSKO_POMAGALO` (
  `Barkod` INT NOT NULL,
  PRIMARY KEY (`Barkod`),
  CONSTRAINT `fk_MEDICINSKO_POMAGALO_ARTIKAL1`
    FOREIGN KEY (`Barkod`)
    REFERENCES `apoteka`.`ARTIKAL` (`Barkod`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `apoteka`.`NARUDZBA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`NARUDZBA` (
  `BrojNarudzbe` INT NOT NULL,
  `DatumNabavke` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `IdZaposlenika` INT NOT NULL,
  `IdProizvodjac` INT(11) NOT NULL,
  PRIMARY KEY (`BrojNarudzbe`),
  INDEX `fk_NARUDZBA_ZAPOSLENIK1_idx` (`IdZaposlenika` ASC) VISIBLE,
  INDEX `fk_NARUDZBA_DOBAVLJAC1_idx` (`IdProizvodjac` ASC) VISIBLE,
  CONSTRAINT `fk_NARUDZBA_DOBAVLJAC1`
    FOREIGN KEY (`IdProizvodjac`)
    REFERENCES `apoteka`.`PROIZVODJAC` (`IdProizvodjac`),
  CONSTRAINT `fk_NARUDZBA_ZAPOSLENIK1`
    FOREIGN KEY (`IdZaposlenika`)
    REFERENCES `apoteka`.`ZAPOSLENIK` (`IdZaposlenika`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `apoteka`.`NARUDZBA_STAVKA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`NARUDZBA_STAVKA` (
  `Kolicina` INT NOT NULL,
  `BrojNarudzbe` INT NOT NULL,
  `Barkod` INT NOT NULL,
  PRIMARY KEY (`BrojNarudzbe`, `Barkod`),
  INDEX `fk_NARUDZBA_STAVKA_NARUDZBA1_idx` (`BrojNarudzbe` ASC) VISIBLE,
  INDEX `fk_NARUDZBA_STAVKA_ARTIKAL1_idx` (`Barkod` ASC) VISIBLE,
  CONSTRAINT `fk_NARUDZBA_STAVKA_ARTIKAL1`
    FOREIGN KEY (`Barkod`)
    REFERENCES `apoteka`.`ARTIKAL` (`Barkod`),
  CONSTRAINT `fk_NARUDZBA_STAVKA_NARUDZBA1`
    FOREIGN KEY (`BrojNarudzbe`)
    REFERENCES `apoteka`.`NARUDZBA` (`BrojNarudzbe`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `apoteka`.`RACUN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`RACUN` (
  `IdRacuna` INT NOT NULL AUTO_INCREMENT,
  `DatumIzdavanja` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UkupanIznos` DECIMAL(6,2) NOT NULL DEFAULT '0.00',
  `IdZaposlenika` INT NOT NULL,
  PRIMARY KEY (`IdRacuna`),
  INDEX `fk_RACUN_ZAPOSLENIK1_idx` (`IdZaposlenika` ASC) VISIBLE,
  CONSTRAINT `fk_RACUN_ZAPOSLENIK1`
    FOREIGN KEY (`IdZaposlenika`)
    REFERENCES `apoteka`.`ZAPOSLENIK` (`IdZaposlenika`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `apoteka`.`RECEPT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`RECEPT` (
  `IdRecept` INT NOT NULL AUTO_INCREMENT,
  `DatumIzdavanja` DATE NOT NULL,
  `BrojKnjizice` CHAR(11) NOT NULL,
  `JMBG` CHAR(13) NOT NULL,
  `SifraDoktora` INT(11) NOT NULL,
  PRIMARY KEY (`IdRecept`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `apoteka`.`RACUN_STAVKA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`RACUN_STAVKA` (
  `IdRacunStavka` INT NOT NULL AUTO_INCREMENT,
  `Kolicina` INT NOT NULL,
  `IdRacuna` INT NOT NULL,
  `Barkod` INT NOT NULL,
  `IdRecept` INT NULL,
  PRIMARY KEY (`IdRacunStavka`),
  INDEX `fk_ARTIKAL_has_RACUN_RACUN1_idx` (`IdRacuna` ASC) INVISIBLE,
  INDEX `fk_ARTIKAL_has_RACUN_ARTIKAL1_idx` (`Barkod` ASC) VISIBLE,
  INDEX `fk_PRODAJA_RECEPT1_idx` (`IdRecept` ASC) VISIBLE,
  CONSTRAINT `fk_ARTIKAL_has_RACUN_ARTIKAL1`
    FOREIGN KEY (`Barkod`)
    REFERENCES `apoteka`.`ARTIKAL` (`Barkod`),
  CONSTRAINT `fk_ARTIKAL_has_RACUN_RACUN1`
    FOREIGN KEY (`IdRacuna`)
    REFERENCES `apoteka`.`RACUN` (`IdRacuna`),
  CONSTRAINT `fk_PRODAJA_RECEPT1`
    FOREIGN KEY (`IdRecept`)
    REFERENCES `apoteka`.`RECEPT` (`IdRecept`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `apoteka`.`RACUN_U_BANCI`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`RACUN_U_BANCI` (
  `BrojRacuna` CHAR(16) NOT NULL,
  `IdBanke` INT(11) NOT NULL,
  PRIMARY KEY (`BrojRacuna`),
  INDEX `fk_RACUN_U_BANCI_BANKA1_idx` (`IdBanke` ASC) VISIBLE,
  CONSTRAINT `fk_RACUN_U_BANCI_BANKA1`
    FOREIGN KEY (`IdBanke`)
    REFERENCES `apoteka`.`BANKA` (`IdBanke`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `apoteka`.`RACUN_APOTEKA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`RACUN_APOTEKA` (
  `IdApoteke` INT(11) NOT NULL,
  `BrojRacuna` CHAR(16) NOT NULL,
  `Stanje` DECIMAL(10,2) NOT NULL DEFAULT '0.00',
  INDEX `fk_RACUN_APOTEKE_APOTEKA1_idx` (`IdApoteke` ASC) VISIBLE,
  PRIMARY KEY (`BrojRacuna`),
  CONSTRAINT `fk_RACUN_APOTEKE_APOTEKA1`
    FOREIGN KEY (`IdApoteke`)
    REFERENCES `apoteka`.`APOTEKA` (`IdApoteke`),
  CONSTRAINT `fk_RACUN_APOTEKA_RACUN_U_BANCI1`
    FOREIGN KEY (`BrojRacuna`)
    REFERENCES `apoteka`.`RACUN_U_BANCI` (`BrojRacuna`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `apoteka`.`RACUN_PROIZVODJAC`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`RACUN_PROIZVODJAC` (
  `IdProizvodjac` INT(11) NOT NULL,
  `BrojRacuna` CHAR(16) NOT NULL,
  `Stanje` DECIMAL(10,2) NOT NULL DEFAULT '0.00',
  INDEX `fk_RACUN_DOBAVLJACA_DOBAVLJAC1_idx` (`IdProizvodjac` ASC) VISIBLE,
  PRIMARY KEY (`BrojRacuna`),
  CONSTRAINT `fk_RACUN_DOBAVLJACA_DOBAVLJAC1`
    FOREIGN KEY (`IdProizvodjac`)
    REFERENCES `apoteka`.`PROIZVODJAC` (`IdProizvodjac`),
  CONSTRAINT `fk_RACUN_PROIZVODJAC_RACUN_U_BANCI1`
    FOREIGN KEY (`BrojRacuna`)
    REFERENCES `apoteka`.`RACUN_U_BANCI` (`BrojRacuna`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `apoteka`.`FAKTURA_STAVKA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`FAKTURA_STAVKA` (
  `IdFaktura` INT NOT NULL,
  `Barkod` INT NOT NULL,
  `Kolicina` INT NOT NULL,
  `CijenaStavke` DECIMAL(5,2) NOT NULL,
  PRIMARY KEY (`IdFaktura`, `Barkod`),
  INDEX `fk_stavka_faktura_ARTIKAL1_idx` (`Barkod` ASC) VISIBLE,
  INDEX `fk_FAKTURA_STAVKA_FAKTURA1_idx` (`IdFaktura` ASC) VISIBLE,
  CONSTRAINT `fk_stavka_faktura_ARTIKAL1`
    FOREIGN KEY (`Barkod`)
    REFERENCES `apoteka`.`ARTIKAL` (`Barkod`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_FAKTURA_STAVKA_FAKTURA1`
    FOREIGN KEY (`IdFaktura`)
    REFERENCES `apoteka`.`FAKTURA` (`IdFaktura`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `apoteka`.`TELEFON_APOTEKE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`TELEFON_APOTEKE` (
  `Telefon` VARCHAR(20) NOT NULL,
  `IdApoteke` INT(11) NOT NULL,
  PRIMARY KEY (`IdApoteke`, `Telefon`),
  INDEX `fk_TELEFON_APOTEKE_APOTEKA1_idx` (`IdApoteke` ASC) VISIBLE,
  CONSTRAINT `fk_TELEFON_APOTEKE_APOTEKA1`
    FOREIGN KEY (`IdApoteke`)
    REFERENCES `apoteka`.`APOTEKA` (`IdApoteke`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `apoteka`.`TIP_ARTIKLA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`TIP_ARTIKLA` (
  `IdTipArtikla` INT NOT NULL AUTO_INCREMENT,
  `NazivTipa` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`IdTipArtikla`),
  UNIQUE INDEX `NazivTipa_UNIQUE` (`NazivTipa` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `apoteka`.`CIJENA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`CIJENA` (
  `IdCijena` INT NOT NULL AUTO_INCREMENT,
  `NabavnaCijena` DECIMAL(5,2) NOT NULL,
  `ProdajnaCijena` DECIMAL(5,2) NOT NULL,
  `DatumOd` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DatumDo` DATETIME NULL,
  `Barkod` INT NOT NULL,
  `IdTipArtikla` INT NOT NULL,
  PRIMARY KEY (`IdCijena`),
  INDEX `fk_CIJENA_TIP_ARTIKLA1_idx` (`IdTipArtikla` ASC) INVISIBLE,
  CONSTRAINT `fk_CIJENA_ARTIKAL1`
    FOREIGN KEY (`Barkod`)
    REFERENCES `apoteka`.`ARTIKAL` (`Barkod`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_CIJENA_TIP_ARTIKLA1`
    FOREIGN KEY (`IdTipArtikla`)
    REFERENCES `apoteka`.`TIP_ARTIKLA` (`IdTipArtikla`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `apoteka`.`ARTIKAL_TIP_ARTIKLA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apoteka`.`ARTIKAL_TIP_ARTIKLA` (
  `Barkod` INT NOT NULL,
  `IdTipArtikla` INT NOT NULL,
  PRIMARY KEY (`Barkod`, `IdTipArtikla`),
  INDEX `fk_ARTIKAL_has_TIP_ARTIKLA_TIP_ARTIKLA1_idx` (`IdTipArtikla` ASC) VISIBLE,
  INDEX `fk_ARTIKAL_has_TIP_ARTIKLA_ARTIKAL1_idx` (`Barkod` ASC) VISIBLE,
  CONSTRAINT `fk_ARTIKAL_has_TIP_ARTIKLA_ARTIKAL1`
    FOREIGN KEY (`Barkod`)
    REFERENCES `apoteka`.`ARTIKAL` (`Barkod`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_ARTIKAL_has_TIP_ARTIKLA_TIP_ARTIKLA1`
    FOREIGN KEY (`IdTipArtikla`)
    REFERENCES `apoteka`.`TIP_ARTIKLA` (`IdTipArtikla`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
