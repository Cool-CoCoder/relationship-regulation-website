package Controller.PersonalHomePage;

import entity.Person;
import service.impl.HomePageServiceImpl;

public class GetPersonInfo {
    public static Person getPerson(String tel){
        Person person = new Person();
        HomePageServiceImpl homePageService = new HomePageServiceImpl();
        person = homePageService.GetHomePageInfo(tel);
        return person;
    }
}
