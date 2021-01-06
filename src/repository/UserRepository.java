package repository;

import entity.User;


public interface UserRepository {
    public int Register(User user);

    public User Login(User user);


}
