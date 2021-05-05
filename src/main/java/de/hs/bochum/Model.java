package de.hs.bochum;

public class Model {

	private Model() {

	}

	private static Model instance = null;

	public static Model getInstance() {
		if (instance == null)
			instance = new Model();
		return instance;

	}
}
