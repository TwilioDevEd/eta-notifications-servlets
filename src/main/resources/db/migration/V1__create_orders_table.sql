create table orders (
  id SERIAL UNIQUE not null PRIMARY KEY,
  customer_name varchar(100) not null,
  customer_phone_number varchar(15) not null,
  status varchar(15) not null default 'Ready',
  notification_status varchar(15) not null default 'None'
);
