package com.epam.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vkrasovsky
 */
public class User {
    private long id;
    private String name;
    private String lastName;
    private String status;
    private List<Long> followers = new ArrayList<Long>();
    private int points;

    public User(long id, String name, String lastName, String status, List<Long> followers, int points) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.status = status;
        this.followers = followers;
        this.points = points;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Long> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Long> followers) {
        this.followers = followers != null ? followers : new ArrayList<Long>();
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status='" + status + '\'' +
                ", friends=" + followers +
                ", points=" + points +
                '}';
    }
}
