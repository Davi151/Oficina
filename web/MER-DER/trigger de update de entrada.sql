/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Davi
 * Created: 16/06/2019
 */

create or replace function update_valor_total()
	returns trigger as $$
declare
	novo_valor decimal;
	preco decimal;
	unidade integer;
   
begin
	select p.p_preco into preco 
	from peca as p
	where p.p_id = new.p_id;
	
	unidade := (old.P_UNIDADE+new.P_UNIDADE);
	
	if (unidade < 0) then
		unidade := 0;
	end if;
	
	novo_valor := preco * unidade;
	update peca set P_VALOR_TOTAL=novo_valor, P_UNIDADE=(old.P_UNIDADE + new.P_UNIDADE) where P_ID = new.P_ID;
	return new;
end;

$$ language plpgsql;

create trigger trigger_update_valor_total
after update on Peca
for each row
WHEN (pg_trigger_depth() = 0) 
execute procedure update_valor_total();
