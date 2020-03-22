package demo.survey;

public class ModifyOperations {

	public String getDynamicXPath(String xpath, String value) {
		String[] parts = xpath.split("#");
		parts[1] = value;
		return parts[0] + parts[1] + parts[2];
	}

	public int getRandomValue() {
		return 5;
	}
}
