package com.bottle.alive.db.typedef;

public enum Role {

    DEFAULT(0), AUTHOR(1), ADMIN(2);

    public final int id;

    Role(int id) {
        this.id = id;
    }
}
