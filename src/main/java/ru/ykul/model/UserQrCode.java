package ru.ykul.model;

import java.util.UUID;

public class UserQrCode {

    private int id;
    private int userId;
    private UUID uuid;

    public UserQrCode(int id, int userId, UUID uuid) {
        this.id = id;
        this.userId = userId;
        this.uuid = uuid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}

