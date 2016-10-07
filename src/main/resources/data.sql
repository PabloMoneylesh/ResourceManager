insert into users (username, password, enabled) values ('user', 'user', true);

insert into authorities (username, authority) values ('user', 'ROLE_ADMIN');

insert into resource_types (resource_type_id, resource_type_name, resource_type_getter_class) values (1, 'file system resource', 'FileResource');
insert into resource_types (resource_type_id, resource_type_name, resource_type_getter_class) values (2, 'DB resource', 'DBResource');

insert into resource_class (resource_class, resource_content_type) values ('ARTWORK', 'application/pdf');
insert into resource_class (resource_class, resource_content_type) values ('LOGO', 'image/jpeg');


insert into resources (resource_key, resource_class, resource_type_id, resource_url)
values ('PN0001', 'ARTWORK', 1, 'PN0001.PDF');

insert into resources (resource_key, resource_class, resource_type_id, resource_url)
values ('PN0002', 'ARTWORK', 1, 'PN0002.PDF');

insert into resources (resource_key, resource_class, resource_type_id, resource_url)
values ('PN0001', 'LOGO', 2, 'PN0001.jpg');

insert into resources (resource_key, resource_class, resource_type_id, resource_url)
values ('PN0002', 'LOGO', 2, 'PN0002.jpg');

INSERT INTO resource_storage(resource_url, resource_body)
VALUES('PN0001.jpg', FILE_READ('PN0001.jpg'));

INSERT INTO resource_storage(resource_url, resource_body)
VALUES('PN0002.jpg', FILE_READ('PN0002.jpg'));


