
-- DATABASE COM INSERÇÕES TESTES PARA O SISTEMA --

CREATE DATABASE `meupetdb`;

-- Table structure for table `animal`
CREATE TABLE `animal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) NOT NULL,
  `idade` int NOT NULL,
  `sexo` char(1) NOT NULL,
  `especie` varchar(50) NOT NULL,
  `raca` varchar(50) NOT NULL,
  `id_proprietario` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5mv5sbvkld1s6gbgx4tgt1pqk` (`id_proprietario`),
  CONSTRAINT `FK5mv5sbvkld1s6gbgx4tgt1pqk` FOREIGN KEY (`id_proprietario`) REFERENCES `proprietario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table `animal`
INSERT INTO `animal` VALUES 
(1,'Rex',5,'M','Cão','Labrador',3),
(2,'Mimi',3,'F','Gato','Siamês',2),
(3,'Bobby',2,'M','Cão','Beagle',3),
(4,'Nina',4,'F','Gato','Persa',2),
(5,'Luna',1,'F','Cão','Bulldog Francês',2),
(6,'werty',3,'f','erty','rty',2),
(7,'nina EDITADO',6,'f','cão','labrador',2),
(8,'Chico EDITADO',2,'M','Gato','Siames',3),
(10,'Valentina',1,'f','Gato','SRD',2);

-- Table structure for table `historico_pa`
CREATE TABLE `historico_pa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_animal` int NOT NULL,
  `peso` double NOT NULL,
  `altura` double NOT NULL,
  `data_cadastro` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_animal` (`id_animal`),
  CONSTRAINT `historico_pa_ibfk_1` FOREIGN KEY (`id_animal`) REFERENCES `animal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table `historico_pa`
INSERT INTO `historico_pa` VALUES 
(1,1,25.5,60,'2024-08-14'),
(2,2,4.3,30,'2024-08-14'),
(3,3,10.1,40,'2024-08-14'),
(4,4,3.9,25,'2024-08-14'),
(5,5,12,35,'2024-08-14');

-- Table structure for table `proprietario`
CREATE TABLE `proprietario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) NOT NULL,
  `sexo` char(1) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `email` varchar(150) NOT NULL,
  `celular` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cpf` (`cpf`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `celular` (`celular`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table `proprietario`
INSERT INTO `proprietario` VALUES 
(1,'Carlos Souza','M','123.456.789-01','carlos.souza@example.com','(11) 98765-4321'),
(2,'Ana Clara','F','234.567.890-12','ana.clara@example.com','(11) 91234-5678'),
(3,'Fernando Lima','M','345.678.901-23','fernando.lima@example.com','(11) 93456-7890'),
(4,'Juliana Mendes','F','456.789.012-34','juliana.mendes@example.com','(11) 92345-6789'),
(5,'Marcos Silva','M','567.890.123-45','marcos.silva@example.com','(11) 95678-9012');

-- Table structure for table `usuario`
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) NOT NULL,
  `sexo` varchar(1) NOT NULL,
  `email` varchar(150) NOT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `grupo` enum('ADMIN','PROPRIETARIO','SECRETARIO','VETERINARIO') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table `usuario`
INSERT INTO `usuario` VALUES 
(8,'Maya Dias','F','maya@gmail.com','$2a$10$SXTyq0jzO/.qDCCL5l4w/ebpc8/9pxFRQ9Ef6M.M6SabRG1FvXD4O','SECRETARIO'),
(9,'Melissa Dias','F','melissa@gmail.com','$2a$10$XjFV1qfrQzMMP1igvcXTY.zRQQcwtBOzMtKqglqqT2V1k5IXhmqZe','ADMIN'),
(10,'Samanta Silva','F','Samantas@gmail.com','$2a$10$XfCRd8q75AEwBfpobycmMeB2EHa6/puYWAXqdZ612Z.rk/H18lTl6','VETERINARIO'),
(11,'Samanta Silva','F','Samantaas@gmail.com','$2a$10$bepzCe7gYauygmx9hD1l9uvuL5fSVEOfG5aGaxQooZxFiCsjH6r0m','VETERINARIO'),
(12,'Samanta Silva','F','samanta2@gmail.com','$2a$10$YooOtIRMrmJVb3xcSbWARuHdtQ2sXEewL4JlGjbYpZj9hq0isbTOu','VETERINARIO'),
(13,'kaio','F','kkk@gmail.com','$2a$10$7/zLmkQS0.at80AnFoZY9unmOAFwFGwKpNwfu/zxL19HZxbfVN.Tm','SECRETARIO');

-- Table structure for table `vacina_animal`
CREATE TABLE `vacina_animal` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_animal` int NOT NULL,
  `id_vacina` int NOT NULL,
  `data_aplicacao` date NOT NULL,
  `data_cadastro` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_vacina` (`id_vacina`),
  KEY `id_animal` (`id_animal`),
  CONSTRAINT `vacina_animal_ibfk_1` FOREIGN KEY (`id_vacina`) REFERENCES `vacinas` (`id`),
  CONSTRAINT `vacina_animal_ibfk_2` FOREIGN KEY (`id_animal`) REFERENCES `animal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table `vacina_animal`
INSERT INTO `vacina_animal` VALUES 
(1,1,1,'2024-01-15','2024-08-14'),
(2,1,2,'2024-02-20','2024-08-14'),
(3,2,3,'2024-03-05','2024-08-14'),
(4,3,4,'2024-04-10','2024-08-14'),
(5,4,5,'2024-05-12','2024-08-14');

-- Table structure for table `vacinas`
CREATE TABLE `vacinas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) NOT NULL,
  `descricao` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table `vacinas`
INSERT INTO `vacinas` VALUES 
(1,'Vacina 1', 'Descrição 1'),
(2,'Vacina 2', 'Descrição 2'),
(3,'Vacina 3', 'Descrição 3'),
(4,'Vacina 4', 'Descrição 4'),
(5,'Vacina 5', 'Descrição 5');
