package fightinggame.springboot.restserver;

import org.springframework.stereotype.Service;

import fightinggame.dao.CRUDDAO;
import fightinggame.dao.DAOFactory;
import fightinggame.users.User;
import fightinggame.users.UserData;
import fightinggame.users.UserId;
import fightinggame.users.LoginSignup;

@Service
public class ServerService {
    private CRUDDAO<User, UserId> dao = DAOFactory.getUserDAO();

    /**
     * Try to get user from data storage
     * 
     * @param username of the corrensponding user
     * @param password of the corrensponding user
     * @return user or null
     */
    public User getUser(String username, String password) {
        return LoginSignup.logIn(username, password);
    }

    /**
     * Adds a new user from data storage
     * 
     * @param username        of the new user
     * @param password        of the new user
     * @param confirmPassword of the new user
     * @return the added user
     */
    public User postUser(String username, String password, String confirmPassword) {
        return LoginSignup.signUp(username, password, confirmPassword);
    }

    /**
     * Update user from data storage
     * @param id    of the user to update
     * @param user  of the new user values to change data storage
     * @return whether or not user was updated
     */
    public boolean updateUser(String id, String userData){
        return dao.update(new UserId(id), new User(new UserId(id), new UserData(userData)));
    }

    /**
     * Delete user from data storage
     * 
     * @param id of the user
     * @return whether or not user was updated
     */
    public boolean deleteUser(UserId id) {
        return dao.delete(id);
    }
}