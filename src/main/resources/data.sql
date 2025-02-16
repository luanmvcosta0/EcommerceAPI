-- Inserir categorias
INSERT INTO categoria (nome, descricao) VALUES ('Eletrônicos', 'Produtos eletrônicos gerais');
INSERT INTO categoria (nome, descricao) VALUES ('Roupas', 'Vestimentas gerais');
INSERT INTO categoria (nome, descricao) VALUES ('Livros', 'Biblioteca de livros gerais');

-- Inserir produtos
INSERT INTO produto (nome, descricao, preco, quantidade_estoque, categoria_id) VALUES ('Smartphone', 'Iphone 13 Pro Max', 6140.99, 7, 1);
INSERT INTO produto (nome, descricao, preco, quantidade_estoque, categoria_id) VALUES ('Notebook', 'Notebook Lenovo Ideaped 5', 3500.00, 3,1);
INSERT INTO produto (nome, descricao, preco, quantidade_estoque, categoria_id) VALUES ('Camiseta', 'Camiseta NIKE', 50.00, 48,2);
INSERT INTO produto (nome, descricao, preco, quantidade_estoque, categoria_id) VALUES ('Calça Jeans', 'Calça Jeans Adidas', 120.00, 30, 2);
INSERT INTO produto (nome, descricao, preco, quantidade_estoque, categoria_id) VALUES ('Dom Casmurro', 'Livro Dom Casmurro', 30.00, 69,3);
INSERT INTO produto (nome, descricao, preco, quantidade_estoque, categoria_id) VALUES ('Harry Potter', 'Harry Potter e o Calice de Fogo', 40.00, 15,3);