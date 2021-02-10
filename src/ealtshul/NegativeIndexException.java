package ealtshul;

public class NegativeIndexException extends Exception {
	public NegativeIndexException() {
		super("Cannot insert into a negative index");
	}
}
