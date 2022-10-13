/*
*
* Andrew 
* Comp Sci 
* 
*/

// Imports
import java.io.*; 
import java.text.*;

class Main {
  // Method that contains the game
  public static Integer game(int balance) throws IOException {

    //creates a deck and shuffles it
    Deck one = new Deck();
    one.shuffle();
    //For debugging the deck
    //one.print();

    // Array that has all the card names
    String[] cards = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};

    // Array with a suits
    String[] suits = {"Hearts", "Spades", "Diamonds", "Clubs"};

    // Variables used during game
    int dealertotal = 0;
    int playertotal = 0;
    double dcard = 0;
    int card = 0;
    int suit = 0;
    int ace = 0;
    int dealerace = 0;
    int hit = 0;
    int bet = 0;

    // String to keep names of the cards dealt
    String player = "";
    String dealer = "";

    // Buffered reader to accept user inputs
    BufferedReader myInput = new BufferedReader (new InputStreamReader (System.in));

    // Loop to get user bet as well as verify its possible
    do{
      System.out.println("How much would you like to bet(in points)?");
      bet = Integer.parseInt(myInput.readLine());
      if (bet > balance){
        System.out.println("\nThat bet is larger than your balance please try again\n");
      }
      else if (bet < 0){
        System.out.println("\nThat bet is less than zero please try again\n");
      }
    } while ((bet > balance)||(bet < 0));
   
    // Generates the players first card
    dcard = one.bring();
    card = (int)Math.floor(dcard) - 2;
    suit = (int)((dcard - (int)dcard) * 10);
    player = "\nYou got a " + cards[card] + " of " + suits[suit];

    // Adds the cards value to the users sum
    if((card >= 8) && (card <= 11)){
      playertotal += 10;
      }
    else if (card == 12){
      playertotal += 11;
      ace += 1;
      } 
    else {
      playertotal += card + 2;
      }

    // Generates the players second card
    dcard = one.bring();
    card = (int)Math.floor(dcard) - 2;
    suit = (int)((dcard - (int)dcard) * 10);

    // Adds the cards value to the users sum
    if((card >= 8) && (card <= 11)){
      playertotal += 10;
      } 
    else if (card == 12){
      playertotal += 11;
      ace += 1;
      } 
    else {
      playertotal += card + 2;
      }
     if(playertotal == 22){
       ace = 1;
       playertotal = 13; 
     } 

    // Tells the user what cards they were dealt as well as there sum
    player = player + " and a " + cards[card] + " of " + suits[suit];
    System.out.println(player);
    System.out.println("Your cards have a total of " + playertotal + "\n");

    // Generates the dealers first card
    dcard = one.bring();
    card = (int)Math.floor(dcard) - 2;
    suit = (int)((dcard - (int)dcard) * 10);
    player = "The dealer got a " + cards[card] + " of " + suits[suit] + " and another card";

    // Adds the cards value to the dealers sum
    if((card >= 8) && (card <= 11)){
      dealertotal += 10;
      } 
    else if (card == 12){
      dealertotal += 11;
      dealerace += 1;
      } 
    else {
      dealertotal += card + 2;
      }

    // Tells the user the dealers first card 
    System.out.println(player);
    System.out.println("The dealers one card total is " + dealertotal + "\n");

    // Generates the dealers second card
    dcard = one.bring();
    card = (int)Math.floor(dcard) - 2;
    suit = (int)((dcard - (int)dcard) * 10);

    // Adds the cards value to the dealers sum
    if((card >= 8) && (card <= 11)){
      dealertotal += 10;
      } 
    else if (card == 12){
      dealertotal += 11;
      dealerace += 1;
      } 
    else {
      dealertotal += card + 2;
      }
     if(dealertotal == 22){
       dealerace = 1;
       dealertotal = 13; 
     } 

    // Saves the dealers second card
    dealer ="\nThe dealers second card was the " + cards[card] + " of " + suits[suit];

    // Asks the user whether they want to hit or stand
    System.out.println("Press 1 to hit and 2 to stand");
    hit = Integer.parseInt(myInput.readLine());

