import java.io.*;
import java.util.*;
import java.util.Scanner;

/**This class is the basics for both boards. It reads a file and creates a board and places ships
based on the txt file. It also allows a move to be played and change the board.
It contains the board layout and the fleet
*/

public class Board
{
   private ArrayList<ArrayList<CellStatus>> layout;
   private Fleet fleet;
   public static final int SIZE = 10;
   
   /**this is the constructor method that takes a string
   @param file is the text file to place the ships
   */
   public Board(String file)throws IOException
   {
      layout = new ArrayList<ArrayList<CellStatus>>(SIZE);
      
      //makes the whole grid cell status nothing
      for(int c = 0; c <= SIZE; c++)
      {
         ArrayList<CellStatus> row = new ArrayList<>(SIZE);
         for(int r = 0; r <= SIZE; r++)
         {
            row.add(CellStatus.NOTHING);
         }
         layout.add(row);
      }
      
      //creates a scanner and reads through file with ships and start and end locations
      Scanner sc = new Scanner(new File(file));
      ArrayList<String> letter = new ArrayList<String>();
      letter.add("A");
      letter.add("B");
      letter.add("C");
      letter.add("D");
      letter.add("E");
      letter.add("F");
      letter.add("G");
      letter.add("H");
      letter.add("I");
      letter.add("J");
     
      //reads through file
      while(sc.hasNext())
      {
         //arraylist to get index of A-J
      
         //makes an array from the line in the file
         String line = sc.nextLine();
         String[] split = line.split(" ");
         
         //assigns each split element to a variable
         String type = split[0];
         String start = split[1];
         String end = split[2];
         
         //start letter and number
         char sLetter = start.charAt(0);
         int startLetter = (int) (sLetter) - 65; //first letter
         int startNum = Integer.parseInt(start.substring(1))-1; //first number
         //System.out.println("firstNum:" + firstNum);
         
         //end letter and number
         char lLetter = end.charAt(0);
         int endLetter = (int) (lLetter) - 65;//last letter
         int endNum = Integer.parseInt(end.substring(1))-1; //second number
         
         //number that stays the same in either row or column
         int num = 0;
         
         //if the ship goes across the grid
         if(endLetter-startLetter == 0)
         {
            //getting the row the ship is on by going through arraylist of letters
            for(String l: letter)
            {
               if(start.substring(0,1).equals(l))
               {
                  char shipType = l.charAt(0);
                  num = (int)(shipType)-65;
               }
            }
            if(type.equals("C"))
            {
               for(int i = startNum; i <= 2+startNum; i++)
               {
                  layout.get(num).set(i,CellStatus.CRUISER);
               }
            }
            if(type.equals("A"))
            {
               for(int i = startNum; i <= 4+startNum; i++)
               {
                  layout.get(num).set(i,CellStatus.AIRCRAFT_CARRIER);
               }
            }
            if(type.equals("B"))
            {
               for(int i = startNum; i <= 3+startNum; i++)
               {
                  layout.get(num).set(i,CellStatus.BATTLESHIP);
               }
            }
            if(type.equals("S"))
            {
               for(int i = startNum; i <= 2+startNum; i++)
               {
                  layout.get(num).set(i,CellStatus.SUB);
               }
            }
            if(type.equals("D"))
            {
               for(int i = startNum; i <= 1+startNum; i++)
               {
                  layout.get(num).set(i,CellStatus.DESTROYER);
               }
            }
         }
            
            //setting the Cell to the shiptype
            
         
         //if the ship goes downwards
         if(endNum-startNum == 0) 
         {
            num = startNum;
            if(type.equals("C"))
            {
               for(int i = startLetter; i <= 2+startLetter; i++)
               {
                  layout.get(i).set(num,CellStatus.CRUISER);
               }
            }
            if(type.equals("A"))
            {
               for(int i = startLetter; i <= 4+startLetter; i++)
               {
                  layout.get(i).set(num,CellStatus.AIRCRAFT_CARRIER);
               }
            }
            if(type.equals("B"))
            {
               for(int i = startLetter; i <= 3+startLetter; i++)
               {
                  layout.get(i).set(num,CellStatus.BATTLESHIP);
               }
            }
            if(type.equals("S"))
            {
               for(int i = startLetter; i <= 2+startLetter; i++)
               {
                  layout.get(i).set(num,CellStatus.SUB);
               }
            }
            if(type.equals("D"))
            {
               for(int i = startLetter; i <= 1+startLetter; i++)
               {
                  layout.get(i).set(num,CellStatus.DESTROYER);
               }
            }
         }
      }
      fleet = new Fleet();
   }
   
