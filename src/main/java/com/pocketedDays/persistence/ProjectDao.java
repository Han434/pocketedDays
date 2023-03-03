package com.pocketedDays.persistence;

import com.pocketedDays.entity.Project;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * The type Project dao.
 */
public class ProjectDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    /**
     * The Session factory.
     */
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Gets all projects.
     *
     * @return the all projects
     */
    public List<Project> getAllProjects() {
        logger.info("Searching all projects");
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Project.class);
        Root<Project> root = query.from(Project.class);
        List<Project> projects =  session.createQuery(query).getResultList();
        logger.info("Searched results {}", projects);
        session.close();
        return  projects;
    }

    /**
     * Gets project by user id.
     *
     * @param projectCreatorId the project creator id
     * @return the project by user id
     */
    public List<Project> getProjectByUserId(int projectCreatorId) {
        logger.info("Searching project by userId of {}", projectCreatorId);
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Project.class);
        Root<Project> root = query.from(Project.class);
        Expression<Integer> propertyPath = root.get("projectCreatorId");
        query.select(root).where(builder.equal(propertyPath, projectCreatorId));
        List<Project> projects =  session.createQuery(query).getResultList();
        logger.info("Searched results {}", projects);
        session.close();
        return  projects;
    }

    /**
     * Gets project by project id.
     *
     * @param projectId the project id
     * @return the project by project id
     */
    public Project getProjectByProjectId(int projectId) {
        logger.info("Searching project by projectId of {}", projectId);
        Session session = sessionFactory.openSession();
        Project project = session.get(Project.class, projectId);
        session.close();
        logger.info("Searched results {}", project);
        return  project;
    }

    /**
     * Save or update project.
     *
     * @param project the project
     */
    public void saveOrUpdateProject(Project project) {
        logger.info("Updating project {}", project);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(project);
        transaction.commit();
        session.close();
    }

    /**
     * Insert project int.
     *
     * @param project the project
     * @return the int
     */
    public int insertProject(Project project) {
        logger.info("Inserting project {}", project);
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(project);
        transaction.commit();
        session.close();
        if (id != 0) {
            logger.info("Success! Inserted project id is {}", id);
        } else {
            logger.info("Failure! Inserted project id is {}", id);
        }

        return id;
    }

    /**
     * Delete project.
     *
     * @param project the project
     */
    public  void deleteProject(Project project) {
        logger.info("Deleting project {}", project);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(project);
        transaction.commit();
        session.close();
    }
}
