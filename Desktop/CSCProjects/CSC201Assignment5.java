//Will O'Brien Spring 2016 CSC201
	
import java.util.Scanner;

public class CSC201Assignment5 
{
	public static int[][] arrayNums = new int[4][4];
	public static String[][] arrayLetters = new String[4][4];
	public static boolean gameRunning = true;
	public static int xCord = 0;
	public static int xCord2 = 0;
	public static int yCord = 0;
	public static int yCord2 = 0;
	public static int roundCount = 0;
	public static void main(String args[]) throws InterruptedException
	{
		
		arrayNums[0][0] = 1;
		arrayNums[0][1] = 2;
		arrayNums[0][2] = 3;
		arrayNums[0][3] = 4;
		
		arrayNums[1][0] = 1;
		arrayNums[1][1] = 2;
		arrayNums[1][2] = 3;
		arrayNums[1][3] = 4;
		
		arrayNums[2][0] = 5;
		arrayNums[2][1] = 6;
		arrayNums[2][2] = 7;
		arrayNums[2][3] = 8;
		
		arrayNums[3][0] = 5;
		arrayNums[3][1] = 6;
		arrayNums[3][2] = 7;
		arrayNums[3][3] = 8;
		
		for(int l = 0; l < arrayLetters.length; l++)
		{
			for(int j = 0; j < arrayLetters[l].length; j++)
			{
				arrayLetters[l][j] = "X";
			}
		}
		shuffle();
		//test();
		while(gameRunning == true)
		{
			screenPrint();
			askCords();
			checkAndReplace();
			linePush();
			roundCount++;
			checkWin();
			
		}
		for(int x = 0; x < arrayNums.length; x++)
		{
			System.out.print("|");
			for(int y = 0; y < arrayNums[x].length; y++)
			{
				System.out.print(arrayLetters[x][y] + "|");
			}
			System.out.println();
			System.out.println("---------");
		}
	}
	public static void linePush()
	{
		for(int x = 0; x < 10; x++)
		{
			System.out.println();
		}
	}
	public static void screenPrint()//Output to the user the current status of the game board
	{
		for(int x = 0; x < arrayNums.length; x++)
		{
			System.out.print("|");
			for(int y = 0; y < arrayNums[x].length; y++)
			{
				System.out.print(arrayLetters[x][y] + "|");
			}
			System.out.println();
			System.out.println("---------");
		}
	}
	public static void test()//test method to ensure proper number placement. remove // above to see.
	{
		for(int x = 0; x < arrayNums.length; x++)
		{
			System.out.print("|");
			for(int y = 0; y < arrayNums[x].length; y++)
			{
				System.out.print(arrayNums[x][y] + "|");
			}
			System.out.println();
			System.out.println("---------");
		}
	}
	public static void askCords()//ask the user for co-ords. 
	{
		System.out.println("Enter your first X and Y Coordinate");
		Scanner keyboard = new Scanner(System.in);
		System.out.print("First: ");
		xCord = keyboard.nextInt();
		System.out.print("Second: ");
		yCord = keyboard.nextInt();
		System.out.println("Enter your second X and Y Coordinate");
		System.out.print("First: ");
		xCord2 = keyboard.nextInt();
		System.out.print("Second: ");
		yCord2 = keyboard.nextInt();
	}
	public static void checkAndReplace() throws InterruptedException//if the co-ords the user entered are a match it replaces the String value with the int value from the co-ords entered
	{
		if(arrayNums[xCord-1][yCord-1] == arrayNums[xCord2-1][yCord2-1])
		{
			if(!(xCord == xCord2 && yCord == yCord2))//to eliminate the possibility that the user enters the same co-ords twice in the same round
			{
			arrayLetters[xCord-1][yCord-1] = String.valueOf(arrayNums[xCord-1][yCord-1]);
			arrayLetters[xCord2-1][yCord2-1] = String.valueOf(arrayNums[xCord2-1][yCord2-1]);
			screenPrint();
			Thread.sleep(5000); // stops the game for a 5 seconds to let the user see the cards he picked.
			}
		}
		else
		{
			arrayLetters[xCord-1][yCord-1] = String.valueOf(arrayNums[xCord-1][yCord-1]);
			arrayLetters[xCord2-1][yCord2-1] = String.valueOf(arrayNums[xCord2-1][yCord2-1]);
			screenPrint();
			Thread.sleep(5000); // stops the game for a 5 seconds to let the user see the cards he picked.
			arrayLetters[xCord-1][yCord-1] = "X";
			arrayLetters[xCord2-1][yCord2-1] = "X";
		
		}
		
	}
	public static void shuffle()
	{
		int[] totalArray = new int[16];//new array to combine the 2D array
		int count = 0;
		int newCount = 0;
		for(int g = 0; g < arrayNums.length; g++)
		{
			for(int f = 0; f < arrayNums[g].length; f++)
			{
				totalArray[count] = arrayNums[g][f];//placing all 16 values in the 2D array into a 1D array
				count++;
			}
		}
		for(int x = 0; x < totalArray.length; x++)//shuffling the new 1D array. Change to shuffle as many times as you want
		{
			int newSpot = (int)(Math.random() * 15);
			int holding = totalArray[x];
			totalArray[x] = totalArray[newSpot];
			totalArray[newSpot] = holding;	
		}
		for(int z = 0; z < arrayNums.length; z++)//putting all 16 values back into the 2D array, but shuffled
		{
			for(int a = 0; a < arrayNums[z].length; a++)
			{
				arrayNums[z][a] = totalArray[newCount];
				newCount++;
			}
		}
		/*for(int y = 0; y < arrayNums.length; y++)//test loop to ensure the order of the 2D array is the same as the 1D array.
		{
			for(int j = 0; j < arrayNums[y].length; j++)
			{
				System.out.print(arrayNums[y][j] + " ");
			}
		}*/
	}
	public static void checkWin()//check the number of X's in the letter array, or lackthereof.
	{
		int letterCount = 0;
		for(int x = 0; x < arrayLetters.length; x++)
		{
			for(int y = 0; y < arrayLetters[x].length; y++)
			{
				if(!arrayLetters[x][y].equals("X"))
				{
					letterCount++;//if a spot in the array is not an X, i.e a number increase count
				}
			}
		}
		if(letterCount == 16)// if it reaches 16 that means there are no more X's in the array. Meaning all 16 spots are filled with numbers.
		{
			gameRunning = false;//stops the game from running
			System.out.println("YOU WIN!!!");
			System.out.println("It took you " + roundCount + " rounds to win!!");
		}
	}
}