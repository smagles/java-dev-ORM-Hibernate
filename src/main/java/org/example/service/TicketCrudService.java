package org.example.service;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Root;
import lombok.extern.log4j.Log4j;
import org.example.entity.Ticket;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

@Log4j
public class TicketCrudService implements CrudService<Ticket> {

    private final SessionFactory sessionFactory =
            HibernateUtil.getInstance().getSessionFactory();

    @Override
    public void create(Ticket ticket) {
        if (ticket == null || ticket.getClient() == null || ticket.getFromPlanet() == null || ticket.getToPlanet() == null) {
            throw new IllegalArgumentException("Ticket, client, fromPlanet, and toPlanet must not be null");
        }
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(ticket);
            transaction.commit();
            log.info("Ticket created: " + ticket.getId());
        }
    }

    @Override
    public Ticket getById(Object id) {
        try (Session session = sessionFactory.openSession()) {
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket == null) {
                log.warn("Ticket with id " + id + " not found");
            }
            return ticket;
        }
    }

    @Override
    public List<Ticket> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Ticket> criteria = builder.createQuery(Ticket.class);
            Root<Ticket> root = criteria.from(Ticket.class);
            criteria.select(root);

            Query<Ticket> query = session.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            log.error("Error while getting all tickets", e);
            return Collections.emptyList();
        }

    }

    @Override
    public void update(Ticket ticket) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(ticket);
            transaction.commit();
            log.info("Ticket updated: " + ticket.getId());
        } catch (Exception e) {
            log.error("Error updating ticket: " + e.getMessage());
        }
    }

    @Override
    public void delete(Ticket ticket) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(ticket);
            transaction.commit();
            log.info("Ticket deleted: " + ticket.getId());
        } catch (Exception e) {
            log.error("Error deleting ticket: " + e.getMessage());
        }
    }
}
