package ru.ykul;

import ru.ykul.dao.UserQrCodesDao;
import ru.ykul.dao.UsersDao;
import ru.ykul.model.UserQrCode;
import ru.ykul.service.UserQrService;

import java.util.Optional;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        UUID uuid = UUID.fromString("7b051475-bbcf-4497-8e20-1c8bc62bf5ad");

        UsersDao usersDao = new UsersDao();
        UserQrCodesDao userQrCodesDao = new UserQrCodesDao();

        UserQrService userQrService = new UserQrService(usersDao, userQrCodesDao);
        userQrService.checkUserUuid(uuid);
    }
}
