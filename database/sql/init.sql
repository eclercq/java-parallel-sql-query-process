DROP TABLE IF EXISTS test_table;

-- Création de la table
CREATE TABLE test_table (
    id SERIAL PRIMARY KEY,
    col1 INT,
    col2 VARCHAR(50),
    col3 TEXT,
    col4 FLOAT,
    col5 DATE,
    col6 BOOLEAN,
    col7 NUMERIC,
    col8 TIMESTAMP,
    col9 VARCHAR(100),
    col10 INT,
    col11 VARCHAR(50),
    col12 TEXT,
    col13 FLOAT,
    col14 DATE,
    col15 BOOLEAN,
    col16 NUMERIC,
    col17 TIMESTAMP,
    col18 VARCHAR(100),
    col19 INT,
    col20 VARCHAR(50)
);

-- Insertion de données aléatoires
DO $$
DECLARE
    i INT := 1;
BEGIN
    FOR i IN 1..20000 LOOP
        INSERT INTO test_table (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20)
        VALUES (FLOOR(RANDOM() * 1000),
                'Texte' || i,
                'Description ' || i,
                RANDOM() * 1000,
                CURRENT_DATE - INTERVAL '1 day' * FLOOR(RANDOM() * 365 * 20),
                CASE WHEN RANDOM() < 0.5 THEN TRUE ELSE FALSE END,
                RANDOM() * 1000,
                CURRENT_TIMESTAMP - INTERVAL '1 day' * FLOOR(RANDOM() * 365 * 20),
                'Autre texte ' || i,
                FLOOR(RANDOM() * 1000),
                'Encore du texte ' || i,
                'Plus de description ' || i,
                RANDOM() * 1000,
                CURRENT_DATE - INTERVAL '1 day' * FLOOR(RANDOM() * 365 * 20),
                CASE WHEN RANDOM() < 0.5 THEN TRUE ELSE FALSE END,
                RANDOM() * 1000,
                CURRENT_TIMESTAMP - INTERVAL '1 day' * FLOOR(RANDOM() * 365 * 20),
                'Dernier texte ' || i,
                FLOOR(RANDOM() * 1000),
                'Encore plus de texte ' || i);
    END LOOP;
END $$;