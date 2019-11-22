package com.deals.services;

import com.deals.daos.UserDao;
import com.deals.models.Role;
import com.deals.models.User;
import com.deals.models.enums.Authority;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class UserService {

    private static UserService INSTANCE;
    private UserDao userDao = new UserDao();
    private RoleService roleService = RoleService.getInstance();

    public static UserService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserService();
        }
        return INSTANCE;
    }

    private static final String LETTER_REGEX = "^[a-zA-Z]*$";

    public void register(String username, String password, String confPassword, String address, String phoneNumber) {
        if (username == null || username.trim().equals("")) throw new IllegalArgumentException("Please enter username!");
        if (password == null || password.trim().equals("")) throw new IllegalArgumentException("Please enter password!");
        if (address == null || address.trim().equals("")) throw new IllegalArgumentException("Please enter address!");
        if (phoneNumber == null || phoneNumber.trim().equals("")) throw new IllegalArgumentException("Please enter phone number!");
        if (phoneNumber.length() > 10) throw new IllegalArgumentException("Phone number can't be bigger than 10 characters!");
        if (phoneNumber.matches(LETTER_REGEX)) throw new IllegalArgumentException("Phone number can't contain letters!");
        if (!confPassword.trim().equals(password.trim())) throw new IllegalArgumentException("Passwords does not match each other!");

        if (userDao.getUserByUsername(username) != null) throw new IllegalArgumentException("This username already exists!");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);

        HashSet<Role> roles = new HashSet<>();
        Role role = roleService.get(Authority.USER.role);
        roles.add(role);
        user.setRoles(roles);
        
        userDao.create(user);
    }

    public long login(String username, String password) {
        if (username == null || username.trim().equals("")) throw new IllegalArgumentException("Please enter username!");
        if (password == null || password.trim().equals("")) throw new IllegalArgumentException("Please enter password!");

        User user = userDao.searchForUser(username, password);
        if (user == null) {
            throw new IllegalArgumentException("This account dos not exist!");
        } else {
            long id = user.getId();
            return id;
        }
    }

    public void update(long id, String username, String password, String confPassword, String phoneNumber, String address) {
        if (username == null || username.trim().equals("")) throw new IllegalArgumentException("Please enter username!");
        User searchedUser = userDao.getUserByUsername(username);
        if ((searchedUser != null) && (searchedUser.getId() != id)) throw new IllegalArgumentException("This username already exists!");
        if (password == null || password.trim().equals("")) throw new IllegalArgumentException("Please enter password!");
        if (confPassword == null || confPassword.trim().equals("")) throw new IllegalArgumentException("Please confirm password!");
        if (!confPassword.trim().equals(password.trim())) throw new IllegalArgumentException("Passwords does not match each other!");
        if (phoneNumber == null || phoneNumber.trim().equals("")) throw new IllegalArgumentException("Please enter phone number!");
        if (phoneNumber.length() > 10) throw new IllegalArgumentException("Phone number can't be bigger than 10 characters!");
        if (phoneNumber.matches(LETTER_REGEX)) throw new IllegalArgumentException("Phone number can't contain letters!");
        if (address == null || address.trim().equals("")) throw new IllegalArgumentException("Please enter address!");

        User user = userDao.get(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        userDao.update(user);
    }

    public List<User> listUsers() {
        return userDao.listUsers();
    }

    public void delete(User user) {
        userDao.delete(user);
    }

    public User get(long id) {
        User user = userDao.get(id);
        if (user == null) throw new IllegalArgumentException("User doesn't exist!");
        return user;
    }

    public void update(User user) {
        userDao.update(user);
    }

}

