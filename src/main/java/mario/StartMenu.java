package mario;

import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.view.View;

public class StartMenu {

    private MainApp app;

    public StartMenu(MainApp app) {
        this.app = app;
        //System.out.println("Start");
        View view = new View(this.app.getWidth(), this.app.getHeight());
        this.app.setView(view);

        TextObject to = new TextObject("Start menu", 40);
        to.setForeColor(255, 255, 255, 255);
        app.addGameObject(to, 100, 100);
    }

}
