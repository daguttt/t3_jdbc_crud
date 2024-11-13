# Sistema de Voluntariado
Desarrolla un sistema de gestión de voluntariado donde los usuarios puedan participar en proyectos como Voluntarios o Publicantes. Un Publicante crea proyectos, y un Voluntario puede inscribirse en los proyectos. El sistema debe incluir un menú interactivo para realizar las siguientes operaciones:

1. **Usuarios:**
- Crear un nuevo usuario con rol de Voluntario o Publicante. (registro)
- Iniciar sesión para que el usuario pueda acceder a las funcionalidades del sistema.
2. **Proyectos:**
- Crear un proyecto (solo los Publicantes).
- Listar todos los proyectos disponibles.
3. **Inscripciones:**
- Inscribirse en un proyecto (solo los Voluntarios).
- Listar las inscripciones de un Voluntario.
- Listar los voluntarios inscritos en un proyecto.

#SQL para Crear las Tablas
Este SQL crea las tablas users, projects e inscriptions con las relaciones necesarias para manejar usuarios, proyectos y las inscripciones.

```sql
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role ENUM('VOLUNTARIO', 'PUBLICANTE') NOT NULL
);

CREATE TABLE projects (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    start_date DATE,
    end_date DATE,
    created_by INT,
    FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE inscriptions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    project_id INT,
    date DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    UNIQUE (user_id, project_id) -- Evita inscripciones duplicadas
);

```
## Resultado Esperado
El sistema debe tener un menú interactivo que permita:

1. Para Publicantes:

- Crear un proyecto de voluntariado.
- Ver la lista de proyectos que han creado.
- Ver la lista de voluntarios inscritos en cada uno de sus proyectos.
2. Para Voluntarios:

- Ver la lista de proyectos disponibles.
- Inscribirse en un proyecto.
- Ver en qué proyectos está inscrito.


Cada usuario puede realizar estas operaciones en el menú interactivo, y el sistema debe manejar correctamente la relación entre usuarios y proyectos para gestionar inscripciones, listados y consultas de manera efectiva.

# Plazo Máximo
Fecha límite de entrega: 15 de Noviembre a las 12 de la noche.

## Entregables
- codigo en  `.zip`
- link github