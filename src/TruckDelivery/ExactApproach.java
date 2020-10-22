package TruckDelivery;

import java.util.*;

public class ExactApproach
{
    private ArrayList<Integer> pickupCost;
    private ArrayList<Integer> dropoffCost;
    private int numberOfTowns;

    public ExactApproach()
    {
        initializeCosts();
    }

    public ExactApproach(ArrayList<Integer> pickupCost, ArrayList<Integer> dropoffCost)
    {
        this.pickupCost = pickupCost;
        this.dropoffCost = dropoffCost;

        numberOfTowns = pickupCost.size();
    }

    private void initializeCosts()
    {
        pickupCost = new ArrayList<Integer>();
        dropoffCost = new ArrayList<Integer>();

        testCases.readFiles(pickupCost, dropoffCost, 3);
//        testCases.readBaseTest(pickupCost, dropoffCost);
//        testCases.createMultipleTestCases();
//        testCases.readTestIt(pickupCost, dropoffCost);

        numberOfTowns = pickupCost.size();
        System.out.println(pickupCost.toString());
        System.out.println(dropoffCost.toString());
    }

    private void emptyArray(String[][] s)
    {
        for (int i = 0;i<s.length;i++)
        {
            for (int j = 0;j<s[i].length;j++)
            {
                s[i][j] ="";
            }
        }
    }

    /*exact approach it starts from small subproblems and then
       combines them together
       Firstly it starts with town 0, then it adds town 1 and finds the best solution there
       then it adds town 2 and finds the best solution.
       It also has to happen k times
    */
    public int exactApproach(int k)
    {
        // this 2 dimensional array recreates a table of subproblems and k stops
        //the columns in this array represent k
        //the rows represent number of towns
        int[][] profits = new int[20][20];

        //this is the same as above array, but it stores the best path not the profit
        String[][] paths = new String[20][20];

        //this will contain the best path
        ArrayList<Integer> bestPath = new ArrayList<>();
        int bestProfit = 0;

        // this functions makes every string in paths array empty, not null
        emptyArray(paths);

        //for k iterations, skipping when k is 0 cuz all values there are 0 already
        for (int ki = 1; ki <= k; ki++)
        {
            int counter = 0; //k counter
            bestProfit = 0; //best profit for current k

            //for every town
            for (int i = 0; i < numberOfTowns; i++)
            {
                // this is the flag that will become true if a better dropoff town found and k counter < k
                boolean found = false;

                //this is the flag that will become true if a dropoff town found and k counter == k
                boolean found1 = false;
                //later it will be used to store the index of the pickup locaiton
                int temp = -1;

                //for every town before the i (dropoff) town
                for (int j = 0; j < i; j++)
                {
                    //check if k counter is already equal to k
                    // also check if previous best solution is less than current
                    if (counter == ki && dropoffCost.get(i) - pickupCost.get(j) + profits[ki - 1][j] > bestProfit)
                    {
                        profits[ki][i] = dropoffCost.get(i) - pickupCost.get(j) + profits[ki - 1][j];
                        bestProfit = profits[ki][i];
                        temp = j;
                        found1 = true;
                    }
                    // if k counter is less than k
                    //if previous best is worse
                    else if (counter < ki && dropoffCost.get(i) - pickupCost.get(j) + profits[ki][j] > bestProfit)
                    {
                        profits[ki][i] = dropoffCost.get(i) - pickupCost.get(j) + profits[ki][j];
                        bestProfit = profits[ki][i];
                        temp = j;
                        found = true;

                    }
                }

                //if found than add to the current best path and increment k counter
                if (found)
                {
                    if (paths[ki][i-1].contains(temp+"-"))
                    {
                        String s = paths[ki][i-1].split(temp+"-")[0];
                        paths[ki][i] = s +" " +temp + "-" + i + " ";
                    }
                    else
                    {
                        paths[ki][i] = paths[ki][i-1]+temp + "-" + i + " ";
                        counter++;
                    }
                }
                //if found1, meaning k counter cant go any further so, the best solution
                // is to take the path from a previous k and add current best solution
                else if (found1)
                {
                    if (paths[ki][i-1].contains(temp+"-"))
                    {
                        String s = paths[ki][i-1].split(temp+"-")[0];
                        paths[ki][i] = s +" " +temp + "-" + i + " ";
                    }
                    else
                    {
                        if (ki == 2 && i==12)
                        {
                            System.out.println();
                        }
                        paths[ki][i] = paths[ki-1][i-1] + temp + "-" + i + " ";
                    }
                }
                //if nothing is found than copy best solution from previous to current
                else {
                    if (i > 0)
                    {
                        paths[ki][i] = paths[ki][i-1];
                    }
                }

                //i>0 then i - 1 or 0
                //adding previous best solution if non found for current i
                if (profits[ki][i] == 0)
                {
                    profits[ki][i] = profits[ki][i > 0 ? i - 1 : 0];
                }
            }
        }

        //adding the best path to the array list for a better representation
        String[] s = paths[k][numberOfTowns-1].split(" ");

        for (int i = 0; i < s.length;i++)
        {
            if (s[i].isEmpty())
            {
                continue;
            }
            String[] temp = s[i].split("-");
            int pick = Integer.parseInt(temp[0]);
            int drop = Integer.parseInt(temp[1]);

            bestPath.add(pick);
            bestPath.add(drop);
        }

        System.out.println(bestPath.toString());

        //after added to the array list, be path can be printed
        printPath(bestPath);
        System.out.println(bestProfit);

        return bestProfit;
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
                System.out.println("Pickup: " + finalTowns.get(i)
                        + " at price: " + pickupCost.get(finalTowns.get(i)));
            } else
            {
                System.out.println("Dropoff: " + finalTowns.get(i)
                        + " at price: " + dropoffCost.get(finalTowns.get(i)));
            }
        }
    }

    public static void main(String[] args)
    {
        ExactApproach trip = new ExactApproach();

        System.out.println("Exact Approach");
        long time2 = System.nanoTime();
        trip.exactApproach(1);
        System.out.println(System.nanoTime() - time2);
        System.out.println("=====================================");
    }
}
