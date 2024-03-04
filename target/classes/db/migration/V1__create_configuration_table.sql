create table medicos(

    id bigint not null auto_increment,
    input_type varchar(10) not null,
    command varchar(100) not null,

    primary key(id)

);