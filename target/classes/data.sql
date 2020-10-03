insert into produto
values(10001, 'Mercearia','87654321-B', 'Fonte de calcio', 'Parmalat', 'Leite Integral', 4.5, 2);
insert into produto
values(10002, 'Perecíveis', '87654322-B', 'Bom para digestao', 'Tio Joao', 'Arroz Integral', 5.5, 2);
insert into produto
values(10003, 'Limpeza', '87654323-B', 'Tira manchas', 'OMO', 'Sabao em Po', 12, 2);
insert into produto
values(10004, 'limpeza', '87654324-C', 'Cuidado para nao manchar roupa colorida', 'Dragao', 'Agua Sanitaria',  3, 2);
insert into produto
values(10005, 'HIGIENE',  '87654325-C', 'Sorriso branco e dentes brilhantes', 'Colgate', 'Creme Dental', 2.5, 2);

insert into carrinho
values(1,0);


INSERT INTO lote
VALUES (1,'15/12/2023', 3, 10005);

UPDATE produto
SET situacao = 1
WHERE id = 10005;

INSERT INTO lote
VALUES (2,'15/12/2023', 5, 10003);

UPDATE produto
SET situacao = 1
WHERE id = 10003;