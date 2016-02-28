package com.epam.training;

import com.google.common.base.Strings;

import java.util.List;

/**
 * @author vkrasovsky
 */
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private static final String STATUS_VIP = "vip";
    private static final int POINTS_MIN = -100;
    private static final int POINTS_MAX = 100;

    public List<User> getUserVip() {
        return userRepository.findByStatus(STATUS_VIP);
    }

    public User changeStatus(String status, long id) {
        User user;
        if (!Strings.isNullOrEmpty(status)) {
            user = userRepository.find(id);
            user.setStatus(status);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Cannot assign empty or null status");
        }
        return user;
    }

    public User removeFriend(long userId, long toRemove) {
        User user = userRepository.find(userId);
        List<Long> followers = user.getFollowers();
        if (!followers.isEmpty()) {
            followers.remove(toRemove);
        }
        userRepository.save(user);

        return user;
    }

    public User sendPoints(long id, int points) {
        User user = userRepository.find(id);

        if (points < POINTS_MIN) {
            user.setPoints(user.getPoints() + POINTS_MIN);
        } else if (points > POINTS_MAX) {
            user.setPoints(user.getPoints() + POINTS_MAX);
        } else {
            user.setPoints(user.getPoints() + points);
        }

        userRepository.save(user);
        return user;
    }
}
