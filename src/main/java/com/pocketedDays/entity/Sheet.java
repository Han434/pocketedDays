package com.pocketedDays.entity;

import com.pocketedDays.utilities.NumberFormatInterface;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * The type Sheet.
 */
@Entity(name = "Sheet")
@Table(name = "sheet")
public class Sheet implements Comparable<Sheet>, NumberFormatInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int sheetId;
    @ManyToOne
    @JoinColumn(name = "projectId",
            foreignKey = @ForeignKey(name = "sheet_project_fk")
    )
    private Project project;
    private String sheetDescription;
    @ManyToOne
    @JoinColumn(name = "sheetCreatorId",
            foreignKey = @ForeignKey(name = "sheet_user_fk")
    )
    private User user;
    private LocalDate updatedDate;
    private String organization;
    private String filePath;
    private String note;
    private String sheetType;
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Row> rows = new TreeSet<>();

    /**
     * Instantiates a new Sheet.
     */
    public Sheet() {
    }


    public Sheet(Project project, String sheetDescription, User user, LocalDate updatedDate, String organization, String filePath, String note, String sheetType) {
        this.project = project;
        this.sheetDescription = sheetDescription;
        this.user = user;
        this.updatedDate = updatedDate;
        this.organization = organization;
        this.filePath = filePath;
        this.note = note;
        this.sheetType = sheetType;
    }

    /**
     * Gets sheet id.
     *
     * @return the sheet id
     */
    public int getSheetId() {
        return sheetId;
    }

    /**
     * Sets sheet id.
     *
     * @param sheetId the sheet id
     */
    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    /**
     * Gets project.
     *
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * Sets project.
     *
     * @param project the project
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Gets sheet description.
     *
     * @return the sheet description
     */
    public String getSheetDescription() {
        return sheetDescription;
    }

    /**
     * Sets sheet description.
     *
     * @param sheetDescription the sheet description
     */
    public void setSheetDescription(String sheetDescription) {
        this.sheetDescription = sheetDescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets created date.
     *
     * @return the created date
     */
    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Sets created date.
     *
     * @param updatedDate the created date
     */
    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * Gets organization.
     *
     * @return the organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * Sets organization.
     *
     * @param organization the organization
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     * Gets file path.
     *
     * @return the file path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets file path.
     *
     * @param filePath the file path
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets note.
     *
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets note.
     *
     * @param note the note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Gets sheet type.
     *
     * @return the sheet type
     */
    public String getSheetType() {
        return sheetType;
    }

    /**
     * Sets sheet type.
     *
     * @param sheetType the sheet type
     */
    public void setSheetType(String sheetType) {
        this.sheetType = sheetType;
    }

    /**
     * Gets rows.
     *
     * @return the rows
     */
    public Set<Row> getRows() {
        return rows;
    }

    /**
     * Sets rows.
     *
     * @param rows the rows
     */
    public void setRows(Set<Row> rows) {
        this.rows = rows;
    }

    /**
     * Add row.
     *
     * @param row the row
     */
    public void addRow(Row row) {
        rows.add(row);
        row.setSheet(this);
    }

    /**
     * Remove row.
     *
     * @param row the row
     */
    public void removeRow(Row row) {
        rows.remove(row);
        row.setSheet(null);
    }

    /**
     * Calculated total int.
     *
     * @return the int
     */
    public int calculateTotal() {
        Set<Row> rows = this.getRows();
        int sheetTotal = 0;
        for (Row row : rows) {
            int rowTotal = row.calculateTotal();
            sheetTotal += rowTotal;
        }
       return sheetTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sheet)) return false;
        Sheet sheet = (Sheet) o;
        return getSheetId() == sheet.getSheetId() && Objects.equals(getProject(), sheet.getProject()) && Objects.equals(getSheetDescription(), sheet.getSheetDescription()) && Objects.equals(getUser(), sheet.getUser()) && Objects.equals(getUpdatedDate(), sheet.getUpdatedDate()) && Objects.equals(getOrganization(), sheet.getOrganization()) && Objects.equals(getFilePath(), sheet.getFilePath()) && Objects.equals(getNote(), sheet.getNote()) && Objects.equals(getSheetType(), sheet.getSheetType()) && Objects.equals(getRows(), sheet.getRows());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSheetId(), getSheetDescription(), getUpdatedDate(), getOrganization(), getFilePath(), getNote(), getSheetType());
    }

    @Override
    public String toString() {
        return "Sheet{" +
                "sheetId=" + sheetId +
                ", project=" + project +
                ", sheetDescription='" + sheetDescription + '\'' +
                ", user=" + user +
                ", updatedDate=" + updatedDate +
                ", organization='" + organization + '\'' +
                ", filePath='" + filePath + '\'' +
                ", note='" + note + '\'' +
                ", sheetType='" + sheetType + '\'' +
                ", rows=" + rows +
                '}';
    }

    @Override
    public int compareTo(Sheet sheet) {
        int hash =((Integer)this.sheetId).compareTo(sheet.sheetId);
        return hash;
    }

//    @Override
//    public int compareTo(Sheet sheet){
//        if (this.sheetId > sheet.sheetId) {
//            return 1;
//        }
//        else if (this.sheetId < sheet.sheetId) {
//            return -1;
//        }
//        else {
//            return 0;
//        }
//    }
}