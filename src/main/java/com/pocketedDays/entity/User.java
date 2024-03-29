package com.pocketedDays.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * The type User.
 */
@Entity(name = "User")
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String gender;
    private String email;
    private LocalDate dateOfBirth;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserProject> projects = new TreeSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Sheet> sheets = new TreeSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Row> rows = new TreeSet<>();

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param firstName   the first name
     * @param lastName    the last name
     * @param userName    the user name
     * @param gender      the gender
     * @param email       the email
     * @param dateOfBirth the date of birth
     */
    public User(String firstName, String lastName, String userName, String gender, String email, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.gender = gender;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets gender.
     *
     * @param gender the gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets projects.
     *
     * @return the projects
     */
    public Set<UserProject> getProjects() {
        return projects;
    }

    /**
     * Add project.
     *
     * @param project the project
     */
    public void addProject(Project project) {
        UserProject userProject = new UserProject(this, project);
        projects.add(userProject);
        project.getUsers().add(userProject);
    }

    public void removeProject(Project project) {
        projects.remove(project);
    }

    /**
     * Sets projects.
     *
     * @param projects the projects
     */
    public void setProjects(TreeSet<UserProject> projects) {
        this.projects = projects;
    }

    /**
     * Add sheet.
     *
     * @param sheet the sheet
     */
    public void addSheet(Sheet sheet) {
        sheets.add(sheet);
        sheet.setUser(this);
    }

    /**
     * Remove sheet.
     *
     * @param sheet the sheet
     */
    public void removeSheet(Sheet sheet) {
        sheets.remove(sheet);
        sheet.setUser(null);
    }

    /**
     * Add row.
     *
     * @param row the row
     */
    public void addRow(Row row) {
        rows.add(row);
        row.setUser(this);
    }

    /**
     * Remove row.
     *
     * @param row the row
     */
    public void removeRow(Row row) {
        rows.remove(row);
        row.setUser(null);
    }

    @Override
    public String toString() {
        StringBuilder user = new StringBuilder("User{");
        user.append("userId='").append(userId);
        user.append("', firstName='").append(firstName);
        user.append("', lastName='").append(lastName);
        user.append("', userName='").append(userName);
        user.append("', gender='").append(gender);
        user.append("', email='").append(email);
        user.append("', dateOfBirth='").append(dateOfBirth);
        user.append("'}");
        return user.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserId() == user.getUserId() && getFirstName().equals(user.getFirstName()) && getLastName().equals(user.getLastName()) && getUserName().equals(user.getUserName()) && getGender().equals(user.getGender()) && getEmail().equals(user.getEmail()) && getDateOfBirth().equals(user.getDateOfBirth());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getFirstName(), getLastName(), getUserName(), getGender(), getEmail(), getDateOfBirth());
    }
}
