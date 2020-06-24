create table Users
(
    ID     int generated always as identity,
    Phone  varchar(11) primary key not null,
    Gender varchar(2)              not null,
    State  smallint                not null
--     init state = 1, del = 0, rejoin = 2, change = 3
);

create table System
(
    Phone   varchar(11),
    Receive varchar(11),
    primary key (Phone, Receive),
    foreign key (Phone) references Users (Phone)
);
insert into Users(Phone, Gender, State)
values ('033345678', 'B', 1),
       ('034345700', 'B', 1),
       ('035345001', 'B', 1),
       ('036666190', 'B', 1),
       ('037778956', 'G', 1),
       ('037712628', 'G', 1),
       ('037796601', 'G', 1),
       ('037706234', 'G', 1)
;
-- get 3 numbers for user Frd B/G
insert into System
SELECT '037796601', Phone
from Users
where Phone not in (SELECT Receive
                    from System S
                    where Phone = '037796601')
  and Gender <> (Select Gender from Users where Phone = '037796601')
  and State <> 0
limit 3;

-- change gender
update Users
set Gender='G',
    State = 3
where Phone = '033345678';
-- del
update Users
set State = 0
where Phone = '033345678';
-- rejoin as Girl :  Frd RJ G
update Users
set State  = 2,
    Gender = 'G'
where Phone = '033345678';

-- change gender : Frd chg
CREATE TRIGGER check_update_gender
    AFTER UPDATE of State
    on Users
    FOR EACH ROW
    when (new.State = 3)
EXECUTE PROCEDURE changeGender();

CREATE OR REPLACE FUNCTION changeGender()
    RETURNS trigger AS
$$
declare
    G varchar;
BEGIN
    select Gender into G from Users where Phone = new.Phone;
    IF G = 'G' THEN
        UPDATE Users SET Gender ='B' where Phone = new.Phone;
        RETURN NEW;
    ELSE
        UPDATE Users SET Gender ='G' where Phone = new.Phone;
        RETURN NEW;
    END IF;
END;
$$
    LANGUAGE plpgsql;

update Users
set State = 3
where Phone = '033345678';

select *
from Users;


-- count girls
select count(*) as Girls
from Users
where Gender = 'G'
  and State <> 0;
-- count boys
select count(*) as Boys
from Users
where Gender = 'G'
  and State <> 0;
-- count del
select count(*) as Del
from Users
where State = 0;

Create table Graph
(
    Date  date primary key,
    Girls int,
    Boys  int,
    Del   int,
    Wrong int
);

INSERT INTO Graph
WITH t0 as (select current_date),
     t1 AS (
         select count(*) as Girls
         from Users
         where Gender = 'G'
           and State <> 0
     ),
     t2 AS (
         select count(*) as Boys
         from Users
         where Gender = 'G'
           and State <> 0
     ),
     t3 AS (
         select count(*) as Del
         from Users
         where State = 0
     )
select t0.*, t1.Girls, t2.Boys, t3.Del
from t0,
     t1,
     t2,
     t3;
