package VideoRental.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String name;

	private List<Rental> rentals = new ArrayList<Rental>();

	public Customer(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);

	}


	// Long method (SRP) - Point - Charge + system.out | Logic + Rental (Divergent Change, Feature Envy)
	public String getReport() {
		String result = "Customer Report for " + getName() + "\n";

		// rentals var
		List<Rental> rentedVideoinfo = getRentals();

		double totalCharge = 0;
		int totalPoint = 0;

		for (Rental each : rentedVideoinfo) {
			double eachCharge = 0;
			int eachPoint = 0 ;

			eachCharge = getEachCharge(each);

			eachPoint = getEachPoints(each);

			result += "\t" + each.getVideoTitle() + "\tDays rented: " + each.getdayRented() + "\tCharge: " + eachCharge
					+ "\tPoint: " + eachPoint + "\n";

			totalCharge += eachCharge;

			totalPoint += eachPoint ;
		}

		result += "Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n";

		PrintCouponInfo(totalPoint);

		return result ;
	}

	private static void PrintCouponInfo(int totalPoint) {
		if ( totalPoint >= 10 ) {
			System.out.println("Congrat! You earned one free coupon");
		}
		if ( totalPoint >= 30 ) {
			System.out.println("Congrat! You earned two free coupon");
		}
	}

	public int getEachPoints(Rental rental) {
		int eachPoint = 0 ;

		if (rental != null) {
			int daysRented = 0;
			daysRented = rental.getdayRented();

			eachPoint++;

			if ((rental.getVideoPriceCode() == Video.NEW_RELEASE) )
				eachPoint++;

			if ( daysRented > rental.getDaysRentedLimit() )
				eachPoint -= Math.min(eachPoint, rental.getVideo().getLateReturnPointPenalty()) ;
		}

		return eachPoint;
	}

	public double getEachCharge(Rental rental) {
		double eachCharge = 0;

		if (rental != null) {
			int daysRented = 0;
			daysRented = rental.getdayRented();

			switch (rental.getVideoPriceCode()) {
				case Video.REGULAR:
					eachCharge += 2;
					if (daysRented > 2)
						eachCharge += (daysRented - 2) * 1.5;
					break;
				case Video.NEW_RELEASE:
					eachCharge = daysRented * 3;
					break;
			}
		}

		return eachCharge;
	}

}
