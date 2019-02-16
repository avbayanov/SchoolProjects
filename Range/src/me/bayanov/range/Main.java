package me.bayanov.range;

import java.util.Scanner;

public class Main {

    private static Range fillFromUser(Scanner scanner) {
        System.out.print("  from: ");
        float from = scanner.nextFloat();

        System.out.print("  to: ");
        float to = scanner.nextFloat();

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

    private static void userGetComplement(Scanner scanner, Range range) {
        System.out.println("Enter another range:");
        Range anotherRange = fillFromUser(scanner);

        Range[] rangeComplement = range.getComplement(anotherRange);
        if (rangeComplement.length == 1) {
            System.out.printf("[%f, %f] \\ [%f, %f] = [%f, %f]",
                    range.getFrom(), range.getTo(),
                    anotherRange.getFrom(), anotherRange.getTo(),
                    rangeComplement[0].getFrom(), rangeComplement[0].getTo());
        } else if (rangeComplement.length == 2) {
            System.out.printf("[%f, %f] \\ [%f, %f] = [%f, %f) \u222A (%f, %f]",
                    range.getFrom(), range.getTo(),
                    anotherRange.getFrom(), anotherRange.getTo(),
                    rangeComplement[0].getFrom(), rangeComplement[0].getTo(),
                    rangeComplement[1].getFrom(), rangeComplement[1].getTo());
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
        for (;;) {
            System.out.println();
            System.out.println("Actions:");
            System.out.println("1) get length of range");
            System.out.println("2) check is number inside of range");
            System.out.println("3) get intersection of range with another range");
            System.out.println("4) get union of range with another range");
            System.out.println("5) get complement of range with another range");
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
                    userGetComplement(scanner, range);
                    break;
                }
                default: {
                    break loop;
                }
            }
        }

    }
}
