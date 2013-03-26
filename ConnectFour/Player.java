package ConnectFour;

public abstract class Player
{
	public char symbol = ' ';
	public char getChar()
	{
		return symbol;
	}
	public String toString()
	{
		return "" + symbol;
	}
	public Player()
	{
		symbol = ' ';
	}
	public Player(char symbol)
	{
		this.symbol = symbol;
	}
	public boolean equals(Object object)
	{
		if(object instanceof Player)
			return true;
		return false;
	}
}
