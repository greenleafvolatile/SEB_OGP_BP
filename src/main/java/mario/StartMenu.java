package mario;

import mario.ui.*;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.view.View;
import processing.core.PConstants;

import javax.xml.soap.Text;

public final class StartMenu {

    private final MainApp app;

    public StartMenu(MainApp app) {

        this.app = app;
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
        textFieldLabel.setForeColor(255, 0, 0, 255);
        this.app.addGameObject(textFieldLabel, app.getWidth() / 2f - app.textWidth(labelText), 160 + fontSize / 4f);



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

        this.app.addGameObject(playButtonObject, this.app.getWidth() / 2f - playButtonObject.getWidth() / 2f, 250);
        this.app.addGameObject(exitButtonObject, this.app.getWidth() / 2f - exitButtonObject.getWidth() / 2f, 360);

    }

    private void addTextField() {

        TextFieldObject textFieldObject = new TextFieldObject( 200, 50);
        textFieldObject.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(int intValue, char charValue) {

                if (intValue == PConstants.BACKSPACE) {
                    textFieldObject.removeChar();
                }
                else if (Character.isLetterOrDigit(charValue)) {
                    textFieldObject.addChar(charValue);
                }
            }
        });

        this.app.addGameObject(textFieldObject, this.app.getWidth() / 2f, 160);

    }

    private void createView() {

        View view = new View(app.getWidth(), app.getHeight());
        this.app.setView(view);
    }



}
