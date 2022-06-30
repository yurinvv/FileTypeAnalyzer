import java.util.HashSet;
import java.util.Set;

/**
 * 1) Calculate the hash value for a pattern.
 * 2) Moving along the text (from right to left), calculate the
 * hash value for the current substring of the text using the rolling hash property.
 * 3) If hash values for the pattern and the current substring are not equal,
 * move to the next substring. Otherwise, perform a symbol-by-symbol comparison
 * of the strings. If the strings are indeed equal, add the current index to a list of occurrences.
 * 4) Repeat steps 2-3 until all substrings of the text are processed. Then, return a list of all occurrences.
 */

public class RabinKarpAlgorithm implements SearchMethod {

    @Override
    public int search(String text, String pattern) {
        if (pattern.length() <= text.length()) {
            int patternHash = polynomialHashing(pattern);
            int substringHash = polynomialHashing(text.substring(0, pattern.length()));

            for (int i = text.length() - 1; i >= pattern.length() - 1; i--) {

            }
        }
        return -1;
    }

    //Hp(Si * S(j+1) * ... * S(j-1)) = ( (Hp( S(i+1) * S(i+2) * ... * Sj ) - Sj * a ^ (j-i-1) ) * a + Si) % m
    //Rolling hashing

    // Hp(s) = (s0 * a^0 + s1 * a^1 + ... + s(n-1) * a^(n-1)) mod m
    // Hp(s) = (s0 * a^0 + s1 * a^1 + ... + s(n-1) * a^(n-1)) % m
    // Here, a is a constant, usually a prime number approximately
    // equal to the total number of different symbols in the alphabet;
    // m is a constant as well, usually a big prime number.
    public int polynomialHashing(String string) {
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
