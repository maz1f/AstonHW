select next_val from product_sequence into @next_id;
insert into product (id, name, price)
    values (@next_id, 'Gaming lapton by Xiaomi', 129.2);
insert into laptop (model, os, id)
    values ('Xiaomi Air pro', 'WINDOWS', @next_id);
update product_sequence
    set next_val=next_val+1;

select next_val from product_sequence into @next_id;
insert into product (id, name, price)
    values (@next_id, 'Apple Windows First Laptop', 999.99);
insert into laptop (model, os, id)
    values ('Apple Windows 11 pro', 'WINDOWS', @next_id);
update product_sequence
    set next_val=next_val+1;

select next_val from product_sequence into @next_id;
insert into product (id, name, price)
    values (@next_id, 'Apple 2024 model', 1029.95);
insert into laptop (model, os, id)
    values ('Apple M24', 'IOS', @next_id);
update product_sequence
    set next_val=next_val+1;