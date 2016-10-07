create table users (
  username varchar(256),
  password varchar(256),
  enabled boolean
);

create table authorities (
  username varchar(256),
  authority varchar(256)
);


create table resources (
  resource_key varchar(256),
  resource_class varchar(256),
  resource_type_id int,
  resource_url varchar(256)
);

create table resource_types (
  resource_type_id int,
  resource_type_name varchar(256),
  resource_type_getter_class varchar(256)
);

create table resource_class (
  resource_class varchar(256),
  resource_content_type varchar(256)
);

create table resource_storage (
  resource_url varchar(256),
  resource_body blob
);


