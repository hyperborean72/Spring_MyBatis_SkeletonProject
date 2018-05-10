CREATE TABLE IF NOT EXISTS Country (  
ID serial,
NAME varchar(255) not null,  
CAPITAL varchar(255) not null,
POPULATION int not null,
constraint PK_Country primary key (ID)
);