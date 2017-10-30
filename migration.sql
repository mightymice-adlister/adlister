USE adlister_db;



DROP TABLE IF EXISTS ads;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;


CREATE TABLE users (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(240) NOT NULL,
    email VARCHAR(240) NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR (100),
    last_name VARCHAR (150),
    day_number VARCHAR (200),
    evening_number VARCHAR (200),
    bio TEXT,
    PRIMARY KEY (id)
);


CREATE TABLE categories (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(50),
    PRIMARY KEY(id)
);

CREATE TABLE ads (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id INT UNSIGNED NOT NULL,
    title VARCHAR(200) NOT NULL,
    description TEXT NOT NULL,
    cat_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE,
    FOREIGN KEY (cat_id) REFERENCES categories(id)
        ON DELETE CASCADE


);


use adlister_db;
SELECT *
FROM ads
JOIN categories AS cat
ON ads.cat_id = cat.id
WHERE ads.id=?;

