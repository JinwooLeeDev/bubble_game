package demo;

public class PersonMain {
    public static void main(String[] args) {
        Person person = new Person("홍", 20);
        System.out.println(person.getAge());
        person.setName("이순신");
        System.out.println(person.toString());
    }
}
