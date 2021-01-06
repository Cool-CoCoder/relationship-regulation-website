package service.impl;

import entity.Person;
import repository.impl.PersonRepositoryImpl;
import service.HomePageService;
import utils.JDBCUtils;

public class HomePageServiceImpl implements HomePageService {
    @Override
    public boolean UploadUserCover(byte[] bytes,String tel) {
        int num = 0;
        PersonRepositoryImpl personRepository = new PersonRepositoryImpl();
        num = personRepository.UploadUserCover(bytes,tel);
        if(num != 0){//判断是否更新成功
            System.out.println("上传用户个人信息成功~");
            return true;
        } else {
            System.out.println("上传用户个人信息失败！");
            return false;
        }

    }

    @Override
    public boolean UploadUserPortrait(byte[] bytes, String tel) {
        int num = 0;
        PersonRepositoryImpl personRepository = new PersonRepositoryImpl();
        num = personRepository.UploadUserPor(bytes,tel);

        if(num != 0){//判断是否更新成功
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean UploadHomePageInfo(Person person) {
        int num = 0;
        PersonRepositoryImpl personRepository = new PersonRepositoryImpl();
        num = personRepository.UploadPersonInfo(person);
        if(num == 0){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Person GetHomePageInfo(String tel) {
        PersonRepositoryImpl personRepository = new PersonRepositoryImpl();
        Person person = personRepository.GetPersonInfo(tel);
        return person;
    }
}
