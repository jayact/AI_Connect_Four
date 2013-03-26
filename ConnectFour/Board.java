package ConnectFour;
import java.util.*;


public class Board implements Comparable
{
	Player[] [] spots;
	int length = 7;
	int height = 6;
	int[] amt; //this is the amount of players in any given column.

	public Board()
	{
		amt = new int[length];
		spots = new Player[height][length];
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < length; j++)
			{
				if(i != 0)
					spots[i][j] = new Empty();
				else
				{
					spots[i][j] = new Empty(Character.forDigit(j, 10));
					amt[j] = 0;
				}
				
			}
		}
	}

	public String toString()
	{
		String ret = "";
		for(int i = 0; i < height; i++)
		{
			ret += "|";
			for(int j = 0; j < length; j++)
			{
				ret += spots[i][j] + "|";
			}
			ret += "\n";
		}
		return ret;
	}
	
	public int freeSpaces()
	{
		int ret = 0;
		for(int i = 0; i < length; i++)
		{
			ret += amt[i] - length;
		}
		return ret;
	}
	//see notes
	public Player win()
	{	return null;}
	
	public boolean tie()
	{
		if(freeSpaces() == 0)
			return true;
		return false;
	}
	
	//change to a. return player, and b. check 4 spots (table?)
	private boolean checkV(int a, int b, int c)
	{
		return false;
	}
	
	public boolean play(int pos, Player play)
	{
		Player temp = spots[amt[pos]][pos];
		if(temp instanceof Empty)
		{
			spots[height - amt[pos]][pos] = play;
			amt[pos] += 1;
			return true;
		}
		return false;
	}
	
	public Player get(int x, int y)
		{	return spots[x][y];	}
	

	public void copy(Board a)
	{
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < length; j++)
			{
				a.spots[i][j] = spots[i][j];
			}
		}
	}
	
	//figure out a way to rate the boards
	public int rating()
	{	return 0;}
	
	public boolean equals(Object object)
	{
		if(!(object instanceof Board))
				return false;
		Board hold = (Board) object;
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < length; j++)
			{
				if(!(hold.spots[i][j].equals(spots[i][j])))
				return false;
			}
		}
		return true;
	}
	@Override
	public int compareTo(Object object) 
	{
		Board hold = (Board) object;
		Integer a = rating();
		return a.compareTo(hold.rating());
	}
}
