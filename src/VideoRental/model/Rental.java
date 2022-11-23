package VideoRental.model;

import java.util.Date;

public class Rental {
	private Video video ;

	// enum
	private int status ; // 0 for Rented, 1 for Returned
	private Date rentDate ;
	private Date returnDate ;

	public Rental(Video video) {
		this.video = video ;
		status = 0 ;
		rentDate = new Date() ;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public String getVideoInfo() {
		return video.getInfo();
	}

	public int getStatus() {
		return status;
	}

	public void returnVideo() {

		// TODO: Dead code, need to fixed
		//
		if ( status == 1 ) {
			this.status = 1;
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
		if (getStatus() == 1) { // returned Video
			diff = returnDate.getTime() - rentDate.getTime();

		} else { // not yet returned
			diff = new Date().getTime() - rentDate.getTime();
		}
		daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		return daysRented;
	}
}
