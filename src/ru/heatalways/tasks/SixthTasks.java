package ru.heatalways.tasks;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SixthTasks {
    public static void main(String[] args) {
        // hiddenAnagram
        System.out.println(hiddenAnagram("My world evolves in a beautiful space called Tesh.",
                "sworn love lived"));
        System.out.println(hiddenAnagram("An old west action hero actor", "Clint Eastwood"));
        System.out.println(hiddenAnagram("Mr. Mojo Rising could be a song title", "Jim Morrison"));
        System.out.println(hiddenAnagram("Banana? margaritas", "ANAGRAM"));
        System.out.println(hiddenAnagram("D e b90it->?$ (c)a r...d,,#~", "bad credit"));
        System.out.println(hiddenAnagram("Bright is the moon", "Bongo mirth"));
        System.out.println("-------------------------------------");

        // collect
        System.out.println(Arrays.toString(collect("intercontinentalisationalism", 6)));
        System.out.println(Arrays.toString(collect("strengths", 3)));
        System.out.println(Arrays.toString(collect("pneumonoultramicroscopicsilicovolcanoconiosis", 15)));
        System.out.println("-------------------------------------");

        // nicoCipher
        System.out.println(nicoCipher("myworldevolvesinhers", "tesh"));
        System.out.println(nicoCipher("andiloveherso", "tesha"));
        System.out.println(nicoCipher("mubashirhassan", "crazy"));
        System.out.println(nicoCipher("edabitisamazing", "matt"));
        System.out.println(nicoCipher("iloveher", "612345"));
        System.out.println("-------------------------------------");

        // twoProduct
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 5, 15, 3}, 45)));
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 15, 3, 5}, 45)));
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, -1, 4, 5, 6, 10, 7}, 20)));
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10)));
        System.out.println(Arrays.toString(twoProduct(new int[]{100, 12, 4, 1, 2}, 15)));
        System.out.println("-------------------------------------");

        // isExact
        System.out.println(Arrays.toString(isExact(6)));
        System.out.println(Arrays.toString(isExact(24)));
        System.out.println(Arrays.toString(isExact(125)));
        System.out.println(Arrays.toString(isExact(720)));
        System.out.println(Arrays.toString(isExact(1024)));
        System.out.println(Arrays.toString(isExact(40320)));
        System.out.println("-------------------------------------");

        // fractions
        System.out.println(fractions("0.(6)"));
        System.out.println(fractions("1.(1)"));
        System.out.println(fractions("3.(142857)"));
        System.out.println(fractions("0.19(2367)"));
        System.out.println(fractions("0.1097(3)"));
        System.out.println("-------------------------------------");

        // pilish
        System.out.println(pilish("33314444"));
        System.out.println(pilish("TOP"));
        System.out.println(pilish("X"));
        System.out.println(pilish(""));
        System.out.println("-------------------------------------");

        // generateNonconsecutive
        System.out.println(generateNonconsecutive(1));
        System.out.println(generateNonconsecutive(2));
        System.out.println(generateNonconsecutive(3));
        System.out.println(generateNonconsecutive(4));
        System.out.println("-------------------------------------");

        // isValid
        System.out.println(isValid("aabbcd"));
        System.out.println(isValid("aabbccddeefghi"));
        System.out.println(isValid("abcdefghhgfedecba"));
        System.out.println("-------------------------------------");

        // sumsUp
        System.out.println(sumsUp(new int[] { 1, 2, 3, 4, 5 }));
        System.out.println(sumsUp(new int[] { 1, 2, 3, 7, 9 }));
        System.out.println(sumsUp(new int[] { 10, 9, 7, 2, 8 }));
        System.out.println(sumsUp(new int[] { 1, 6, 5, 4, 8, 2, 3, 7 }));
    }

    public static String hiddenAnagram(String str, String anagramLettersStr) {
        str = str.toLowerCase().replaceAll("[^a-z]", "");
        anagramLettersStr = anagramLettersStr.toLowerCase().replaceAll("[^a-z]", "");

        List<Character> anagramChars = anagramLettersStr.chars()
                .mapToObj(it -> (char) it)
                .collect(Collectors.toList());

        StringBuilder resultBuilder = new StringBuilder();
        boolean isResultFound = false;

        for (int i = 0; i < str.length() && !isResultFound; i++) {
            resultBuilder = new StringBuilder();
            List<Character> tempAnagramChars = new ArrayList<>(anagramChars);
            for (int j = i; j < str.length(); j++) {
                char symbol = str.charAt(j);
                if (tempAnagramChars.contains(symbol)) {
                    resultBuilder.append(symbol);
                    tempAnagramChars.remove(Character.valueOf(symbol));
                    if (tempAnagramChars.isEmpty()) {
                        isResultFound = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        return isResultFound ? resultBuilder.toString() : "noutfond";
    }

    public static String[] collect(String str, int n) {
        int collectionSize = str.length() / n;
        String[] collection = new String[collectionSize];
        for (int i = 0; i < collectionSize; i++) {
            collection[i] = str.substring(i * n, i * n + n);
        }
        Arrays.sort(collection);
        return collection;
    }

    public static String nicoCipher(String message, String key) {
        message = message.replaceAll(" ", "");
        char[] messageChars = message.toCharArray();

        List<Character> keys = key.chars().mapToObj(it -> (char) it).collect(Collectors.toList());
        List<Character> sortedKeys = keys.stream().sorted().collect(Collectors.toList());

        Map<Integer, char[]> columns = new HashMap<>();
        int rowsCount = (int) Math.ceil(messageChars.length / (double) keys.size());

        for (int i = 0; i < keys.size(); i++) {
            char[] columnChars = new char[rowsCount];
            Arrays.fill(columnChars, ' ');
            for (int j = i; j < messageChars.length; j += keys.size()) {
                columnChars[j / keys.size()] = messageChars[j];
            }
            int index = sortedKeys.indexOf(keys.get(i));
            columns.put(index, columnChars);
            sortedKeys.set(index, ' ');
        }

        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columns.size(); j++) {
                resultBuilder.append(columns.get(j)[i]);
            }
        }

        return resultBuilder.toString();
    }

    public static int[] twoProduct(int[] arr, int n) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] * arr[i] == n) return new int[] {arr[j], arr[i]};
            }
        }
        return new int[0];
    }

    public static int[] isExact(int n) {
        try {
            return new int[] { n, isExact(n, 2) };
        } catch (Exception e) {
            return new int[0];
        }
    }

    private static int isExact(float n, float divider) {
        float result = n / divider;

        if (result > 1) return isExact(n / divider, ++divider);
        else if (result == 1) return (int)n;
        else throw new IllegalArgumentException("Первый аргумент не является факториалом!");
    }


    //----------------- FRACTIONS ------------------------
    public static String fractions(String decimalNum) {
        int commaIndex = decimalNum.indexOf('.');
        int openBracketIndex = decimalNum.indexOf('(');

        String integerPart = decimalNum.substring(0, commaIndex);
        String beforePeriodPart = decimalNum.substring(commaIndex + 1, openBracketIndex);
        String periodPart = decimalNum.substring(openBracketIndex + 1, decimalNum.length() - 1);
        String decimalPart = decimalNum
                .split("\\.")[1]
                .replaceAll("\\(", "")
                .replaceAll("\\)", "");

        int k = periodPart.length();
        int m = beforePeriodPart.length();

        int a = Integer.parseInt(decimalPart);
        int b = m != 0 ? Integer.parseInt(beforePeriodPart) : 0;

        String numerator = String.valueOf(a - b);
        String denominator = "9".repeat(k) + "0".repeat(m);
        return toCommonFraction(integerPart ,numerator + "/" + denominator);
    }

    private static String toCommonFraction(String integer, String fraction) {
        String numerator = fraction.split("/")[0];
        String denominator = fraction.split("/")[1];

        int integerNum = Integer.parseInt(integer);
        int numeratorNum = Integer.parseInt(numerator);
        int denominatorNum = Integer.parseInt(denominator);

        String newNumerator = String.valueOf(integerNum * denominatorNum + numeratorNum);
        return reduceFraction(newNumerator + "/" + denominatorNum);
    }

    private static String reduceFraction(String fraction) {
        int numeratorNum = Integer.parseInt(fraction.split("/")[0]);
        int denominatorNum = Integer.parseInt(fraction.split("/")[1]);

        int limit = Math.min(numeratorNum, denominatorNum);
        int divider = 2;

        while (divider < limit) {
            if (numeratorNum % divider == 0 && denominatorNum % divider == 0) {
                numeratorNum /= divider;
                denominatorNum /= divider;
            } else {
                divider++;
            }
        }

        return numeratorNum + "/" + denominatorNum;
    }
    //----------------------------------------------


    public static String pilish(String str) {
        if (str.isEmpty()) return str;

        int[] piDigits = new int[] {3,1,4,1,5,9,2,6,5,3,5,8,9,7,9};

        int currentIndex = 0;
        int spaceIndex = piDigits[currentIndex];
        StringBuilder stringBuilder = new StringBuilder(str);

        while (spaceIndex < stringBuilder.length()) {
            stringBuilder.insert(spaceIndex, " ");
            currentIndex++;
            spaceIndex += piDigits[currentIndex] + 1;
        }
        stringBuilder.append(lastCharOf(str).repeat(spaceIndex - stringBuilder.length()));

        return stringBuilder.toString();
    }

    private static String lastCharOf(String str) {
        return str.substring(str.length() - 1);
    }


    public static String generateNonconsecutive(int n) {
        int num = 0;
        String binaryString = Integer.toBinaryString(num);
        StringBuilder stringBuilder = new StringBuilder();

        while (binaryString.length() <= n) {
            if (!binaryString.contains("11")) {
                stringBuilder.append(resizeBinaryString(binaryString, n)).append(" ");
            }
            binaryString = Integer.toBinaryString(++num);
        }

        return stringBuilder.toString();
    }

    private static String resizeBinaryString(String binary, int n) {
        return "0".repeat(n - binary.length()) + binary;
    }


    public static String isValid(String str) {
        Map<Character, Integer> counts = new HashMap<>();

        for (Character c: str.toCharArray()) {
            counts.put(c, counts.get(c) != null ? counts.get(c) + 1 : 1);
        }

        int firstValue = (int) counts.values().toArray()[0];
        List<Integer> otherValues = counts
                .values()
                .stream()
                .filter(x -> x != firstValue)
                .collect(Collectors.toList());

        return  otherValues.size() == 1 &&
                otherValues.get(0) - firstValue == 1 ? "YES" : "NO";
    }

    public static List<List<Integer>> sumsUp(int[] input) {
        List<List<Integer>> pairs = new ArrayList<>();

        for (int i = input.length - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                if (j != i && (input[i] + input[j]) == 8) {
                    List<Integer> pair = new ArrayList<>(List.of(input[i], 8 - input[i]));
                    pair.sort(Integer::compare);
                    pairs.add(pair);
                }
            }
        }

        Collections.reverse(pairs);
        return pairs;
    }
}
