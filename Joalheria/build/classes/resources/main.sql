-- -----------------------------------------------------
-- Schema db_PI
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `db_PI` ;

-- -----------------------------------------------------
-- Schema db_PI
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_PI` DEFAULT CHARACTER SET utf8 ;
USE `db_PI` ;

-- -----------------------------------------------------
-- Table `Cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Cliente` ;

CREATE TABLE IF NOT EXISTS `Cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `sobrenome` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `rg` VARCHAR(45) NOT NULL,
  `cpf` VARCHAR(45) NOT NULL,
  `endereco` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(13) NOT NULL,
  `sexo` VARCHAR(1) NOT NULL,
  `dtNascimento` DATE NOT NULL,
  `enabled` TINYINT NOT NULL,
  PRIMARY KEY (`idCliente`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `rg_UNIQUE` ON `Cliente` (`rg` ASC);

CREATE UNIQUE INDEX `cpf_UNIQUE` ON `Cliente` (`cpf` ASC);


-- -----------------------------------------------------
-- Table `Funcionario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Funcionario` ;

CREATE TABLE IF NOT EXISTS `Funcionario` (
  `idFuncionario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `sobrenome` VARCHAR(100) NOT NULL,
  `senha` VARCHAR(100) NOT NULL,
  `tipo` VARCHAR(5) NOT NULL,
  `enabled` TINYINT NOT NULL,
  PRIMARY KEY (`idFuncionario`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `nomeFuncionario_UNIQUE` ON `Funcionario` (`nome` ASC);


-- -----------------------------------------------------
-- Table `Categoria_Produto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Categoria_Produto` ;

CREATE TABLE IF NOT EXISTS `Categoria_Produto` (
  `idCategoria` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `enabled` TINYINT NOT NULL,
  PRIMARY KEY (`idCategoria`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `nomeCategoria_UNIQUE` ON `Categoria_Produto` (`nome` ASC);


-- -----------------------------------------------------
-- Table `Produto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Produto` ;

CREATE TABLE IF NOT EXISTS `Produto` (
  `idProduto` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(200) NOT NULL,
  `marca` VARCHAR(100) NOT NULL,
  `quant` INT NOT NULL,
  `material` VARCHAR(100) NOT NULL,
  `precoUni` FLOAT NOT NULL,
  `img` BLOB NULL,
  `segmento` VARCHAR(1) NULL,
  `tamanho` VARCHAR(45) NULL,
  `codigo` INT NOT NULL,
  `enabled` TINYINT NOT NULL,
  `idCategoria` INT NOT NULL,
  PRIMARY KEY (`idProduto`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `codigo_UNIQUE` ON `Produto` (`codigo` ASC);


-- -----------------------------------------------------
-- Table `Venda`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Venda` ;

CREATE TABLE IF NOT EXISTS `Venda` (
  `idVenda` INT NOT NULL AUTO_INCREMENT,
  `dtVenda` DATE NOT NULL,
  `tipoPagamento` VARCHAR(45) NOT NULL,
  `desconto` FLOAT NULL,
  `parcelas` INT NULL,
  `totalVenda` FLOAT NOT NULL,
  `idCliente` INT NOT NULL,
  `idFuncionario` INT NOT NULL,
  PRIMARY KEY (`idVenda`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Item_venda`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Item_venda` ;

CREATE TABLE IF NOT EXISTS `Item_venda` (
  `idItemVenda` INT NOT NULL AUTO_INCREMENT,
  `quant` INT NOT NULL,
  `precoUni` FLOAT NOT NULL,
  `idVenda` INT NOT NULL,
  `idProduto` INT NOT NULL,
  PRIMARY KEY (`idItemVenda`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Cria um usuario do tipo 'user'
-- -----------------------------------------------------
INSERT INTO funcionario (nome, sobrenome, senha, tipo, enabled)
VALUES ('user', 'user', '123456', 'user', true);

-- -----------------------------------------------------
-- Cria um usuario do tipo 'admin'
-- -----------------------------------------------------
INSERT INTO funcionario (nome, sobrenome, senha, tipo, enabled)
VALUES ('admin', 'admin', '123456', 'admin', true);

-- -----------------------------------------------------
-- Tabela de dominio Categoria Produto
-- -----------------------------------------------------
INSERT INTO categoria_produto (nome, enabled)
VALUES('Anéis', true);

INSERT INTO categoria_produto (nome, enabled)
VALUES('Brincos', true);

INSERT INTO categoria_produto (nome, enabled)
VALUES('Correntes', true);

INSERT INTO categoria_produto (nome, enabled)
VALUES('Pingentes', true);

INSERT INTO categoria_produto (nome, enabled)
VALUES('Pulseiras', true);

INSERT INTO categoria_produto (nome, enabled)
VALUES('Relógios', true);
