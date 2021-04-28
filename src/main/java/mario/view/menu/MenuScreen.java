package mario.view.menu;

import mario.MainApp;
import mario.model.Player;
import mario.ui.*;
import mario.view.Screen;
import mario.view.game.GameScreen;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.view.View;
import processing.core.PConstants;
import processing.core.PImage;

/**
 * This class represents a start menu.
 *
 * @author Christiaan Wiggers en Daan Pol
 * @version 1.1.
 * @since 27-04-2021
 */
public class MenuScreen extends Screen {

    private static final int BUTTON_WIDTH = 350;
    private static final int BUTTON_HEIGHT= 100;

    private TextField textField;

    /**
     * The constructor
     *
     * @param app an instance of the game engine.
     */
    public MenuScreen(MainApp app) {
        super(app);
        super.render();
    }

    @Override
    public void addObjects() {

        this.addTextFieldLabel();

        this.app.addGameObject(
                new Image(new Sprite(MainApp.MEDIA_URL.concat("media/sprites/logo.png"))),
                app.getWidth() / 2f - 225,
                50
        );

        this.app.addGameObject(
                createPlayButton(),
                this.app.getWidth() / 2f - createPlayButton().getWidth() / 2f,
                300
        );

        this.app.addGameObject(
                createExitButton(),
                this.app.getWidth() / 2f - createExitButton().getWidth() / 2f,
                430
        );

        this.createTextField();

        this.app.addGameObject(
                this.textField,
                this.app.getWidth() / 2f,
                210
        );
    }

    /**
     * This method adds a label to the text field.
     */
    private void addTextFieldLabel() {
        final int fontSize = 40;
        final int yPos = 200;
        final String labelText = "Enter name:";

        this.app.textSize(fontSize);

        TextObject textFieldLabel = new TextObject(labelText, fontSize);
        textFieldLabel.setForeColor(0, 0, 255, 255);
        this.app.addGameObject(textFieldLabel, this.app.getWidth() / 2f - this.app.textWidth(labelText), yPos + fontSize / 4f);
    }

    /**
     * This method creates a button that
     * starts the game.
     * @return Button a button.
     */
    private Button createPlayButton() {
        final Button createPlayButton = new Button(new Sprite(MainApp.MEDIA_URL.concat("media/sprites/buttons/play_button.png")), BUTTON_WIDTH, BUTTON_HEIGHT);
        createPlayButton.addListener(new MouseAdapter() {

            @Override
            public void mousePressed(int x, int y, int button) {
                new GameScreen(MenuScreen.this.app, new Player(MenuScreen.this.app, MenuScreen.this.textField.getInputValue()));
            }
        });
        return createPlayButton;
    }

    /**
     * This method creates a button for
     * exiting the game.
     * @return Button a button.
     */
    private Button createExitButton() {
        final Button createExitButton = new Button(new Sprite(MainApp.MEDIA_URL.concat("media/sprites/buttons/exit_button.png")), BUTTON_WIDTH, BUTTON_HEIGHT);
        createExitButton.addListener(new MouseAdapter() {

            @Override
            public void mousePressed(int x, int y, int button) {
                System.exit(0);
            }
        });
        return createExitButton;
    }

    /**
     * This methods creates a text field for entering
     * the user's name.
     */
    private void createTextField() {
        final int textFieldWidth = 200;
        final int textFieldHeight = 50;

        this.textField = new TextField( textFieldWidth, textFieldHeight);
        this.textField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(int intValue, char charValue) {

                if (intValue == PConstants.BACKSPACE) {
                    MenuScreen.this.textField.removeChar();
                } else if (Character.isLetterOrDigit(charValue)) {
                    MenuScreen.this.textField.addChar(charValue);
                } else if (intValue == PConstants.ENTER) {
                    new GameScreen(MenuScreen.this.app, new Player(MenuScreen.this.app, MenuScreen.this.textField.getInputValue()));
                }
            }
        });
    }

    @Override
    public View createView() {
        View view = new View(this.app.getWidth(), this.app.getHeight());

        PImage image = this.app.loadImage(MainApp.MEDIA_URL.concat("media/background/menu_background.jpg"));
        view.setBackground(image);

        return view;
    }
}
