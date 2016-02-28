package com.epam.training;

import java.util.List;

/**
 * @author vkrasovsky
 */
public interface UserService {
    List<User> getUserVip();

    User changeStatus(String status, long id);

    User removeFriend(long userId, long remove);

    User sendPoints(long id, int points);
}
