/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Davi
 * Created: 18/06/2019
 */


/*primeira view*/

create view view_auditoria_pecas as 
select distinct a.a_id, a.p_id, a.a_descricao
from controla as c, auditoria a
where c.p_id = a.p_id and (not a.a_descricao like '%ENTRADA%') and (not a.a_descricao like '%SAIDA%');

/* segunda view da entrada*/
create view view_auditoria_pecas_entrada as 
select distinct a.a_id, a.p_id, a.a_descricao
from controla as c, auditoria a
where (a.a_descricao like '%ENTRADA%' and  c.p_id = a.p_id) and (not a.a_descricao like '%SAIDA%');


/* terceira view da saida pra garantir a lógica*/
create view view_auditoria_pecas_saida as 
select distinct a.a_id, a.p_id, a.a_descricao
from controla as c, auditoria a
where (a.a_descricao like '%SAIDA%' and  c.p_id = a.p_id) and (not a.a_descricao like '%ENTRADA%');

/* quarta view da entrada e saida juntas*/
create view view_auditoria_pecas_entrada_saida as 
select distinct a.a_id, a.p_id, a.a_descricao
from controla as c, auditoria a
where (c.p_id = a.p_id and a.a_descricao like '%SAIDA%' or a.a_descricao like '%ENTRADA%');

drop view view_auditoria_pecas_entrada_saida










