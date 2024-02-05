package ru.ykul;

import ru.ykul.dao.UserQrCodesDao;
import ru.ykul.dao.UsersDao;
import ru.ykul.service.UserQrService;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        UUID uuid = UUID.fromString("8e107530-ba8e-4dac-a8c0-a7f18e3c0300");

        UsersDao usersDao = new UsersDao();
        UserQrCodesDao userQrCodesDao = new UserQrCodesDao();

        UserQrService userQrService = new UserQrService(usersDao, userQrCodesDao);
        userQrService.checkingUserUuid(uuid);
    }
}