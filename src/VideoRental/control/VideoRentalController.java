package VideoRental.control;

import VideoRental.model.Customer;
import VideoRental.model.Video;
import VideoRental.model.VideoRentalDataManager;
import VideoRental.view.VRUI;

public class VideoRentalController {

    private final VRUI ui;

    private final VideoRentalDataManager dataManager;

    public VideoRentalController(VideoRentalDataManager dataManager) {
        this.dataManager = dataManager;
        this.ui = new VRUI(dataManager);
    }

    public int showCommand() {
        return this.ui.showCommand();
    }

    public void addCustomer(Customer customer) {
        this.dataManager.addCustomer(customer);
    }

    public void addVideo(Video video) {
        this.dataManager.addVideo(video);
    }

    public void runCommand(int command) {
        switch ( command ) {
            case 1: ui.listCustomers() ; break ;
            case 2: ui.listVideos() ; break ;
            case 3: addCustomer(ui.registerCustomer()); ; break ;
            case 4: addVideo(ui.registerVideo()); ; break ;
            case 5: ui.rentVideo() ; break ;
            case 6: ui.returnVideo() ; break ;
            case 7: ui.getCustomerReport() ; break;
            case 8: ui.clearRentals() ; break ;
            case -1: ui.init() ; break ;
            default: break ;
        }
    }
}
