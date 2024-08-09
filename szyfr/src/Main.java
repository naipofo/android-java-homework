import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static String key = "gaderypoluki";
    static HashMap<Character, Character> cypher = new HashMap<>();

    static {
        for (int i = 0; i < key.length(); i += 2) {
            cypher.put(key.charAt(i), key.charAt(i + 1));
            cypher.put(key.charAt(i + 1), key.charAt(i));
        }
    }

    static String encrypt(String data) {
        StringBuilder s = new StringBuilder();
        for (int c : data.chars().toArray()) {
            s.append(cypher.getOrDefault((char) c, (char) c));
        }
        return s.toString();
    }

    public static void main(String[] args) {
        System.out.print("Podaj dane do zaszyfrowania: ");
        System.out.println(encrypt(new Scanner(System.in).next()));
    }
}