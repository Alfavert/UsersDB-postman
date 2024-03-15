package Usersdbgradle.service;

import Usersdbgradle.models.UsersDB3;
import Usersdbgradle.repo.UsersDBRepo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    @Test
    void testSaveDetails_NewUser() {
        UsersDB3 newUser = new UsersDB3(111L ,"111", "John Doe", "john@example.com");
        when(usersRepo.findById(111L)).thenReturn(Optional.empty());
        when(usersRepo.save(newUser)).thenReturn(newUser);

        UsersDB3 savedUser = userService.saveDetails(newUser);

        verify(usersRepo).findById(111L);
        verify(usersRepo).save(newUser);
        assertEquals(newUser, savedUser);
    }

    @Test
    void testSaveDetails_ExistingUser() {
        UsersDB3 existingUser = new UsersDB3(222L,"222", "Jane Doe", "jane@example.com");
        when(usersRepo.findById(222L)).thenReturn(Optional.of(existingUser));

        UsersDB3 savedUser = userService.saveDetails(existingUser);

        verify(usersRepo).findById(222L);
        verify(usersRepo, never()).save(existingUser);
        assertNull(savedUser);
    }

    @Test
    public void testDeleteStudent_ExistingId() {
        long existingId = 123L;
        when(usersRepo.existsById(existingId)).thenReturn(true);

        String result = userService.deleteStudent(existingId);

        verify(usersRepo).existsById(existingId);
        verify(usersRepo).deleteById(existingId);
        assertEquals("deleted" + existingId, result);
    }

    @Test
    public void testDeleteStudent_NonExistingId() {
        long nonExistingId = 456L;
        when(usersRepo.existsById(nonExistingId)).thenReturn(false);

        String result = userService.deleteStudent(nonExistingId);

        verify(usersRepo).existsById(nonExistingId);
        verify(usersRepo, never()).deleteById(nonExistingId);
        assertEquals("ID is not existing", result);
    }
}

