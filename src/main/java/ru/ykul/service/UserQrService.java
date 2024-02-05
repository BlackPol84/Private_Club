package ru.ykul.service;

import ru.ykul.dao.UserQrCodesDao;
import ru.ykul.dao.UsersDao;
import ru.ykul.model.User;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public class UserQrService {

    private final UsersDao usersDao;
    private final UserQrCodesDao userQrCodesDao;

    public UserQrService(UsersDao usersDao, UserQrCodesDao userQrCodesDao) {
        this.usersDao = usersDao;
        this.userQrCodesDao = userQrCodesDao;
    }

    public void checkingUserUuid(UUID uuid) {

        Optional<User> userOptional = usersDao.getUserByUuid(uuid);
        userOptional.ifPresentOrElse(user -> {System.out.println(user.getSurname() +
                " " + user.getName() + " " + user.getMiddleName()); userQrCodesDao.update(user);},
                () -> System.out.println("Человека с таким uuid в базе нет"));
    }
}
