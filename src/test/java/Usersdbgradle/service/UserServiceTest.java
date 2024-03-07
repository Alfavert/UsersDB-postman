package Usersdbgradle.service;

import Usersdbgradle.models.UsersDB3;
import Usersdbgradle.repo.UsersDBRepo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private final UsersDBRepo usersRepo = mock(UsersDBRepo.class);
    private final UserService userService = mock(UserService.class);
//    private MockMvc mockMvc;

    @Test
    void getDetails() {

        UsersDB3 user1 = new UsersDB3("FullName", "email@email.org", "123123");
        UsersDB3 user2 = new UsersDB3("FullName2", "fullname@email.org", "34563456");
        List<UsersDB3> actualUsers = Arrays.asList(user1, user2);
        List<UsersDB3> expectedUsers = Arrays.asList(user1, user2);

        when(usersRepo.findAll()).thenReturn(actualUsers);

        when(userService.getDetails()).thenReturn(expectedUsers);

        assertThat(expectedUsers).isNotNull();

        userService.getDetails();
        verify(userService, times(1)).getDetails();

        assertEquals(actualUsers, expectedUsers);

    }


}