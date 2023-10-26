package org.example;


import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.flyway.DatabaseMigration;
import org.example.service.ClientCrudService;
import org.example.service.PlanetCrudService;

public class Main {
    public static void main(String[] args) {
        DatabaseMigration databaseMigration = new DatabaseMigration();
        databaseMigration.fwMigration();

        ClientCrudService clientCrudService = new ClientCrudService();
        Client newClient = new Client();
        newClient.setName("Thanos");
        clientCrudService.create(newClient);
        clientCrudService.delete(newClient);

        PlanetCrudService planetCrudService = new PlanetCrudService();
        Planet newPlanet = new Planet();
        newPlanet.setId("TITAN");
        newPlanet.setName("Titan");
        planetCrudService.create(newPlanet);
        planetCrudService.delete(newPlanet);
    }
}