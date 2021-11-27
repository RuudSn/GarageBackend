
INSERT INTO users (username, password, email, enabled)

VALUES   ('user', '$2a$12$TAKl2cn3O0KyQQed8d00MeAVomp2uQvNCJ4K8lQNwn1LLxuaASQ5i','user@mail.nl', TRUE),
         ('admin', '$2a$12$TAKl2cn3O0KyQQed8d00MeAVomp2uQvNCJ4K8lQNwn1LLxuaASQ5i', 'admin@mail.nl', TRUE),
         ('ruud', '$2a$12$TAKl2cn3O0KyQQed8d00MeAVomp2uQvNCJ4K8lQNwn1LLxuaASQ5i', 'ruud@mail.nl', TRUE);



INSERT INTO authorities (username, authority)

VALUES  ('user', 'ROLE_USER'),
        ('ruud', 'ROLE_USER'),
        ('ruud', 'ROLE_ADMIN'),
        ('admin', 'ROLE_ADMIN');
