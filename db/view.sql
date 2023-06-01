create or replace view v_solde as
select
            row_number() over (order by client_id) as id,
            client_id,
            sum (amount * signe) as solde
from movement m
         join
     move_type mt on m.move_type_id = mt.id
group by client_id;


create or replace view v_total_rembourses as
select
    row_number() over () as id,
    extract(month from date) mois,
    extract (year from date) annee,
    sum (total) totalRembourses
from
    reimbursement
where status = 10
group by mois, annee
order by annee, mois;


create or replace view v_total_empruntes as
select
            row_number() over () as id,
            extract(month from l.date) mois,
            extract (year from l.date) annee,
            sum (amount) totalEmpruntes
from
    loan l
join
    loan_request lr on l.request_id = lr.id
where status = 20
group by mois,annee;

drop view if exists v_benefice;
create or replace view v_benefice as
select
    row_number() over () as id,
    coalesce(v_total_empruntes.mois, v_total_rembourses.mois) as mois,
    coalesce(v_total_empruntes.annee, v_total_rembourses.annee) as annee,
    coalesce(v_total_empruntes.totalEmpruntes, 0) as totalEmpruntes,
    coalesce(v_total_rembourses.totalRembourses, 0) as totalRembourses,
    coalesce(v_total_rembourses.totalRembourses,0) - coalesce(v_total_empruntes.totalEmpruntes, 0) as benefice
from
    v_total_empruntes
FULL JOIN
    v_total_rembourses on v_total_empruntes.mois = v_total_rembourses.mois and v_total_empruntes.annee = v_total_rembourses.annee
order by annee, mois;