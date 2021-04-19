package mario;

import mario.ui.ButtonObject;
import mario.ui.MouseListener;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.view.View;

public final class StartMenu {

    private final MainApp app;

    public StartMenu(MainApp app) {

        this.app = app;
        this.createView();
        this.addTitle();
        this.addButtons();
    }

    private void addTitle() {

        final int fontSize = 60;
        final String title = "SUPER MARIO SPEEDRUNNER";

        this.app.textSize(fontSize);

        TextObject titleObject = new TextObject (title, fontSize);
        titleObject.setForeColor(255, 0, 0, 255);

        this.app.addGameObject(titleObject, app.getWidth() / 2f - this.app.textWidth(titleObject.getText()) / 2, 50);
    }

    private void addButtons() {

        final int buttonWidth = 200;
        final int buttonHeight = 100;

        ButtonObject playButtonObject = new ButtonObject(new Sprite(MainApp.MEDIA_URL.concat("/sprites/buttons/play_red.png")), buttonWidth, buttonHeight);
        playButtonObject.addListener(new MouseListener() {

            @Override
            public void mousePressed(int x, int y, int button)  {
                System.out.println("Pressed play button.");
                // To do: implement play button action.
            }
        });

        ButtonObject exitButtonObject = new ButtonObject(new Sprite(MainApp.MEDIA_URL.concat("/sprites/buttons/exit_red.png")), buttonWidth, buttonHeight);
        exitButtonObject.addListener(new MouseListener() {

            @Override
            public void mousePressed(int x, int y, int button) {
                System.out.println("Pressed exit button.");
                // To do: implement exit button action.
            }

        });

        this.app.addGameObject(playButtonObject, this.app.getWidth() / 2f - playButtonObject.getWidth() / 2f, 160);
        this.app.addGameObject(exitButtonObject, this.app.getWidth() / 2f - exitButtonObject.getWidth() / 2f, 310);

    }

    private void createView() {

        View view = new View(app.getWidth(), app.getHeight());
        this.app.setView(view);
    }



}
