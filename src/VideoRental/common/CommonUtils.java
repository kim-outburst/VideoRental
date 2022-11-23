package VideoRental.common;

import VideoRental.model.Customer;
import VideoRental.model.Video;

import java.util.List;

public class CommonUtils {

    public static Customer findCustomer(String customerName, List<Customer> customers) {
        Customer foundCustomer = null ;
        for ( Customer customer: customers ) {
            if ( customer.getName().equals(customerName)) {
                foundCustomer = customer ;
                break ;
            }
        }
        return foundCustomer ;
    }

    public static Video findVideo(String videoTitle, List<Video> videos) {
        Video foundVideo = null ;
        for ( Video video: videos ) {
            if ( video.getTitle().equals(videoTitle) && video.isRented() == false ) {
                foundVideo = video ;
                break ;
            }
        }
        return foundVideo ;
    }
}
