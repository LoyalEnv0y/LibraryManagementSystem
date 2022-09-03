package libraryManagementSystem.library;

import libraryManagementSystem.account.Account;
import libraryManagementSystem.catalog.Catalog;
import libraryManagementSystem.users.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private final Catalog catalog;
    private final Address address;
    private final HashMap<String, User> userMap;
    private final HashMap<Integer, Account> accountMap;
    private String name;

    public Library(String name, Address address, Catalog catalog) {
        this.catalog = catalog;
        this.address = address;
        this.userMap = new HashMap<>();
        this.accountMap = new HashMap<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public Address getAddress() {
        return address;
    }

    public HashMap<String, User> getUserMap() {
        return userMap;
    }

    public HashMap<Integer, Account> getAccountMap() {
        return accountMap;
    }

    public void addUser(User user) {
        this.userMap.put(user.getId(), user);
        this.accountMap.put(user.getAccount().getAccountId(), user.getAccount());
    }

    public void addUser(List<User> users) {
        users.forEach(this::addUser);
    }

    public void addUser(Map<String, User> users) {
        this.userMap.putAll(users);
    }

    public void deleteUser(String userId) {
        deleteAccount(findAccount(userId).getAccountId());
        this.userMap.remove(userId);
    }

    public void deleteUser(User user) {
        deleteUser(user.getId());
    }

    public void deleteUser(List<User> users) {
        users.forEach(this::deleteUser);
    }

    private void deleteAccount(Integer accountId) {
        this.accountMap.remove(accountId);
    }

    public User findUser(String userId) {
        return userMap.get(userId);
    }

    public User findUser(Integer accountId) {
        return findUser(findAccount(accountId).getUserId());
    }

    public User findUser(Account account) {
        return findUser(account.getAccountId());
    }

    public Account findAccount(Integer accountId) {
        return accountMap.get(accountId);
    }

    public Account findAccount(Account account) {
        return findAccount(account.getAccountId());
    }

    public Account findAccount(User user) {
        return findAccount(user.getAccount());
    }

    public Account findAccount(String userid) {
        return findAccount(findUser(userid));
    }
}
