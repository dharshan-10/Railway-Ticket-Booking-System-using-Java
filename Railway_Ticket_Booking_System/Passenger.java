public class Passenger {
    static int id = 1;
    String name;
    int age;
    String berthPreference = new String();
    String allotted;
    int passengerid;
    int number;

    public Passenger(String name, int age, String berthPreference) {
        this.name = name;
        this.age = age;
        this.berthPreference = berthPreference;
        passengerid = id++;
        number = -1;
        allotted = "";
    }
}