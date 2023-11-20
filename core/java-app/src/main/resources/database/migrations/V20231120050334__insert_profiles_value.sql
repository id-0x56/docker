INSERT INTO profiles (user_id, first_name, last_name) VALUES
    (1, 'Howard', 'Green'),
    (2, 'Kathleen', 'Adams'),
    (3, 'Marie', 'Baker'),
    (4, 'Louise', 'Turner'),
    (5, 'Sharon', 'Morris'),
    (6, 'Danny', 'Ramos'),
    (7, 'Roxie', 'Brooks')
ON CONFLICT (user_id) DO NOTHING;

-- INSERT IGNORE profiles (user_id, first_name, last_name) VALUES
--     (1, 'Howard', 'Green'),
--     (2, 'Kathleen', 'Adams'),
--     (3, 'Marie', 'Baker'),
--     (4, 'Louise', 'Turner'),
--     (5, 'Sharon', 'Morris'),
--     (6, 'Danny', 'Ramos'),
--     (7, 'Roxie', 'Brooks');
