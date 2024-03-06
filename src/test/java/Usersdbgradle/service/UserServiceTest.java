package Usersdbgradle.service;

import Usersdbgradle.models.UsersDB3;
import Usersdbgradle.repo.UsersDBRepo;
import Usersdbgradle.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UsersDBRepo usersRepo = mock(UsersDBRepo.class);
    private UserService userService = mock(UserService.class);

    private MockMvc mockMvc;

    @Test
    void getDetails() {
        UsersDB3 user1 = new UsersDB3("FullName", "email@email.org", "123123");
        UsersDB3 user2 = new UsersDB3("FullName2", "fullname@email.org", "34563456");
        List<UsersDB3> expectedUsers = Arrays.asList(user1, user2);
        when(usersRepo.findAll()).thenReturn(expectedUsers);
        List<UsersDB3> actualUsers = userService.getDetails();
        verify(usersRepo).findAll();
        assertEquals(expectedUsers, actualUsers);
    }
}