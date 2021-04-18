package super_mario_speed_runner;

import nl.han.ica.oopg.objects.TextObject;

public class StartMenu {

    private MainApp app;

    public StartMenu(MainApp app) {
        this.app = app;
        //System.out.println("Start");

        TextObject to = new TextObject("Start menu", 40);
        to.setForeColor(255, 255, 255, 255);
        app.addGameObject(to, 100, 100);
    }

}
