package VideoRental.model;

import java.util.Date;

public class Rental {
	private Video video ;

	// enum
	private statusType status;

	private enum statusType {
		Rented, Returned;
	}
	private Date rentDate ;
	private Date returnDate ;

	public Rental(Video video) {
		this.video = video ;
		setStatus(statusType.Rented) ;
		rentDate = new Date() ;
	}

	public Video getVideo() {
		return video;
	}

	public String getVideoTitle() {
		return video.getTitle();
	}

	public int getVideoPriceCode() {
		return video.getPriceCode();
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public statusType getStatus() {
		return status;
	}

	public statusType setStatus(statusType status) {
		return status;
	}

	public void returnVideo() {

		// TODO: Dead code, need to fixed
		//
		if ( getStatus() == statusType.Returned) {
			this.status = statusType.Returned;
			returnDate = new Date() ;
		}
		//
	}
	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getDaysRentedLimit() {
		int limit = 0 ;

		if ( getdayRented() <= 2) return limit ;

		switch ( video.getVideoType() ) {
			case VHS: limit = 5 ; break ;
			case CD: limit = 3 ; break ;
			case DVD: limit = 2 ; break ;
		}
		return limit ;
	}

	public int getdayRented() {
		int daysRented;
		long diff = 0;
		if (getStatus() == statusType.Returned) { // returned Video
			diff = returnDate.getTime() - rentDate.getTime();

		} else { // not yet returned
			diff = new Date().getTime() - rentDate.getTime();
		}
		daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		return daysRented;
	}
}
