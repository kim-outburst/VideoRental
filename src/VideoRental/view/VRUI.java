package VideoRental.view;

import VideoRental.common.CommonUtils;
import VideoRental.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VRUI {
	private static final Scanner scanner = new Scanner(System.in) ;

	private final VideoRentalDataManager dataManager;

	public VRUI(VideoRentalDataManager dataManager) {
		this.dataManager = dataManager;
	}

	// separate query from modifier
	public void clearRentals() {
		System.out.println("Enter customer name: ") ;
		final String customerName = scanner.next() ;
		final Customer foundCustomer = CommonUtils.findCustomer(customerName, this.dataManager.getCustomers());

		showCustomer(foundCustomer);

		this.dataManager.clearRentals(foundCustomer);
	}

	private void showCustomer(Customer foundCustomer) {
		if ( foundCustomer == null ) {
			System.out.println("No customer found") ;
		} else {
			System.out.println(foundCustomer.getRentalsInfo()) ;
			for ( Rental rental: foundCustomer.getRentals() ) {
				System.out.print(rental.getVideoInfo()) ;
			}
		}
	}


	public void init() {
		this.dataManager.init();
//		System.out.println("Initialize Success");
	}

	public void listVideos() {
		System.out.println("List of videos");
		List<Video> videos = this.dataManager.getVideos();
		for ( Video video: videos ) {
			System.out.println(video.getInfo()) ;
		}
		System.out.println("End of list");
	}

	public void listCustomers() {
		System.out.println("List of customers");
		List<Customer> customers = this.dataManager.getCustomers();
		for ( Customer customer: customers ) {
			System.out.println(customer.getRentalsInfo()) ;
			for ( Rental rental: customer.getRentals() ) {
				System.out.print(rental.getVideoInfo()) ;
			}
		}
		System.out.println("End of list");
	}

	public void getCustomerReport() {
		System.out.println("Enter customer name: ") ;
		String customerName = scanner.next() ;

		Customer foundCustomer = CommonUtils.findCustomer(customerName, this.dataManager.getCustomers());

		if ( foundCustomer == null ) {
			System.out.println("No customer found") ;
		} else {
			String result = foundCustomer.getReport() ;
			System.out.println(result);
		}
	}


	public int showCommand() {
		System.out.println("\nSelect a command !");
		System.out.println("\t 0. Quit");
		System.out.println("\t 1. List customers");
		System.out.println("\t 2. List videos");
		System.out.println("\t 3. Register customer");
		System.out.println("\t 4. Register video");
		System.out.println("\t 5. Rent video");
		System.out.println("\t 6. Return video");
		System.out.println("\t 7. Show customer report");
		System.out.println("\t 8. Show customer and clear rentals");
		return scanner.nextInt() ;
	}
}
