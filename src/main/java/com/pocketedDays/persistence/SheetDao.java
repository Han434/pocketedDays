package com.pocketedDays.persistence;

import com.pocketedDays.entity.Sheet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * The type Sheet dao.
 */
public class SheetDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    /**
     * The Session factory.
     */
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Gets all sheets.
     *
     * @return the all sheets
     */
    public List<Sheet> getAllSheets() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Sheet.class);
        Root<Sheet> root = query.from(Sheet.class);
        List<Sheet> sheets =  session.createQuery(query).getResultList();
        session.close();
        return sheets;
    }

    /**
     * Gets sheet by sheet id.
     *
     * @param sheetId the sheet id
     * @return the sheet by sheet id
     */
    public Sheet getSheetBySheetId(int sheetId) {
        Session session = sessionFactory.openSession();
        Sheet sheet = session.get(Sheet.class, sheetId);
        session.close();
        return  sheet;
    }

    /**
     * Insert sheet int.
     *
     * @param sheet the sheet
     * @return the int
     */
    public int insertSheet(Sheet sheet) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(sheet);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete sheet.
     *
     * @param sheet the sheet
     */
    public  void deleteSheet(Sheet sheet) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(sheet);
        transaction.commit();
        session.close();
    }
}
