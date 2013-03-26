package ConnectFour;
import java.util.*;
public class StateList 
{
	Map<Board,StateList>states;
	Board board;
	public StateList()
	{
		board = new Board();
		states = new HashMap<Board, StateList>();
	}
	public StateList(Board board)
	{
		this.board = board;
		states = new HashMap<Board,StateList>();
	}
	//change to new board size
	public void generateList(Player play)
	{
		states.clear();
		for(int i = 0; i < 9; i++)
		{
			Board temp = new Board();
			board.copy(temp);
			boolean cond = temp.play(i,play);
			if(cond)
				states.put(temp,new StateList(temp));
		}
	}
	
	public String toString()
	{
		String ret = "Original: \n" + board + "\n";
		Set<Board> boards = states.keySet();
		int i = 0;
		for(Board board : boards)
		{
			ret += "Possibility " + i + "\n" + board;
			i++;
		}
		return ret;
	}
}
