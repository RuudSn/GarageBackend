
INSERT INTO users (username, password, email, enabled)

VALUES   ('user', '$2a$12$TAKl2cn3O0KyQQed8d00MeAVomp2uQvNCJ4K8lQNwn1LLxuaASQ5i','user@mail.nl', TRUE),
         ('admin', '$2a$12$TAKl2cn3O0KyQQed8d00MeAVomp2uQvNCJ4K8lQNwn1LLxuaASQ5i', 'admin@mail.nl', TRUE),
         ('ruud', '$2a$12$TAKl2cn3O0KyQQed8d00MeAVomp2uQvNCJ4K8lQNwn1LLxuaASQ5i', 'ruud@mail.nl', TRUE);



INSERT INTO authorities (username, authority)

VALUES  ('user', 'ROLE_USER'),
        ('ruud', 'ROLE_USER'),
        ('ruud', 'ROLE_ADMIN'),
        ('admin', 'ROLE_ADMIN');


INSERT INTO parts (id, description, price, quantity)

VALUES  (1, 'tire-12QE', 65.50, 23 ),
        (2, 'tire-24QA', 78.50, 18),
        (3, 'tire-36YE', 112.50,  15 ),
        (4, 'windshield-capri2q', 280.00, 4),
        (5, 'windshield-escortwq2', 245.50, 7),
        (6, 'fuel-pump-R3', 398.85,  4),
        (7, 'fuel-pump-q6', 478.85,  4),
        (8, 'clutch-12Lt', 890.00,  2),
        (9, 'clutch-16Lt', 1012.00,  2),
        (12, 'battery-M45', 98.90,  5),
        (13, 'battery-K60', 1025.70, 12),
        (14, 'injector-kl123 R', 306.00, 12),
        (15, 'oil-filter-4rt', 100.50, 16),
        (16, 'brake-pads-G12', 98.90,  12),
        (17, 'brake-disc Var23', 300.45, 7 ),
        (18, 'head-gasket-8Ge', 798.50, 4),
        (19, 'air-filter-WER2', 76.50, 18),
        (20, 'injector vilt65', 323.00,  3),
        (21, 'injector flip12', 299.98, 5);

 INSERT INTO operations (id, description, price)

 VALUES (1, 'keuring-standaard', 45.00 ),
        (2, 'overige werkzmheden/1hr: afronden op 10min', 72.00 ),
        (3, 'replace waterpump', 250.00 );
