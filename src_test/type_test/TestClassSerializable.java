package type_test;

import java.io.Serializable;

public class TestClassSerializable implements Serializable {

	private static final long serialVersionUID = 5475659435421115852L;

	private int number = -1;
	private String string = null;

	public TestClassSerializable() {
	}

	public TestClassSerializable(int number, String string) {
		this.number = number;
		this.string = string;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

}
