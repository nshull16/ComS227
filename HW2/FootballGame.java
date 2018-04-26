package hw2;

/**
 * This class encapsulates the logic and state for a simplified
 * game of American football.  
 * 
 * @author nshull16 (Nathan Shull)
 */
public class FootballGame
{
  /**
   * Number of points awarded for a touchdown.
   */  
  public static final int TOUCHDOWN_POINTS = 6;
  
  /**
   * Number of points awarded for a successful extra point attempt
   * after a touchdown.
   */ 
  public static final int EXTRA_POINTS = 1;
  
  /**
   * Number of points awarded for a field goal.
   */
  public static final int FIELD_GOAL_POINTS = 3;
  
  /**
   * Total length of the field from goal line to goal line, in yards.
   */
  public static final int FIELD_LENGTH = 100;
  
  /**
   * Initial position of the offensive team after receiving a kickoff.
   */ 
  public static final int STARTING_POSITION = 70;
  
  /**
   * Yards required to get a first down.
   */
  public static final int YARDS_FOR_FIRST_DOWN = 10;
  
  // TODO - everything else :)
 
  /**
   * The distance from the current ball position
   * to the opponents goal line.
   */
  private int yardsToGoal;
  /**
   * The current down. Ranges from 1 to 4.
   */
  private int down;
  /**
   * The score of team 0. 
   */
  private int score0;
 /**
  * The score of team 1.
  */
  private int score1;
  /**
   * The distance the offensive team has to move the ball
   * to get a first down.
   */
  private int yardsToFirst;
  /**
   * True if team 0 has possession of the ball, false otherwise.
   */
  private boolean ball0;
  /**
   * True if team 1 has possession of the ball, false otherwise.
   */
  private boolean ball1;
  

