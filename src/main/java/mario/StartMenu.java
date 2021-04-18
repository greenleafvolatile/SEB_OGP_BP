package mario;

import mario.ui.UIButton;
import mario.ui.UIComponent;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.view.View;

import java.awt.*;
import java.util.ArrayList;

public class StartMenu {

    private MainApp app;
    private TextObject titleObject;
    private ArrayList<UIButton> buttons = new ArrayList<>();

    public StartMenu(MainApp app) {
        this.app = app;

        this.createView();
        this.addTitle();
        this.addButtons();
    }

    private void addTitle() {

        final int fontSize = 40;
        final String title = "SUPER MARIO SPEEDRUNNER";

        app.textSize(fontSize);
        float textWidth = app.textWidth(title);

        titleObject = new TextObject (title, fontSize);
        titleObject.setForeColor(255, 0, 0, 255);
        app.addGameObject(titleObject, app.getWidth() / 2f - textWidth / 2, 100);


    }

    private void addButtons() {




        UIButton startButton = new UIButton(app, app.MEDIA_URL.concat("/sprites/buttons/start.png"), 200, 100);
        UIButton highscoresButton = new UIButton(app, app.MEDIA_URL.concat("/sprites/buttons/highscores.png"), 200, 100);

        UIButton exitButton = new UIButton(app, app.MEDIA_URL.concat("/sprites/buttons/exit.png"), 200, 100);

        System.out.println(highscoresButton.getButtonImage().width);
        System.out.println(startButton.getButtonImage().width);

        app.addGameObject(highscoresButton, app.getWidth() / 2f - highscoresButton.getButtonWidth() / 2f, 150);
        System.out.println(highscoresButton.getY());


        app.addGameObject(startButton, app.getWidth() / 2f - startButton.getButtonWidth() / 2f, 250);
        app.addGameObject(exitButton, app.getWidth() / 2f - exitButton.getButtonWidth() / 2f, 350);

    }

    private void createView() {

        View view = new View(app.getWidth(), app.getHeight());
        this.app.setView(view);
    }



}
