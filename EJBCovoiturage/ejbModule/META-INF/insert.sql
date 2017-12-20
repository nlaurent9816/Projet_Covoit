INSERT INTO VILLE (id, ville) VALUES (0, 'Rennes');
INSERT INTO VILLE (id, ville) VALUES (1, 'Paris');
INSERT INTO VILLE (id, ville) VALUES (2, 'Lyon');
INSERT INTO VILLE (id, ville) VALUES (3, 'Bourges');
INSERT INTO VILLE (id, ville) VALUES (4, 'Marseille');
INSERT INTO VILLE (id, ville) VALUES (5, 'Angouleme');
INSERT INTO VILLE (id, ville) VALUES (6, 'Bordeaux');


INSERT INTO VEHICULE (id, gabaritvehicule) VALUES (0, 'tout-terrain');
INSERT INTO VEHICULE (id, gabaritvehicule) VALUES (1, 'mini');
INSERT INTO VEHICULE (id, gabaritvehicule) VALUES (2, 'citadine');
INSERT INTO VEHICULE (id, gabaritvehicule) VALUES (3, 'compacte');
INSERT INTO VEHICULE (id, gabaritvehicule) VALUES (4, 'familiale');
INSERT INTO VEHICULE (id, gabaritvehicule) VALUES (5, 'routiere');
INSERT INTO VEHICULE (id, gabaritvehicule) VALUES (6, 'berline');
INSERT INTO VEHICULE (id, gabaritvehicule) VALUES (7, 'coupe');
INSERT INTO VEHICULE (id, gabaritvehicule) VALUES (8, 'sportive');
INSERT INTO VEHICULE (id, gabaritvehicule) VALUES (9, 'monospace');

INSERT INTO INFOUTILISATEUR (idinfo, mail, nom, prenom, sexe, tel) VALUES (0, 'admin.admin@admin.ad', 'Admin', 'Admin', 'homme', '0123456789');
INSERT INTO LOGIN (login, password, role, infos_idinfo) VALUES ('admin', '8c6976e5b541415bde98bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 0, 0);

INSERT INTO INFOUTILISATEUR (idinfo, mail, nom, prenom, sexe, tel) VALUES (1, 'user.user@mail.fr', 'User', 'User', 'homme', '0978534212');
INSERT INTO LOGIN (login, password, role, infos_idinfo) VALUES ('user', '4f8996da763b7a969b128ee307569eaf3a635486ddab211d512c85b9df8fb', 1, 1);

INSERT INTO INFOUTILISATEUR (idinfo, mail, nom, prenom, sexe, tel) VALUES (2, 'manuel.ory@m.fr', 'Ory', 'Manuel', 'homme', '0978534212');
INSERT INTO LOGIN (login, password, role, infos_idinfo) VALUES ('manuel', 'f2d81a26dea8a10dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9', 1, 2);

INSERT INTO INFOUTILISATEUR (idinfo, mail, nom, prenom, sexe, tel) VALUES (3, 'nicolas.laurent@m.fr', 'Laurent', 'Nicolas', 'homme', '0978534212');
INSERT INTO LOGIN (login, password, role, infos_idinfo) VALUES ('nicolas', '204fb6b2a5fddfef1e9a7c5cf4b07b3c381df33526abc58a881bc4772425e', 1, 3);


INSERT INTO TRAJET (idTrajet, datedepart, heuredepart, monvehicule, nombreplaces, nombreplacesrestantes, conducteur_idinfo, gabaritvehicule_id, villedepart_id) VALUES (0, '2018-02-17', '8 h 20', 'Citroen Rouge', 3, 3, 2, 4, 0);
INSERT INTO TRAJET (idTrajet, datedepart, heuredepart, monvehicule, nombreplaces, nombreplacesrestantes, conducteur_idinfo, gabaritvehicule_id, villedepart_id) VALUES (1, '2017-12-31', '23 h 30', 'Renault Bleue', 4, 4, 3, 3, 4);
INSERT INTO TRAJET (idTrajet, datedepart, heuredepart, monvehicule, nombreplaces, nombreplacesrestantes, conducteur_idinfo, gabaritvehicule_id, villedepart_id) VALUES (2, '2018-01-26', '12 h 40', 'Clio rouge', 3, 3, 2, 2, 2);
INSERT INTO TRAJET (idTrajet, datedepart, heuredepart, monvehicule, nombreplaces, nombreplacesrestantes, conducteur_idinfo, gabaritvehicule_id, villedepart_id) VALUES (3, '2018-03-20', '9 h 10', 'Clio noire', 2, 2, 2, 1, 3);
INSERT INTO TRAJET (idTrajet, datedepart, heuredepart, monvehicule, nombreplaces, nombreplacesrestantes, conducteur_idinfo, gabaritvehicule_id, villedepart_id) VALUES (4, '2018-02-23', '13 h 40', 'BMW', 1, 1, 3, 7, 1);
INSERT INTO TRAJET (idTrajet, datedepart, heuredepart, monvehicule, nombreplaces, nombreplacesrestantes, conducteur_idinfo, gabaritvehicule_id, villedepart_id) VALUES (5, '2018-01-12', '11 h 15', 'Mercedes grise', 2, 2, 1, 6, 5);


INSERT INTO ETAPE (id,tarif, ville_id) VALUES (0,25.0,3);
INSERT INTO ETAPE (id,tarif, ville_id) VALUES (1,32.0,2);
INSERT INTO ETAPE (id,tarif, ville_id) VALUES (2,50.0,2);
INSERT INTO ETAPE (id,tarif, ville_id) VALUES (3,23.0,3);
INSERT INTO ETAPE (id,tarif, ville_id) VALUES (4,60.0,0);
INSERT INTO ETAPE (id,tarif, ville_id) VALUES (5,75.0,1);
INSERT INTO ETAPE (id,tarif, ville_id) VALUES (6,20.0,1);
INSERT INTO ETAPE (id,tarif, ville_id) VALUES (7,18.0,0);
INSERT INTO ETAPE (id,tarif, ville_id) VALUES (8,40.0,5);
INSERT INTO ETAPE (id,tarif, ville_id) VALUES (9,48.0,6);
INSERT INTO ETAPE (id,tarif, ville_id) VALUES (10,10.0,6);


INSERT INTO ETAPESTRAJET (id_trajet, id_etape) VALUES (0, 0);/*Rennes->Bourges->Lyon*/
INSERT INTO ETAPESTRAJET (id_trajet, id_etape) VALUES (0, 1);
INSERT INTO ETAPESTRAJET (id_trajet, id_etape) VALUES (1, 2);/*Marseille->Lyon*/
INSERT INTO ETAPESTRAJET (id_trajet, id_etape) VALUES (2, 3);/*Lyon->Bourges->Rennes->Paris*/
INSERT INTO ETAPESTRAJET (id_trajet, id_etape) VALUES (2, 4);
INSERT INTO ETAPESTRAJET (id_trajet, id_etape) VALUES (2, 5);
INSERT INTO ETAPESTRAJET (id_trajet, id_etape) VALUES (3, 6); /*Bourges->Paris*/

INSERT INTO ETAPESTRAJET (id_trajet, id_etape) VALUES (4, 7);/*Paris->Rennes->Angouleme->Bordeaux*/
INSERT INTO ETAPESTRAJET (id_trajet, id_etape) VALUES (4, 8);
INSERT INTO ETAPESTRAJET (id_trajet, id_etape) VALUES (4, 9);
INSERT INTO ETAPESTRAJET (id_trajet, id_etape) VALUES (5, 10);/*Angouleme->Bordeaux*/

