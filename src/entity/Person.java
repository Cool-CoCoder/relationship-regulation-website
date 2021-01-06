package entity;

public class Person {
    private String tel;
    private String sex;
    private String intro;
    private String work;
    private String location;

    public Person() {
    }

    public Person(String tel, String sex, String intro, String work, String location) {
        this.tel = tel;
        this.sex = sex;
        this.intro = intro;
        this.work = work;
        this.location = location;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Person{" +
                "tel='" + tel + '\'' +
                ", sex='" + sex + '\'' +
                ", intro='" + intro + '\'' +
                ", work='" + work + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
