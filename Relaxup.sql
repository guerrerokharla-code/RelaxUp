-- Insertar el usuario en la tabla users (seguridad)
INSERT INTO users (username, password, enabled) 
VALUES ('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', true);

-- Asignar el rol ADMIN
INSERT INTO roles (rol, user_id) 
VALUES ('ADMIN', (SELECT id FROM users WHERE username = 'admin'));

-- (Opcional) Crear su perfil en la tabla usuario
INSERT INTO usuario (nombre, email, direccion, celular, id_user) 
VALUES ('Admin Principal', 'admin@relaxup.com', 'Calle Central 1', 999999999, (SELECT id FROM users WHERE username = 'admin'));