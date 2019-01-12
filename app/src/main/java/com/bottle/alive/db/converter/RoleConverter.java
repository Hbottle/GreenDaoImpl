package com.bottle.alive.db.converter;

import com.bottle.alive.db.typedef.Role;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * 数据库中存在自定义类型时，可以将自定义类型转为基础类型，通过 PropertyConverter 进行存取
 * <a href="http://greenrobot.org/greendao/documentation/custom-types/">Custom types</a>
 */
public class RoleConverter implements PropertyConverter<Role, Integer> {
    @Override
    public Role convertToEntityProperty(Integer databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        for (Role role : Role.values()) {
            if (role.id == databaseValue) {
                return role;
            }
        }
        return Role.DEFAULT;
    }

    @Override
    public Integer convertToDatabaseValue(Role entityProperty) {
        return entityProperty == null ? null : entityProperty.id;
    }
}
