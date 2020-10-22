package TruckDelivery;

import java.util.*;

//The program Generates towns each run
//If you want to test it on the same town comment this code testCases.createMultipleTestCases();
//It shows all 3 approaches, their best profit, the path and the time it took to solve the problem

public class TruckDelivery
{
    private ArrayList<Integer> pickupCost;
    private ArrayList<Integer> dropoffCost;

    private BruteForce bruteForce;
    private ExactApproach exactApproach;
    private ApproximateApproach approximateApproach;

    public TruckDelivery()
    {
        pickupCost = new ArrayList<>();
        dropoffCost = new ArrayList<>();
//        testCases.createMultipleTestCases();// comment this line if want to keep one test case
//        testCases.readTestIt(pickupCost, dropoffCost);
        testCases.readFiles(pickupCost,dropoffCost,5);
        // this tests a base case that was posted on blackboard by Andrew
//        testCases.readBaseTest(pickupCost,dropoffCost);
        // this reads files from one of the 5 text files,
        // the third parameter is the file number 1-5
//        testCases.readFiles(pickupCost, dropoffCost, 2);

        bruteForce = new BruteForce(pickupCost, dropoffCost);
        exactApproach = new ExactApproach(pickupCost, dropoffCost);
        approximateApproach = new ApproximateApproach(pickupCost, dropoffCost);
    }


    public static void main(String[] args)
    {
        TruckDelivery trip = new TruckDelivery();

        System.out.println(trip.pickupCost.toString());
        System.out.println(trip.dropoffCost.toString());

        System.out.println("Exact Approach");
        long time2 = System.nanoTime();
        trip.exactApproach.exactApproach(10);
        System.out.println((double) (System.nanoTime() - time2)/1000000000);

        System.out.println("=====================================");
        System.out.println("=====================================");

        System.out.println("BruteForce Approach");
        long time1 = System.nanoTime();
        trip.bruteForce.implementBruteForce(10);
        System.out.println((double) (System.nanoTime() - time1)/1000000000);

        System.out.println("=====================================");
        System.out.println("=====================================");

        System.out.println("Approximate Approach");
        time2 = System.nanoTime();
        trip.approximateApproach.approximateApproach(10);
        System.out.println((double) (System.nanoTime() - time2)/1000000000);

/*
 The code below tests 100 cases of brute force and exact approaches
 */

//        for (int i = 0; i < 100; i++)
//        {
//            TruckDelivery trip = new TruckDelivery();
//
//            System.out.println(trip.pickupCost.toString());
//            System.out.println(trip.dropoffCost.toString());
//
//            System.out.println();
//            System.out.println("Exact Approach k = 10");
//            int ea = trip.exactApproach.exactApproach(10);
//            System.out.println("BruteForce Approach k = 10");
//            int ba = trip.bruteForce.implementBruteForce(10);
//            if (ba != ea)
//            {
//                System.out.println("STOP");
//                break;
//            }
//
//            System.out.println("===============================");
//            System.out.println("Exact Approach k = 2");
//            ea = trip.exactApproach.exactApproach(2);
//            System.out.println("BruteForce Approach k = 2");
//            ba = trip.bruteForce.implementBruteForce(2);
//
//            if (ba != ea)
//            {
//                System.out.println("STOP1");
//                break;
//            }
//            System.out.println();
//
//            if (i == 99)
//            {
//                System.out.println("Test Successful");
//            }
//        }
    }
}