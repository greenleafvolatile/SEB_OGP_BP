package mario.view;

import mario.MainApp;
import nl.han.ica.oopg.view.View;

public abstract class Screen {

    protected MainApp app;

    public Screen(MainApp app) {
        this.app = app;
        this.app.setView(createView());
        this.addObjects();
    }

    public abstract void addObjects();
    public abstract View createView();

}
