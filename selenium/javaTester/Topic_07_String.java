package javaTester;

public class Topic_07_String {
    public static void main(String[] args) {
        String firstName = "Hoa";
        String lastName = "auto";

        String fullName = firstName + " " + lastName;
        System.out.println(fullName);

        fullName= firstName.concat(" ").concat(lastName);
        System.out.println(fullName);
        String hotelMsg = "Welcome to International Hotel";
        System.out.println(hotelMsg);
    }
}
