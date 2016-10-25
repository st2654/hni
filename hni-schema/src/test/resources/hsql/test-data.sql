insert into users values(1,'Super','User','M','mphone', 'superuser@hni.com', 0, 'pwd', 'salt', now());
insert into users values(2,'Freddy','Fikes','M','479-555-1212', '', 0, '', '', now());
insert into users values(3,'Mikey','Multiphone','M','479-555-4321', '', 0, '', '', now());
insert into users values(4,'Mikey','Multiphone','M','479-555-5678', '', 0, '', '', now());
insert into users values(5, 'Ericka', 'Energy', 'F', '123-456-7830', '' ,0, '', '', now());
insert into users values(6, 'Barbara', 'Bollingsworth', 'F', '123-456-7830', '' ,0, '', '', now());

insert into organizations values(1, 'Not Impossible', '', '', now(), 1);
insert into organizations values(2, 'Samaritan House', '', '', now(), 1);

insert into user_organization_role values(2, 2, 7);
insert into user_organization_role values(3, 2, 7);
insert into user_organization_role values(4, 2, 7);
insert into user_organization_role values(5, 4, 5);
insert into user_organization_role values(5, 4, 2);
insert into user_organization_role values(5, 2, 5);
insert into user_organization_role values(5, 2, 2);
insert into user_organization_role values(6, 4, 5);
insert into user_organization_role values(6, 4, 2);
insert into user_organization_role values(6, 2, 5);
insert into user_organization_role values(6, 2, 2);
