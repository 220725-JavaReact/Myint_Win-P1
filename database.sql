drop table customer;
create table if not exists customer(
	email varchar primary key,
	name varchar not null,
	address varchar
);

drop table store_front;
create table if not exists store_front(
	name varchar primary key,
	address varchar
);

insert into store_front values('XYZ Fifth Avenue Store','New York');
insert into store_front values('XYZ Hudson Yardse','New York');
insert into store_front values('XYZ Soho Store','New York');
insert into store_front values('XYZ Bay Plaza','Bronx');
insert into store_front values('XYZ Skyview Center','Queens');

select name,address from store_front where LOWER(address) like LOWER('%new%') ;


create table if not exists product(
	name varchar primary key,
	price float not null
);

insert into product values('Activeware',100);
insert into product values('Leather Jacket',299);
insert into product values('Dress Pants',89);
insert into product values('Blazer',149);
insert into product values('Graphic Tees',19);
insert into product values('Slippers',99);
insert into product values('Socks',4);

create table if not exists line_item(
	product_name varchar references product(name) ON DELETE CASCADE,
	store_name varchar references store_front(name) ON DELETE CASCADE,
	quantity float not null,
	PRIMARY KEY(product_name, store_name)
);

insert into line_item values('Activeware','XYZ Fifth Avenue Store',10);
insert into line_item values('Leather Jacket','XYZ Fifth Avenue Store',10);
insert into line_item values('Slippers','XYZ Fifth Avenue Store',10);

insert into line_item values('Activeware','XYZ Hudson Yardse',10);
insert into line_item values('Leather Jacket','XYZ Hudson Yardse',10);

insert into line_item values('Graphic Tees','XYZ Soho Store',10);

insert into line_item values('Slippers','XYZ Bay Plaza',10);
insert into line_item values('Activeware','XYZ Bay Plaza',10);
insert into line_item values('Dress Pants','XYZ Bay Plaza',10);
insert into line_item values('Socks','XYZ Bay Plaza',10);

insert into line_item values('Graphic Tees','XYZ Skyview Center',10);
insert into line_item values('Leather Jacket','XYZ Skyview Center',10);
insert into line_item values('Dress Pants','XYZ Skyview Center',10);
insert into line_item values('Activeware','XYZ Skyview Center',10);

select * from store_front sf 
join line_item li on sf.name = li.store_name
join product p on li.product_name = p.name
where sf.name = 'XYZ Fifth Avenue Store'

create table if not exists customer_order(
	order_id SERIAL primary key,
	customer_email varchar references customer(email) ON DELETE CASCADE,
	product_name varchar references product(name) ON DELETE CASCADE,
	store_name varchar references store_front(name) ON DELETE CASCADE,
	total_price float not null
);

insert into customer_order(customer_email,product_name,product_name,store_name,toatl_price)

CREATE or replace FUNCTION order_product (c_email varchar,p_name varchar,s_name varchar,qua int, t_price float)   
RETURNS void
as 
$$    
BEGIN    
	update line_item set quantity = quantity - qua where product_name = p_name and store_name = s_name;
	insert into customer_order(customer_email,product_name,store_name,total_price) values(c_email,p_name,s_name,t_price);
END;   
 $$ language 'plpgsql';



select order_product('win@mail.com','Activeware','XYZ Fifth Avenue Store','2');

select * from customer_order co join product p on co.product_name = p.name

select * from line_item li 

select * from customer c 
delete  from customer c2 where c2."name" ='Win Thurein Myint'

select co.order_id,co.product_name,co.store_name,co.total_price  from customer_order co where co.customer_email = 'win@mail.com'

select * from store_front sf 

select * from customer_order co join product p on co.product_name = p.name where co.store_name = 'XYZ Fifth Avenue Store'

CREATE or replace FUNCTION store_inventory_replenish (p_name varchar,s_name varchar,qua int)   
RETURNS void
as 
$$    
DECLARE 
	store varchar;
BEGIN    
	select store_name from line_item where product_name = p_name and store_name = s_name into store;
	if store is not null then
		update line_item set quantity = quantity + qua where product_name = p_name and store_name = s_name;
	else
		insert into line_item values(p_name,s_name,qua);
	end if;
END;   
 $$ language 'plpgsql';

select li.product_name,p.price,li.store_name,li.quantity  from line_item li join product p on li.product_name = p.name where li.store_name = 'XYZ Fifth Avenue Store'
select * from customer_order co 

select store_inventory_replenish('Dress Pants','XYZ Fifth Avenue Store',200);

--CREATE or replace FUNCTION order_product (c_email varchar,p_name varchar,s_name varchar,qua int)   
--RETURNS void
--as 
--$$    
--BEGIN    
--	update line_item set quantity = quantity - qua where product_name = p_name and store_name = s_name;
--	insert into customer_order(customer_email,product_name,store_name,total_price) values(c_email,p_name,s_name,qua*(select p.price from product as p where p."name" =p_name ));
--END;   
-- $$ language 'plpgsql';

CREATE or replace FUNCTION order_product (c_email varchar,p_name varchar,s_name varchar,qua int)   
RETURNS int
as 
$$    
declare
	avaliable int;
	status int;
BEGIN    
	select quantity from line_item where product_name = p_name and store_name = s_name into avaliable;
	if avaliable>qua then
		update line_item set quantity = quantity - qua where product_name = p_name and store_name = s_name;
		insert into customer_order(customer_email,product_name,store_name,total_price) values(c_email,p_name,s_name,qua*(select p.price from product as p where p."name" =p_name )) returning order_id into status;
	end if;
	returning status;
END;   
 $$ language 'plpgsql';

