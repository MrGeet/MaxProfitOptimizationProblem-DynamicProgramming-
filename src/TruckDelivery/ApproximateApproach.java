package TruckDelivery;

import com.sun.jdi.IntegerValue;

import java.lang.reflect.Array;
import java.util.*;

public class ApproximateApproach
{
    private ArrayList<Integer> pickupCost;
    private ArrayList<Integer> dropoffCost;
    private int numberOfTowns;

    public ApproximateApproach()
    {
        initializeCosts();
    }

    public ApproximateApproach(ArrayList<Integer> pickupCost, ArrayList<Integer> dropoffCost)
    {
        this.pickupCost = pickupCost;
        this.dropoffCost = dropoffCost;

        numberOfTowns = pickupCost.size();
    }

    private void initializeCosts()
    {
        pickupCost = new ArrayList<Integer>();
        dropoffCost = new ArrayList<Integer>();

        testCases.readFiles(pickupCost,dropoffCost,2);
//        testCases.readBaseTest(pickupCost, dropoffCost);
//
//        testCases.readTestIt(pickupCost,dropoffCost);
        numberOfTowns = pickupCost.size();
        System.out.println(pickupCost.toString());
        System.out.println(dropoffCost.toString());
    }

    /*
    This approach checks every pick up town and then looks at the dropoff price
     of the next 2 towns, it drops the item in the city where drop off is highest,
     if none can be found then it takes next pick up town and performs the same operation.
     Then if say town 2 has pick up price 140, it found that town 4 had drop off at 150
     it adds 10 to the final profit and saves 10 in prevP, so that later if town 3 had pickup price
     at 110 it can dropoff at town 4, and get a profit of 40. Then it checks if prevP lower than current
     p, in this case it is, then it just subtracts prevP from finalProfit and adds p.
     */
    public void approximateApproach(int k)
    {
        ArrayList<Integer> sortedPickup = (ArrayList<Integer>) pickupCost.clone();
        ArrayList<Integer> sortedBenefit = (ArrayList<Integer>) dropoffCost.clone();
        ArrayList<Integer> path = new ArrayList<>();
        ArrayList<Integer> efficientPath = new ArrayList<>();

        int prevP = 0;
        int finalProfit = 0;
        int temp = -1;
        int kCounter = 0;

        for (int i = 0; i < numberOfTowns; i++)
        {
            int p = 0;
            for (int j = i + 1; j < i+3; j++)
            {
                if (j < numberOfTowns)
                {
                    if (sortedPickup.get(i) < sortedBenefit.get(j)
                            && p < sortedBenefit.get(j) - sortedPickup.get(i))
                    {
                        if (p>0)
                        {
                            path.remove(path.size()-1);
                            path.remove(path.size()-1);
                            kCounter--;
                        }
                        p = sortedBenefit.get(j) - sortedPickup.get(i);

                        if (temp == j && p > prevP)
                        {
                            finalProfit -= prevP;
                            path.remove(path.size()-1);
                            path.remove(path.size()-1);
                            kCounter--;
                        }
                        path.add(i);
                        path.add(j);
                        kCounter++;
                        temp = j;
                        i=j;
                    }
                }
            }
            efficientPath = (ArrayList<Integer>) path.clone();
            finalProfit += p;
            if (kCounter>=k)
            {
                break;
            }

            prevP = p;
        }

        printPath(efficientPath);
        System.out.println(finalProfit);
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
        ApproximateApproach trip = new ApproximateApproach();

        System.out.println("Approximate Approach");
        long time2 = System.nanoTime();
        trip.approximateApproach(3);
        System.out.println(System.nanoTime() - time2);
        System.out.println("=====================================");
    }
}
