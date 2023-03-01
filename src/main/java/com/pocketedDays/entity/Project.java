package com.pocketedDays.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * The type Project.
 */
@Entity(name = "Project")
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int projectId;
    private int projectCreatorId;
    private String projectName;
    private String projectPassword;
    private LocalDate createdDate;
    private String projectDescription;

    /**
     * Instantiates a new Project.
     */
    public Project() {
    }

    /**
     * Instantiates a new Project.
     *
     * @param projectCreatorId   the project creator id
     * @param projectName        the project name
     * @param projectPassword    the project password
     * @param createdDate        the created date
     * @param projectDescription the project description
     */
    public Project(int projectCreatorId, String projectName, String projectPassword, LocalDate createdDate, String projectDescription) {
        this.projectCreatorId = projectCreatorId;
        this.projectName = projectName;
        this.projectPassword = projectPassword;
        this.createdDate = createdDate;
        this.projectDescription = projectDescription;
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
     * Gets project creator id.
     *
     * @return the project creator id
     */
    public int getProjectCreatorId() {
        return projectCreatorId;
    }

    /**
     * Sets project creator id.
     *
     * @param projectCreatorId the project creator id
     */
    public void setProjectCreatorId(int projectCreatorId) {
        this.projectCreatorId = projectCreatorId;
    }

    /**
     * Gets project name.
     *
     * @return the project name
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets project name.
     *
     * @param projectName the project name
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Gets project password.
     *
     * @return the project password
     */
    public String getProjectPassword() {
        return projectPassword;
    }

    /**
     * Sets project password.
     *
     * @param projectPassword the project password
     */
    public void setProjectPassword(String projectPassword) {
        this.projectPassword = projectPassword;
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
     * Gets project description.
     *
     * @return the project description
     */
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     * Sets project description.
     *
     * @param projectDescription the project description
     */
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
}
