drop table if exists product cascade ;
create table if not exists product(
id bigserial primary key,
title varchar (255),
price int
);

drop table if exists customer cascade ;
create table if not exists customer(
    id bigserial primary key,
    name varchar (255)
);

drop table if exists cart;
create table if not exists cart
(
    id bigserial primary key,
    product_id bigint references product (id),
    customer_id bigint references customer (id),
    product_price int

);

insert into product (title, price)
values ('Телефон', 1500),
       ('Телевизор', 2500),
       ('Ноутбук', 3500),
       ('Монитор', 2000),
       ('Принтер', 1000);
insert into customer (name)
values ('Иван'),
       ('Николай'),
       ('Анна'),
       ('Андрей');

insert into cart (customer_id, product_id, product_price)
values (1, 1, 1500),
       (1, 4, 2000),
       (2, 3, 3500),
       (2, 4, 2000),
       (2, 2, 2500),
       (3, 1, 1500),
       (3, 3, 3500),
       (4, 1, 1500),
       (4, 2, 2500);

select * from product;
select name, title, product_price from cart
join customer c on c.id = cart.customer_id
join product p on p.id = cart.product_id