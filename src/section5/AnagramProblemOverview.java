package section5;

public class AnagramProblemOverview {

    public static void main(String[] args) {

        String string = "resultt";
        String anagram = "";
        int len = string.length();
        if (len % 2 == 0) {
            for (int i = 0; i < string.length() / 2; i++) {
                anagram = string.substring(len / 2 + i, len / 2 + i + 1) + anagram + string.substring(len / 2 - i - 1, len / 2 - i);
            }
        } else {
            anagram = string.substring(len / 2, len / 2 + 1);
            for (int i = 0; i < string.length() / 2; i++) {
                anagram = string.substring(len / 2 + i + 1, len / 2 + i + 2) + anagram + string.substring(len / 2 - i - 1, len / 2 - i);
            }
        }

        System.out.println(anagram);

    }


}
