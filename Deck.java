import java.util.*;
//im not commenting this and you can't make me
//It works and I forgot to name my varriables so it just is now
class Deck {
  public Double[] cards = new Double[52];
  Stack<Double> stack = new Stack<Double>();
  double n = 0;
  double y = 2;

  public Deck() {
    for(int i = 0; i < 52;i++){
          cards[i] = y + (n/10);
            n++;
      if (n == 4){
        y += 1;
        n = 0;
      }
            }    
    
  }
public void shuffle(){
List<Double> intList = Arrays.asList(cards);

		Collections.shuffle(intList);

		intList.toArray(cards);
  
  for (int i = 0; i < cards.length; i++){
    stack.push(cards[i]);
  }
}

  public double bring(){
    return stack.pop();
  }

  
  public void print(){
    for(double x : stack)
{
    System.out.println(x);
}
    }
  }
