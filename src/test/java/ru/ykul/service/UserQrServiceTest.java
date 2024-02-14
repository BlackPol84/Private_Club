package ru.ykul.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ykul.dao.UserQrCodesDao;
import ru.ykul.dao.UsersDao;
import ru.ykul.model.User;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserQrServiceTest {

    @InjectMocks
    UserQrService userQrService;

    @Mock
    UsersDao usersDaoMock;

    @Mock
    UserQrCodesDao userQrCodesDaoMock;

    @Test
    void checkUserUuid_ifUserExists_twoMethodsCalled() {

        UUID uuid = UUID.randomUUID();
        User user = new User(2, "Соколов", "Роман", "Сергеевич");
        Optional<User> userOptional = Optional.of(user);

        when(usersDaoMock.getUserByUuid(uuid)).thenReturn(userOptional);

        userQrService.checkUserUuid(uuid);

        verify(usersDaoMock, times(1)).getUserByUuid(uuid);
        verify(userQrCodesDaoMock,times(1)).updateByUserId(user.getId());
    }

    @Test
    void checkUserUuid_ifUserNotExists_oneMethodCalled() {

        UUID uuid = UUID.randomUUID();
        Optional<User> userOptional = Optional.empty();

        when(usersDaoMock.getUserByUuid(uuid)).thenReturn(userOptional);

        userQrService.checkUserUuid(uuid);

        verify(usersDaoMock, times(1)).getUserByUuid(uuid);
        verifyNoInteractions(userQrCodesDaoMock);
    }

    @Test
    void checkUserUuid_ifUuidNull_throwIllegalArgumentException() {

        assertThrows(IllegalArgumentException.class, () ->
                userQrService.checkUserUuid(null));
    }
}
