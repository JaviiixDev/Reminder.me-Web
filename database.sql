DROP DATABASE IF EXISTS notas_bd;
CREATE DATABASE notas_bd CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
USE notas_bd;

CREATE TABLE `usuario` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `user_password` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
ALTER TABLE usuario ADD UNIQUE (user_email);

CREATE TABLE `categoria` (
  `cat_id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_nombre` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `cat_description` varchar(200) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`cat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
ALTER TABLE categoria CHANGE cat_nombre cat_name VARCHAR(100) NOT NULL;

CREATE TABLE `nota` (
  `nota_id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `nota_title` varchar(200) COLLATE utf8mb4_bin NOT NULL,
  `nota_content` varchar(500) COLLATE utf8mb4_bin NOT NULL,
  `nota_create_date` DATE NOT NULL,
  `nota_last_date` DATE NOT NULL,
  PRIMARY KEY (`nota_id`),
  FOREIGN KEY (user_id) references usuario(user_id),
  FOREIGN KEY (cat_id) references categoria(cat_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
ALTER TABLE nota
MODIFY nota_content TEXT COLLATE utf8mb4_bin NOT NULL;

CREATE USER 'admin_notas'@'%' IDENTIFIED BY 'campusjalpa';
GRANT ALL PRIVILEGES ON notas_bd.* TO 'admin_notas'@'%';
FLUSH PRIVILEGES;

insert into categoria (cat_name, cat_description) values('Personal', 'Muestra notas personales como, rutinas de gym, lista de compras, etc.');
insert into categoria (cat_name, cat_description) values('Trabajo', 'Muestra notas de trabajo como, fechas de entrega, etc.');
insert into categoria (cat_name, cat_description) values('Escuela', 'Muestra notas de escuela como, tareas, etc.');
insert into categoria (cat_name, cat_description) values('Viajes', 'Muestra notas de viajes como, rutas a visitar, etc.');

insert into usuario (user_email, user_password) values('javierquesadillachilaquil@gmail.com','campusjalpa');
update usuario set user_password = '$2a$12$mPwwFZ7MaHRUmWntuXuF7ehGTRJaWqbZ1kdrIoew7VUe3ATjFE852' where user_id = 1; 
insert into usuario (user_email, user_password) values('campusjalpa','$2a$12$1vZmhZNMroNAY8XNUf65UevYWx4RnkvcHVDCPp3Rra3fHudxPkJaS');

insert into nota (cat_id, user_id, nota_title, nota_content, nota_create_date, nota_last_date) values
(2,1,'Tareas','Hacer tarea de redes y resumen, hacer tarea de bd y diseño de base de datos, empezar la app de android',
'2024-01-01','2024-01-01');

INSERT INTO nota (cat_id, user_id, nota_title, nota_content, nota_create_date, nota_last_date) VALUES
(3, 1, 'Ideas para proyecto', 'Pensar ideas para la app final, investigar APIs y tecnologías nuevas que pueda aplicar', '2024-10-05', '2024-10-06');

INSERT INTO nota (cat_id, user_id, nota_title, nota_content, nota_create_date, nota_last_date) VALUES
(4, 1, 'Recordatorio de reunión', 'Reunión con equipo de trabajo para revisar avances, preparar presentación para asesoría', '2025-04-10', '2025-04-11');

INSERT INTO nota (cat_id, user_id, nota_title, nota_content, nota_create_date, nota_last_date) VALUES
(2, 1, 'Cosas personales', 'Llevar la guitarra a reparar, organizar los libros y apuntes de este semestre', '2024-08-15', '2024-08-15');

INSERT INTO nota (cat_id, user_id, nota_title, nota_content, nota_create_date, nota_last_date) VALUES
(1, 1, 'Lista de compras', 'Comprar cable USB para ESP32, cuaderno nuevo, stickers y adaptador de red', '2025-03-01', '2025-03-02');

Select * from nota;
