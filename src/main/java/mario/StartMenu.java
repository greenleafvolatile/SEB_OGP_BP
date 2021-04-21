package mario;

import mario.ui.*;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.view.View;
import processing.core.PConstants;
import processing.core.PImage;

public final class StartMenu {

    private final MainApp app;
    private final StateManager manager;
    private TextField textField;

    public StartMenu(MainApp app, StateManager manager) {

        this.app = app;
        this.manager = manager;
        this.createView();
        this.addTitle();
        this.addLabel();
        this.addTextField();
        this.addButtons();
    }

    private void addTitle() {

        final int fontSize = 60;
        final String title = "SUPER MARIO SPEEDRUNNER";

        this.app.textSize(fontSize);

        TextObject titleObject = new TextObject(title, fontSize);
        titleObject.setForeColor(255, 0, 0, 255);

        this.app.addGameObject(titleObject, app.getWidth() / 2f - app.textWidth(title) / 2, 50);
    }

    private void addLabel() {

        final int fontSize = 40;
        String labelText = "Enter name:";
        this.app.textSize(40);

        TextObject textFieldLabel = new TextObject(labelText, 40);
        textFieldLabel.setForeColor(0, 0, 255, 255);
        this.app.addGameObject(textFieldLabel, app.getWidth() / 2f - app.textWidth(labelText), 160 + fontSize / 4f);



    }

    private void addButtons() {

        final int buttonWidth = 200;
        final int buttonHeight = 100;

        Button playButton = new Button(new Sprite(MainApp.MEDIA_URL.concat("/sprites/buttons/play_red.png")), buttonWidth, buttonHeight);
        playButton.addListener(new MouseListener() {

            @Override
            public void mousePressed(int x, int y, int button)  {

                //StartMenu.this.manager.setGameState(GameState.GAME);
                StartMenu.this.app.deleteAllGameOBjects();
                new Game(StartMenu.this.app, StartMenu.this.textField.getPlayerName());


            }
        });

        Button exitButton = new Button(new Sprite(MainApp.MEDIA_URL.concat("/sprites/buttons/exit_red.png")), buttonWidth, buttonHeight);
        exitButton.addListener(new MouseListener() {

            @Override
            public void mousePressed(int x, int y, int button) {
                System.exit(0);
            }

        });

        this.app.addGameObject(playButton, this.app.getWidth() / 2f - playButton.getWidth() / 2f, 250);
        this.app.addGameObject(exitButton, this.app.getWidth() / 2f - exitButton.getWidth() / 2f, 360);

    }

    private void addTextField() {

        this.textField = new TextField( 200, 50);
        textField.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(int intValue, char charValue) {

                if (intValue == PConstants.BACKSPACE) {
                    textField.removeChar();
                }
                else if (Character.isLetterOrDigit(charValue)) {
                    textField.addChar(charValue);
                }
            }
        });

        this.app.addGameObject(textField, this.app.getWidth() / 2f, 160);

    }

    private void createView() {

        View view = new View(this.app.getWidth(), this.app.getHeight());

        PImage image = this.app.loadImage(MainApp.MEDIA_URL.concat("/background/mario_background_one.jpg"));
        view.setBackground(image);

        this.app.setView(view);
    }



}