    // Checks whether the user wanted to hit or not
    for(int i = 2; i != hit;){

      // Generates the card
      dcard = one.bring();
    card = (int)Math.floor(dcard) - 2;
    suit = (int)((dcard - (int)dcard) * 10);
      player = "\nYou got a " + cards[card] + " of " + suits[suit];

      // Adds the card to the players total
      if((card >= 8) && (card <= 11)){
        playertotal += 10;
      } 
      else if ((card == 12)){
        playertotal += 11;
        ace += 1;
      } 
      else {
        playertotal += card + 2;
      }
      System.out.println(player);

      // Checks whether the users sum is greater than 21 or if they have an ace
      if ((playertotal > 21) && (ace > 0) && (playertotal - 10 <= 21)){
        ace -= 1;
        playertotal -= 10;
        System.out.println("Your new card total is " + playertotal + "\n");
        System.out.println("Press 1 to hit and 2 to stand");
        hit = Integer.parseInt(myInput.readLine());
      }
      else if ((playertotal > 21) && (ace > 0)){
            ace -= 1;
            playertotal -= 10;
            System.out.println("Your new card total is " + playertotal);
            System.out.println("\nYou have busted");
            balance -= bet;
            hit = 2;
        } 
      else if(playertotal > 21){
        System.out.println("Your new card total is " + playertotal);
        System.out.println("\nYou have busted");
        balance -= bet;
        hit = 2;
      } 
      else{
        System.out.println("Your new card total is " + playertotal + "\n");
        System.out.println("Press 1 to hit and 2 to stand");
        hit = Integer.parseInt(myInput.readLine());
      }
    } 

    // Checks if the player has busted on their last hand
    if(playertotal <= 21){

      // Tells the user what the dealers second card was
      System.out.println(dealer);
      System.out.println("The dealers new total is " + dealertotal + "\n");

      // If the dealers total is below 17 they hit
      for(int g = 17; g > dealertotal;){
        dcard = one.bring();
      card = (int)Math.floor(dcard) - 2;
      suit = (int)((dcard - (int)dcard) * 10);
        System.out.println("The dealer got a " + cards[card] + " of " + suits[suit]);

        // Adds the new cards sum to the dealers total
        if((card >= 8) && (card <= 11)){
          dealertotal += 10;
      } 
        else if (card == 12){
          dealertotal += 11;
          dealerace += 1;
      } 
        else {
          dealertotal += card + 2;
      }

      // Checks if the dealers sum is greater than 21
        if ((dealertotal > 21) && (dealerace > 0) && (dealertotal - 10 <= 21)){
          dealerace -= 1;
          dealertotal -= 10;
          System.out.println("The dealers new card total is " + dealertotal + "\n");
      } 
        else if ((dealertotal > 21) && (dealerace > 0)){
          dealerace -= 1;
          dealertotal -= 10;
          System.out.println("The dealers new card total is " + dealertotal + "\n");
          System.out.println("The dealer has busted\n");
        } 
        else if(dealertotal > 21){
          System.out.println("The dealers new card total is " + dealertotal + "\n");
          System.out.println("The dealer has busted\n");
      } 
        else{
          System.out.println("The dealers new card total is " + dealertotal + "\n");
      }
    } 

      // Checks who has won the hand
      // Checks if dealer has won
      if ((dealertotal > playertotal) && (dealertotal <= 21)){
        System.out.println("The dealer has won with a total of " + dealertotal);
        balance -= bet;
      }

      // Checks if it is a stand
      else if ((dealertotal == playertotal) && (dealertotal <= 21)) {
        System.out.println("It's a push no one has lost or won");
        System.out.println("Your total is " + balance);
      }

      // If no other conditions are met the player must have won
      else{
        System.out.println("The player has won with a total of " + playertotal);
        balance += bet;
      }
    }

    // Resets the dealer and players card values as well as any aces they had
    dealertotal = 0;
    dealerace = 0;
    playertotal = 0;
    ace = 0;  

