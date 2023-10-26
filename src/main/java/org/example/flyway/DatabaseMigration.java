package org.example.flyway;

import org.flywaydb.core.Flyway;

import static org.example.util.Config.*;

public class DatabaseMigration {
    public void fwMigration() {
        Flyway flyway = Flyway.configure()
                .dataSource(URL, USERNAME, PASSWORD)
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .load();

        flyway.migrate();
    }
}
