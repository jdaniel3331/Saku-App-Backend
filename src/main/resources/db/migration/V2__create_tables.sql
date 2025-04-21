-- Tabla de información de usuarios
CREATE TABLE authentication.users_info (
     user_id BIGSERIAL PRIMARY KEY,
     first_name VARCHAR(255) NOT NULL,
     second_name VARCHAR(255),
     first_surname VARCHAR(255) NOT NULL,
     second_surname VARCHAR(255),
     date_of_birth DATE NOT NULL,
     is_active BOOLEAN DEFAULT true,
     created_at DATE DEFAULT CURRENT_DATE
);

-- Tabla de credenciales de usuario
CREATE TABLE authentication.user_credentials (
     credential_id BIGSERIAL PRIMARY KEY,
     user_id BIGINT NOT NULL,
     email VARCHAR(255) NOT NULL UNIQUE,
     password VARCHAR(255) NOT NULL,
     was_verified BOOLEAN DEFAULT false,
     last_login TIMESTAMP,
     FOREIGN KEY (user_id)
         REFERENCES authentication.users_info(user_id)
         ON DELETE CASCADE
);

-- Tabla de tokens de acceso
CREATE TABLE authentication.access_tokens (
    access_token_id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    access_token TEXT NOT NULL UNIQUE,
    is_expired BOOLEAN DEFAULT false,
    is_revoked BOOLEAN DEFAULT false,
    FOREIGN KEY (user_id)
        REFERENCES authentication.users_info(user_id)
        ON DELETE CASCADE
);

-- Tabla de tokens de verificación
CREATE TABLE authentication.verification_tokens (
    verification_token_id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    verification_token TEXT NOT NULL UNIQUE,
    expiration_time TIMESTAMP NOT NULL,
    was_used BOOLEAN DEFAULT false,
    FOREIGN KEY (user_id)
        REFERENCES authentication.users_info(user_id)
        ON DELETE CASCADE
);

-- Tabla de categorías de tareas
CREATE TABLE task.categories (
    category_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    user_id BIGINT,
    FOREIGN KEY (user_id)
        REFERENCES authentication.users_info(user_id)
        ON DELETE CASCADE
);

-- Tabla de niveles de prioridad
CREATE TABLE task.priority_levels (
    priority_level_id SMALLSERIAL PRIMARY KEY,
    level VARCHAR(255) NOT NULL
);

-- Tabla de estados de tareas
CREATE TABLE task.task_states (
    task_id SMALLSERIAL PRIMARY KEY,
    state VARCHAR(255) NOT NULL
);

-- Tabla de tareas
CREATE TABLE task.tasks (
    task_id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    created_at DATE NOT NULL,
    due_date DATE,
    category BIGSERIAL,
    task_state SMALLINT NOT NULL,
    priority_level SMALLINT NOT NULL,
    FOREIGN KEY (category) REFERENCES task.categories(category_id) ON DELETE RESTRICT,
    FOREIGN KEY (task_state) REFERENCES task.task_states(task_id) ON DELETE CASCADE,
    FOREIGN KEY (priority_level) REFERENCES task.priority_levels(priority_level_id) ON DELETE CASCADE
);
