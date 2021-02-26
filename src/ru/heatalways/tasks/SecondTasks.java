package ru.heatalways.tasks;

import java.util.Arrays;

public class SecondTasks {
    public static void main(String[] args) {
        // oppositeHouse
        System.out.println("oppositeHouse:");
        System.out.println(oppositeHouse(1, 3));
        System.out.println(oppositeHouse(2, 3));
        System.out.println(oppositeHouse(3, 5));
        System.out.println(oppositeHouse(5, 46));
        System.out.println("-------------------------------------");

        // name shuffle
        System.out.println("nameShuffle:");
        System.out.println(nameShuffle("Donald Trump"));
        System.out.println("-------------------------------------");

        // discount
        System.out.println("discount:");
        System.out.println(discount(1500, 50));
        System.out.println("-------------------------------------");

        // difference
        System.out.println("differenceMaxMin:");
        System.out.println(differenceMaxMin(new int[] {10, 4, 1, 4, -10, -50, 32, 21}));
        System.out.println("-------------------------------------");

        // equal
        System.out.println("equal:");
        System.out.println(equal(3, 4, 3));
        System.out.println(equal(1, 1, 1));
        System.out.println(equal(3, 4, 1));
        System.out.println("-------------------------------------");

        // reverse
        System.out.println("reverse:");
        System.out.println(reverse("Hello World!"));
        System.out.println(reverse("The quick brown fox."));
        System.out.println(reverse("Edabit is really helpful!"));
        System.out.println("-------------------------------------");

        // programmers
        System.out.println("programmers:");
        System.out.println(programmers(147, 33, 526));
        System.out.println(programmers(33, 72, 74));
        System.out.println(programmers(1, 5, 9));
        System.out.println("-------------------------------------");

        // getXO
        System.out.println("getXO:");
        System.out.println(getXO("ooxx"));
        System.out.println(getXO("xooxx"));
        System.out.println(getXO("ooxXm"));
        System.out.println(getXO("zpzpzpp"));
        System.out.println("-------------------------------------");

        // bomb
        System.out.println("bomb:");
        System.out.println(bomb("There is a bomb."));
        System.out.println(bomb("Hey, did you think there is a BOMB?"));
        System.out.println(bomb("This goes boom!!!"));
        System.out.println("-------------------------------------");

        // sameAscii
        System.out.println("sameAscii:");
        System.out.println(sameAscii("a", "a"));
        System.out.println(sameAscii("AA", "B@"));
        System.out.println(sameAscii("EdAbIt", "EDABIT"));
        System.out.println("-------------------------------------");
    }

    public static int oppositeHouse(int houseNum, int streetLength) {
        return streetLength * 2 - (houseNum - 1);
    }

    public static String nameShuffle(String fullName) {
        String[] nameParts = fullName.split(" ");
        return nameParts[1] + " " + nameParts[0];
    }

    public static double discount(int price, int percent) {
        return price - price * (percent / 100d);
    }

    public static int differenceMaxMin(int[] nums) {
        Arrays.sort(nums);
        int min = nums[0];
        int max = nums[nums.length - 1];
        return max - min;
    }

    public static int equal(int a, int b, int c) {
        int count = 0;
        if (a == b) count++;
        if (b == c) count++;
        if (c == a) count++;
        return count == 1 ? count + 1 : count;
    }

    public static String reverse(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char symbol: str.toCharArray()) {
            stringBuilder.insert(0, symbol);
        }
        return stringBuilder.toString();
    }

    public static int programmers(int firstSalary, int secondSalary, int thirdSalary) {
        return differenceMaxMin(new int[]{ firstSalary, secondSalary, thirdSalary });
    }

    public static boolean getXO(String str) {
        str = str.toLowerCase();
        if (!str.contains("x") && !str.contains("o")) return true;
        long xCount = str.chars().filter(it -> it == 'x').count();
        long oCount = str.chars().filter(it -> it == 'o').count();
        return xCount == oCount;
    }

    public static String bomb(String str) {
        str = str.toLowerCase();
        if (str.contains("bomb")) return "DUCK!";
        else return "Relax, there's no bomb.";
    }

    public static boolean sameAscii(String str1, String str2) {
        int asciiSum1 = str1.chars().sum();
        int asciiSum2 = str2.chars().sum();
        return asciiSum1 == asciiSum2;
    }
}
