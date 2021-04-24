package mario.view;

import mario.MainApp;
import nl.han.ica.oopg.view.View;

public abstract class Screen {

    protected MainApp app;

    public Screen(MainApp app) {
        this.app = app;
    }

    public void setupView(View view) {
        this.app.setView(view);
    }
}
