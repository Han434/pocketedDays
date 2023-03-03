package com.pocketedDays.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * The type Sheet.
 */
@Entity(name = "Sheet")
@Table(name = "sheet")
public class Sheet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int sheetId;
    private int projectId;
    private String sheetDescription;
    private int sheetCreatorId;
    private LocalDate createdDate;
    private String organization;
    private String filePath;
    private String note;
    private String sheetType;

    /**
     * Instantiates a new Sheet.
     */
    public Sheet() {
    }

    /**
     * Instantiates a new Sheet.
     *
     * @param projectId        the project id
     * @param sheetDescription the sheet description
     * @param sheetCreatorId   the sheet creator id
     * @param createdDate      the created date
     * @param organization     the organization
     * @param filePath         the file path
     * @param note             the note
     * @param sheetType        the sheet type
     */
    public Sheet(int projectId, String sheetDescription, int sheetCreatorId, LocalDate createdDate, String organization, String filePath, String note, String sheetType) {
        this.projectId = projectId;
        this.sheetDescription = sheetDescription;
        this.sheetCreatorId = sheetCreatorId;
        this.createdDate = createdDate;
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
     * Gets project id.
     *
     * @return the project id
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * Sets project id.
     *
     * @param projectId the project id
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
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
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
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
}