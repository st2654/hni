insert into users values(1,'Super','User','M','mphone', 'superuser@hni.com', 0, 'pwd', 'salt', now());
insert into users values(2,'Freddy','Fikes','M','479-555-1212', '', 0, '', '', now());
insert into users values(3,'Mikey','Multiphone','M','479-555-4321', '', 0, '', '', now());
insert into users values(4,'Mikey','Multiphone','M','479-555-5678', '', 0, '', '', now());
insert into users values(5, 'Ericka', 'Energy', 'F', '123-456-7830', '' ,0, '', '', now());
insert into users values(6, 'Barbara', 'Bollingsworth', 'F', '123-456-7830', '' ,0, '', '', now());

insert into organizations values(1, 'Not Impossible', 'phone', 'phone', 'logo', now(), 1);
insert into organizations values(2, 'Samaritan House', 'phone', 'website', 'logo', now(), 1);

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

insert into roles values(1,'Administrator');

insert into permissions values(1,'organizations','create',null);
insert into permissions values(2,'organizations','read',null);
insert into permissions values(3,'organizations','update',null);
insert into permissions values(4,'organizations','delete',null);
insert into permissions values(5,'organizations','*',null);

insert into permissions values(6,'users','create',null);
insert into permissions values(7,'users','read',null);
insert into permissions values(8,'users','update',null);
insert into permissions values(9,'users','delete',null);
insert into permissions values(10,'users','*',null);

insert into permissions values(11,'roles','create',null);
insert into permissions values(12,'roles','read',null);
insert into permissions values(13,'roles','update',null);
insert into permissions values(14,'roles','delete',null);
insert into permissions values(15,'roles','*',null);

insert into permissions values(16,'permissions','create',null);
insert into permissions values(17,'permissions','read',null);
insert into permissions values(18,'permissions','update',null);
insert into permissions values(19,'permissions','delete',null);
insert into permissions values(20,'permissions','*',null);

insert into permissions values(21,'providers','create',null);
insert into permissions values(22,'providers','read',null);
insert into permissions values(23,'providers','update',null);
insert into permissions values(24,'providers','delete',null);
insert into permissions values(25,'providers','*',null);

insert into permissions values(26,'provider-locations','create',null);
insert into permissions values(27,'provider-locations','read',null);
insert into permissions values(28,'provider-locations','update',null);
insert into permissions values(29,'provider-locations','delete',null);
insert into permissions values(30,'provider-locations','*',null);

insert into permissions values(31,'orders','create',null);
insert into permissions values(32,'orders','read',null);
insert into permissions values(33,'orders','update',null);
insert into permissions values(34,'orders','delete',null);
insert into permissions values(35,'orders','*',null);

insert into permissions values(36,'menus','create',null);
insert into permissions values(37,'menus','read',null);
insert into permissions values(38,'menus','update',null);
insert into permissions values(39,'menus','delete',null);
insert into permissions values(40,'menus','*',null);

insert into permissions values(41,'menu-items','create',null);
insert into permissions values(42,'menu-items','read',null);
insert into permissions values(43,'menu-items','update',null);
insert into permissions values(44,'menu-items','delete',null);
insert into permissions values(45,'menu-items','*',null);

insert into permissions values(46,'order-items','create',null);
insert into permissions values(47,'order-items','read',null);
insert into permissions values(48,'order-items','update',null);
insert into permissions values(49,'order-items','delete',null);
insert into permissions values(50,'order-items','*',null);

insert into permissions values(51,'addresses', 'create', null);
insert into permissions values(52,'addresses', 'read', null);
insert into permissions values(53,'addresses', 'update', null);
insert into permissions values(54,'addresses', 'delete', null);
insert into permissions values(55,'addresses', '*', null);

insert into permissions values(56,'activation-codes','create',null);
insert into permissions values(57,'activation-codes','read',null);
insert into permissions values(58,'activation-codes','update',null);
insert into permissions values(59,'activation-codes','delete',null);
insert into permissions values(60,'activation-codes','*',null);