    // Returns the players balance to the main method
    return balance;
  }

  // Main method
  public static void main(String[] args) throws IOException {
    BufferedReader myInput = new BufferedReader (new InputStreamReader (System.in));

    // Variables
    int money = 2000;
    int repeat = 0;
    int bailout = 1;
    // Clears console to make it looks cleaner 
    System.out.print("\033[H\033[2J");   
    System.out.flush();

    // If statment that asks users if they would like to see the rules
    System.out.println("Blackjack Menu:");
    System.out.println("Press 1 to see the rules of blackjack");
    System.out.println("Press 2 to start playing blackjack");
    repeat = Integer.parseInt(myInput.readLine());
    if(repeat == 1){
      System.out.print("\033[H\033[2J");   
      System.out.flush();
      System.out.printf("%-1s\n","Black Jack Rules:");
      System.out.printf("%-1s\n","Objective of the Game: To make the sum of your cards"); 
      System.out.printf("%-1s\n","as close to 21 as possible without going over, while"); System.out.printf("%-1s\n","also being higher than the dealers total\n");
      System.out.printf("%-1s\n","Card values: The Ace has a value of 1 or 11 depending");System.out.printf("%-1s\n","on whether the players total is larger than 21,"); 
      System.out.printf("%-1s\n","Face cards have a value of ten, Every other card"); System.out.printf("%-1s\n","keeps its original value\n");
      System.out.printf("%-1s\n","Betting: Before the cards are dealt each player is"); System.out.printf("%-1s\n","asked to place a bet. This amount will then be"); System.out.printf("%-1s\n","deducted from there balance\n");
      System.out.printf("%-1s\n","Dealing: After the bet is given the player is dealt"); System.out.printf("%-1s\n","two cards and given the sum of them. The dealer is"); System.out.printf("%-1s\n","then also given two cards however one is face down."); System.out.printf("%-1s\n","You are then given the sum of the dealers single card\n");
      System.out.printf("%-1s\n","Hitting: The player is then asked if they would like"); System.out.printf("%-1s\n","to receive another card. This is known as hitting."); System.out.printf("%-1s\n","They may do this as many times as they like, however"); System.out.printf("%-1s\n","if they go over 21 they instantly lose their bet."); System.out.printf("%-1s\n","Once they want to stop receiving cards the player will stand\n");
      System.out.printf("%-1s\n","The Dealer: After the player has stood the dealer"); System.out.printf("%-1s\n","will flip his second card face up. At this point"); 
      System.out.printf("%-1s\n","the dealer hits or stands depending on their sum."); 
      System.out.printf("%-1s\n","If the dealers cards are less than 17 they must hit"); System.out.printf("%-1s\n","untill they bust or are the sum of their cards is"); System.out.printf("%-1s\n","greater than 17. However if there original sum is 17"); System.out.printf("%-1s\n","or more they must stand no matter what the players card are\n");
      System.out.printf("%-1s\n","Winning the game: There are two ways for the player"); System.out.printf("%-1s\n","to win. The first is if the dealer bust's which is"); System.out.printf("%-1s\n","when the dealer goes over 21. The next way to win is"); System.out.printf("%-1s\n","to have a higher sum of cards then the dealer. If you");System.out.printf("%-1s\n","do not meet eitehr of these condition the dealer"); System.out.printf("%-1s\n","wins and you lose your bet\n");
      System.out.printf("%-1s\n","Payout: This game pays out 2:1. ie if you bet 100$ you get back 200$ if you win\n");

      // Makes sure the user has understood the rules and clears the consol for formating
      System.out.println("Press 1 if you have unerstood these rules");
      repeat = Integer.parseInt(myInput.readLine());
      System.out.print("\033[H\033[2J");   
      System.out.flush();
      }
    else{
      System.out.print("\033[H\033[2J");   
      System.out.flush();
     } 

    // Do loop that llows the player to continue or quit
    do{

      // Introduction to game
      System.out.println("Welcome to BlackJack");
      System.out.println("============================");  
      System.out.println("Your balance is " + money + " points\n");

      // Calls the game method and allows the user to play the game
      // Also updates the users value after completion of the game
      money = game(money);
      System.out.println("\nYour new balance is " + money + " points\n");
      

     // Checks if the users balance is at $0
     // If the user has run out of money it gives them the option of a one time bailout 
    if((money == 0) && (bailout == 1)){
      bailout = 0;
      System.out.println("You have run out of money\n");
      System.out.println("Would you like to receive a one time bailout of $1000?");
      System.out.println("1 - Yes");
      System.out.println("2 - No I would like to quit");
      repeat = Integer.parseInt(myInput.readLine());
      if (repeat == 1){
        money = 1000;

        // Clears the console so it is not as clustered 
        System.out.print("\033[H\033[2J");   
        System.out.flush();
      }
      else{

        // Clears the console so it is not as clustered 
        System.out.print("\033[H\033[2J");   
        System.out.flush();
        repeat = 0;
      }
    }

    // If the user has run out of money again and has no bailouts left the game ends
    else if((money == 0) && (bailout == 0)){
      System.out.print("\033[H\033[2J");   
      System.out.flush();
      System.out.println("You have run out of money again and have no bailout's left\n");
      repeat = 0;
    }
    else if (money > 0){

      // Asks the user whether or not they want to play again
      System.out.println("Press 1 to play again and 2 to quit");
      repeat = Integer.parseInt(myInput.readLine());

      // Clears the console so it is not as clustered 
      System.out.print("\033[H\033[2J");   
      System.out.flush();
    }
      } while (repeat == 1);
      System.out.println("You finished the game with a total of " + money + " points");
    }
}