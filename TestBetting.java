
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TestBetting{

	MockRandom mock;
	Betting checker;

	@BeforeEach
	void setUp() {
		mock = new MockRandom();
		checker = new Betting(0, mock);
	}

	@Test
	void testCurrentBalanceIsZeroAtStart() {
		assertEquals(0, checker.getCurrentBalance());
	}

	@Test
	void testCanBetWhenCurrentBalanceIsNotAboveMinimumBalanceByAmnt() {
		checker.addMoney(10);
		assertTrue(checker.canBet(9));
	}

	@Test
	void testCantBetWhenCurrentBalanceIsAboveMinimumBalanceByAmnt() {
		checker.addMoney(10);
		assertFalse(checker.canBet(19));
	}

	@Test
	void testRejectMoneyLessThanZero() {
		checker.addMoney(5);
		checker.addMoney(-4);
		assertEquals(5, checker.getCurrentBalance());
	}

	@Test
	void testWinsBetOnANumber() {
		checker.addMoney(10);
		checker.betOnANumber(5, 0, 30, 5);
		assertEquals(155, checker.getCurrentBalance());
	}

	@Test
	void testLostBestOnANumber() {
		checker.addMoney(100);
		checker.betOnANumber(20, 50, 90, 80);
		assertEquals(80, checker.getCurrentBalance());
	}

	@Test
	void testCurrentBalanceLessThanMinBalanceAfterBetOnNumber() {
		checker.addMoney(10);
		assertEquals(0, checker.betOnANumber(20, 50, 90, 80));

	}

	@Test
	void testProbabilitiesGreaterThanOne() {
		checker.addMoney(100);
		assertThrows(negativeOrGreaterThanOneException.class, () -> checker.betOnProbability(10, 3));
	}

	@Test
	void testProbabilitiesLessThanOne() {
		checker.addMoney(100);
		assertThrows(negativeOrGreaterThanOneException.class, () -> checker.betOnProbability(10, -3));
	}

	@Test
	void testWinBetOnProbability() {
		checker.addMoney(100);
		checker.betOnProbability(30, .5);
		assertEquals(130, checker.getCurrentBalance());
	}

	@Test
	void testLostBetOnProbability() {
		checker.addMoney(100);
		checker.betOnProbability(30, .02);
		assertEquals(70, checker.getCurrentBalance());
	}

	@Test
	void testCurrentBalanceLessThanMinWhenBetOnProbability() {
		checker.addMoney(10);
		checker.betOnProbability(30, .02);
		assertEquals(0, checker.getCurrentBalance());
	}
}
