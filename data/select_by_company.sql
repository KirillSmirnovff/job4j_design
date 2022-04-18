select p.name, c.name
from person as p
join company as c
on p.company_id = c.id
where c.id != 5;


select c.name, count(p)
from company as c
join person as p
on c.id = p.company_id
group by c.name
having count(p) = (select max(count_number)
                  	from (
                        select company_id, count(*) as count_number
						from person
                        group by company_id)
				   as x)