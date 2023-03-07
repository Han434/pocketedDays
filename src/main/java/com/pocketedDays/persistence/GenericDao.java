package com.pocketedDays.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;


/**
 * The type Generic dao.
 *
 * @param <T> the type parameter
 */
public class GenericDao<T> {
    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new Generic dao.
     *
     * @param type the type
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }

    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<T> getAll() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<T> entities =  session.createQuery(query).getResultList();
        session.close();
        return entities;
    }

    /**
     * Gets by property.
     *
     * @param propertyName     the property name
     * @param projectCreatorId the project creator id
     * @return the by property
     */
    public List<T> getByProperty(String propertyName, int projectCreatorId) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Expression<Integer> propertyPath = root.get(propertyName);
        query.select(root).where(builder.equal(propertyPath, projectCreatorId));
        List<T> entities =  session.createQuery(query).getResultList();
        session.close();
        return entities;
    }

    /**
     * Gets by id.
     *
     * @param <T> the type parameter
     * @param id  the id
     * @return the by id
     */
    public <T> T getById(int id) {
        Session session = getSession();
        T entity = (T) session.get(type, id);
        session.close();
        return entity;
    }

    /**
     * Insert entity int.
     *
     * @param entity the entity
     * @return the int
     */
    public int insertEntity(T entity) {
        int id = 0;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(entity);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Save or update entity.
     *
     * @param entity the entity
     */
    public void saveOrUpdateEntity(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Delete entity.
     *
     * @param entity the entity
     */
    public  void deleteEntity(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }
}
