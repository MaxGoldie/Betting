
public class MockRandom implements IRandomValueGenerator{
	
	private int number;

	@Override
	public void setRandom(int min, int max) {
		number = 5;
		
	}

	@Override
	public int getRandom() {
		return number;
	}
}
