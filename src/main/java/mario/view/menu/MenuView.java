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
 * This class represents a start menu.
 */
public class MenuView extends Screen {

    private final int buttonWidth = 350;
    private final int buttonHeight = 100;

    private TextField textField;

    /**
     * The constructor
     *
     * @param app an instance of the game engine.
     */
    public MenuView(MainApp app) {
        super(app);
        super.render();
    }

    @Override
    public void addObjects() {

        this.addTextFieldLabel();

        this.app.addGameObject(
                new Image(new Sprite(MainApp.MEDIA_URL.concat("logo.png"))),
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
    // TODO Maybe create a class for text labels...?
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
        final Button createPlayButton = new Button(new Sprite(MainApp.MEDIA_URL.concat("/sprites/buttons/play_button.png")), this.buttonWidth, this.buttonHeight);
        createPlayButton.addListener(new MouseAdapter() {

            @Override
            public void mousePressed(int x, int y, int button) {
                new GameView(MenuView.this.app, new Player(MenuView.this.app, MenuView.this.textField.getInputValue()));
            }
        });
        return createPlayButton;
    }

    /**
     * This method creates a button for
     * exiting the game.
     * @return
     */
    private Button createExitButton() {
        final Button createExitButton = new Button(new Sprite(MainApp.MEDIA_URL.concat("/sprites/buttons/exit_button.png")), this.buttonWidth, this.buttonHeight);
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
                    MenuView.this.textField.removeChar();
                } else if (Character.isLetterOrDigit(charValue)) {
                    MenuView.this.textField.addChar(charValue);
                } else if (intValue == PConstants.ENTER) {
                    new GameView(MenuView.this.app, new Player(MenuView.this.app, MenuView.this.textField.getInputValue()));
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
