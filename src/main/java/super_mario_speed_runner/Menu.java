package super_mario_speed_runner;

import nl.han.ica.oopg.objects.TextObject;

public class Menu {

    private MainApp app;

    public Menu(MainApp app) {
        this.app = app;
        System.out.println("MENU");

        TextObject to = new TextObject("Hello, World!", 40);
        to.setForeColor(255, 255, 255, 255);
        app.addGameObject(to, 100, 100);
    }

}
