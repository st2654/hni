insert into users values(1, 'Super','User','M','mphone', 'superuser@hni.com', 0, 'qqIDI/cPs/CqdMo15uDhwAN/Zc+Z9VOUjLNGgxlC864=', '42M7kr4oektqA6Jgy9u1YQ==', now(), '0');
insert into users values(2, 'Freddy','Fikes','M','479-555-1212', '', 0, '', '', now(), '0');
insert into users values(3, 'Mikey','Multiphone','M','479-555-4321', '', 0, '', '', now(), '0');
insert into users values(4, 'Mikey','Multiphone','M','479-555-5678', '', 0, '', '', now(), '0');
insert into users values(5, 'Ericka', 'Energy', 'F', '123-456-7830', '' ,0, '', '', now(), '0');
insert into users values(6, 'Barbara', 'Bollingsworth', 'F', '123-456-7830', '' ,0, '', '', now(), '0');

insert into organizations values(1, 'Not Impossible', 'phone', 'website', 'logo', now(), 1);
insert into organizations values(2, 'Samaritan House', 'phone', 'website', 'logo', now(), 1);
insert into organizations values(3, 'Volunteer Organization', 'phone', 'website', 'logo', now(), 1);

insert into user_organization_role values(1, 2, 1);
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

insert into providers values(1, 'Subway', now(), 1);
insert into provider_locations values(1, 'Subway #1', 1, now(), 1);
insert into menus values(1, '"Subway Lunch', 1, 10, 16); /* 10am to 4pm */
insert into menu_items values(1, 1, 'Ham Sandwich', 'ham and cheese with LTP', 6.99, null);
insert into menu_items values(2, 1, 'Turkey Sandwich', 'turkey and cheese with LTP', 7.99, null);
insert into menu_items values(3, 1, 'Roast beef Sandwich', 'beef and cheese with LTP', 8.99, null);
insert into menu_items values(4, 1, 'Club Sandwich', 'turkey, ham and cheese with LTP', 7.95, null);

insert into orders values(1, 2, 1, dateadd('DAY', -2, current_date), dateadd('DAY', -2, current_date), null, 9.95, 1.20, 1);

insert into security_roles values(1,'Super User');
insert into security_roles values(2,'Administrator');
insert into security_roles values(3,'Volunteer');
insert into security_roles values(4,'Client');
insert into security_roles values(5,'User');

insert into security_permissions values(1,'*','*'); /* user-user only! */

insert into security_permissions values(11,'organizations','create');
insert into security_permissions values(12,'organizations','read');
insert into security_permissions values(13,'organizations','update');
insert into security_permissions values(14,'organizations','delete');
insert into security_permissions values(15,'organizations','*');

insert into security_permissions values(21,'users','create');
insert into security_permissions values(22,'users','read');
insert into security_permissions values(23,'users','update');
insert into security_permissions values(24,'users','delete');
insert into security_permissions values(25,'users','*');

insert into security_permissions values(41,'providers','create');
insert into security_permissions values(42,'providers','read');
insert into security_permissions values(43,'providers','update');
insert into security_permissions values(44,'providers','delete');
insert into security_permissions values(45,'providers','*');

insert into security_permissions values(61,'orders','create');
insert into security_permissions values(62,'orders','read');
insert into security_permissions values(63,'orders','update');
insert into security_permissions values(64,'orders','delete');
insert into security_permissions values(65,'orders','*');
insert into security_permissions values(67,'orders','provision');


insert into security_permissions values(81,'activation-codes','create');
insert into security_permissions values(82,'activation-codes','read');
insert into security_permissions values(83,'activation-codes','update');
insert into security_permissions values(84,'activation-codes','delete');
insert into security_permissions values(85,'activation-codes','*');

/* super-user / admin */
insert into security_role_permissions values(1,1,1);
insert into security_role_permissions values(2,15,0);
insert into security_role_permissions values(2,25,1);
insert into security_role_permissions values(2,45,1);
insert into security_role_permissions values(2,65,1);
insert into security_role_permissions values(2,85,1);

/* volunteer */
insert into security_role_permissions values(3,12,0);
insert into security_role_permissions values(3,67,1);

/* client/user */
insert into security_role_permissions values(4,12,0);
insert into security_role_permissions values(5,12,0);

insert into activation_codes values('MTIzNDU2WX', 123456, 2, 10, 10, 0, 29, now(), null);
insert into activation_codes values('OTg3NjU0KZ', 987654, 2, 10, 10, 1, 'freddy has activated this', now(), 2);


insert into providers values(2, 'KFC', now(), 1);
insert into providers values(3, 'Wendys', now(), 1);

insert into provider_locations values(2, 'KFC #1', 1, now(), 1);
insert into provider_locations values(3, 'Wendy #1', 1, now(), 1);

insert into addresses values
(1, 'subway addr', '1251 Phoenician way', '', 'columbus','oh','43240', '-82.98402279999999', '40.138686','etc');


insert into addresses values
(2, 'subway addr', '10790 parkridge blvd', '', 'reston','va','20191', '-77.316008', '38.9455603','etc');


insert into provider_location_addresses values(1, 1);
insert into provider_location_addresses values(1, 2);
insert into provider_location_addresses values(2, 2);
insert into provider_location_addresses values(3, 2);