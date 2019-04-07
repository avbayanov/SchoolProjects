package me.bayanov.rangeMain;

import me.bayanov.range.Range;

import java.util.Scanner;

public class Main {
    private static Range fillFromUser(Scanner scanner) {
        System.out.print("  from: ");
        double from = scanner.nextFloat();

        System.out.print("  to: ");
        double to = scanner.nextFloat();

        scanner.nextLine();

        return new Range(from, to);
    }

    private static void userCheckInside(Scanner scanner, Range range) {
        System.out.print("Input number: ");
        if (range.isInside(scanner.nextFloat())) {
            System.out.println("Number is inside of range");
        } else {
            System.out.println("Number is outside of range");
        }

        scanner.nextLine();
    }

    private static void userGetIntersection(Scanner scanner, Range range) {
        System.out.println("Enter another range:");
        Range anotherRange = fillFromUser(scanner);

        Range rangeIntersection = range.getIntersection(anotherRange);
        if (rangeIntersection != null) {
            System.out.printf("[%f, %f] \u2229 [%f, %f] = [%f, %f]",
                    range.getFrom(), range.getTo(),
                    anotherRange.getFrom(), anotherRange.getTo(),
                    rangeIntersection.getFrom(), rangeIntersection.getTo());
        } else {
            System.out.printf("[%f, %f] \u2229 [%f, %f] = \u2205",
                    range.getFrom(), range.getTo(),
                    anotherRange.getFrom(), anotherRange.getTo());
        }
        System.out.println();
    }

    private static void userGetUnion(Scanner scanner, Range range) {
        System.out.println("Enter another range:");
        Range anotherRange = fillFromUser(scanner);

        Range[] rangeUnion = range.getUnion(anotherRange);
        if (rangeUnion.length == 1) {
            System.out.printf("[%f, %f] \u222A [%f, %f] = [%f, %f]",
                    range.getFrom(), range.getTo(),
                    anotherRange.getFrom(), anotherRange.getTo(),
                    rangeUnion[0].getFrom(), rangeUnion[0].getTo());
        } else {
            System.out.printf("[%f, %f] \u222A [%f, %f] = [%f, %f] \u222A [%f, %f]",
                    range.getFrom(), range.getTo(),
                    anotherRange.getFrom(), anotherRange.getTo(),
                    rangeUnion[0].getFrom(), rangeUnion[0].getTo(),
                    rangeUnion[1].getFrom(), rangeUnion[1].getTo());
        }
        System.out.println();
    }

    private static void userGetDifference(Scanner scanner, Range range) {
        System.out.println("Enter another range:");
        Range anotherRange = fillFromUser(scanner);

        Range[] rangeDifference = range.getDifference(anotherRange);
        if (rangeDifference.length == 1) {

            if (rangeDifference[0].getTo() > range.getFrom()
                    && rangeDifference[0].getTo() < range.getTo()
                    || range.getTo() == anotherRange.getFrom()) {
                System.out.printf("[%f, %f] \\ [%f, %f] = [%f, %f)",
                        range.getFrom(), range.getTo(),
                        anotherRange.getFrom(), anotherRange.getTo(),
                        rangeDifference[0].getFrom(), rangeDifference[0].getTo());
            } else if (rangeDifference[0].getFrom() > range.getFrom()
                    && rangeDifference[0].getFrom() < range.getTo()
                    || range.getFrom() == anotherRange.getTo()) {
                System.out.printf("[%f, %f] \\ [%f, %f] = (%f, %f]",
                        range.getFrom(), range.getTo(),
                        anotherRange.getFrom(), anotherRange.getTo(), rangeDifference[0].getFrom(), rangeDifference[0].getTo());
            } else {
                System.out.printf("[%f, %f] \\ [%f, %f] = [%f, %f]",
                        range.getFrom(), range.getTo(),
                        anotherRange.getFrom(), anotherRange.getTo(),
                        rangeDifference[0].getFrom(), rangeDifference[0].getTo());
            }

        } else if (rangeDifference.length == 2) {
            System.out.printf("[%f, %f] \\ [%f, %f] = [%f, %f) \u222A (%f, %f]",
                    range.getFrom(), range.getTo(),
                    anotherRange.getFrom(), anotherRange.getTo(),
                    rangeDifference[0].getFrom(), rangeDifference[0].getTo(),
                    rangeDifference[1].getFrom(), rangeDifference[1].getTo());
        } else {
            System.out.printf("[%f, %f] \\ [%f, %f] = \u2205",
                    range.getFrom(), range.getTo(),
                    anotherRange.getFrom(), anotherRange.getTo());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter range:");
        Range range = fillFromUser(scanner);

        loop:
        for (; ; ) {
            System.out.println();
            System.out.println("Actions:");
            System.out.println("1) get length of range");
            System.out.println("2) check is number inside of range");
            System.out.println("3) get intersection of range with another range");
            System.out.println("4) get union of range with another range");
            System.out.println("5) get difference of range with another range");
            System.out.println("0) quit program");
            System.out.print("Your choice: ");

            switch (scanner.nextLine()) {
                case "1": {
                    System.out.printf("Range length = %f", range.getLength());
                    System.out.println();
                    break;
                }
                case "2": {
                    userCheckInside(scanner, range);
                    break;
                }
                case "3": {
                    userGetIntersection(scanner, range);
                    break;
                }
                case "4": {
                    userGetUnion(scanner, range);
                    break;
                }
                case "5": {
                    userGetDifference(scanner, range);
                    break;
                }
                default: {
                    break loop;
                }
            }
        }

    }
}
