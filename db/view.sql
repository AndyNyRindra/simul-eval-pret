create or replace view v_solde as
select
            row_number() over (order by client_id) as id,
            client_id,
            sum (amount * signe) as solde
from movement m
         join
     move_type mt on m.move_type_id = mt.id
group by client_id;