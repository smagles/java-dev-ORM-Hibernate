CREATE TABLE clients (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL
);

CREATE TABLE planets (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(500) NOT NULL
);
CREATE TABLE tickets (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP,
    client_id LONG,
    from_planet_id VARCHAR(50),
    to_planet_id VARCHAR(50),
    FOREIGN KEY (client_id) REFERENCES client (id),
    FOREIGN KEY (from_planet_id) REFERENCES planet (id),
    FOREIGN KEY (to_planet_id) REFERENCES planet (id)
);

