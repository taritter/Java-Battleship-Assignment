import java.io.*;
import java.util.*;
/**This class is the user's board and has all the method for the computer to play against the userboard.
It is a subclass of Board and also has an arraylist of moves to make sure there are no repeats.
*/
public class UserBoard extends Board
{
   //instance variables
   private ArrayList<Move> moves;
   private Random rand;
   
   /**This constructor instantiates rand and moves as well as calls super's contstructor
   @param s is the filename that contains moves to set up user's board
   */
   public UserBoard(String s)throws IOException
   {
      super(s);
      rand = new Random();
      moves = new ArrayList<Move>();
   }
   /**This method makes the computer take a move against the user's board
   @returns sink which is an arrayList with the move and if the ship sank
   */
   public String[] makeComputerMove()
   {
      String[] sink = new String[2];
      Move userMove = pickRandomMove();
      ArrayList<ArrayList<CellStatus>> userLayout = super.getLayout();
      //if user's board has a ship
      if(userLayout.get(userMove.row()).get(userMove.col()-1) == CellStatus.AIRCRAFT_CARRIER)
      {
         //applies move to user's board
         userLayout = super.applyMoveToLayout(userMove);
         //determines if this sunk the ship
         boolean sunk = super.getFleet().updateFleet(ShipType.ST_AIRCRAFT_CARRIER);
         if(sunk)
         {
            sink = new String[] {userMove.toString(),"You Sank My Aircraft Carrier!"};
            return sink;
         }
         else
         {
            sink = new String[] {userMove.toString(),null};
            return sink;         
         }
      }
      if(userLayout.get(userMove.row()).get(userMove.col()-1) == CellStatus.BATTLESHIP)
      {
         super.getFleet();
         userLayout = super.applyMoveToLayout(userMove);
         boolean sunk = super.getFleet().updateFleet(ShipType.ST_BATTLESHIP);
         if(sunk)
         {
            sink = new String[] {userMove.toString(),"You Sank My Battleship!"};
            return sink;
         }
         else
         {
            sink = new String[] {userMove.toString(),null};
            return sink;         
         }      
      }
      if(userLayout.get(userMove.row()+1).get(userMove.col()-1) == CellStatus.CRUISER)
      {
         super.getFleet();
         userLayout = super.applyMoveToLayout(userMove);
         boolean sunk = super.getFleet().updateFleet(ShipType.ST_CRUISER);
         if(sunk)
         {
            sink = new String[] {userMove.toString(),"You Sank My Cruiser!"};
            return sink;
         }
         else
         {
            sink = new String[] {userMove.toString(),null};
            return sink;         
         }
      }
      if(userLayout.get(userMove.row()+1).get(userMove.col()-1) == CellStatus.DESTROYER)
      {
         super.getFleet();
         userLayout = super.applyMoveToLayout(userMove);
         boolean sunk = super.getFleet().updateFleet(ShipType.ST_DESTROYER);
         if(sunk)
         {
            sink = new String[] {userMove.toString(),"You Sank My Destroyer!"};
            return sink;
         }
         else
         {
            sink = new String[] {userMove.toString(),null};
            return sink;         
         }
      }
      if(userLayout.get(userMove.row()+1).get(userMove.col()-1) == CellStatus.SUB)
      {
         super.getFleet();
         userLayout = super.applyMoveToLayout(userMove);
         boolean sunk = super.getFleet().updateFleet(ShipType.ST_SUB);
         if(sunk)
         {
            sink = new String[] {userMove.toString(),"You Sank My Sub!"};
            return sink;
         }
         else
         {
            sink = new String[] {userMove.toString(),null};
            return sink;         
         }
      }
      if(userLayout.get(userMove.row()+1).get(userMove.col()-1) == CellStatus.NOTHING)
      {
         super.getFleet();
         userLayout = super.applyMoveToLayout(userMove);
         sink = new String[]{userMove.toString(),null};
      }
      return sink;
   }
   /**this private method picks a letter between A - J and a number between 1 - 10 then creates a new move object with those random letters and numbers
   @returns rMove which is the random move generated
   */
   private Move pickRandomMove()
   {
      int randLetter = rand.nextInt(10);
      int randNum = rand.nextInt(10);
      Move rMove = new Move(randLetter,randNum);
      for(Move m: moves)
      {
         if(rMove.toString().equals(m))
         {
            pickRandomMove();
         }
      }
      moves.add(rMove);
      return rMove;
   }
   /**this is the toString method
   @returns a formatted string
   */
   public String toString()
   {
      String[] letter = {"A","B","C","D","E","F","G","H","I","J"};
      ArrayList<ArrayList<CellStatus>> userLayout = super.getLayout();
      String ts = "   1 2 3 4 5 6 7 8 9 10 \n";
      for(int r = 0; r < 10; r++)
      {
         ts += letter[r] + "  ";
         for(int c = 0; c < 10; c++)
         {
            ts += userLayout.get(r).get(c).toString().substring(0,1) + " ";
         }
         ts += "\n";
      }
      return ts;
   }
}