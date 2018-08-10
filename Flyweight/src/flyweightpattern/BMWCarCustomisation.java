package flyweightpattern;

import java.util.HashMap;
import java.util.Map;

interface BMWCarCustomisation {
	// Customise Tire size
	int getTireSize();
	String getLaserSignature();
	// a lot of customisation attributes can be in there for a BMW car
	void printCustomisations();
}

interface BMWCar {
	double calculatePrice(BMWCarCustomisation custom);
	void printFullCharacteristics(BMWCarCustomisation custom);
}

interface BMWCarFactory {
	BMWCar createCar();
}

interface BMWCarFlyWeightFactory {
	enum Model {Serie1, Serie2};
	BMWCar getBWMModel(Model m);
}

class BMWSerie1 implements BMWCar {
	private final static double BASE_PRICE = 25000;

	@Override
	public double calculatePrice(BMWCarCustomisation custom) {
		return BASE_PRICE + getSpecificSerie1PriceBasedOnCustom(custom) + getExportationTaxe(custom);
	}

	@Override
	public void printFullCharacteristics(BMWCarCustomisation custom) {
		// print all BMW 1 Series specific characteristics
		// (codes in there)
		custom.printCustomisations(); // print details based on these customisations
	}

	private double getSpecificSerie1PriceBasedOnCustom(BMWCarCustomisation custom) {
		// (e.g., calculation based on custom specific to Series 1)
		double sum = 0;
		if (custom.getTireSize() == 19) {
			sum += 1200;
		} else {
			sum += 2100;
		}
		return sum;
	}

	private double getExportationTaxe(BMWCarCustomisation custom) {
		// (calculation based on custom exportation taxes only for this model)
		double sum = 0;
		if (!custom.getLaserSignature().isEmpty()) {
			sum += 987;
		}
		return sum;
	}
}

class BMWSerie2 implements BMWCar {
	private final static double BASE_PRICE = 28000;

	@Override
	public double calculatePrice(BMWCarCustomisation custom) {
		return BASE_PRICE + getSpecificSerie2PriceBasedOnCustom(custom);
	}

	@Override
	public void printFullCharacteristics(BMWCarCustomisation custom) {
		// print all BMW 2 Series specific characteristics
		// (codes in there)
		custom.printCustomisations(); // print details based on these customisations
	}

	private double getSpecificSerie2PriceBasedOnCustom(BMWCarCustomisation custom) {
		// (e.g., calculation based on custom specific to Series 2)
		double sum = 0;
		if (custom.getTireSize() == 19) {
			sum += 2000;
		} else {
			sum += 3000;
		}
		if (!custom.getLaserSignature().isEmpty()) {
			if (custom.getLaserSignature().length() > 10) {
				sum += 1200;
			} else {
				sum += 400;
			}
		}
		return sum;
	}
}

class BMWSerie2Factory implements BMWCarFactory {
	@Override
	public BMWCar createCar() {
		return new BMWSerie2();
	}
}

class BMWSerie1Factory implements BMWCarFactory {
	@Override
	public BMWCar createCar() {
		return new BMWSerie1();
	}
}

class BMWSerieFlyWeightFactory implements BMWCarFlyWeightFactory {
	private Map<Model, BMWCar> cache = new HashMap<>();

	public synchronized BMWCar getBWMModel(Model m) {
		if (!cache.containsKey(m)) {
			BMWCarFactory concreteFactory;
			switch (m) {
			case Serie1:
				concreteFactory = new BMWSerie1Factory();
				break;
			case Serie2:
				concreteFactory = new BMWSerie2Factory();
				break;
			/*
			 * Just code to have a hint !
			 */
			default:
				concreteFactory = new BMWSerie1Factory();
				break;
			}
			cache.put(m, concreteFactory.createCar());
		}
		return cache.get(m);
	}
}

class BMWSerieCarCustomisation implements BMWCarCustomisation {

	private int tireSize;
	private String laserSignature;

	public BMWSerieCarCustomisation(int tireSize, String laserSignature) {
		this.tireSize = tireSize;
		this.laserSignature = laserSignature;
	}

	public int getTireSize() {
		return tireSize;
	}

	public String getLaserSignature() {
		return laserSignature;
	}

	@Override
	public void printCustomisations() {
		System.out.println("Tire Size:" + getTireSize());
		System.out.println("LaserSignature:" + getLaserSignature());
		System.out.println("LaserSignature Size:" + getLaserSignature().length());
	}
}
