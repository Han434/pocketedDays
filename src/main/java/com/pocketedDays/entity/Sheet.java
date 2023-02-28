package com.pocketedDays.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

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

    public Sheet() {
    }

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

    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getSheetDescription() {
        return sheetDescription;
    }

    public void setSheetDescription(String sheetDescription) {
        this.sheetDescription = sheetDescription;
    }

    public int getSheetCreatorId() {
        return sheetCreatorId;
    }

    public void setSheetCreatorId(int sheetCreatorId) {
        this.sheetCreatorId = sheetCreatorId;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSheetType() {
        return sheetType;
    }

    public void setSheetType(String sheetType) {
        this.sheetType = sheetType;
    }
}