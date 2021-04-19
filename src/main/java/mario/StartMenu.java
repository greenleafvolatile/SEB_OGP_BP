package mario;

import mario.ui.ButtonObject;
import mario.ui.MouseListener;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.view.View;

public class StartMenu {

    private MainApp app;

    public StartMenu(MainApp app) {

        this.app = app;
        this.createView();
        this.addTitle();
        this.addButtons();
    }

    private void addTitle() {

        final int fontSize = 60;
        final String title = "SUPER MARIO SPEEDRUNNER";

        app.textSize(fontSize);
        float textWidth = app.textWidth(title);

        TextObject titleObject = new TextObject (title, fontSize);
        titleObject.setForeColor(255, 0, 0, 255);
        app.addGameObject(titleObject, app.getWidth() / 2f - textWidth / 2, 100);

    }

    private void addButtons() {

        ButtonObject playButtonObject = new ButtonObject(new Sprite(MainApp.MEDIA_URL.concat("/sprites/buttons/play_red.png")), 200, 100);
        playButtonObject.addListener(new MouseListener() {

            @Override
            public void mousePressed(int x, int y, int button)  {
                System.out.println("Pressed play button.");
                // To do: implement play button action.
            }
        });

        ButtonObject exitButtonObject = new ButtonObject(new Sprite(MainApp.MEDIA_URL.concat("/sprites/buttons/exit_red.png")), 200, 100);
        exitButtonObject.addListener(new MouseListener() {

            @Override
            public void mousePressed(int x, int y, int button) {
                System.out.println("Pressed exit button.");
                // To do: implement exit button action.
            }

        });

        this.app.addGameObject(playButtonObject, this.app.getWidth() / 2f - playButtonObject.getWidth() / 2f, 150);

        this.app.addGameObject(exitButtonObject, this.app.getWidth() / 2f - exitButtonObject.getWidth() / 2f, 300);


    }

    private void createView() {

        View view = new View(app.getWidth(), app.getHeight());
        this.app.setView(view);
    }



}
