package christmas;

import christmas.Controller.EventController;

public class Application {
    public static void main(String[] args) {
        EventController eventController = new EventController();
        eventController.run();
    }
}
