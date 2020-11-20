class Bicycle {

	private String id, name;

	void Bicycle() {
		id = "UNREGISTERED";
		name = "UNKNOWN";
	}

	void setName(String newName) {
		name = newName;
	}

	void setID (String newID) {
		id = newID;
	}

	String getName () {
		return name;
	}

	String getID () {
		return id;
	}

}