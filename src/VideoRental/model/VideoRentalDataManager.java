package VideoRental.model;

import java.util.ArrayList;
import java.util.List;

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
}
