CREATE TABLE `Rennen` (
                          `RennID` int  NOT NULL ,
                          `datum` date  NOT NULL ,
                          `ort` varchar(30)  NOT NULL ,
                          PRIMARY KEY (
                                       `RennID`
                              )
);

CREATE TABLE `Reifen` (
                          `reifenID` int AUTO_INCREMENT NOT NULL ,
                          `bezeichnung` varchar(30)  NOT NULL ,
                          `uhrzeit` time  NOT NULL ,
                          `session` varchar(30)  NOT NULL ,
                          `spez` varchar(30)  NOT NULL ,
                          `kaltdruck1` int  NOT NULL ,
                          `kaltdruck2` int  NOT NULL ,
                          `kaltdruck3` int  NOT NULL ,
                          `kaltdruck4` int  NOT NULL ,
                          `kaltdruckTemp` int  NOT NULL ,
                          `heatingTemp` int  NOT NULL ,
                          `heatingTime` int  NOT NULL ,
                          `heatingStart` time  NOT NULL ,
                          `heatingEnd` time  NOT NULL ,
                          `bleed_in_blanket` double  NULL ,
                          `TP_hot1` double  NULL ,
                          `TP_hot2` double  NULL ,
                          `TP_hot3` double  NULL ,
                          `TP_hot4` double  NULL ,
                          `target` double  NULL ,
                          `bleed_hot1` double  NULL ,
                          `bleed_hot2` double  NULL ,
                          `bleed_hot3` double  NULL ,
                          `bleed_hot4` double  NULL ,
                          `abgegben_fuer` varchar(30)  NULL ,
                          PRIMARY KEY (
                                       `reifenID`
                              )
);

CREATE TABLE `Wetter` (
                          `WetterID` int  NOT NULL ,
                          `RennID` int  NOT NULL ,
                          `uhrzeit` time  NOT NULL ,
                          `luftTemp` int  NOT NULL ,
                          `strecktTemp` int  NOT NULL ,
                          `conditions` varchar(30)  NOT NULL ,
                          PRIMARY KEY (
                                       `WetterID`
                              )
);

CREATE TABLE `ReifenRennneRel` (
                                   `reifenID` int  NOT NULL ,
                                   `RennID` int  NOT NULL
);

ALTER TABLE `Wetter` ADD CONSTRAINT `fk_Wetter_RennID` FOREIGN KEY(`RennID`)
    REFERENCES `Rennen` (`RennID`);

ALTER TABLE `ReifenRennneRel` ADD CONSTRAINT `fk_ReifenRennneRel_reifenID` FOREIGN KEY(`reifenID`)
    REFERENCES `Reifen` (`reifenID`);

ALTER TABLE `ReifenRennneRel` ADD CONSTRAINT `fk_ReifenRennneRel_RennID` FOREIGN KEY(`RennID`)
    REFERENCES `Rennen` (`RennID`);