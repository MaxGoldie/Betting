
import java.util.Random;

public class TrueRandom implements IRandomValueGenerator{
	
	private int number;

	@Override
	public void setRandom(int min, int max) {	
		Random rand = new Random();
		number = rand.nextInt((max - min) + 1) + min;
	
		}
		
	
	@Override
	public int getRandom() {
		return number;
	}
}
