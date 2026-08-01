INSERT INTO users (
    id,
    created_at,
    updated_at,
    full_name,
    username,
    password,
    role,
    status
)
VALUES (
           gen_random_uuid(),
           NOW(),
           NOW(),
           'Ahror Orzimurodov',
           'admin',
           '$2a$10$/1/MiSD42G65uVupSoMfeuGsjcuNnYHV8yzo4XoMa5DySfhyNCUj6',
           'ADMIN',
           'ACTIVE'
       );