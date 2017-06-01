package ru.isakovalexey.lunch;

import ru.isakovalexey.lunch.model.BaseEntity;

/**
 * Created by user on 30.05.2017.
 */
public class AuthorizedUser {
    public static int id = BaseEntity.START_SEQ;

    public static int id() {
        return id;
    }

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }
}
