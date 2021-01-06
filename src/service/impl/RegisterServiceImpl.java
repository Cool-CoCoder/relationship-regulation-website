package service.impl;

import entity.User;
import repository.impl.UserRepositoryImpl;
import service.RegisterService;
import utils.JDBCUtils;

public class RegisterServiceImpl implements RegisterService {

    @Override
    public int Register(User user) {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        int num = userRepository.Register(user);
        return num;
    }
}
