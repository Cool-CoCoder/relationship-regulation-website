package service;

import entity.User;

public interface LoginService {
    public boolean Login(User user);

    //根据user的手机号返回完整的user信息
    public User Search(User user);
}
