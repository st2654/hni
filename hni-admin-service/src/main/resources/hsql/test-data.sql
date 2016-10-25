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