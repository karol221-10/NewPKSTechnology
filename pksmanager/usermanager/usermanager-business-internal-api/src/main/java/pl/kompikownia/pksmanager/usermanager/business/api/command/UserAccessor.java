package pl.kompikownia.pksmanager.usermanager.business.api.command;

public interface UserAccessor {
    String createNewUser(String name, String surname, String login, String password, String email);
    Integer getUserCount();
    Integer getWorkerCount();
}
