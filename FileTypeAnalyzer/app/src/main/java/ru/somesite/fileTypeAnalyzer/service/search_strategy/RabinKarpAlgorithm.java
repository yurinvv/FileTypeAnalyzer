import java.util.HashSet;
import java.util.Set;

public class RabinKarpAlgorithm implements SearchMethod {

    @Override
    public int search(String text, String pattern) {
        return 0;
    }

    // Hp(s) = (s0 * a^0 + s1 * a^1 + ... + s(n-1) * a^(n-1)) mod m
    // Hp(s) = (s0 * a^0 + s1 * a^1 + ... + s(n-1) * a^(n-1)) % m
    // Here, a is a constant, usually a prime number approximately
    // equal to the total number of different symbols in the alphabet;
    // m is a constant as well, usually a big prime number.
    public int PolynomialHashing(String string) {
        int a = findNumberA(string);
        int m = findNumberM(string);
        int hashCode = 0;
        char[] chars = string.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            hashCode += (int)chars[i] * (int)Math.pow(a,i);
        }

        return hashCode % m;
    }
    // A prime number approximately equal to the total number of different
    // symbols in the string
    public int findNumberA(String string) {
        Set<Character> set = new HashSet<>();
        for (char ch : string.toCharArray()) {
            set.add(ch);
        }
        return set.size();
    }

    // m is the total sum of values of all symbols in the string
    public int findNumberM(String string) {
        int sum = 0;
        for (char ch : string.toCharArray()) {
            sum += ch;
        }
        return sum;
    }
}
