package com.pocketedDays.entity;

import com.pocketedDays.utilities.NumberFormatInterface;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The type User project.
 */
@Entity(name = "UserProject")
@Table(name = "userProject")
public class UserProject implements Comparable<UserProject> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    @ManyToOne
    @JoinColumn(name = "userId",
            foreignKey = @ForeignKey(name = "userId_fk")
    )
    private User user;
    @ManyToOne
    @JoinColumn(name = "projectId",
            foreignKey = @ForeignKey(name = "projectId_fk")
    )
    private Project project;
    private String userType;
    private LocalDate joinInDate;

    /**
     * Instantiates a new User project.
     */
    public UserProject() {
    }

    /**
     * Instantiates a new User project.
     *
     * @param user    the user
     * @param project the project
     */
    public UserProject(User user, Project project) {
        this.user = user;
        this.project = project;
    }

    /**
     * Instantiates a new User project.
     *
     * @param user       the user
     * @param project    the project
     * @param userType   the user type
     * @param joinInDate the join in date
     */
    public UserProject(User user, Project project, String userType, LocalDate joinInDate) {
        this.user = user;
        this.project = project;
        this.userType = userType;
        this.joinInDate = joinInDate;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
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
     * Gets user type.
     *
     * @return the user type
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets user type.
     *
     * @param userType the user type
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Gets join in date.
     *
     * @return the join in date
     */
    public LocalDate getJoinInDate() {
        return joinInDate;
    }

    /**
     * Sets join in date.
     *
     * @param joinInDate the join in date
     */
    public void setJoinInDate(LocalDate joinInDate) {
        this.joinInDate = joinInDate;
    }

    @Override
    public String toString() {
        StringBuilder userProject = new StringBuilder("UserProject{");
        userProject.append("id='").append(id);
        userProject.append("', user='").append(user);
        userProject.append("', userType='").append(userType);
        userProject.append("', joinInDate='").append(joinInDate);
        userProject.append("'}");
        return userProject.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserProject)) return false;
        UserProject that = (UserProject) o;
        return getId() == that.getId() && Objects.equals(getUser(), that.getUser()) && Objects.equals(getProject(), that.getProject()) && Objects.equals(getUserType(), that.getUserType()) && Objects.equals(getJoinInDate(), that.getJoinInDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getProject(), getUserType(), getJoinInDate());
    }

    @Override
    public int compareTo(UserProject userProject) {
        if (this.id == userProject.id) {
            return 0;
        } else if (this.id > userProject.id) {
            return 1;
        } else {
            return -1;
        }
    }
}
