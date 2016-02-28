package com.epam.training;

import com.google.common.collect.Lists;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vkrasovsky
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @InjectMocks
    UserService userService = new UserServiceImpl();

    @Mock
    UserRepositoryImpl userRepository;

    @Before
    public void init() {
        Mockito.when(userRepository.find(1)).thenReturn(new User(1, "Vitaly", "Krasovsky", "gold", Lists.newArrayList(1001L, 1002L, 1003L), 8));
        Mockito.when(userRepository.find(2)).thenReturn(new User(2, "Dmitry", "Pupkin", "vip", Lists.newArrayList(2001L, 2002L, 2003L), 5));
        Mockito.when(userRepository.find(3)).thenReturn(new User(3, "Nick", "Brook", "vip", Lists.newArrayList(3001L, 3002L, 3003L), 0));
        Mockito.when(userRepository.find(4)).thenReturn(new User(4, "David", "Davidin", "status", new ArrayList<Long>(), 23));
    }

    @Test
    public void getUserVipTestShouldReturnAllUsersWithVipStatus() {
        ArrayList<User> usersVip = Lists.newArrayList(
                new User(2, "Dmitry", "Pupkin", "vip", Lists.newArrayList(2001L, 2002L, 2003L), 5),
                new User(3, "Nick", "Brook", "vip", Lists.newArrayList(3001L, 3002L, 3003L), 0));

        Mockito.when(userRepository.findByStatus("vip")).thenReturn(usersVip);

        List<User> usersVipActual = userService.getUserVip();
        Assert.assertThat(usersVipActual, IsIterableContainingInAnyOrder.containsInAnyOrder(usersVip.toArray()));
    }

    @Test
    public void changeStatusTestShouldReturnUserWithUpdatedStatusWhenStatusCorrect() {
        User user = userService.changeStatus("new-status", 1L);
        Assert.assertEquals("new-status", user.getStatus());
    }

    @Test(expected = RuntimeException.class)
    public void changeStatusTestShouldThrowRuntimeExceptionWhenStatusIsNull() {
        userService.changeStatus(null, 1L);

    }

    @Test(expected = RuntimeException.class)
    public void changeStatusTestShouldThrowRuntimeExceptionWhenStatusIsEmpty() {
        userService.changeStatus("", 1L);
    }

    @Test
    public void removeFriendTestShouldRemoveFollowerIdFromFollowersListIfNotEmpty() {
        User user = userService.removeFriend(2L, 2003L);
        Assert.assertFalse(user.getFollowers().contains(2003L));
    }

    @Test
    public void removeFriendTestShouldReturnEmptyFollowersListIfItsEmpty() {
        User user = userService.removeFriend(4L, 4003L);
        Assert.assertTrue(user.getFollowers().isEmpty());
    }

    @Test
    public void sendPointsTestShouldSetDefaultedMinus100Points() {
        User user = userService.sendPoints(3L, -130);
        Assert.assertEquals(-100, user.getPoints());
    }

    @Test
    public void sendPointsTestShouldSetDefaultedPlus100Points() {
        User user = userService.sendPoints(3L, 130);
        Assert.assertEquals(100, user.getPoints());
    }

    @Test
    public void sendPointsTestShouldSetActualPointsIfPointsInPlusRange() {
        User user = userService.sendPoints(3L, 20);
        Assert.assertEquals(20, user.getPoints());
    }

    @Test
    public void sendPointsTestShouldSetActualPointsIfPointsInMinusRange() {
        User user = userService.sendPoints(3L, -50);
        Assert.assertEquals(-50, user.getPoints());
    }
}
