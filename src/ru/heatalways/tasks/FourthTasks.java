package ru.heatalways.tasks;


import java.util.*;

public class FourthTasks {
    public static void main(String[] args) {
        // sevenBoom
        System.out.println("sevenBoom");
        System.out.println(sevenBoom(new int[]{1, 2, 3, 4, 5, 6, 7}));
        System.out.println(sevenBoom(new int[]{8, 6, 33, 100}));
        System.out.println(sevenBoom(new int[]{2, 55, 60, 97, 86}));
        System.out.println("-------------------------------------");

        // cons
        System.out.println("cons");
        System.out.println(cons(new int[]{5, 1, 4, 3, 2}));
        System.out.println(cons(new int[]{5, 1, 4, 3, 2, 8}));
        System.out.println(cons(new int[]{5, 6, 7, 8, 9, 9}));
        System.out.println("-------------------------------------");

        // unmix
        System.out.println("unmix");
        System.out.println(unmix("123456"));
        System.out.println(unmix("hTsii  s aimex dpus rtni.g"));
        System.out.println(unmix("badce"));
        System.out.println("-------------------------------------");

        // noYelling
        System.out.println("noYelling");
        System.out.println(noYelling("What went wrong?????????"));
        System.out.println(noYelling("Oh my goodness!!!"));
        System.out.println(noYelling("I just!!! can!!! not!!! believe!!! it!!!"));
        System.out.println(noYelling("Oh my goodness!"));
        System.out.println("I just cannot believe it.");
        System.out.println("-------------------------------------");

        // xPronounce
        System.out.println("xPronounce");
        System.out.println(xPronounce("Inside the box was a xylophone"));
        System.out.println(xPronounce("The x ray is excellent"));
        System.out.println(xPronounce("OMG x box unboxing video x D"));
        System.out.println("-------------------------------------");

        // largestGap
        System.out.println("largestGap");
        System.out.println(largestGap(new int[] {9, 4, 26, 26, 0, 0, 5, 20, 6, 25, 5}));
        System.out.println(largestGap(new int[] {14, 13, 7, 1, 4, 12, 3, 7, 7, 12, 11, 5, 7}));
        System.out.println(largestGap(new int[] {13, 3, 8, 5, 5, 2, 13, 6, 14, 2, 11, 4, 10, 8, 1, 9}));
        System.out.println("-------------------------------------");

        // minusMinimal
        System.out.println("minusMinimal");
        System.out.println(minusMinimal(832));
        System.out.println(minusMinimal(51));
        System.out.println(minusMinimal(7977));
        System.out.println(minusMinimal(1));
        System.out.println(minusMinimal(665));
        System.out.println(minusMinimal(149));
        System.out.println("-------------------------------------");

        // commonLastVowel
        System.out.println("commonLastVowel");
        System.out.println(commonLastVowel("Hello World!"));
        System.out.println(commonLastVowel("Watch the characters dance!"));
        System.out.println(commonLastVowel("OOI UUI EEI AAI"));
        System.out.println("-------------------------------------");

        // memeSum
        System.out.println("memeSum");
        System.out.println(memeSum(26, 39));
        System.out.println(memeSum(122, 81));
        System.out.println(memeSum(1222, 30277));
        System.out.println("-------------------------------------");

        // unrepeated
        System.out.println("unrepeated");
        System.out.println(unrepeated("teshahset"));
        System.out.println(unrepeated("hello"));
        System.out.println(unrepeated("aaaaa"));
        System.out.println(unrepeated("WWE!!!"));
        System.out.println(unrepeated("call 911"));
        System.out.println("-------------------------------------");
    }

    public static String sevenBoom(int[] nums) {
        for (int num: nums) {
            while (num > 0) {
                if (num % 10 == 7) return "Boom!";
                num /= 10;
            }
        }
        return "there is no 7 in the array";
    }

    public static boolean cons(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] != 1) return false;
        }
        return true;
    }

    public static String unmix(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i+=2) {
            if (i + 1 < str.length()) {
                stringBuilder
                        .append(str.charAt(i + 1))
                        .append(str.charAt(i));
            } else {
                stringBuilder.append(str.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

    public static String noYelling(String str) {
        if (!str.endsWith("!") && !str.endsWith("?")) return str;
        StringBuilder stringBuilder = new StringBuilder(str);
        for (int i = stringBuilder.length() - 1; i >= 0; i--) {
            char c = stringBuilder.charAt(i - 1);
            if (c == '?' || c == '!') {
                stringBuilder.deleteCharAt(i);
            } else break;
        }
        return stringBuilder.toString();
    }

    public static String xPronounce(String str) {
        String[] separatedStr = str.toLowerCase().split(" ");
        for (int i = 0; i < separatedStr.length; i++) {
            if (separatedStr[i].equals("x")) separatedStr[i] = "ecks";
            if (separatedStr[i].startsWith("x")) {
                separatedStr[i] = separatedStr[i].replaceFirst("x", "z");
            }
            if (separatedStr[i].contains("x")) {
                separatedStr[i] = separatedStr[i].replace("x", "cks");
            }
        }
        return String.join(" ", separatedStr);
    }

    public static int largestGap(int[] nums) {
        Arrays.sort(nums);
        int largestGap = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] - nums[i] > largestGap) {
                largestGap = nums[i + 1] - nums[i];
            }
        }
        return largestGap;
    }

    public static int minusMinimal(int num) {
        List<Integer> digits = new ArrayList<>();

        int numCopy = num;
        while (numCopy > 0) {
            digits.add(numCopy % 10);
            numCopy /= 10;
        }
        digits.sort(Collections.reverseOrder());

        int newNum = 0;
        for (int i = 0; i < digits.size(); i++) {
            newNum += digits.get(i) * (int) Math.pow(10, i);
        }

        return num - newNum;
    }

    public static char commonLastVowel(String str) {
        List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u', 'y');
        Map<Character, Integer> commonLastVowels = new HashMap<>();
        String[] separatedString = str.toLowerCase().split(" ");
        for (String word: separatedString) {
            for (int i = word.length() - 1; i >= 0; i--) {
                if (vowels.contains(word.charAt(i))) {
                    int newValue =
                            commonLastVowels.containsKey(word.charAt(i)) ?
                            commonLastVowels.get(word.charAt(i)) + 1 :
                            1;
                    commonLastVowels.put(word.charAt(i), newValue);
                    break;
                }
            }
        }

        return commonLastVowels.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

    public static int memeSum(int a, int b) {
        List<Integer> digits1 = new ArrayList<>();
        List<Integer> digits2 = new ArrayList<>();

        int numCopy = a;
        while (numCopy > 0) {
            digits1.add(numCopy % 10);
            numCopy /= 10;
        }

        numCopy = b;
        while (numCopy > 0) {
            digits2.add(numCopy % 10);
            numCopy /= 10;
        }

        int iterationsCount = Math.max(digits1.size(), digits2.size());
        int newNum = 0;
        int numLength = 0;
        for (int i = 0; i < iterationsCount; i++) {
            int first = i < digits1.size() ? digits1.get(i) : 0;
            int second = i < digits2.size() ? digits2.get(i) : 0;

            newNum += (first + second) * (int) Math.pow(10, numLength);

            if (first + second >= 10) numLength+=2;
            else numLength++;
        }
        return newNum;
    }

    public static String unrepeated(String str) {
        List<Character> usedChars = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c: str.toCharArray()) {
            if (!usedChars.contains(c)) {
                stringBuilder.append(c);
                usedChars.add(c);
            }
        }
        return stringBuilder.toString();
    }
}
