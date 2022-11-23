package VideoRental.model;

import VideoRental.common.CommonUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VideoRentalDataManager {

    private List<Customer> customers = new ArrayList<Customer>() ;

    private List<Video> videos = new ArrayList<Video>() ;

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        this.customers.remove(customer);
    }

    public List<Customer> getCustomers() {
        return this.customers;
    }

    public void addVideo(Video video) {
        this.videos.add(video);
    }

    public void removeVideo(Video video) {
        this.videos.remove(video);
    }

    public List<Video> getVideos() {
        return this.videos;
    }

    public void init() {
        Customer james = new Customer("James") ;
        Customer brown = new Customer("Brown") ;
        addCustomer(james);
        addCustomer(brown) ;

        Video v1 = new Video("v1", VideoType.CD, Video.REGULAR, new Date()) ;
        Video v2 = new Video("v2", VideoType.DVD, Video.NEW_RELEASE, new Date()) ;
        addVideo(v1);
        addVideo(v2) ;

        Rental r1 = new Rental(v1) ;
        Rental r2 = new Rental(v2) ;

        james.addRental(r1) ;
        james.addRental(r2) ;
    }

    public void clearRentals(Customer customer) {
        if (customer != null) {
            List<Rental> rentals = new ArrayList<Rental>();
            customer.setRentals(rentals);
        }
    }

    public void returnVideo(Customer customer, String videoTitle) {
        List<Rental> customerRentals = customer.getRentals() ;
        for ( Rental rental: customerRentals ) {
            final Video video = rental.getVideo();
            if ( video.getTitle().equals(videoTitle) && video.isRented() ) {
                rental.returnVideo();
                rental.getVideo().setRented(false);
                break ;
            }
        }
    }

    public void rentVideo(Customer foundCustomer, String videoTitle) {
        Video foundVideo = CommonUtils.findVideo(videoTitle, getVideos()) ;
        if ( foundVideo == null ) return;

        Rental rental = new Rental(foundVideo) ;
        foundVideo.setRented(true);

        // encapsulate collection
        foundCustomer.addRental(rental);
    }
}
