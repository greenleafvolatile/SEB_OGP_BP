package mario;

import mario.ui.Button;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.view.View;

public class EndScreen {

    private final MainApp app;

    public EndScreen(MainApp app) {

        this.app = app;
        this.init();

    }

    private void init() {
        this.createView();
    }

    private void createView() {
        View view = new View(this.app.getWidth() , this.app.getHeight());

        // Insert background here.

        this.app.setView(view);

    }



}
