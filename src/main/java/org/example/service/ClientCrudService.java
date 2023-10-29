package org.example.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.log4j.Log4j;
import org.example.entity.Client;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

@Log4j
public class ClientCrudService implements CrudService<Client> {
    private final SessionFactory sessionFactory =
            HibernateUtil.getInstance().getSessionFactory();

    @Override
    public void create(Client client) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
            log.info("Client created: " + client.getName());
        } catch (Exception e) {
            log.error("Error creating client: " + e.getMessage());
        }
    }

    @Override
    public Client getById(Object id) {
        try (Session session = sessionFactory.openSession()) {
            Client client = session.get(Client.class, id);
            if (client == null) {
                log.warn("Client with id " + id + " not found");
            }
            return client;
        }
    }

    @Override
    public List<Client> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Client> criteria = builder.createQuery(Client.class);
            Root<Client> root = criteria.from(Client.class);
            criteria.select(root);

            Query<Client> query = session.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            log.error("Error while getting all clients", e);
            return Collections.emptyList();
        }
    }

    @Override
    public void update(Client client) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(client);
            transaction.commit();
            log.info("Client updated: " + client.getName());
        } catch (Exception e) {
            log.error("Error updating client: " + e.getMessage());
        }
    }

    @Override
    public void delete(Client client) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(client);
            transaction.commit();
            log.info("Client deleted: " + client.getName());
        } catch (Exception e) {
            log.error("Error deleting client: " + e.getMessage());
        }
    }
}

