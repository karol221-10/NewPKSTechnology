DELETE FROM BUS;
DELETE FROM FUEL;
DELETE FROM INSURANCES;
DELETE FROM INSPECTION;

/* fuel id, quantify, type;
    inspection id type, creation date, expirydate, comment
    insurances id ,type,creation date, expirydate,comment
    bus id model registrationnumber
 */

INSERT INTO BUS VALUES(1,'CITROEN','TKA 2137');
INSERT INTO BUS VALUES (2, 'TOYOTA','TKA M761A');

 INSERT INTO FUEL(ID,QUANTIFY,BUS_ID,TYPE) VALUES(1, 10.0 ,1, 'BENZINE');
 INSERT INTO FUEL(ID,QUANTIFY,BUS_ID,TYPE) VALUES(2, 15.0,2, 'DIESEL');

 INSERT INTO INSPECTION(ID,TYPE,CREATION_DATE,EXPIRY_DATE,COMMENT,BUS_ID) VALUES (1,'CYCLIC', parsedatetime('18-04-2020 09:00:00', 'dd-MM-yyyy hh:mm:ss'),parsedatetime('18-05-2020 09:00:00', 'dd-MM-yyyy hh:mm:ss'),'Brak usterek',1);
 INSERT INTO INSPECTION(ID,TYPE,CREATION_DATE,EXPIRY_DATE,COMMENT,BUS_ID) VALUES (2,'CYCLIC', parsedatetime('20-04-2020 09:00:00', 'dd-MM-yyyy hh:mm:ss'),parsedatetime('20-05-2020 09:00:00', 'dd-MM-yyyy hh:mm:ss'),'Wymieniono wycieraczki',2);

 INSERT INTO INSURANCES(ID,TYPE,CREATION_DATE,EXPIRY_DATE,COMMENT,BUS_ID) VALUES(1, 'OC', parsedatetime('18-04-2020 09:00:00', 'dd-MM-yyyy hh:mm:ss'),parsedatetime('18-10-2020 09:00:00', 'dd-MM-yyyy hh:mm:ss'),'null',1);
 INSERT INTO INSURANCES(ID,TYPE,CREATION_DATE,EXPIRY_DATE,COMMENT,BUS_ID) VALUES(2, 'OC/AC', parsedatetime('20-04-2020 09:00:00', 'dd-MM-yyyy hh:mm:ss'),parsedatetime('20-10-2020 09:00:00', 'dd-MM-yyyy hh:mm:ss'),'null',2);

