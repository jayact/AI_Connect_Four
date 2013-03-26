package ConnectFour;

public class X extends Player
{
	public X()
	{
		super('X');
	}
	public boolean equals(Object object)
	{
		if(object instanceof X)
			return true;
		return false;
	}
}
