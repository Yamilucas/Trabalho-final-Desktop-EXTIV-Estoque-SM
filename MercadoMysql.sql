-- Tabela Categoria (para categorias de produtos)
CREATE DATABASE IF NOT EXISTS MercadoMysql;
USE MercadoMysql;

CREATE TABLE Categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE Produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    quantidade INT DEFAULT 0,
    preco DECIMAL(10, 2) DEFAULT 0.00,
    categoria_id INT,
    FOREIGN KEY (categoria_id) REFERENCES Categoria(id) ON DELETE CASCADE
);

-- Inserir uma nova categoria
INSERT INTO Categoria (nome) VALUES ('Limpeza'), ('Alimentos');

-- Inserir um novo produto
INSERT INTO Produto (nome, quantidade, preco, categoria_id) 
VALUES ('Detergente', 50, 5.99, 1), ('Arroz', 100, 19.90, 2);

-- Consultar produtos por categoria
SELECT p.nome, p.quantidade, p.preco, c.nome AS categoria
FROM Produto p
INNER JOIN Categoria c ON p.categoria_id = c.id;

-- Tabela Venda (registro de vendas realizadas)
CREATE TABLE Venda (
    idVenda INT AUTO_INCREMENT PRIMARY KEY,         -- ID único para cada venda
    produto_id INT NOT NULL,                        -- ID do produto vendido (FK)
    clienteNome VARCHAR(255) NOT NULL,              -- Nome do cliente que realizou a compra
    quantidade INT NOT NULL,                        -- Quantidade vendida
    precoUnitario DECIMAL(10, 2) NOT NULL,          -- Preço unitário do produto no momento da venda
    precoTotal DECIMAL(10, 2) NOT NULL,             -- Preço total da venda (quantidade * precoUnitario)
    dataVenda TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Data e hora da venda
    FOREIGN KEY (produto_id) REFERENCES Produto(id) ON DELETE CASCADE
);

-- Inserção de exemplo para registrar uma venda
-- Venda de 2 unidades de "Arroz" para o cliente "João Silva"
INSERT INTO Venda (produto_id, clienteNome, quantidade, precoUnitario, precoTotal) 
VALUES (2, 'João Silva', 2, 19.90, 39.80);

-- Consulta para listar todas as vendas realizadas
SELECT 
    v.idVenda,
    v.clienteNome,
    p.nome AS nomeProduto,
    c.nome AS categoriaProduto,
    v.quantidade,
    v.precoUnitario,
    v.precoTotal,
    v.dataVenda
FROM Venda v
INNER JOIN Produto p ON v.produto_id = p.id
INNER JOIN Categoria c ON p.categoria_id = c.id;

-- Consulta para somar o total de vendas por produto
SELECT 
    p.nome AS nomeProduto,
    SUM(v.quantidade) AS totalVendido,
    SUM(v.precoTotal) AS totalFaturado
FROM Venda v
INNER JOIN Produto p ON v.produto_id = p.id
GROUP BY p.id;

-- Tabela de usuários (login)
CREATE TABLE Login (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

-- Inserção de Usuários
INSERT INTO Login (usuario, senha) VALUES ('adm', '1234');

-- T
