package com.pocketedDays.persistence;

import com.pocketedDays.entity.Project;
import com.pocketedDays.entity.Sheet;
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

public class SheetDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public List<Sheet> getAllSheets() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Sheet.class);
        Root<Sheet> root = query.from(Sheet.class);
        List<Sheet> sheets =  session.createQuery(query).getResultList();
        session.close();
        return sheets;
    }

    public List<Sheet> getSheetsByProjectId(int projectId) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Project.class);
        Root<Sheet> root = query.from(Project.class);
        Expression<String> propertyPath = root.get("projectId");
        query.where(builder.like(propertyPath, "%" + projectId + "%"));
        List<Sheet> sheets =  session.createQuery(query).getResultList();
        session.close();
        return  sheets;
    }

    public Sheet getSheetById(int sheetId) {
        Session session = sessionFactory.openSession();
        Sheet sheet = session.get(Sheet.class, sheetId);
        session.close();
        return  sheet;
    }

    public int insertSheet(Sheet sheet) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(sheet);
        transaction.commit();
        session.close();
        return id;
    }

    public  void deleteSheet(Sheet sheet) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(sheet);
        transaction.commit();
        session.close();
    }
}
