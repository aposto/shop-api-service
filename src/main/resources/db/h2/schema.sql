-- schema.sql

DROP TABLE if exists brands;
CREATE TABLE brands
(
    id          INT NOT NULL AUTO_INCREMENT,
    name        VARCHAR(200) NOT NULL,
    PRIMARY KEY ( id ),
    UNIQUE (name)
);

DROP TABLE if exists categories;
CREATE TABLE categories
(
    id          INT NOT NULL AUTO_INCREMENT,
    name        VARCHAR(100) NOT NULL,
    PRIMARY KEY ( id ),
    UNIQUE (name)
);

DROP TABLE if exists products;
CREATE TABLE products
(
    id          INT NOT NULL AUTO_INCREMENT,
    brand_id    INT NOT NULL,
    category_id INT NOT NULL,
    price       INT NOT NULL,
    PRIMARY KEY ( id )
);

CREATE INDEX idx_products_category
    ON products (brand_id, category_id);

