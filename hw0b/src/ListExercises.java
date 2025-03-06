import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
    public static int sum(List<Integer> L) {
        int sum = 0;
        for (int number: L) {
            sum += number;
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> evenElements = new ArrayList<>();
        for (int element: L) {
            if (element % 2 == 0) {
                evenElements.add(element);
            }
        }
        return evenElements;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> commonElements = new ArrayList<>();
        for (int element: L1) {
            if (L2.contains(element)) {
                commonElements.add(element);
            }
        }
        return commonElements;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int occurrences = 0;
        for (String word: words) {
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == c) {
                    occurrences++;
                }
            }
        }
        return occurrences;
    }
}
