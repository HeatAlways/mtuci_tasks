package ru.heatalways.tasks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ThirdTasks {
    public static void main(String[] args) {
        // millionsRounding
        System.out.println("millionsRounding");
        System.out.println(millionsRounding(new HashMap<String, Integer>(){{
            put("Nice", 942208);
            put("Abu Dhabi", 1482816);
            put("Naples", 2186853);
            put("Vatican City", 572);
        }}));
        System.out.println("-------------------------------------");

        // otherSides
        System.out.println("otherSides");
        System.out.println(Arrays.toString(otherSides(1)));
        System.out.println(Arrays.toString(otherSides(12)));
        System.out.println(Arrays.toString(otherSides(2)));
        System.out.println(Arrays.toString(otherSides(3)));
        System.out.println("-------------------------------------");

        // rps
        System.out.println("rps");
        System.out.println(rps("rock", "paper"));
        System.out.println(rps("paper", "rock"));
        System.out.println(rps("paper", "scissors"));
        System.out.println(rps("scissors", "scissors"));
        System.out.println(rps("scissors", "paper"));
        System.out.println("-------------------------------------");

        // warOfNumber
        System.out.println("warOfNumber");
        System.out.println(warOfNumber(new int[]{2, 8, 7, 5}));
        System.out.println(warOfNumber(new int[]{12, 90, 75}));
        System.out.println(warOfNumber(new int[]{5, 9, 45, 6, 2, 7, 34, 8, 6, 90, 5, 243}));
        System.out.println("-------------------------------------");

        // reverseCase
        System.out.println("reverseCase");
        System.out.println(reverseCase("Happy Birthday"));
        System.out.println(reverseCase("MANY THANKS"));
        System.out.println(reverseCase("sPoNtAnEoUs"));
        System.out.println("-------------------------------------");

        // inatorInator
        System.out.println("inatorInator");
        System.out.println(inatorInator("Shrink"));
        System.out.println(inatorInator("Doom"));
        System.out.println(inatorInator("EvilClone"));
        System.out.println("-------------------------------------");

        // doesBrickFit
        System.out.println("doesBrickFit");
        System.out.println(doesBrickFit(1, 1, 1, 1, 1));
        System.out.println(doesBrickFit(1, 2, 1, 1, 1));
        System.out.println(doesBrickFit(1, 2, 2, 1, 1));
        System.out.println("-------------------------------------");

        // totalDistance
        System.out.println("totalDistance");
        System.out.println(totalDistance(70, 7, 0, false));
        System.out.println(totalDistance(36.1, 8.6, 3, true));
        System.out.println(totalDistance(55.5, 5.5, 5, false));
        System.out.println("-------------------------------------");

        // mean
        System.out.println("mean");
        System.out.println(mean(new int[]{1, 0, 4, 5, 2, 4, 1, 2, 3, 3, 3}));
        System.out.println(mean(new int[]{2, 3, 2, 3}));
        System.out.println(mean(new int[]{3, 3, 3, 3}));
        System.out.println("-------------------------------------");

        // parityAnalysis
        System.out.println("parityAnalysis");
        System.out.println(parityAnalysis(243));
        System.out.println(parityAnalysis(12));
        System.out.println(parityAnalysis(3));
        System.out.println("-------------------------------------");
    }

    public static Map<String, Integer> millionsRounding(Map<String, Integer> cities) {
        return cities.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> Math.round(entry.getValue() / 1_000_000f) * 1_000_000
                ));
    }



    public static double[] otherSides(double a) {
        double c = a / (Math.sin(Math.PI / 6));
        double b = Math.sqrt(c * c - a * a);
        return new double[] {
                roundTo2Decimals(c),
                roundTo2Decimals(b)
        };
    }

    public static double roundTo2Decimals(double value) {
        return Math.round(value * 100) / 100d;
    }



    public static String rps(String firstPlayerTurnStr, String secondPlayerTurnStr) {
        RPSTurn firstPlayerTurn = RPSTurn.getRPSTurn(firstPlayerTurnStr);
        RPSTurn secondPlayerTurn = RPSTurn.getRPSTurn(secondPlayerTurnStr);

        if (firstPlayerTurn == secondPlayerTurn) return "TIE";
        if (firstPlayerTurn.isBeats(secondPlayerTurn)) return "Player 1 wins";
        else return "Player 2 wins";
    }

    private enum RPSTurn {
        PAPER, ROCK, SCISSORS;

        public boolean isBeats(RPSTurn anotherTurn) {
            if (this == ROCK && anotherTurn == SCISSORS) return true;
            else if (this == PAPER && anotherTurn == ROCK) return true;
            else return this == SCISSORS && anotherTurn == PAPER;
        }

        public static RPSTurn getRPSTurn(String turn) {
            switch (turn) {
                case "rock":
                    return ROCK;
                case "paper":
                    return PAPER;
                case "scissors":
                    return SCISSORS;
                default:
                    return null;
            }
        }
    }


    public static int warOfNumber(int[] numbers) {
        int evensSum = Arrays.stream(numbers).filter(x -> x % 2 == 0).sum();
        int oddsSum = Arrays.stream(numbers).sum() - evensSum;
        return Math.abs(evensSum - oddsSum);
    }

    public static String reverseCase(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c: str.toCharArray()) {
            stringBuilder.append(
                    Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c)
            );
        }
        return stringBuilder.toString();
    }

    public static String inatorInator(String str) {
        char[] vowels = new char[] {'a', 'e', 'i', 'o', 'u', 'y'};
        boolean lastIsVowel = false;
        for (char c : vowels) {
            if (c == str.toCharArray()[str.length() - 1]) {
                lastIsVowel = true;
            }
        }
        if (lastIsVowel) return str + "-inator " + str.length() + "000";
        else return str + "inator " + str.length() + "000";
    }

    public static boolean doesBrickFit(int a, int b, int c, int w, int h) {
        int holeSize = w * h;
        return (a * b) <= holeSize || (b * c) <= holeSize || (c * a) <= holeSize;
    }

    public static double totalDistance(
            double fuel,
            double consumption,
            int passengersCount,
            boolean hasAirConditioner
    ) {
        if (hasAirConditioner) consumption *= 1.1;
        consumption *= Math.pow(1.05, passengersCount);
        return fuel / (consumption / 100);
    }

    public static double mean(int[] nums) {
        return Arrays.stream(nums).average().orElse(0);
    }

    public static boolean parityAnalysis(int num) {
        boolean isEven = num % 2 == 0;
        int digitsSum = 0;

        while (num > 0) {
            digitsSum += num % 10;
            num /= 10;
        }

        return isEven == (digitsSum % 2 == 0);
    }
}
