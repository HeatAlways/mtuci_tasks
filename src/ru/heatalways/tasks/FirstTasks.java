package ru.heatalways.tasks;

public class FirstTasks {
    public static void main(String[] args) {
        System.out.println(convert(2));
        System.out.println(points(2, 3));
        System.out.println(footballPoints(2, 1, 0));
        System.out.println(divisibleByFive(6));
        System.out.println(and(true, false));
        System.out.println(howManyWalls(53, 2, 2));
        System.out.println(profitableGamble(0.2f, 50, 9));
        System.out.println(frames(10, 25));
        System.out.println(mod(5, 2));
    }

    public static int convert(int minutes) {
        return minutes * 60;
    }

    public static int points(int two, int three) {
        return two * 2 + three * 3;
    }

    public static int footballPoints(int winsCount, int drawsCount, int losesCount) {
        return winsCount * 3 + drawsCount;
    }

    public static boolean divisibleByFive(int num) {
        return num % 5 == 0;
    }

    public static boolean and(boolean b1, boolean b2) {
        return b1 && b2;
    }

    public static int howManyWalls(int meters, int w, int h) {
        int metersPerWall = w * h;
        return meters / metersPerWall;
    }

    /*
    - точка с запятой
    - переменная a -> b в теле
     */
    public static int squared(int b) {
        return b * b;
    }

    public static boolean profitableGamble(float prob, float prize, float pay) {
        return prob * prize > pay;
    }

    public static int frames(int fps, int minutes) {
        return minutes * 60 * fps;
    }

    public static int mod(int a, int b) {
        while (a >= b) {
            a -= b;
        }
        return a;
    }
}
