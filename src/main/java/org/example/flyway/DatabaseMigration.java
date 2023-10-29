package org.example.flyway;

import lombok.extern.log4j.Log4j;
import org.flywaydb.core.Flyway;

import static org.example.util.Config.*;

@Log4j
public class DatabaseMigration {
    public void fwMigration() {
        try {
            Flyway flyway = Flyway.configure()
                    .dataSource(URL, USERNAME, PASSWORD)
                    .locations("classpath:db/migration")
                    .baselineOnMigrate(true)
                    .load();

            flyway.migrate();
            log.info("Database migration successfully completed.");
        } catch (Exception e) {
            log.error("Error during database migration: " + e.getMessage(), e);
        }
    }
}