insert into permissions values(61,'location-hours','create',null);
insert into permissions values(62,'location-hours','read',null);
insert into permissions values(63,'location-hours','update',null);
insert into permissions values(64,'location-hours','delete',null);
insert into permissions values(65,'location-hours','*',null);

insert into permissions values(66,'user-organization-roles','link', null);
insert into permissions values(67,'user-organization-roles','unlink', null);

insert into permissions values(68,'user-provider-roles','link',null);
insert into permissions values(69,'user-provider-roles','unlink',null);

insert into permissions values(70,'provider-addresses','link',null);
insert into permissions values(71,'provider-addresses','unlink',null);

insert into permissions values(72,'provider-location-addresses','link',null);
insert into permissions values(73,'provider-location-addresses','unlink',null);

insert into permissions values(74,'organization-addresses','link',null);
insert into permissions values(75,'organization-addresses','unlink',null);

insert into permissions values(76,'role-permissions','link',null);
insert into permissions values(77,'role-permissions','unlink',null);

insert into permissions values(78,'orders','provision',null);



insert into role_permissions values(1,1);
insert into role_permissions values(1,2);
insert into role_permissions values(1,3);
insert into role_permissions values(1,4);
insert into role_permissions values(1,5);
insert into role_permissions values(1,6);
insert into role_permissions values(1,7);
insert into role_permissions values(1,8);
insert into role_permissions values(1,9);
insert into role_permissions values(1,10);
insert into role_permissions values(1,11);
insert into role_permissions values(1,12);
insert into role_permissions values(1,13);
insert into role_permissions values(1,14);
insert into role_permissions values(1,15);
insert into role_permissions values(1,16);
insert into role_permissions values(1,17);
insert into role_permissions values(1,18);
insert into role_permissions values(1,19);
insert into role_permissions values(1,20);
insert into role_permissions values(1,21);
insert into role_permissions values(1,22);
insert into role_permissions values(1,23);
insert into role_permissions values(1,24);
insert into role_permissions values(1,25);
insert into role_permissions values(1,26);
insert into role_permissions values(1,27);
insert into role_permissions values(1,28);
insert into role_permissions values(1,29);
insert into role_permissions values(1,30);
insert into role_permissions values(1,31);
insert into role_permissions values(1,32);
insert into role_permissions values(1,33);
insert into role_permissions values(1,34);
insert into role_permissions values(1,35);
insert into role_permissions values(1,36);
insert into role_permissions values(1,37);
insert into role_permissions values(1,38);
insert into role_permissions values(1,39);
insert into role_permissions values(1,40);
insert into role_permissions values(1,41);
insert into role_permissions values(1,42);
insert into role_permissions values(1,43);
insert into role_permissions values(1,44);
insert into role_permissions values(1,45);
insert into role_permissions values(1,46);
insert into role_permissions values(1,47);
insert into role_permissions values(1,48);
insert into role_permissions values(1,49);
insert into role_permissions values(1,50);
insert into role_permissions values(1,51);
insert into role_permissions values(1,52);
insert into role_permissions values(1,53);
insert into role_permissions values(1,54);
insert into role_permissions values(1,55);
insert into role_permissions values(1,56);
insert into role_permissions values(1,57);
insert into role_permissions values(1,58);
insert into role_permissions values(1,59);
insert into role_permissions values(1,60);
insert into role_permissions values(1,61);
insert into role_permissions values(1,62);
insert into role_permissions values(1,63);
insert into role_permissions values(1,64);
insert into role_permissions values(1,65);
insert into role_permissions values(1,66);
insert into role_permissions values(1,67);
insert into role_permissions values(1,68);
insert into role_permissions values(1,69);
insert into role_permissions values(1,70);
insert into role_permissions values(1,71);
insert into role_permissions values(1,72);
insert into role_permissions values(1,73);
insert into role_permissions values(1,74);
insert into role_permissions values(1,75);
insert into role_permissions values(1,76);
insert into role_permissions values(1,77);
insert into role_permissions values(1,78);

insert into secrets values(1,'VlN6NTh3OFg0YituY3l5M29ESk5kZz09');