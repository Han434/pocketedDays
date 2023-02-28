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

public class ProjectDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public List<Project> getAllProjects() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Project.class);
        Root<Project> root = query.from(Project.class);
        List<Project> projects =  session.createQuery(query).getResultList();
        session.close();
        return  projects;
    }

    public List<Project> getProjectByUserId(int projectCreatorId) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Project.class);
        Root<Project> root = query.from(Project.class);
        Expression<Integer> propertyPath = root.get("projectCreatorId");
        query.select(root).where(builder.equal(propertyPath, projectCreatorId));
        List<Project> projects =  session.createQuery(query).getResultList();
        session.close();
        return  projects;
    }

    public Project getProjectById(int projectId) {
        Session session = sessionFactory.openSession();
        Project project = session.get(Project.class, projectId);
        session.close();
        return  project;
    }

    public void saveOrUpdateProject(Project project) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(project);
        transaction.commit();
        session.close();
    }

    public int insertProject(Project project) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(project);
        transaction.commit();
        session.close();
        return id;
    }

    public  void deleteProject(Project project) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(project);
        transaction.commit();
        session.close();
    }
}
