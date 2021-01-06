package service.impl;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import repository.impl.PersonRepositoryImpl;
import service.LoadImageService;
import utils.JDBCUtils;

import javax.sql.DataSource;


public class LoadImageServiceImpl  implements LoadImageService {


    @Override
    public byte[] showUserCover(String tel) {
        PersonRepositoryImpl personRepository = new PersonRepositoryImpl();
        byte[] bytes = personRepository.readCover(tel);
        return bytes;
    }

    @Override
    public byte[] showUserPortrait(String tel) {
        PersonRepositoryImpl personRepository = new PersonRepositoryImpl();
        byte[] bytes = personRepository.readPor(tel);
        return bytes;
    }


}
