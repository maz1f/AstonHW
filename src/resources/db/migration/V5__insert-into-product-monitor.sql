select next_val from product_sequence into @next_id;
insert into product (id, name, price)
    values (@next_id, 'Apple monitor 2024', 590.99);
insert into monitor (ghz, manufacturer, id)
    values (144, 'Apple', @next_id);
update product_sequence
    set next_val=next_val+1;

select next_val from product_sequence into @next_id;
insert into product (id, name, price)
    values (@next_id, 'Google monitor 2024', 540);
insert into monitor (ghz, manufacturer, id)
    values (220, 'Google', @next_id);
update product_sequence
    set next_val=next_val+1;

select next_val from product_sequence into @next_id;
insert into product (id, name, price)
    values (@next_id, 'LG monitor 2020', 199.99);
insert into monitor (ghz, manufacturer, id)
    values (244, 'LG', @next_id);
update product_sequence
    set next_val=next_val+1;