package VideoRental.view;

import VideoRental.common.CommonUtils;
import VideoRental.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VRUI {
	private static final Scanner scanner = new Scanner(System.in) ;

	private static final String[] MENU_LIST = {
		"\nSelect a command !",
		"\t 0. Quit",
		"\t 1. List customers",
		"\t 2. List videos",
		"\t 3. Register customer",
		"\t 4. Register video",
		"\t 5. Rent video",
		"\t 6. Return video",
		"\t 7. Show customer report",
		"\t 8. Show customer and clear rentals",
	};

	private final VideoRentalDataManager dataManager;

	public VRUI(VideoRentalDataManager dataManager) {
		this.dataManager = dataManager;
	}

	public int showCommand() {
		for (String menu : MENU_LIST) {
			System.out.println(menu);
		}
		return scanner.nextInt() ;
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

	public void returnVideo() {
		System.out.println("Enter customer name: ") ;
		String customerName = scanner.next() ;

		Customer foundCustomer = CommonUtils.findCustomer(customerName, this.dataManager.getCustomers());
		if ( foundCustomer == null ) return ;

		System.out.println("Enter video title to return: ") ;
		String videoTitle = scanner.next() ;

		this.dataManager.returnVideo(foundCustomer, videoTitle);
	}

	public void rentVideo() {
		System.out.println("Enter customer name: ") ;
		String customerName = scanner.next() ;

		Customer foundCustomer = CommonUtils.findCustomer(customerName, this.dataManager.getCustomers());

		if ( foundCustomer == null ) return ;

		System.out.println("Enter video title to rent: ") ;
		String videoTitle = scanner.next() ;

		this.dataManager.rentVideo(foundCustomer, videoTitle);
	}

	public void registerCustomer() {
		System.out.println("Enter customer name: ") ;
		String name = scanner.next();
		this.dataManager.addCustomer(new Customer(name)) ;
	}

	public void registerVideo() {
		System.out.println("Enter video title to register: ") ;
		String title = scanner.next() ;

		System.out.println("Enter video type( 1 for VHD, 2 for CD, 3 for DVD ):") ;
		int videoType = scanner.nextInt();

		System.out.println("Enter price code( 1 for Regular, 2 for New Release ):") ;
		int priceCode = scanner.nextInt();

		Date registeredDate = new Date();
		this.dataManager.addVideo(new Video(title, VideoType.convert(videoType), priceCode, registeredDate)) ;
	}
}
