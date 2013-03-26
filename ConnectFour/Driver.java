package ConnectFour;
import java.util.*;
public class Driver 
{
	static private boolean userTurn = true;
	public static void main(String[] args)
	{
		boolean play = true;
		char a = 'n';
		while(play)
		{
			boolean read = false;
			Scanner scan = new Scanner(System.in);
			System.out.println("New game!");
			System.out.println("You will play as X");
			StateList state = new StateList();
			playGame(state);
			while(!read)
			{
				System.out.println("Play again? y/n");
				a = scan.nextLine().charAt(0);		
				if(a == 'y')
				{
						play = true;
						read = true;
				}
				else if(a == 'n')
				{
					play = false;
					read = true;
				}
				else
					System.out.println("Command not recognized");
			}
		}
	}
	private static void playGame(StateList state)
	{
		System.out.println(state.board);
		Player win = state.board.win();
		if(win instanceof X || win instanceof O || state.board.tie())
		{
			if(win instanceof X)
				System.out.println("You must have cheated, obviously.");
			else if(win instanceof O)
				System.out.println("Good try! I kicked your ass, though");
			else
				System.out.println("It's a tie, apparently..");
			return;
		}
		Board board = null;
		if(userTurn)
			board = playTurn(state);
		else
		{
			try{
				board = aiTurn(state);
			}catch (Exception e)
			{
				
				System.out.println(e.getMessage());
				System.exit(0);
			}
		}
		userTurn = !userTurn;
		playGame(new StateList(board));
	}
	private static Board aiTurn(StateList state) throws Exception
	{
		System.out.println("My turn!");
		Board ret = estimateList(state);
		if(ret.equals(state.board))
			throw new Exception("AI didn't take turn");
		return ret;
	}
	//change to new board size
	private static Board playTurn(StateList state)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter a position!");
		while(true)
		{
			int next = scan.nextInt();
			System.out.println("\n");
			Board temp = new Board();
			state.board.copy(temp);
			if(next > -1 && next < temp.length)
			{
				boolean cond = temp.play(next, new X());
				if(cond)
				{
					return temp;
				}
			}
			System.out.println("Sorry, you cannot play there. Please reenter a position");
		}
	}
	//tries to figure out a way to win.
	private static Board estimateList(StateList states)
	{
		//start by getting rating of lowest board (selest by player, min or max)
		//return the rating, till you get the final rating of the future board.
		//if rating > best, hold onto the board.
		int best = -10;
		states.generateList(new O()); 
		Collection<Board> temp = states.states.keySet();
		Board bestBoard = states.board;
		for(Board board : temp)
		{
			int tempRating = futureRating(new StateList(board),new X());
			//return max
			//System.out.println(tempRating);
			if(tempRating >= best)
			{
				best = tempRating;
				bestBoard = board;
			}
		}
		//gamble on this investment
		return bestBoard;
		
	}
	private static int futureRating(StateList states, Player play)
	{
		states.generateList(play);
		Collection<Board> poss = states.states.keySet();
		int best;
		if(play instanceof O)
			best = -10;
		else
			best = 10;
		Player player;
		if(play instanceof X)
			player = new O();
		else
			player = new X();
		for(Board board : poss)
		{
			int temp = futureRating(new StateList(board),player);
			if(play instanceof O)
			{
				if(temp > best)
					best = temp;
			}
			else
			{
				if(temp < best)
					best = temp;
			}
			
		}
		if(poss.size() == 0)
			return states.board.rating();
		return best;
	}
}
