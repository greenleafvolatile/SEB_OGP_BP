package mario.view;

import mario.MainApp;
import nl.han.ica.oopg.view.View;

/**
 * The type Screen.
 */
public abstract class Screen {

    /**
     * The App.
     */
    protected final MainApp app;

    /**
     * Instantiates a new Screen.
     *
     * @param app the app
     */
    protected Screen(MainApp app) {
        this.app = app;
        dispose();
    }


    /**
     * Add objects.
     */
    public abstract void addObjects();

    /**
     * Create new View instance.
     *
     * @return the view
     */
    public abstract View createView();

    /**
     * Render Screen with instance of View and GameObjects.
     */
    protected void render() {
        this.app.setView(createView());
        this.addObjects();
    }

    /**
     * Dispose all GameObjects and Dashboards.
     */
    protected void dispose() {
        this.app.deleteAllGameOBjects();
        this.app.deleteAllDashboards();
    }

}