   /**this method applies move and checks if the CellStatus is NOTHING
   @param m is the move wanted to be applied
   @return the 2D arraylist
   */
   public ArrayList<ArrayList<CellStatus>> applyMoveToLayout(Move m)
   {
      //if the cell status has nothing in it
      if(layout.get(m.row()).get(m.col()-1).equals(CellStatus.NOTHING))
      {
         layout.get(m.row()).set(m.col()-1,CellStatus.NOTHING_HIT);
      }
      //if there is a ship in cell status
      else
      {
         if(layout.get(m.row()).get(m.col()-1).equals(CellStatus.AIRCRAFT_CARRIER))
         {
            layout.get(m.row()).set(m.col()-1,CellStatus.AIRCRAFT_CARRIER_HIT);
         }
         if(layout.get(m.row()).get(m.col()-1).equals(CellStatus.BATTLESHIP))
         {
            layout.get(m.row()).set(m.col()-1,CellStatus.BATTLESHIP_HIT);
         }
         if(layout.get(m.row()).get(m.col()-1).equals(CellStatus.CRUISER))
         {
            layout.get(m.row()).set(m.col()-1,CellStatus.CRUISER_HIT);
         }
         if(layout.get(m.row()).get(m.col()-1).equals(CellStatus.DESTROYER))
         {
            layout.get(m.row()).set(m.col()-1,CellStatus.DESTROYER_HIT);
         }
         if(layout.get(m.row()).get(m.col()-1).equals(CellStatus.SUB))
         {
            layout.get(m.row()).set(m.col()-1,CellStatus.SUB_HIT);
         }
      } 
      return layout;
   }
   
   /**takes a move and determines if the spot is available
   @param move is checked
   @return true if the move is available, if not false
   */
   public boolean moveTaken(Move m)
   {
      if(layout.get(m.row()).get(m.col()-1).equals(CellStatus.NOTHING_HIT))
      {
         return false;
      }
      if(layout.get(m.row()).get(m.col()-1).equals(CellStatus.AIRCRAFT_CARRIER_HIT))
      {
         return false;
      }
      if(layout.get(m.row()).get(m.col()-1).equals(CellStatus.BATTLESHIP_HIT))
      {
         return false;
      }
      if(layout.get(m.row()).get(m.col()-1).equals(CellStatus.CRUISER_HIT))
      {
         return false;
      }
      if(layout.get(m.row()).get(m.col()-1).equals(CellStatus.DESTROYER_HIT))
      {
         return false;
      }
      if(layout.get(m.row()).get(m.col()-1).equals(CellStatus.SUB_HIT))
      {
         return false;
      }
      else
      {
         return true;
      }
   }
   
   /**this method determines if game is over by calling fleet method
   @returns true if game is over, false if all fleet hasn't sunk
   */
   public boolean gameOver()
   {
      if(fleet.gameOver())
         return true;
      return false;
   }
   
   /**this method returns a copy of layout
   @returns ArrayList of a copy of layout
   */
   public ArrayList<ArrayList<CellStatus>> getLayout()
   {
      ArrayList<ArrayList<CellStatus>> layoutCopy = new ArrayList<ArrayList<CellStatus>>(SIZE);
      for(int c = 0; c <= SIZE; c++)
      {
         ArrayList<CellStatus> rowCopy = new ArrayList<CellStatus>(SIZE);
         for(int r = 0; r <= SIZE; r++)
         {
            rowCopy.add(layout.get(c).get(r));
         }
         layoutCopy.add(rowCopy);
      }
      return layoutCopy;
   }
   
   /**this method get the fleet
   @returns fleet
   */
   public Fleet getFleet()
   {
      return fleet;
   }
}
