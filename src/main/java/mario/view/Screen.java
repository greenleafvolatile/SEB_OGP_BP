package mario.view;

import mario.MainApp;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.view.View;

import java.util.Vector;

public abstract class Screen {

    protected MainApp app;

    public Screen(MainApp app) {
        this.app = app;
        this.app.setView(createView());
        addObjects();
    }

    public abstract void addObjects();
    public abstract View createView();

}
