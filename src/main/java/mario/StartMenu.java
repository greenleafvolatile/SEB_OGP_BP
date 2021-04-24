package mario;

import mario.ui.*;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.view.View;
import processing.core.PConstants;
import processing.core.PImage;

public final class StartMenu {

    private final MainApp app;
    //private final StateManager manager;
    private TextField textField;

    public StartMenu(MainApp app) { //}, StateManager manager) {

        this.app = app;
        //this.manager = manager;
        this.createView();
        this.addLogo();
        this.addLabel();
        this.addTextField();
        this.addButtons();
    }

    private void addLogo() {
        Image logo = new Image(new Sprite(MainApp.MEDIA_URL.concat("logo.png")));
        this.app.addGameObject(logo, app.getWidth() / 2f - 225, 50);
    }

    private void addLabel() {
        final int fontSize = 40;
        String labelText = "Enter name:";
        this.app.textSize(40);

        TextObject textFieldLabel = new TextObject(labelText, 40);
        textFieldLabel.setForeColor(0, 0, 255, 255);
        this.app.addGameObject(textFieldLabel, app.getWidth() / 2f - app.textWidth(labelText), 200 + fontSize / 4f);
    }

    private void addButtons() {

        final int buttonWidth = 350;
        final int buttonHeight = 100;

        Button playButton = new Button(new Sprite(MainApp.MEDIA_URL.concat("/sprites/buttons/play_button.png")), buttonWidth, buttonHeight);
        playButton.addListener(new MouseListener() {

            @Override
            public void mousePressed(int x, int y, int button)  {

                StartMenu.this.app.deleteAllGameOBjects();
                new Game(StartMenu.this.app, StartMenu.this.textField.getPlayerName());


            }
        });

        Button exitButton = new Button(new Sprite(MainApp.MEDIA_URL.concat("/sprites/buttons/exit_button.png")), buttonWidth, buttonHeight);
        exitButton.addListener(new MouseListener() {

            @Override
            public void mousePressed(int x, int y, int button) {
                System.out.println("Pressed exit button.");
                System.exit(0);
            }
        });

        this.app.addGameObject(playButton, this.app.getWidth() / 2f - playButton.getWidth() / 2f, 300);

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

        this.app.addGameObject(textField, this.app.getWidth() / 2f, 210);

    }

    private void createView() {

        View view = new View(this.app.getWidth(), this.app.getHeight());

        PImage image = this.app.loadImage(MainApp.MEDIA_URL.concat("/background/menu_background.jpg"));
        view.setBackground(image);

        this.app.setView(view);
    }



}
