package ru.ykul;

import ru.ykul.dao.UserQrCodesDao;
import ru.ykul.dao.UsersDao;
import ru.ykul.model.UserQrCode;
import ru.ykul.service.UserQrService;

import java.util.Optional;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        UUID uuid = UUID.fromString("d67d7d36-dc3f-4425-a9dc-172e13ce84e4");

        UsersDao usersDao = new UsersDao();
        UserQrCodesDao userQrCodesDao = new UserQrCodesDao();

        UserQrService userQrService = new UserQrService(usersDao, userQrCodesDao);
        userQrService.checkUserUuid(uuid);
    }
}
