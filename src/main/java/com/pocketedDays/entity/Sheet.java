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
    private int sheetCreatorId;
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

    /**
     * Instantiates a new Sheet.
     *
     * @param project          the project id
     * @param sheetDescription the sheet description
     * @param sheetCreatorId   the sheet creator id
     * @param updatedDate      the created date
     * @param organization     the organization
     * @param filePath         the file path
     * @param note             the note
     * @param sheetType        the sheet type
     */
    public Sheet(Project project, String sheetDescription, int sheetCreatorId, LocalDate updatedDate, String organization, String filePath, String note, String sheetType) {
        this.project = project;
        this.sheetDescription = sheetDescription;
        this.sheetCreatorId = sheetCreatorId;
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

    /**
     * Gets sheet creator id.
     *
     * @return the sheet creator id
     */
    public int getSheetCreatorId() {
        return sheetCreatorId;
    }

    /**
     * Sets sheet creator id.
     *
     * @param sheetCreatorId the sheet creator id
     */
    public void setSheetCreatorId(int sheetCreatorId) {
        this.sheetCreatorId = sheetCreatorId;
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
        return getSheetId() == sheet.getSheetId() && getSheetCreatorId() == sheet.getSheetCreatorId() && getProject().equals(sheet.getProject()) && getSheetDescription().equals(sheet.getSheetDescription()) && getUpdatedDate().equals(sheet.getUpdatedDate()) && getOrganization().equals(sheet.getOrganization()) && getFilePath().equals(sheet.getFilePath()) && getNote().equals(sheet.getNote()) && getSheetType().equals(sheet.getSheetType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSheetId(), getProject(), getSheetDescription(), getSheetCreatorId(), getUpdatedDate(), getOrganization(), getFilePath(), getNote(), getSheetType());
    }

    @Override
    public String toString() {
        return "Sheet{" +
                "sheetId=" + sheetId +
                ", project=" + project.toString() +
                ", sheetDescription='" + sheetDescription + '\'' +
                ", sheetCreatorId=" + sheetCreatorId +
                ", updatedDate=" + updatedDate +
                ", organization='" + organization + '\'' +
                ", filePath='" + filePath + '\'' +
                ", note='" + note + '\'' +
                ", sheetType='" + sheetType + '\'' +
                //", rows=" + rows.toString() +
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