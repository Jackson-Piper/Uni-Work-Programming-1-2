import java.util.*;
public class Main
{

  public static void main (String[]args)
  {
    Scanner sc = new Scanner (System.in);

      System.out.println ("How many days were you recording for?");
    int length; 
    length = sc.nextInt();
      sc.nextLine();
    int arrLength = length;
    if (length < 0);
    {
      System.out.println
	("Error input invalid. Please enter a positive number (i.e 3, 5, 8)");
      length = sc.nextInt ();
      sc.nextLine ();
    }
    int cases[] = new int[length];
    for (int x = 0; x < arrLength; x++)
      {
	System.out.println ("Please enter the number of cases for day " +
			    (x + 1) + ": ");

	cases[x] = sc.nextInt();
	sc.nextLine();

	if (cases[x] < 0)
	  {
	    System.out.println ("Error please enter a positive number:");
	    cases[x] = sc.nextInt();
	    sc.nextLine();
	  }

      }
    for (int i = 0; i < arrLength; i++)
      {
	System.out.print (cases[i] + ", ");
      }
    int max = 0;
    int min = 99999;
    for (int i = 0; i < arrLength; i++)
      {
	if (max < cases[i])
	  {
	    max = cases[i];
	  }
	if (min > cases[i])
	  {
	    min = cases[i];
	  }
      }



    int counter = 0;
    int avgTemp = 0;
    for (int i = 0; i < arrLength; i++)
      {

	avgTemp += cases[i];
	counter++;
      }
    int avg;
    avg = avgTemp / counter;



    System.out.println ("\nMax case numbers: " + max);
    System.out.println ("Min case numbers: " + min);
    System.out.println ("Average no. of cases: " + avg);



    sc.close();

  }


}
