package org.example.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.log4j.Log4j;
import org.example.entity.Planet;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

@Log4j
public class PlanetCrudService implements CrudService<Planet> {
    private final SessionFactory sessionFactory =
            HibernateUtil.getInstance().getSessionFactory();

    @Override
    public void create(Planet planet) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(planet);
            transaction.commit();
            log.info("Planet created: " + planet.getName());
        } catch (Exception e) {
            log.error("Error creating planet: " + e.getMessage());
        }
    }

    @Override
    public Planet getById(Object id) {
        try (Session session = sessionFactory.openSession()) {
            Planet planet = session.get(Planet.class, id);
            if (planet == null) {
                log.warn("Planet with id " + id + " not found");
            }
            return planet;
        }
    }

    @Override
    public List<Planet> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Planet> criteria = builder.createQuery(Planet.class);
            Root<Planet> root = criteria.from(Planet.class);

            criteria.select(root);

            Query<Planet> query = session.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            log.error("Error while getting all planets", e);
            return Collections.emptyList();
        }
    }

    @Override
    public void update(Planet planet) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(planet);
            transaction.commit();
            log.info("Planet updated: " + planet.getName());
        } catch (Exception e) {
            log.error("Error updating planet: " + e.getMessage());
        }
    }

    @Override
    public void delete(Planet planet) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(planet);
            transaction.commit();
            log.info("Planet deleted: " + planet.getName());
        } catch (Exception e) {
            log.error("Error deleting planet: " + e.getMessage());
        }
    }
}
