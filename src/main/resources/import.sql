insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into restaurante (id, nome, taxa_frete, cozinha_id,data_cadastro,data_atualizacao,ativo) values (1, 'Thai Gourmet', 10, 1,utc_timestamp,utc_timestamp,true);
insert into restaurante (id, nome, taxa_frete, cozinha_id,data_cadastro,data_atualizacao,ativo) values (2, 'Thai Delivery', 9.50, 1,utc_timestamp,utc_timestamp,true);
insert into restaurante (id, nome, taxa_frete, cozinha_id,data_cadastro,data_atualizacao,ativo) values (3, 'Tuk Tuk Comida Indiana', 15, 2,utc_timestamp,utc_timestamp,true);

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1),(1,2),(1,3),(2,3),(3,2),(3,3);



insert into usuario (id, nome, email, senha, data_cadastro) values (1, 'João da Silva', 'joao.ger@algafood.com', '123', utc_timestamp),(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', utc_timestamp),(3, 'José Souza', 'jose.aux@algafood.com', '123', utc_timestamp),(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', utc_timestamp),(5, 'Manoel Lima', 'manoel.loja@gmail.com', '123', utc_timestamp);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);




insert into pedido (id,codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep,endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,status, data_criacao, subtotal, taxa_frete, valor_total) values (1,uuid(), 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil','CRIADO', utc_timestamp, 298.90, 10, 308.90);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) values (1, 1, 1, 1, 78.9, 78.9, null);










