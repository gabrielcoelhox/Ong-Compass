INSERT INTO USUARIO(CPF, EMAIL, IDADE, NOME, SENHA, TELEFONE, ADMIN) VALUES ('111.222.333-44', 'lazarofonseca@compass.com', 36, 'Lázaro Fonseca', '$2a$10$NAEClbeLfjW3qigHUfA5nOCcg8SYFk5kTju1a88.vDpS4Olw34Apm', '83988776655', 'true'); -- senha: 12345
INSERT INTO USUARIO(CPF, EMAIL, IDADE, NOME, SENHA, TELEFONE, ADMIN) VALUES ('222.333.444-55', 'gabrielcoelho@compass.com', 22, 'Gabriel Coelho', '$2a$10$z6RCrPEUdJKok4wWQ.MhGeEGftpnzXIS5wk/HuiE.yTZ.Hk08ZWJq', '83999887722', 'true'); -- senha: 123456
INSERT INTO USUARIO(CPF, EMAIL, IDADE, NOME, SENHA, TELEFONE, ADMIN) VALUES ('333.444.555-66', 'dayanaferreira@compass.com', 22, 'Dayana Ferreira', '$2a$10$nDmjJNiMN8AsELmnqeH4aOKkePhUTWMqX85FWuvYUqwv/7ZvJF5s.', '71996585780', 'TRUE'); -- senha: teste123
INSERT INTO USUARIO(CPF, EMAIL, IDADE, NOME, SENHA, TELEFONE, ADMIN) VALUES ('444.555.666-77', 'juniormororo@compass.com', 28, 'Júnior Mororó', '$2a$10$ZnWdt6ssll618hT1nskxuuxLHQOFVQ3DB651aGkXDcMn2uUO5XFGO', '71994578949', 'TRUE'); -- senha: oi123
INSERT INTO USUARIO(CPF, EMAIL, IDADE, NOME, SENHA, TELEFONE, ADMIN) VALUES ('555.555.777-88', 'teste@compass.com', 50, 'Teste User', '$2a$10$NHwSOPqFN2Q.Zidl4cCiX.UrgdxZfBxgRDCwWJmUsz194hLD3y2O2', '71994578949', 'FALSE'); -- senha: 123


INSERT INTO ENDERECO(logradouro, cep, numero, complemento, bairro, cidade, estado) values ('Projetada', '61567-375', '4171', 'Sala 10', 'Planalto', 'Camaragibe', 'PE');
INSERT INTO ENDERECO(logradouro, cep, numero, complemento, bairro, cidade, estado) values ('R. Escrivão Sebastião de Azevedo Bastos', '58038-491', '889', 'Apt 301', 'Manaíra', 'João Pessoa', 'PB');
INSERT INTO ENDERECO(logradouro, cep, numero, complemento, bairro, cidade, estado) values ('R. João Matias da Costa', '58059-696', '67', 'Casa', 'Mangabeiro 8', 'João Pessoa', 'PB');
INSERT INTO ENDERECO(logradouro, cep, numero, complemento, bairro, cidade, estado) values ('R. Belém de São Francisco', '50741-460', '915', 'Apt 202', 'Várzea', 'Recife', 'PE');


INSERT INTO ANIMAL(sexo, porte, idade, raca, especie) VALUES ('M', 'P', '3', 'Poodle', 'cachorro');
INSERT INTO ANIMAL(sexo, porte, idade, raca, especie) VALUES ('F', 'G', '8', 'Chihuahua', 'cachorro');
INSERT INTO ANIMAL(sexo, porte, idade, raca, especie) VALUES ('M', 'P', '5', 'Pug', 'cachorro');
INSERT INTO ANIMAL(sexo, porte, idade, raca, especie) VALUES ('M', 'P', '2', 'Siamês', 'gato');


INSERT INTO RESGATE(endereco, caracteristicas_animal, descricao, usuario, status) VALUES ('R. Oscar Carneiro, Tamarineira, Recife - PE', 'Cachorro da cor marrom', 'Estava perto da Drogaria Recife', 'João', 'REALIZADO');
INSERT INTO RESGATE(endereco, caracteristicas_animal, descricao, usuario, status) VALUES ('R. Bananeiras, Manaíra, João Pessoa - PB', 'Cachorro da cor branco com mel ', 'Próximo ao restaurante Kanpai. Tinha uma coleira da cor azul.', 'Miguel', 'REALIZADO');
INSERT INTO RESGATE(endereco, caracteristicas_animal, descricao, usuario, status) VALUES ('R. Barão de Grajaú, Ibura, Recife - PE', 'Gato da cor preto', 'Visto perto da casa de cor rosa, número 02', 'Ana', 'AGUARDANDO');

INSERT INTO ADOCAO(id_animal, id_usuario) VALUES (1,2);
