package ru.heatalways.tasks;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class FifthTasks {
    public static void main(String[] args) {
        // sameLetterPattern
        System.out.println(sameLetterPattern("ABAB", "CDCD"));
        System.out.println(sameLetterPattern("ABCBA", "BCDCB"));
        System.out.println(sameLetterPattern("FFGG", "CDCD"));
        System.out.println(sameLetterPattern("FFFF", "ABCD"));
        System.out.println("-------------------------------------");

        // spiderVsFly
        System.out.println(spiderVsFly("H3", "E2"));
        System.out.println(spiderVsFly("A4", "B2"));
        System.out.println(spiderVsFly("A4", "C2"));
        System.out.println("-------------------------------------");

        // digitsCount
        System.out.println(digitsCount(4666));
        System.out.println(digitsCount(544));
        System.out.println(digitsCount(121317));
        System.out.println(digitsCount(0));
        System.out.println(digitsCount(12345));
        System.out.println(digitsCount(1289396387328L));
        System.out.println("-------------------------------------");

        // totalPoints
        System.out.println(totalPoints(new String[]{"cat", "create", "sat"}, "caster"));
        System.out.println(totalPoints(new String[]{"trance", "recant"}, "recant"));
        System.out.println(totalPoints(new String[]{"dote", "dotes", "toes", "set", "dot", "dots", "sted"}, "tossed"));
        System.out.println("-------------------------------------");

        // longestRun
        System.out.println(longestRun(new int[] {1, 2, 3, 5, 6, 7, 8, 9}));
        System.out.println(longestRun(new int[] {1, 2, 3, 10, 11, 15}));
        System.out.println(longestRun(new int[] {5, 4, 2, 1}));
        System.out.println(longestRun(new int[] {3, 5, 7, 10, 15}));
        System.out.println("-------------------------------------");

        // takeDownAverage
        System.out.println(takeDownAverage(new int[] {95, 83, 90, 87, 88, 93}));
        System.out.println(takeDownAverage(new int[] {10}));
        System.out.println(takeDownAverage(new int[] {53, 79}));
        System.out.println("-------------------------------------");

        // rearrange
        System.out.println(rearrange("Tesh3 th5e 1I lov2e way6 she7 j4ust i8s."));
        System.out.println(rearrange("the4 t3o man5 Happ1iest of6 no7 birt2hday steel8!"));
        System.out.println(rearrange("is2 Thi1s T4est 3a"));
        System.out.println(rearrange("4of Fo1r pe6ople g3ood th5e the2"));
        System.out.println(rearrange(" "));
        System.out.println("-------------------------------------");

        // maxPossible
        System.out.println(maxPossible(523, 76));
        System.out.println(maxPossible(9132, 5564));
        System.out.println(maxPossible(8732, 91255));
        System.out.println("-------------------------------------");

        // timeDifference
        System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra"));
        System.out.println(timeDifference("London", "July 31, 1983 23:01", "Rome"));
        System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing"));
        System.out.println("-------------------------------------");

        // isNew
        System.out.println(isNew(3));
        System.out.println(isNew(30));
        System.out.println(isNew(321));
        System.out.println(isNew(123));
        System.out.println("-------------------------------------");
    }

    public static boolean sameLetterPattern(String str1, String str2) {
        return getPattern(str1).equals(getPattern(str2));
    }

    private static String getPattern(String str) {
        List<Character> strLetters = new ArrayList<>();
        StringBuilder patternBuilder = new StringBuilder();

        for (char c: str.toCharArray()) {
            if (!strLetters.contains(c)) {
                strLetters.add(c);
                patternBuilder.append(strLetters.size() - 1);
            } else {
                patternBuilder.append(strLetters.indexOf(c));
            }
        }

        return patternBuilder.toString();
    }

    public static String spiderVsFly(String start, String end) {
        Node startNode = new Node(start);
        Node endNode = new Node(end);
        Node currentNode = startNode;

        StringBuilder pathBuilder = new StringBuilder(startNode.getCode());

        while (!currentNode.equals(endNode)) {
            List<Node> neighbourNodes = currentNode.getNeighbourNodes();
            Node nextNode = neighbourNodes.get(0);
            double complexDistance = currentNode.getComplexDistance(nextNode, endNode);
            for (Node neighbourNode: neighbourNodes) {
                double newComplexDistance = currentNode.getComplexDistance(neighbourNode, endNode);
                if (newComplexDistance < complexDistance) {
                    complexDistance = newComplexDistance;
                    nextNode = neighbourNode;
                }
            }

            currentNode = nextNode;
            pathBuilder.append('-').append(currentNode.getCode());
        }

        return pathBuilder.toString();
    }

    public static long digitsCount(long num) {
        if (num >= 10) return 1 + digitsCount(num / 10);
        return 1;
    }

    public static int totalPoints(String[] guessedWords, String srcWord) {
        int totalSum = 0;

        List<Character> srcWordChars = srcWord.chars()
                .mapToObj(it -> (char) it)
                .collect(Collectors.toList());

        for (String guessedWord: guessedWords) {
            List<Character> tempChars = new ArrayList<>(srcWordChars);
            boolean isValid = true;

            for (char symbol: guessedWord.toCharArray()) {
                if (tempChars.contains(symbol)) tempChars.remove(Character.valueOf(symbol));
                else { isValid = false; break; }
            }

            if (isValid) {
                totalSum += switch (guessedWord.length()) {
                    case 3 -> 1;
                    case 4 -> 2;
                    case 5 -> 3;
                    case 6 -> 54;
                    default -> 0;
                };
            }
        }

        return totalSum;
    }

    public static int longestRun(int[] nums) {
        int currentRunIndex = 0;
        boolean isCurrentRunPositive = true;
        List<Integer> allLengths = new ArrayList<>(){{ add(1); }};

        for (int i = 1; i < nums.length; i++) {
            if ((nums[i] - nums[i - 1] == 1 && isCurrentRunPositive) ||
                (nums[i] - nums[i - 1] == -1 && !isCurrentRunPositive)) {

                allLengths.set(currentRunIndex, allLengths.get(currentRunIndex) + 1);
            } else {
                isCurrentRunPositive = nums[i] - nums[i - 1] > 0;
                currentRunIndex++;
                allLengths.add(1);
            }
        }

        return allLengths.stream().max(Integer::compare).orElse(1);
    }

    public static int takeDownAverage(int[] percents) {
        int sum = Arrays.stream(percents).sum();
        double avg = sum / (double) percents.length;
        double newAvg = avg - 5;
        return (int) Math.round(newAvg * (percents.length + 1) - sum);
    }

    public static String rearrange(String str) {
        List<WordPosition> words = Arrays.stream(str.split(" "))
                .map(it -> new WordPosition(
                        it.replaceAll("[1-9]",""),
                        Integer.parseInt(it.replaceAll("\\D+","")))
                )
                .collect(Collectors.toList());

        return words.stream()
                .sorted(Comparator.comparing(WordPosition::getPosition))
                .map(WordPosition::getWord)
                .collect(Collectors.joining(" "));
    }

    public static int maxPossible(int num, int replacement) {
        int result = 0;

        List<Short> numDigits = new ArrayList<>();
        while (num > 0) {
            numDigits.add(0, (short) (num % 10));
            num = num / 10;
        }

        List<Short> newDigits = new ArrayList<>();
        while (replacement > 0) {
            newDigits.add(0, (short) (replacement % 10));
            replacement = replacement / 10;
        }

        for (int i = 0; i < numDigits.size(); i++) {
            short currentDigit = numDigits.get(i);
            short newDigit = newDigits.stream().max(Short::compare).orElse((short)0);

            if (newDigit > currentDigit) {
                newDigits.remove(Short.valueOf(newDigit));
                result += newDigit * Math.pow(10, numDigits.size() - i - 1);
            }
            else result += currentDigit * Math.pow(10, numDigits.size() - i - 1);
        }

        return result;
    }

    public static String timeDifference(String cityA, String dateA, String cityB) {
        Map<String, String> timestamps = new HashMap<>(){{
            put("Los Angeles", "-08:00");
            put("New York", "-05:00");
            put("Caracas", "-04:30");
            put("Buenos Aires", "-03:00");
            put("London", "+00:00");
            put("Rome", "+01:00");
            put("Moscow", "+03:00");
            put("Tehran", "+03:30");
            put("New Delhi", "+05:30");
            put("Beijing", "+08:00");
            put("Canberra", "+10:00");
        }};

        ZoneId cityAZone = ZoneId.ofOffset("", ZoneOffset.of(timestamps.get(cityA)));
        ZoneId cityBZone = ZoneId.ofOffset("", ZoneOffset.of(timestamps.get(cityB)));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm").withZone(cityAZone);
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateA, formatter).withZoneSameInstant(cityBZone);
        return zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-M-d HH:mm"));
    }

    public static boolean isNew(int num) {
        List<Short> digits = new ArrayList<>();

        int numCopy = num;
        int numLength = 0;
        while (numCopy > 0) {
            numLength++;
            digits.add((short) (numCopy % 10));
            numCopy /= 10;
        }
        digits.sort(Collections.reverseOrder());

        int newNum = 0;
        int newNumLength = 0;
        for (int i = 0; i < digits.size(); i++) {
            short digit = digits.get(i);
            if (!(digit == 0 && i == digits.size() - 1)) newNumLength++;
            newNum += digit * (int) Math.pow(10, i);
        }

        return num == newNum || numLength != newNumLength;
    }

    private static class WordPosition {
        private String word;
        private int position;

        public WordPosition(String word, int position) {
            this.word = word;
            this.position = position;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }

    private static class Node {
        private static final List<Character> letters = new ArrayList<>(){{
            add('A'); add('B');
            add('C'); add('D');
            add('E'); add('F');
            add('G'); add('H');
        }};
        private static final int degreesBetweenRadials = 45;
        private static final double radiansBetweenRadials = Math.toRadians(degreesBetweenRadials);
        private static final double distanceBetweenIndexes = 1;
        private static final double minDistanceBetweenLetters = Math.sqrt(2 - Math.sqrt(2));

        private final char letter;
        private final int index;

        public Node(char letter, int index) {
            this.letter = letter;
            this.index = index;
        }
        public Node(String code) {
            this(code.charAt(0), Integer.parseInt(code.substring(1)));
        }

        public double getComplexDistance(Node nextNode, Node endNode) {
            return distanceTo(nextNode) + getDistanceBetween(nextNode, endNode);
        }

        public double distanceTo(Node node) {
            if (index != node.index)
                return distanceBetweenIndexes;
            else if (letter != node.letter)
                return minDistanceBetweenLetters * index;

            throw new IllegalArgumentException("Необхадимо указывать соседний узел! Ваши узлы не являются соседними. Также учтите, что есть одно допущение: H1 -> A0 не являются соседними узлами, а H1 -> H0 являются!");
        }

        public List<Node> getNeighbourNodes() {
            List<Node> nodes = new ArrayList<>();

            if (index == 0) {
                letters.forEach(newLetter -> nodes.add(new Node(newLetter, 1)));
            } else {
                if (index + 1 <= 4) nodes.add(new Node(letter, index + 1));
                if (index - 1 >= 0) nodes.add(new Node(letter, index - 1));
                nodes.add(new Node(getNextLetter(), index));
                nodes.add(new Node(getPreviousLetter(), index));
            }

            return nodes;
        }

        public String getCode() {
            return String.valueOf(letter) + index;
        }

        public static double getDistanceBetween(Node first, Node second) {
            if (first.letter == second.letter) {
                return Math.abs(first.index - second.index);
            }

            double radians = Math.abs(first.getLetterIndex() - second.getLetterIndex()) * radiansBetweenRadials;
            double realRadians = radians <= Math.PI ? radians : Math.PI * 2 - radians;

            double a = distanceBetweenIndexes * first.index;
            double b = distanceBetweenIndexes * second.index;
            return Math.sqrt(a*a + b*b - 2*a*b*Math.cos(realRadians));
        }

        private char getLetter() {
            return letter;
        }

        private int getIndex() {
            return index;
        }

        private int getLetterIndex() {
            return letters.indexOf(letter);
        }

        private char getNextLetter() {
            int letterIndex = getLetterIndex();
            int nextLetterIndex = letterIndex + 1 <= letters.size() - 1 ? letterIndex + 1 : 0;
            return letters.get(nextLetterIndex);
        }

        private char getPreviousLetter() {
            int letterIndex = getLetterIndex();
            int previousLetterIndex = letterIndex - 1 >= 0 ? letterIndex - 1 : letters.size() - 1;
            return letters.get(previousLetterIndex);
        }

        @Override
        public String toString() {
            return getCode();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(getCode(), node.getCode());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getCode());
        }
    }
}
