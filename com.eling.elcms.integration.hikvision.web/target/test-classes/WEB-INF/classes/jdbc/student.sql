create table student(
id int,
name varchar(32),
age int,
phone varchar(32),
email varchar(32));

select * from t1
alter table t1 modify column content varchar(30);--varchar 一个汉字占一个长度

alter table t1 add content2 text(12);
alter table t1 modify column content2 text(100);

insert into t1(content) value('我我');

insert into t1(content2) value
('我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我');


select * from t1
truncate t1