-- Criação da tabela categoria
CREATE TABLE categoria (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- ID gerado automaticamente
    nome VARCHAR(255) NOT NULL,            -- Nome da categoria (não pode ser nulo)
    descricao VARCHAR(255)                 -- Descrição da categoria (pode ser nulo)
);

-- Criação da tabela produto
CREATE TABLE produto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- ID gerado automaticamente
    nome VARCHAR(255) NOT NULL,            -- Nome do produto (não pode ser nulo)
    descricao VARCHAR(255),                -- Descrição do produto (pode ser nulo)
    preco DOUBLE NOT NULL,                 -- Preço do produto (não pode ser nulo)
    quantidade_estoque INT NOT NULL,       -- Quantidade em estoque (não pode ser nulo)
    categoria_id BIGINT NOT NULL,          -- Chave estrangeira para a tabela categoria
    FOREIGN KEY (categoria_id) REFERENCES categoria(id)  -- Relacionamento com a tabela categoria
);