drop table account IF EXISTS;

create table account (
account_number bigint not null, 
account_type varchar(255), 
branch_address varchar(255), 
created_at date, 
customer_id bigint, 
primary key (account_number)
);
