package com.pocketedDays.entity;

import com.pocketedDays.utilities.NumberFormatInterface;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * The type Project.
 */
@Entity(name = "Project")
@Table(name = "project")
public class Project implements NumberFormatInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int projectId;
    private int projectCreatorId;
    private String projectName;
    private String projectPassword;
    private LocalDate updatedDate;
    private String projectDescription;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Sheet> sheets = new TreeSet<>();

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
     * @param updatedDate        the created date
     * @param projectDescription the project description
     */
    public Project(int projectCreatorId, String projectName, String projectPassword, LocalDate updatedDate, String projectDescription) {
        this.projectCreatorId = projectCreatorId;
        this.projectName = projectName;
        this.projectPassword = projectPassword;
        this.updatedDate = updatedDate;
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

    /**
     * Gets sheets.
     *
     * @return the sheets
     */
    public Set<Sheet> getSheets() {
        return sheets;
    }

    /**
     * Sets sheets.
     *
     * @param sheets the sheets
     */
    public void setSheets(Set<Sheet> sheets) {
        this.sheets = sheets;
    }

    /**
     * Add sheet.
     *
     * @param sheet the sheet
     */
    public void addSheet(Sheet sheet) {
        sheets.add(sheet);
        sheet.setProject(this);
    }

    /**
     * Remove sheet.
     *
     * @param sheet the sheet
     */
    public void removeSheet(Sheet sheet) {
        sheets.remove(sheet);
        sheet.setProject(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return getProjectId() == project.getProjectId() && getProjectCreatorId() == project.getProjectCreatorId() && getProjectName().equals(project.getProjectName()) && getProjectPassword().equals(project.getProjectPassword()) && getUpdatedDate().equals(project.getUpdatedDate()) && getProjectDescription().equals(project.getProjectDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProjectId(), getProjectCreatorId(), getProjectName(), getProjectPassword(), getUpdatedDate(), getProjectDescription());
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectCreatorId=" + projectCreatorId +
                ", projectName='" + projectName + '\'' +
                ", projectPassword='" + projectPassword + '\'' +
                ", updatedDate=" + updatedDate +
                ", projectDescription='" + projectDescription + '\'' +
                ", sheets=" + sheets +
                '}';
    }

    public int calculateTotal() {
        Set<Sheet> sheets = this.getSheets();
        int projectTotal = 0;
        for (Sheet sheet : sheets) {
            int sheetTotal = sheet.calculateTotal();
            projectTotal += sheetTotal;
        }
        return projectTotal;
    }
}
