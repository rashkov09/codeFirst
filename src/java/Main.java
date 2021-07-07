import entities.Exercise;
import entities.ex1.ExOne;
import entities.ex2.ExTwo;
import entities.ex3.ExThree;
import entities.ex4.ExFour;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter exercise number:");
        int input = Integer.parseInt(sc.nextLine());

        Exercise exercise = null;

        switch (input) {
            case 1:
                exercise = new ExOne();
                break;
            case 2:
                exercise = new ExTwo();
                break;
            case 3:
                exercise = new ExThree();

                break;
            case 4:
                exercise = new ExFour();
                break;
            default:
                System.out.println("Wrong exercise number! Please try again.");
        }

        if (exercise != null) {
            exercise.run();
        }

    }

}

