INSERT INTO VILLE (id, ville) VALUES (0, 'Rennes');
INSERT INTO VILLE (id, ville) VALUES (1, 'Paris');
INSERT INTO VILLE (id, ville) VALUES (2, 'Lyon');
INSERT INTO VILLE (id, ville) VALUES (3, 'Bourges');
INSERT INTO VILLE (id, ville) VALUES (4, 'Marseille');
INSERT INTO VILLE (id, ville) VALUES (5, 'Angouleme');


INSERT INTO VEHICULE (id, gabaritvehicule) VALUES (0, 'tout-terrain');
INSERT INTO VEHICULE (id, gabaritvehicule) VALUES (1, 'mini');
INSERT INTO VEHICULE (id, gabaritvehicule) VALUES (2, 'citadine');
INSERT INTO VEHICULE (id, gabaritvehicule) VALUES (3, 'compacte');
INSERT INTO VEHICULE (id, gabaritvehicule) VALUES (4, 'familiale');
INSERT INTO VEHICULE (id, gabaritvehicule) VALUES (5, 'routiere');
INSERT INTO VEHICULE (id, gabaritvehicule) VALUES (6, 'berline');
INSERT INTO VEHICULE (id, gabaritvehicule) VALUES (7, 'coupé');
INSERT INTO VEHICULE (id, gabaritvehicule) VALUES (8, 'sportive');
INSERT INTO VEHICULE (id, gabaritvehicule) VALUES (9, 'monospace');

INSERT INTO INFOUTILISATEUR (idinfo, mail, nom, prenom, sexe, tel) VALUES (0, 'admin.admin@admin.ad', 'Admin', 'Admin', 'homme', '0123456789');
INSERT INTO LOGIN (login, password, role, infos_idinfo) VALUES ('admin', '8c6976e5b541415bde98bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 0, 0);

INSERT INTO INFOUTILISATEUR (idinfo, mail, nom, prenom, sexe, tel) VALUES (1, 'georges.perec@mail.fr', 'Georges', 'Perec', 'homme', '0978534212');
INSERT INTO LOGIN (login, password, role, infos_idinfo) VALUES ('a', 'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 1, 1);

INSERT INTO TRAJET (idTrajet, datedepart, heuredepart, monvehicule, nombreplaces, nombreplacesrestantes, conducteur_idinfo, gabaritvehicule_id, villedepart_id) VALUES (0, '2017-12-17', '8 h 20', 'aa', 3, 3, 0, 0, 0);
INSERT INTO TRAJET (idTrajet, datedepart, heuredepart, monvehicule, nombreplaces, nombreplacesrestantes, conducteur_idinfo, gabaritvehicule_id, villedepart_id) VALUES (1, '2017-12-31', '23 h 30', 'aaa', 4, 4, 0, 3, 4);
INSERT INTO TRAJET (idTrajet, datedepart, heuredepart, monvehicule, nombreplaces, nombreplacesrestantes, conducteur_idinfo, gabaritvehicule_id, villedepart_id) VALUES (2, '2017-12-26', '12 h 40', 'aaa', 1, 1, 0, 3, 2);
INSERT INTO TRAJET (idTrajet, datedepart, heuredepart, monvehicule, nombreplaces, nombreplacesrestantes, conducteur_idinfo, gabaritvehicule_id, villedepart_id) VALUES (3, '2017-12-20', '9 h 10', 'aaa', 2, 2, 0, 3, 3);


INSERT INTO ETAPE (id,tarif, ville_id) VALUES (0,32.0,2);
INSERT INTO ETAPE (id,tarif, ville_id) VALUES (1,25.0,3);
INSERT INTO ETAPE (id,tarif, ville_id) VALUES (2,50.0,2);
INSERT INTO ETAPE (id,tarif, ville_id) VALUES (3,75.0,5);
INSERT INTO ETAPE (id,tarif, ville_id) VALUES (4,60.0,4);
INSERT INTO ETAPE (id,tarif, ville_id) VALUES (5,23.0,3);
INSERT INTO ETAPE (id,tarif, ville_id) VALUES (6,18.0,1);


INSERT INTO ETAPESTRAJET (id_trajet, id_etape) VALUES (0, 1);
INSERT INTO ETAPESTRAJET (id_trajet, id_etape) VALUES (0, 2);
INSERT INTO ETAPESTRAJET (id_trajet, id_etape) VALUES (1, 3);
INSERT INTO ETAPESTRAJET (id_trajet, id_etape) VALUES (2, 4);
INSERT INTO ETAPESTRAJET (id_trajet, id_etape) VALUES (2, 5);
INSERT INTO ETAPESTRAJET (id_trajet, id_etape) VALUES (2, 6);
INSERT INTO ETAPESTRAJET (id_trajet, id_etape) VALUES (3, 0);

