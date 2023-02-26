package com.pocketedDays.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Project")
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int projectId;
    private int creatorId;
    private String projectName;
    private String projectPassword;
    private LocalDate createdDate;
    private String projectDescription;

    public Project() {
    }

    public Project(int creatorId, String projectName, String projectPassword, LocalDate createdDate, String projectDescription) {
        this.creatorId = creatorId;
        this.projectName = projectName;
        this.projectPassword = projectPassword;
        this.createdDate = createdDate;
        this.projectDescription = projectDescription;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectPassword() {
        return projectPassword;
    }

    public void setProjectPassword(String projectPassword) {
        this.projectPassword = projectPassword;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
}
