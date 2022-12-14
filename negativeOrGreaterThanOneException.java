public class negativeOrGreaterThanOneException extends RuntimeException{
	 public negativeOrGreaterThanOneException() {
		 super("Probability can not be negative or greater than one");
	 }
}
