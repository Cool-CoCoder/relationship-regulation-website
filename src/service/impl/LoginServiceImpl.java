package service.impl;

import entity.User;
import repository.impl.UserRepositoryImpl;
import service.LoginService;
import utils.JDBCUtils;

public class LoginServiceImpl implements LoginService {

    @Override
    public boolean Login(User user) {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        User user1 = userRepository.Login(user);
        if(user1 == null){
            return false;
        } else{
            return true;
        }
    }

    @Override
    public User Search(User user) {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        User user1 = userRepository.Login(user);
        return user1;
    }
}
