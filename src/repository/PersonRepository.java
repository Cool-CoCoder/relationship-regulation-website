package repository;

import entity.Person;

public interface PersonRepository {
    public byte[] readCover(String tel);

    public byte[] readPor(String tel);

    public int UploadPersonInfo(Person person);

    public Person GetPersonInfo(String tel);

    public int UploadUserCover(byte[] bytes,String tel);

    public int UploadUserPor(byte[] bytes,String tel);
}
