create table record (
    id identity not null primary key,
    project_id varchar(50) not null,
    start_time timestamp,
    end_time timestamp,
    description varchar(500)
);