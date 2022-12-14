
public class Betting {
	private double currentBalance;
	private int minBalance;
	private IRandomValueGenerator random;

	public Betting(int minBalance, IRandomValueGenerator random) {
		this.currentBalance = 0;
		this.minBalance = minBalance;
		this.random = random;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public boolean canBet(double amnt) {
		if ((currentBalance - amnt) >= minBalance) {
			return true;
		}
		return false;
	}

	public void addMoney(double amnt) {
		if (amnt > 0) {
			currentBalance += amnt;
		}

	}

	public double betOnANumber(double amnt, int min, int max, int selectedNumber) {
		random.setRandom(min, max);
		int number = random.getRandom();
		double startBalance = currentBalance;
		if (number == selectedNumber) {
			currentBalance += ((max - min) - 1) * amnt;

		} else {
			currentBalance -= amnt;
		}
		if (currentBalance < minBalance) {
			currentBalance = 0;
			return 0;
		}

		return startBalance - currentBalance;
	}

	public double betOnProbability(double amnt, double p) {

		random.setRandom(1, 100);
		int number = random.getRandom();

		if (p < 0 || p > 1) {
			throw new negativeOrGreaterThanOneException();
		}

		if (number < (p * 100)) {
			currentBalance += ((Math.pow(p, -1) - 1) * amnt);
		} else {
			currentBalance -= amnt;
			if (currentBalance < minBalance) {
				currentBalance = 0;
				return 0;
			}
		}

		return currentBalance;

	}

}
