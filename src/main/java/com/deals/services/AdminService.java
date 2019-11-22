package com.deals.services;

import com.deals.models.Role;
import com.deals.models.User;
import com.deals.models.enums.Authority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AdminService {

    private static AdminService INSTANCE;

    private UserService userService = UserService.getInstance();
    private RoleService roleService = RoleService.getInstance();

    public static AdminService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AdminService();
        }
        return INSTANCE;
    }

    private boolean isUnauthorized(long id) {
        return !userService.get(id).getRoles().stream().anyMatch(r -> r.getAuthority().equals(Authority.ADMIN.role));
    }

    public void updateUser(long adminId, long userId, String pass, String confPass,
                           String phoneNumber, String address, boolean isAdmin) throws IllegalAccessException {

        if (isUnauthorized(adminId)) {
            throw new IllegalAccessException("You don't have admin rights!");
        }


        if (pass == null || pass.trim().equals("")) throw new IllegalArgumentException("Please enter password!");
        if (confPass == null || confPass.trim().equals(""))
            throw new IllegalArgumentException("Please confirm password!");
        if (!confPass.trim().equals(pass.trim()))
            throw new IllegalArgumentException("Passwords does not match each other!");
        if (phoneNumber == null || phoneNumber.trim().equals(""))
            throw new IllegalArgumentException("Please enter phone number!");
        if (address == null || address.trim().equals("")) throw new IllegalArgumentException("Please enter address!");

        User user = userService.get(userId);
        user.setPassword(pass);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);

        if (isAdmin) {
            Role adminRole = roleService.get(Authority.ADMIN.role);
            if (!user.getRoles().contains(adminRole)) {
                user.getRoles().add(adminRole);
            }
        } else {
            Set<Role> roles = user.getRoles().stream().filter(r -> !r.getAuthority().equals(Authority.ADMIN.role)).collect(Collectors.toSet());
            user.setRoles(roles);
        }

        userService.update(user);
    }

    public void deleteUser(long adminId, long userId) throws IllegalAccessException {
        if (isUnauthorized(adminId)) {
            throw new IllegalAccessException("You don't have admin rights!");
        }
        userService.delete(userService.get(userId));
    }

    public User getUser(long adminId, long userId) throws IllegalAccessException {
        if (isUnauthorized(adminId)) {
            throw new IllegalAccessException("You don't have admin rights!");
        }
        return userService.get(userId);
    }

    public List<User> listUsers(long adminId) throws IllegalAccessException {
        if (isUnauthorized(adminId)) {
            throw new IllegalAccessException("You don't have admin rights!");
        }
        return userService.listUsers();
    }
}
