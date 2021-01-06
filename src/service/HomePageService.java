package service;

import entity.Person;

public interface HomePageService {
    public boolean UploadUserCover(byte[] bytes,String tel);

    public boolean UploadUserPortrait(byte[] bytes,String tel);

    public boolean UploadHomePageInfo(Person person);

    public Person GetHomePageInfo(String tel);
}
