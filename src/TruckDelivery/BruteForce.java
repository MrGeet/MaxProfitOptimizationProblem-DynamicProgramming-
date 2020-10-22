package TruckDelivery;

import com.sun.jdi.IntegerValue;

import java.lang.reflect.Array;
import java.util.*;

public class BruteForce
{
    private ArrayList<Integer> pickupCost;
    private ArrayList<Integer> dropoffCost;
    private HashMap<Integer, Integer> profitControl;

    private ArrayList<Integer> path = new ArrayList<>();
    //    private ArrayList<int[]> path = new ArrayList<int[]>();
//    private ArrayList<int[]> efficientPath = new ArrayList<int[]>();
    private ArrayList<Integer> efficientPath = new ArrayList<>();
    private int biggestProfit = 0;
    private int numberOfTowns;
    private boolean recursionEnded = false;
    private int k = 0;
    private int counterK = 0;

    public BruteForce(ArrayList<Integer> pickupCost, ArrayList<Integer> dropoffCost)
    {
        this.pickupCost = pickupCost;
        this.dropoffCost = dropoffCost;

        numberOfTowns = pickupCost.size();
        profitControl = new HashMap<>();

        //base case where first 0 is a town and second is the profit
        profitControl.put(0, 0);
    }

    public BruteForce()
    {
        initializeCosts();
    }

    private void initializeCosts()
    {
        pickupCost = new ArrayList<Integer>();
        dropoffCost = new ArrayList<Integer>();

//        testCases.createMultipleTestCases();
//        testCases.readTestIt(pickupCost, dropoffCost);
        testCases.readFiles(pickupCost,dropoffCost,2);
//        testCases.readBaseTest(pickupCost, dropoffCost);

        numberOfTowns = pickupCost.size();
        profitControl = new HashMap<>();

        //base case where first 0 is a town and second is the profit
        profitControl.put(0, 0);

        System.out.println(pickupCost.toString());
        System.out.println(dropoffCost.toString());
    }

    //This is a recursive function that has 2 iterations inside
    //firstly it iterates through 0-1 and then recursively 1-4 and keeps going
    //until it finds all the possible paths to find the best profit
    private void bruteForce(int startTown, int endTown)
    {
        //this is the profit in the current function that will be gained
        int p = 0;

        for (int pickup = startTown; pickup <= endTown - 1; pickup++)
        {
            for (int dropoff = pickup + 1; dropoff <= endTown; dropoff++)
            {
                if (recursionEnded)
                {
                    //if the recursion ended than assign p to a previous town profit
                    p = profitControl.get(startTown);
                    //then remove towns from the path
                    int startTownFirst = path.indexOf(startTown);
                    for (int i = startTownFirst; i < path.size(); i++)
                    {
                        if (startTownFirst == 0)
                        {
                            path.remove(startTownFirst);
                            path.remove(startTownFirst);
                        } else
                        {
                            path.remove(startTownFirst + 1);
                            path.remove(startTownFirst + 1);
                        }
                        //decrement k counter
                        counterK--;
                    }
                    recursionEnded = false;
                }

                //if k counter is less than k
                if (counterK < k)
                {
                    p -= pickupCost.get(pickup);
                    p += dropoffCost.get(dropoff);
                    counterK++;

                    path.add(pickup);
                    path.add(dropoff);

//                    System.out.println(path.toSring());
//                    printPath(path);

                    //if current biggest profit than p than p is the biggest profit
                    if (biggestProfit < p)
                    {
                        efficientPath = (ArrayList<Integer>) path.clone();
                        biggestProfit = p;
                    }
                    profitControl.put(dropoff, p);

                    bruteForce(dropoff, endTown);
                }


            }
        }
        //if function recursion ended than turn this flag to true
        recursionEnded = true;
    }

    /*
    implements the brute force and returns the highest profit
     */
    public int implementBruteForce(int k)
    {
        this.k = k;
        biggestProfit = 0;

        if (k > 0)
        {
            bruteForce(0, numberOfTowns - 1);
            System.out.println(efficientPath.toString());
            printPath(efficientPath);
            System.out.println("Biggest profit is " + biggestProfit);
            return biggestProfit;
        }
        return 0;
    }

    //The print path function takes the arraylist
    //and then prints the path the truck took to get the highest benefit
    //the even indexes of the array list are pickup towns
    //the odd indexes are dropoff towns
    private void printPath(ArrayList<Integer> finalTowns)
    {
        for (int i = 0; i < finalTowns.size(); i++)
        {
            if (i % 2 == 0)
            {
                if (i < numberOfTowns - 2 &&
                        pickupCost.get(finalTowns.get(i)) < dropoffCost.get(finalTowns.get(i + 1)))
                {
                    System.out.println("Pickup: " + finalTowns.get(i)
                            + " at price: " + pickupCost.get(finalTowns.get(i)));
                }
            } else
            {
                if (i > 0 &&
                        pickupCost.get(finalTowns.get(i - 1)) < dropoffCost.get(finalTowns.get(i)))
                {
                    System.out.println("Dropoff: " + finalTowns.get(i)
                            + " at price: " + dropoffCost.get(finalTowns.get(i)));
                }

            }
        }
    }

    public static void main(String[] args)
    {
        BruteForce trip = new BruteForce();

        System.out.println("=====================================");
        System.out.println("BruteForce Approach");
        long time1 = System.nanoTime();
        trip.implementBruteForce(2);
        System.out.println(System.nanoTime() - time1);
    }
}

