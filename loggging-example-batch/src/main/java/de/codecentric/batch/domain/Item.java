package de.codecentric.batch.domain;

public class Item {

	private String value;
	private boolean valid;

	public Item(String value, boolean valid) {
		super();
		this.value = value;
		this.valid = valid;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Override
	public String toString() {
		return "Item [value=" + value + ", valid=" + valid + "]";
	}

}
