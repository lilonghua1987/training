create table gstar_demo as select * from rdredb.rdre_gstar_demographic;
create table gstar_finan as select * from rdredb.rdre_gstar_financial;

inner join:
--------------
select count(*) from rdre_gstar_demographic a join rdre_gstar_financial b on a.dr_cm13=b.dr_cm13
select count(*) from rdre_gstar_demographic a inner join rdre_gstar_financial b on a.dr_cm13 = b.dr_cm13 limit 100

from rdredb.rdre_gstar_demographic a,rdredb.rdre_gstar_financial b
select *
where (a.dr_cm13 = b.dr_cm13)


outer join
---------------

select * from gstar_demo a full outer join gstar_finan b on (a.dr_cm13 = b.dr_cm13) where a.dr_cm13 is null Or b.dr_cm13 is null
