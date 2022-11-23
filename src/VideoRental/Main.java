package VideoRental;

import VideoRental.control.VideoRentalController;
import VideoRental.model.VideoRentalDataManager;
import VideoRental.view.VRUI;

public class Main {

    public static void main(String[] args) {
        final VideoRentalController controller = new VideoRentalController(new VideoRentalDataManager());
        boolean quit = false ;
        while ( ! quit ) {
            int command = controller.showCommand() ;
            if (command == 0) {
                quit = true;
            } else {
                controller.runCommand(command);
            }
        }
        System.out.println("Bye");
    }
}
