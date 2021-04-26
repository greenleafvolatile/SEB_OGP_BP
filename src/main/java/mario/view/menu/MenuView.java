package mario.view.menu;

import mario.MainApp;
import mario.model.Player;
import mario.ui.*;
import mario.view.Screen;
import mario.view.game.GameView;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.view.View;
import processing.core.PConstants;
import processing.core.PImage;

/**
 * The type Menu view.
 */
public class MenuView extends Screen {

    private final int buttonWidth = 350;
    private final int buttonHeight = 100;

    private TextField textField;

    /**
     * Instantiates a new Menu view.
     *
     * @param app the app
     */
    public MenuView(MainApp app) {
        super(app);
        super.render();
    }

    @Override
    public void addObjects() {

        this.addLabel();

        this.app.addGameObject(
                new Image(new Sprite(MainApp.MEDIA_URL.concat("logo.png"))),
                app.getWidth() / 2f - 225,
                50
        );

        this.app.addGameObject(
                playButton(),
                this.app.getWidth() / 2f - playButton().getWidth() / 2f,
                300
        );

        this.app.addGameObject(
                exitButton(),
                this.app.getWidth() / 2f - exitButton().getWidth() / 2f,
                430
        );

        this.createTextField();

        this.app.addGameObject(
                this.textField,
                this.app.getWidth() / 2f,
                210
        );

    }

    // TODO Maybe create a class for text labels...?
    private void addLabel() {
        final int fontSize = 40;
        String labelText = "Enter name:";
        this.app.textSize(40);

        TextObject textFieldLabel = new TextObject(labelText, 40);
        textFieldLabel.setForeColor(0, 0, 255, 255);
        this.app.addGameObject(textFieldLabel, app.getWidth() / 2f - app.textWidth(labelText), 200 + fontSize / 4f);
    }

    private Button playButton() {

        Button playButton = new Button(new Sprite(MainApp.MEDIA_URL.concat("/sprites/buttons/play_button.png")), buttonWidth, buttonHeight);
        playButton.addListener(new MouseListener() {

            @Override
            public void mousePressed(int x, int y, int button) {
                new GameView(MenuView.this.app, new Player(MenuView.this.app, MenuView.this.textField.getInputValue()));
            }
        });
        return playButton;
    }

    private Button exitButton() {

        Button exitButton = new Button(new Sprite(MainApp.MEDIA_URL.concat("/sprites/buttons/exit_button.png")), buttonWidth, buttonHeight);
        exitButton.addListener(new MouseListener() {

            @Override
            public void mousePressed(int x, int y, int button) {
                System.exit(0);
            }
        });
        return exitButton;
    }

    private void createTextField() {

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
    }

    @Override
    public View createView() {
        View view = new View(this.app.getWidth(), this.app.getHeight());

        PImage image = this.app.loadImage(MainApp.MEDIA_URL.concat("/background/menu_background.jpg"));
        view.setBackground(image);

        return view;
    }

}
