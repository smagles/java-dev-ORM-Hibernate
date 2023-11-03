create TABLE Clients (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL
);

create TABLE Planets (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(500) NOT NULL
);
create TABLE Tickets (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP,
    client_id LONG,
    from_planet_id VARCHAR(50),
    to_planet_id VARCHAR(50),
    FOREIGN KEY (client_id) REFERENCES Clients (id),
    FOREIGN KEY (from_planet_id) REFERENCES Planets (id),
    FOREIGN KEY (to_planet_id) REFERENCES Planets (id)
);

