package com.deals.services;
import com.deals.daos.RoleDao;
import com.deals.models.Role;
import com.deals.models.enums.Authority;
import com.deals.utils.HibernateUtils;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.Statement;

public class RoleService {

    private static RoleService INSTANCE;

    public static RoleService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RoleService();
        }
        return INSTANCE;
    }

    public Role get(String authority) {
        return RoleDao.getInstance().get(authority);
    }

    public void initialize() {
            for (Authority a : Authority.values()) {
                if (RoleDao.getInstance().get(a.role) == null) {
                    com.deals.models.Role userRole = new com.deals.models.Role();
                    userRole.setAuthority(a.role);
                    RoleDao.getInstance().create(userRole);
                }
            }
    }
}
