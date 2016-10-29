insert into users values(1,'Super','User','M','mphone', 'superuser@hni.com', 0, 'pwd', 'salt', now());
insert into users values(2,'Freddy','Fikes','M','479-555-1212', '', 0, '', '', now());
insert into users values(3,'Mikey','Multiphone','M','479-555-4321', '', 0, '', '', now());
insert into users values(4,'Mikey','Multiphone','M','479-555-5678', '', 0, '', '', now());
insert into users values(5, 'Ericka', 'Energy', 'F', '123-456-7830', '' ,0, '', '', now());
insert into users values(6, 'Barbara', 'Bollingsworth', 'F', '123-456-7830', '' ,0, '', '', now());

insert into organizations values(1, 'Not Impossible', 'phone', 'phone', 'logo', now(), 1);
insert into organizations values(2, 'Samaritan House', 'phone', 'website', 'logo', now(), 1);

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

insert into user_token values(1,'ABCDEFGHIJKLMNOP', current_timestamp);
insert into user_token values(2,'BBCDEFGHIJKLMNOP', current_timestamp);
insert into user_token values(3,'CBCDEFGHIJKLMNOP', current_timestamp);
insert into user_token values(4,'DBCDEFGHIJKLMNOP', current_timestamp);
insert into user_token values(5,'EBCDEFGHIJKLMNOP', current_timestamp);
insert into user_token values(6,'FBCDEFGHIJKLMNOP', current_timestamp);

insert into roles values(1,'Administrator');

insert into permissions values(1,'Create Organization');
insert into permissions values(2,'Read Organization');
insert into permissions values(3,'Update Organization');
insert into permissions values(4,'Delete Organization');

insert into permissions values(5,'Create User');
insert into permissions values(6,'Read User');
insert into permissions values(7,'Update User');
insert into permissions values(8,'Delete User');

insert into permissions values(9,'Create Role');
insert into permissions values(10,'Read Role');
insert into permissions values(11,'Update Role');
insert into permissions values(12,'Delete Role');

insert into permissions values(13,'Create Permission');
insert into permissions values(14,'Read Permission');
insert into permissions values(15,'Update Permission');
insert into permissions values(16,'Delete Permission');

insert into permissions values(17,'Create Providers');
insert into permissions values(18,'Read Providers');
insert into permissions values(19,'Update Providers');
insert into permissions values(20,'Delete Providers');

insert into permissions values(21,'Create Provider Location');
insert into permissions values(22,'Read Provider Location');
insert into permissions values(23,'Update Provider Location');
insert into permissions values(24,'Delete Provider Location');

insert into permissions values(25,'Create Order');
insert into permissions values(26,'Read Order');
insert into permissions values(27,'Update Order');
insert into permissions values(28,'Delete Order');

insert into permissions values(29,'Create Menu');
insert into permissions values(30,'Read Menu');
insert into permissions values(31,'Update Menu');
insert into permissions values(32,'Delete Menu');

insert into permissions values(33,'Create Menu Item');
insert into permissions values(34,'Read Menu Item');
insert into permissions values(35,'Update Menu Item');
insert into permissions values(36,'Delete Menu Item');

insert into permissions values(37,'Create Order Item');
insert into permissions values(38,'Read Order Item');
insert into permissions values(39,'Update Order Item');
insert into permissions values(40,'Delete Order Item');

insert into permissions values(41,'Create Address');
insert into permissions values(42,'Read Address');
insert into permissions values(43,'Update Address');
insert into permissions values(44,'Delete Address');

insert into permissions values(45,'Create Activation Code');
insert into permissions values(46,'Read Activiation Code');
insert into permissions values(47,'Update Activation Code');
insert into permissions values(48,'Delete Activation Code');

insert into permissions values(49,'Create Location Hours');
insert into permissions values(50,'Read Location Hours');
insert into permissions values(51,'Update Location Hours');
insert into permissions values(52,'Delete Location Hours');

insert into permissions values(53,'Link User-Organization-Role');
insert into permissions values(54,'Unlink User-Organization-Role');

insert into permissions values(55,'Link User-Provider-Role');
insert into permissions values(56,'Unlink User-Provider-Role');

insert into permissions values(57,'Link Provider-Address');
insert into permissions values(58,'Unlink Provider-Address');

insert into permissions values(59,'Link Provider-Location-Address');
insert into permissions values(60,'Unlink Provider-Location-Address');

insert into permissions values(61,'Link Organization-Address');
insert into permissions values(62,'Unlink Organization-Address');

insert into permissions values(63,'Link Role-Permission');
insert into permissions values(64,'Unlink Role-Permission');

insert into user_organization_role values(1, 2, 1);

insert into role_permission values(1,1);
insert into role_permission values(1,2);
insert into role_permission values(1,3);
insert into role_permission values(1,4);
insert into role_permission values(1,5);
insert into role_permission values(1,6);
insert into role_permission values(1,7);
insert into role_permission values(1,8);
insert into role_permission values(1,9);
insert into role_permission values(1,10);
insert into role_permission values(1,11);
insert into role_permission values(1,12);
insert into role_permission values(1,13);
insert into role_permission values(1,14);
insert into role_permission values(1,15);
insert into role_permission values(1,16);
insert into role_permission values(1,17);
insert into role_permission values(1,18);
insert into role_permission values(1,19);
insert into role_permission values(1,20);
insert into role_permission values(1,21);
insert into role_permission values(1,22);
insert into role_permission values(1,23);
insert into role_permission values(1,24);
insert into role_permission values(1,25);
insert into role_permission values(1,26);
insert into role_permission values(1,27);
insert into role_permission values(1,28);
insert into role_permission values(1,29);
insert into role_permission values(1,30);
insert into role_permission values(1,31);
insert into role_permission values(1,32);
insert into role_permission values(1,33);
insert into role_permission values(1,34);
insert into role_permission values(1,35);
insert into role_permission values(1,36);
insert into role_permission values(1,37);
insert into role_permission values(1,38);
insert into role_permission values(1,39);
insert into role_permission values(1,40);
insert into role_permission values(1,41);
insert into role_permission values(1,42);
insert into role_permission values(1,43);
insert into role_permission values(1,44);
insert into role_permission values(1,45);
insert into role_permission values(1,46);
insert into role_permission values(1,47);
insert into role_permission values(1,48);
insert into role_permission values(1,49);
insert into role_permission values(1,50);
insert into role_permission values(1,51);
insert into role_permission values(1,52);
insert into role_permission values(1,53);
insert into role_permission values(1,54);
insert into role_permission values(1,55);
insert into role_permission values(1,56);
insert into role_permission values(1,57);
insert into role_permission values(1,58);
insert into role_permission values(1,59);
insert into role_permission values(1,60);
insert into role_permission values(1,61);
insert into role_permission values(1,62);
insert into role_permission values(1,63);
insert into role_permission values(1,64);