  /**
   * Constructs a football game. The initial distance to goal
   * is 70 yards, team 0 starts with the ball, and it is
   * first down.
   */
  public FootballGame()
  {
	  yardsToGoal = 70;
	  down = 1;
	  ball0 = true;
	  ball1 = false;
  }
  /**
   * Records the result of a extra attempt,
   * adding EXTRA_POINTS if the attempt
   * is successful. Whether or not the attempt is
   * successful, the defense gets the ball and starts
   * with a first down, STARTING_POSITION yards from
   * the goal line.
   * @param success
   * 	True if the attempt was successful, false otherwise
   */
  public void extraPoint(boolean success)
  {
		 if(ball0)
		 {
			 if(success)
			 {
				 score0 = score0 + EXTRA_POINTS;
			 }
		 }
		 if(ball1)
		 {
			 if(success)
			 {
				 score1 = score1 + EXTRA_POINTS;
			 }
		 }
		 if(ball0)
		 {
			 ball0 = false;
			 ball1 = true;
		 }
		 down = 1;
		 
  }
  /**
   * Records the result of a field goal attempt, adding
   * FIELD_GOAL_POINTS points if the field goal was successful.
   * If the attempt is successful, the defense get the
   * ball and starts with a first down, STARTING_POSITION yards
   * from the goal line. If the attempt is unsuccessful,
   * the defense gets the ball at its current location
   * @param success
   * 	True if the attempt was successful, false otherwise
   */
  public void fieldGoal(boolean success)
  {
	 if(ball0)
	 {
		 if(success)
		 {
			 score0 = score0 + FIELD_GOAL_POINTS;
		 }
		 if(!success)
		 {
			 yardsToGoal = FIELD_LENGTH - yardsToGoal;
		 }
	 }
	 else
	 {
		 if(success)
		 {
			 score1 = score1 + FIELD_GOAL_POINTS;
		 }
		 if(!success)
		 {
			 yardsToGoal = FIELD_LENGTH - yardsToGoal;
		 }
	 }
	 if(ball0){
		 ball0 = false;
		 ball1 = true;
	 }
	 else
	 {
		 ball0 = true;
		 ball1 = false;
	 }
	 
	 down = 1;
  }
/**
 * Returns the current down. Possible values
 * are one through four.
 * @return
 * 	the current down as a number one through four
 */
  public int getDown()
  {
	  return down;
  }
  /**
   * Returns the index for the team currently
   * playing offense.
   * @return
   * 	index of the team playing offense, one or zero
   */
  public int getOffense()
  {
	 if(ball0){
		 return 0;
	 }
	 else{
		 return 1;
	 }
  }
  /**
   * Returns the points for the indicated team
   * @param whichTeam
   * 	team index of zero or one
   * @return
   * 	Score for team zero if the given parameter is zero,
   * 	score for team one otherwise
   */
  public int getScore(int whichTeam)
  {
	  if(whichTeam == 0)
	  {
		  return score0;  
	  }
	  else
	  {
		  return score1;
	  }
	 
  }
  /**
   * Returns the number of yards the offense must
   * advance the ball to get a first down.
   * @return
   * 	number of yards to get a first down
   */
  public int getYardsToFirstDown()
  {
	  if(down == 1 || down > 4)
	  {
		  return YARDS_FOR_FIRST_DOWN;
	  }
	  else
	  {
		  return yardsToFirst;
	  }

  }
  /**
   * Returns the location of the ball as a number
   * of yards to the opposing team's goal line
   * @return
   * 	number of yards from the ball to the defense's goal line
   */
  public int getYardsToGoalLine()
  {
	  return yardsToGoal;
  }
  /**
   * Records the result of a punt. The defense gets the ball
   * with a first down after it has advance the given number
   * of yards; however, if the ball would have advanced past
   * the defense's goal line, the defense starts with the ball at 
   * locations FIELD_LENGTH. The given number of yards should not
   * be negative.
   * @param yards
   * 	number of yards the ball is advanced by the punt
   */
  public void punt(int yards)
  {
	  if(yardsToGoal - yards > 0)
	  {
		  yardsToGoal = (FIELD_LENGTH - yardsToGoal) + yards;
	  }
	  if(yardsToGoal - yards <= 0)
	  {
		  yardsToGoal = 100;
	  }
	  if(ball0)
	  {
		  ball0 = false;
		  ball1 = true;
	  }
	  else
	  {
		  ball0 = true;
		  ball1 = false;
	  }
	  down = 1;
  }
  /**
   * Records the result of advancing the ball the given number
   * of yards, possibly resulting in a first down, a touchdown, or
   * a turnover. If distance to goal is less than zero, a touchdown is 
   * awarded and the offense get TOUCHDOWN_POINTS points. If the ball is 
   * advanced 10 yards in 4 downs or less, the offense gets to keep the
   * ball and gets a first down. If it is 4th down, there is no touchdown, 
   * and the ball has not been advanced 10 yards or more, 
   * then the defense takes possession at the ball's current location.
   * Otherwise, the offense keeps the ball and the down increases by 1.
   * The numberof yards the ball is advanced may be negative, however, the 
   * distance ot the goal line doesn't ever go over FIELD_LENGTH yards.
   * @param yards
   * 	Number of yards the ball is moved
   */
  public void runOrPass(int yards)
  {
	 yardsToFirst = YARDS_FOR_FIRST_DOWN - yards;
	 if(yardsToFirst > 0)
	 {
		 ++down;
	 }
	 else
	 {
		 down = 1;
	 }
	 if(down <= 4)
	 {
		 if(yardsToGoal - yards <= 100)
		 {
			 yardsToGoal = yardsToGoal - yards;
		 }
		 else
		 {
			 yardsToGoal = 100;
		 }
	 }
	 if(down > 4)
	 {
		 if(ball0)
		 {
			 ball0 = false;
			 ball1 = true;
		 }
		 else
		 {
			 ball0 = true;
			 ball1 = false;
		 }
		 down = 1;
		 yardsToGoal = FIELD_LENGTH - (yardsToGoal - 1);
	 }
	 if(yardsToGoal <= 0)
	 {
		 if(ball0)
		 {
			 score0 = score0 + TOUCHDOWN_POINTS;
		 }
		 else
		 {
			 score1 = score1 + TOUCHDOWN_POINTS;
		 }
	 }
  }
}
