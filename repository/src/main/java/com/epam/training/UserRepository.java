package com.epam.training;

import java.util.List;

/**
 * @author vkrasovsky
 */
public interface UserRepository {
    void save(User user);

    void update(User user);

    User find(long id);

    List<User> findAll(long id);

    List<User> findByName(String name);

    List<User> findByStatus(String status);
}
