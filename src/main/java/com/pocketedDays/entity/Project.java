package com.pocketedDays.entity;

import com.pocketedDays.persistence.GenericDao;
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
    private String projectName;
    private String projectPassword;
    private LocalDate updatedDate;
    private String projectDescription;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Sheet> sheets = new TreeSet<>();
    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private Set<UserProject> users = new HashSet<UserProject>();

    /**
     * Instantiates a new Project.
     */
    public Project() {
    }

    /**
     * Instantiates a new Project.
     *
     * @param projectName        the project name
     * @param projectPassword    the project password
     * @param updatedDate        the updated date
     * @param projectDescription the project description
     */
    public Project(String projectName, String projectPassword, LocalDate updatedDate, String projectDescription) {
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
     * Gets updated date.
     *
     * @return the updated date
     */
    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Sets updated date.
     *
     * @param updatedDate the updated date
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
     * Gets users.
     *
     * @return the users
     */
    public Set<UserProject> getUsers() {
        return users;
    }

    /**
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers(Set<UserProject> users) {
        this.users = users;
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

    /**
     * Add user.
     *
     * @param user the user
     */
    public void addUser(User user) {
        UserProject userProject = new UserProject(user, this);
        users.add(userProject);
        user.getProjects().add(userProject);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return getProjectId() == project.getProjectId() && getProjectName().equals(project.getProjectName()) && getProjectPassword().equals(project.getProjectPassword()) && getUpdatedDate().equals(project.getUpdatedDate()) && getProjectDescription().equals(project.getProjectDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProjectId(), getProjectName(), getProjectPassword(), getUpdatedDate(), getProjectDescription());
    }

    @Override
    public String toString() {
        StringBuilder project = new StringBuilder("Project{");
        project.append("projectId='").append(projectId);
        project.append("', projectName='").append(projectName);
        project.append("', projectPassword='").append(projectPassword);
        project.append("', updatedDate='").append(updatedDate);
        project.append("', projectDescription='").append(projectDescription);
        project.append("', users='").append(users);
        project.append("'}");
        return project.toString();
    }

    /**
     * Calculate total revenue int.
     *
     * @return the int
     */
    public int calculateTotalRevenue() {
        GenericDao sheetDao = new GenericDao(Sheet.class);
        Map<String, Object> propertyMap = new HashMap<String, Object>();
        propertyMap.put("sheetType", "Revenue");
        propertyMap.put("project", this);

        List<Sheet> sheets = (List<Sheet>) sheetDao.findByPropertyEqual(propertyMap);
        int sheetsTotal = 0;
        for (Sheet sheet : sheets) {
            int sheetTotal = sheet.calculateTotal();
            sheetsTotal += sheetTotal;
        }
        return sheetsTotal;
    }

    /**
     * Calculate total expense int.
     *
     * @return the int
     */
    public int calculateTotalExpense() {
        GenericDao sheetDao = new GenericDao(Sheet.class);
        Map<String, Object> propertyMap = new HashMap<String, Object>();
        propertyMap.put("sheetType", "Expense");
        propertyMap.put("project", this);

        List<Sheet> sheets = (List<Sheet>) sheetDao.findByPropertyEqual(propertyMap);
        int sheetsTotal = 0;
        for (Sheet sheet : sheets) {
            int sheetTotal = sheet.calculateTotal();
            sheetsTotal += sheetTotal;
        }
        return sheetsTotal;
    }
}
