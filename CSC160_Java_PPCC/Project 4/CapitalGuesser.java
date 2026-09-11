//name: josh manchester
//date: 8 Oct 2019
//project: project 4

import java.util.Scanner;

public class CapitalGuesser {
      
   public static void main(String[] args) {
   
      int j = 0;
      int i = 0;
      int y = 0;
      int moveon = 0;
      int numCorrect = 0;
      int numWrong = 0;
      
      boolean notcorrect = true; // flag for checking if the players answer is wrong or right

      Scanner input = new Scanner(System.in); // for grabbing the players input
      
      String guess = ""; // place holder for the players guess
      
      String[][] temp = new String[2][2]; // place holder for random swapping
           
      String[][] States = { // multidimensional array for storing states and capitals
      
{"Alabama", "Montgomery"},
{"Alaska", "Juneau"},
{"Arizona", "Phoenix"},
{"Arkansas", "Little Rock"},
{"California", "Sacramento"},
{"Colorado", "Denver"},
{"Connecticut", "Hartford"},
{"Delaware", "Dover"},
{"Florida", "Tallahassee"},
{"Georgia", "Atlanta"},
{"Hawaii", "Honolulu"},
{"Idaho", "Boise"},
{"Illinois", "Springfield"},
{"Indiana", "Indianapolis"},
{"Iowa", "Des Moines"},
{"Kansas", "Topeka"},
{"Kentucky", "Frankfort"},
{"Louisiana", "Baton Rouge"},
{"Maine", "Augusta"},
{"Maryland", "Annapolis"},
{"Massachusetts", "Boston"},
{"Michigan", "Lansing"},
{"Minnesota", "St. Paul"},
{"Mississippi", "Jackson"},
{"Missouri", "Jefferson City"},
{"Montana", "Helena"},
{"Nebraska", "Lincoln"},
{"Nevada", "Carson City"},
{"New Hampshire", "Concord"},
{"New Jersey", "Trenton"},
{"New Mexico", "Santa Fe"},
{"New York", "Albany"},
{"North Carolina", "Raleigh"},
{"North Dakota", "Bismarck"},
{"Ohio", "Columbus"},
{"Oklahoma", "Oklahoma City"},
{"Oregon", "Salem"},
{"Pennsylvania", "Harrisburg"},
{"Rhode Island", "Providence"},
{"South Carolina", "Columbia"},
{"South Dakota", "Pierre"},
{"Tennessee", "Nashville"},
{"Texas", "Austin"},
{"Utah", "Salt Lake City"},
{"Vermont", "Montpelier"},
{"Virginia", "Richmond"},
{"Washington", "Olympia"},
{"West Virginia", "Charleston"},
{"Wisconsin", "Madison"},
{"Wyoming", "Cheyenne"},
};

      for (j = 0; j < States.length; j++) {
      
         y = (int)(Math.random() * States.length-1); // gets random number
         temp[0][0] = States[y][0]; // stores original state in memory
         temp[0][1] = States[y][1]; // capital info
         States[y][0] = States[j][0]; // copies random state to the original states position in the array
         States[y][1] = States[j][1]; // capital info
         States[j][0] = temp[0][0]; // copies the original state to the random states position in the array
         States[j][1] = temp[0][1]; // capital info

      }
      
      System.out.println("The State Capital guessing game");
      System.out.println("Guess the Capitals of all 50 United States");
      System.out.println("in random order");
      System.out.println("type \"stop\" at anytime to end the game");
      System.out.println();
      
      while (i < States.length) {
         while (notcorrect && (moveon <= 3)) { // loop to check for correct answers and to tally correct and incorrect answers.  Also checks if the question was answered wrong 3 times
            System.out.print("Question " + (i+1) + ": What is the capital of " + States[i][0] + " ? "); // ask the question by using a random number from numList to grab a capital and state combo
            guess = input.nextLine().toLowerCase(); // force answer to be all lowercase
            if (guess.equals("stop")) { // check to see if the player wants to quit
               i = 55; // set counter to number outside range that makes the loop true
               break; // kill the loop
            }
            if (guess.equals(States[i][1].toLowerCase())) { // check answer against capital
               System.out.println("That's correct... " + States[i][1] + " is the capital of " + States[i][0]); // state if the answer is correct
               System.out.println();
               notcorrect = false; // reset flag
               moveon = 0; // reset wrong answer counter if there were wrong answers
               numCorrect = numCorrect + 1; // tally correct answers given
            }
            else {
               System.out.println(guess + " is incorrect.. please try again."); // awww too bad you got it wrong
               System.out.println();
               moveon = moveon + 1; // tally wrong answers until there are 3 wrong and then moveon
            }
            if (moveon == 3) { // if the answer is wrong 3 times give player the correct answer
               System.out.println("The correct answer is " + States[i][1]); // print the correct answer
               System.out.println();
               moveon = 0;  // reset wrong answer counter
               notcorrect = false;  // reset flag
               numWrong = numWrong + 1; // tally wrong answers given
            }
               
         }
         i = i + 1; // increment the loop
         notcorrect = true; // reset flag
      }
      System.out.println("You answered " + numCorrect + " correct out of " + (numCorrect + numWrong) + " atempts"); // give the tally of correct answers
   }
}