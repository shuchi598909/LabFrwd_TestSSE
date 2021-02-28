DROP SEQUENCE IF EXISTS ITEM_SEQ;
CREATE SEQUENCE ITEM_SEQ
  START WITH 1000
  INCREMENT BY 1;
  
DROP SEQUENCE IF EXISTS CAT_SEQ;
CREATE SEQUENCE CAT_SEQ
  START WITH 2000
  INCREMENT BY 1;

DROP SEQUENCE IF EXISTS EXPMNT_SEQ;
CREATE SEQUENCE EXPMNT_SEQ
  START WITH 3000
  INCREMENT BY 1;
  
CREATE TABLE CATEGORY 
(
	cat_id integer not null AUTO_INCREMENT,
	cat_name varchar(45) not null,
	primary key(cat_id)
);
  
CREATE TABLE ITEM 
(
	item_id integer not null AUTO_INCREMENT,
	name varchar(45) not null,
	quantity varchar(45) not null,
	cat_id integer not null,
	primary key(item_id),
	foreign key (cat_id) REFERENCES category(cat_id)
);

CREATE TABLE EXPERIMENT 
(
	expmnt_id integer not null AUTO_INCREMENT,
	expmnt_name varchar(45) not null,
	expmnt_phase varchar(45) not null,
	expmnt_description varchar(255) not null,
	item_id integer not null,
	primary key(expmnt_id),
	foreign key(item_id) REFERENCES Item(item_id)
);