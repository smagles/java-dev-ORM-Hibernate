package org.example;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.example.flyway.DatabaseMigration;
import org.example.service.ClientCrudService;
import org.example.service.PlanetCrudService;
import org.example.service.TicketCrudService;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        DatabaseMigration databaseMigration = new DatabaseMigration();
        databaseMigration.fwMigration();

        ClientCrudService clientCrudService = new ClientCrudService();
        Client newClient = new Client();
        newClient.setName("Thanos");
        clientCrudService.create(newClient);

        PlanetCrudService planetCrudService = new PlanetCrudService();
        Planet planetTitan = new Planet();
        planetTitan.setId("TITAN");
        planetTitan.setName("Titan");
        planetCrudService.create(planetTitan);

        Planet planetGarden = new Planet();
        planetGarden.setId("0259-S");
        planetGarden.setName("Garden");
        planetCrudService.create(planetGarden);

        TicketCrudService ticketCrudService = new TicketCrudService();
        Ticket newTicket = new Ticket();
        Date currentDate = new Date();
        newTicket.setCreatedAt(currentDate);
        newTicket.setClient(newClient);
        newTicket.setFromPlanet(planetTitan);
        newTicket.setToPlanet(planetGarden);
        ticketCrudService.create(newTicket);



    }
}