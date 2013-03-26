package ConnectFour;

public class Empty extends Player
{
	public Empty()
	{
		super(' ');
	}
	public Empty(char a)
	{
		super(a);
	}
	public boolean equals(Object object)
	{
		if(object instanceof Empty)
			return true;
		return false;
	}
}
