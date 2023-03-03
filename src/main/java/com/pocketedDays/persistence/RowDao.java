package com.pocketedDays.persistence;

import com.pocketedDays.entity.Project;
import com.pocketedDays.entity.Row;
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

/**
 * The type Row dao.
 */
public class RowDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    /**
     * The Session factory.
     */
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Gets all rows.
     *
     * @return the all rows
     */
    public List<Row> getAllRows() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Row.class);
        Root<Row> root = query.from(Row.class);
        List<Row> rows =  session.createQuery(query).getResultList();
        session.close();
        return rows;
    }

    /**
     * Gets rows by sheet id.
     *
     * @param sheetId the sheet id
     * @return the rows by sheet id
     */
    public List<Row> getRowsBySheetId(int sheetId) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Row.class);
        Root<Row> root = query.from(Row.class);
        Expression<Integer> propertyPath = root.get("sheetId");
        query.select(root).where(builder.equal(propertyPath, sheetId));
        List<Row> rows =  session.createQuery(query).getResultList();
        session.close();
        return  rows;
    }

    /**
     * Gets rows by row id.
     *
     * @param rowId the row id
     * @return the rows by row id
     */
    public Row getRowsByRowId(int rowId) {
        Session session = sessionFactory.openSession();
        Row row = session.get(Row.class, rowId);
        session.close();
        return  row;
    }

    /**
     * Save or update row.
     *
     * @param row the row
     */
    public void saveOrUpdateRow(Row row) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(row);
        transaction.commit();
        session.close();
    }

    /**
     * Insert row int.
     *
     * @param row the row
     * @return the int
     */
    public int insertRow(Row row) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(row);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete row.
     *
     * @param row the row
     */
    public  void deleteRow(Row row) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(row);
        transaction.commit();
        session.close();
    }
}
