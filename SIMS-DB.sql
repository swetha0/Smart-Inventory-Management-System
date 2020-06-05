--customer table 
CREATE TABLE customerData1(
name VARCHAR(50),
password VARCHAR(50),
email VARCHAR(50));

select * from customerData1;
drop table customerData1;

--Admin table 
CREATE TABLE Admin1(
username VARCHAR(20),
password VARCHAR(20));

insert into Admin1 values('Admin','Admin@123');  

select * from Admin1;

commit; --to save the values

--Suppliers Table 
create table suppliersProducts(
id NUMBER(20) PRIMARY KEY,
name VARCHAR(50),
productId NUMBER(20) unique,
productName VARCHAR(50),
category VARCHAR(50),
brand VARCHAR(50),
quantity NUMBER(10),
price NUMBER(10)
);

select * from suppliersProducts;

insert into suppliersProducts values(1,'Anjali guptha',001,'samsung j6','mobile','samsung',300,15000);
insert into suppliersProducts values(2,'prasad',002,'LG 5 star','refrigerator','LG',280,50000);
insert into suppliersProducts values(3,'varma',003,'Sony full Hd','TV','sony',200,90000);
insert into suppliersProducts values(4,'Baradwaj',004,'Haier 3star','AC','Haier',300,40000);
insert into suppliersProducts values(5,'Reddy',005,'Lenovo desktop i3','Laptop','Lenovo',280,32000);
insert into suppliersProducts values(6,'Ching chong',006,'Real me 3 pro','Mobile','Real me',400,19000);
insert into suppliersProducts values(7,'Mahendra',007,'Washing Machine 12.0','Washing Machine','ButterFly',400,19000);
insert into suppliersProducts values(8,'Narendra',008,'water cooler','Cooler','Usha',300,7000);
insert into suppliersProducts values(9,'Goutham Reddy',009,'Bajaj Platini PX97','Cooler','Bajaj',550,6000);
insert into suppliersProducts values(10,'Swetha',010,'Usha a15','Cooler','Bajaj',500,8000);
insert into suppliersProducts values(11,'Avan Kumar',011,'Vivo A50','mobile','Vivo',500,20000);
insert into suppliersProducts values(12,'Jaishwanth',012,'Dell Desktop i5','Laptop','Dell',300,50000);
insert into suppliersProducts values(13,'Surender Reddy',013,'Sony headphone','Headphone','sony',400,9000);
insert into suppliersProducts values(14,'Pushpalatha',014,'Haier 3star','AC','Haier',800,30000);
insert into suppliersProducts values(15,'Dinesh',015,'Hp desktop i3','Laptop','HP',200,40000);

Commit;

drop table suppliersProducts;

select * from suppliersProducts where id=1 and quantity>40;

select productsinstore.productid from productsinstore,suppliersProducts where productsinstore.productid =suppliersProducts.productid and suppliersProducts.id=1;

--products in store table
create table productsinstore(
productId number(10) primary key,
productName VARCHAR(50),
category VARCHAR(50),
brand VARCHAR(50),
quantity number(10),
price number(10));

select * from productsinstore;


--customer purchase table
create table customerpurchases(
id NUMBER(10) ,
productId number(10),
productName VARCHAR(50),
category VARCHAR(50),
brand VARCHAR(50),
quantity number(10),
price number(10)
);

select * from customerpurchases;

select customerpurchases.id,customerpurchases.productname,customerpurchases.category,customerpurchases.quantity,customerpurchases.price,customerdata.name from customerpurchases,customerdata where customerpurchases.id=customerdata.id order by customerdata.name;

--customerdata table
CREATE TABLE customerData(
id NUMBER(10) PRIMARY KEY,
name VARCHAR(50),
password VARCHAR(50),
email VARCHAR(50));
  
select * from customerData;
CREATE SEQUENCE customerId_sequence;

CREATE OR REPLACE TRIGGER customerDataInsert
  BEFORE INSERT ON customerData
  FOR EACH ROW
BEGIN
  SELECT customerId_sequence.nextval
  INTO :new.id
  FROM dual;
END;

select * from customerdata where email = swert@gmail.com;

select customerpurchases.productname,customerpurchases.category,customerpurchases.quantity,customerpurchases.price,customerdata.name from customerpurchases,customerdata where customerpurchases.id=customerdata.id and customerdata.email=swert@gmail.com;

CREATE SEQUENCE supplier_sequence;

CREATE OR REPLACE TRIGGER supplierProdDataInsert
  BEFORE INSERT ON suppliersProducts
  FOR EACH ROW
BEGIN
  SELECT supplier_sequence.nextval
  INTO :new.id
  FROM dual;
END;




