package ConnectFour;

public class O extends Player
{
	public O()
	{
		super('O');
	}
	public boolean equals(Object object)
	{
		if(object instanceof O)
			return true;
		return false;
	}
}
