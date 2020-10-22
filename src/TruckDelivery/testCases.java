package TruckDelivery;

import java.io.*;
import java.util.ArrayList;
/*
This class generates different test cases for the algorithm
 */
public class testCases
{
    //This class creates test cases where start is the start is the minimum number of towns
    //End is the range of the towns
    public static void creatingTestCases(int start, int end)
    {
        FileWriter w;
        for (int i = 0; i < 5; i++)
        {
            try
            {
                w = new FileWriter("test" + (i + 1) + ".txt");

                int numOfTowns = (int) Math.round(Math.random() * start + end);

                w.write("" + numOfTowns + "\n");

                for (int k = 0; k < numOfTowns; k++)
                {
                    int pickup = (int) Math.round(Math.random() * 80 + 100) / 10 * 10;
                    int dropoff = (int) Math.round(Math.random() * 70 + 80) / 10 * 10;

                    while (dropoff > pickup)
                    {
                        dropoff = (int) Math.round(Math.random() * 70 + 80) / 10 * 10;
                    }

                    w.write("" + pickup + "\n");
                    w.write("" + dropoff + "\n");
                }

                w.close();

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("Tests were created");
    }

    //This method reads towns from a text file and saves them into the arrays
    private static void read (ArrayList<Integer> pickup, ArrayList<Integer> dropoff, String text)
    {
        File file = new File(text);
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;

            st = br.readLine();

            int numTowns = Integer.parseInt(st);

            for (int i = 0; i < numTowns * 2; i++)
            {
                if ((st = br.readLine()) != null)
                {
                    int n = Integer.parseInt(st);

                    if (i % 2 == 0)
                    {
                        pickup.add(n);
                    } else
                    {
                        dropoff.add(n);
                    }
                }
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //this function reads the base test, the one that Andrew posted on blackboard
    public static void readBaseTest(ArrayList<Integer> pickup, ArrayList<Integer> dropoff)
    {
        read(pickup,dropoff,"baseTest.txt");
    }

    //This function reads a certain file
    public static void readFiles(ArrayList<Integer> pickup, ArrayList<Integer> dropoff, int fileNum)
    {
        String st = "test" + fileNum + ".txt";

        read(pickup,dropoff,st);
    }

    //this function was created to conduct multiple
    // tests and save them in a single file each time
    public static void createMultipleTestCases()
    {
        FileWriter w;
        {
            try
            {
                w = new FileWriter("testIt.txt");

                int numOfTowns = (int) Math.round(Math.random() * 7 + 8);

                w.write("" + numOfTowns + "\n");

                for (int k = 0; k < numOfTowns; k++)
                {
                    int pickup = (int) Math.round(Math.random() * 80 + 100) / 10 * 10;
                    int dropoff = (int) Math.round(Math.random() * 70 + 80) / 10 * 10;

                    while (dropoff > pickup)
                    {
                        dropoff = (int) Math.round(Math.random() * 70 + 80) / 10 * 10;
                    }

                    w.write("" + pickup + "\n");
                    w.write("" + dropoff + "\n");
                }

                w.close();

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("Test was created");
    }

    //reads the testIt.txt file
    public static void readTestIt(ArrayList<Integer> pickup, ArrayList<Integer> dropoff)
    {
        read(pickup,dropoff, "testIt.txt");
    }

    //If you run this class it will generate 8 to 15 towns in files test1 to test5
    public static void main(String args[])
    {
        testCases.creatingTestCases(8,7);
    }
}
