DROP TABLE IF EXISTS PRODUCTO;
CREATE TABLE PRODUCTO ( ID_PRODUCTO bigint PRIMARY KEY AUTO_INCREMENT, stock INT NOT NULL, nombre VARCHAR(150) NOT NULL,
marca VARCHAR(150) , detalle VARCHAR(350), precio_Unitario INT NOT NULL,  PRIMARY KEY (id_Producto));

