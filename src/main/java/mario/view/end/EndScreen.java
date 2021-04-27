package mario.view.end;

import mario.MainApp;
import mario.model.Player;
import mario.model.score.Highscores;
import mario.model.score.Score;
import mario.ui.Button;
import mario.ui.MouseAdapter;
import mario.view.Screen;
import mario.view.game.GameView;
import mario.view.menu.MenuScreen;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.view.View;
import processing.core.PImage;
import java.util.List;

/**
 * This class represents an end screen where
 * the player can see the high scores, go back
 * to the menu screen or choose to play again.
 */
public class EndScreen extends Screen {

    private static final int BUTTON_WIDTH = 350;
    private static final int BUTTON_HEIGHT = 100;

    private final Player player;

    public EndScreen(MainApp app, Player player) {
        super(app);
        this.player = player;
        super.render();
    }

    @Override
    public void addObjects() {
        final String endText = this.player.isSuccessFull() ? "You won!" : "You lost!";
        final int endTextFontSize = 100;

        addText(endText, endTextFontSize, this.app.getWidth() / 2f - getTextWidth(endText, endTextFontSize) / 2f, endTextFontSize * .5f);

        final String highscores = "Highscores:";
        final int highscoresFontSize = 50;
        addText(highscores, highscoresFontSize, this.app.getWidth() / 2f - getTextWidth(highscores, highscoresFontSize) / 2f, 200);

        addHighScores();

        this.app.addGameObject(
                createMenuButton(),
                this.app.getWidth() / 2f - createPlayAgainButton().getWidth() * 1.05f,
                this.app.getHeight() - createPlayAgainButton().getHeight() * 2.25f);

        this.app.addGameObject(
                createPlayAgainButton(),
                this.app.getWidth() / 2f + createMenuButton().getWidth() * .05f,
                this.app.getHeight() - createMenuButton().getHeight() * 2.25f);

    }

    /**
     * This method adds text for the end screen.
     * @param text the text to show.
     * @param fontSize the font size of the text.
     * @param xPos the x-position of the text.
     * @param yPos the y-position of the text.
     */
    private void addText(String text, int fontSize, float xPos, float yPos) {
        TextObject label = new TextObject(text, fontSize);
        label.setForeColor(0, 0, 0, 255);

        this.app.addGameObject(label, xPos, yPos);
    }

    /**
     * This helper method returns the width of a string.
     * @param string a string.
     * @param fontSize a font size.
     * @return float the width of the string.
     */
    private float getTextWidth(String string, int fontSize) {
        this.app.textSize(fontSize);
        return this.app.textWidth(string);
    }

    /**
     * This method adds the high scores to the end screen.
     */
    private void addHighScores() {
        final List<Score> highscores = Highscores.loadHighscores();
        final int fontSize = 32;
        final float maxNameWidth = getMaxNameWidth(highscores, fontSize) / 10f; // To align the names you need to know the length of the longest name.

        int yPos = 275;

        for (Score score : highscores) {

            this.addText(score.getName(), fontSize, this.app.getWidth() / 2f - getMaxNameWidth(highscores, fontSize), yPos);
            this.addText(score.getTime(), fontSize,  this.app.getWidth() / 2f + maxNameWidth, yPos);

            yPos += fontSize * 1.5f;
        }
    }

    /**
     * This method returns the length of the longest name
     * in the high scores list.
     * @param highscores a list of high scores.
     * @param fontSize a font size.
     * @return float the length of the name.
     */
    @SuppressWarnings("SameParameterValue")
    private float getMaxNameWidth(List<Score> highscores, int fontSize) {
        float maxWidth = 0;

        this.app.textSize(fontSize);

        for (Score score : highscores)  {

            if (this.app.textWidth(score.getName()) > maxWidth) {
                maxWidth = this.app.textWidth(score.getName());
            }

        }
        return maxWidth;
    }


    /**
     * This methods creates a button that starts
     * a new game for the same player.
     * @return Button a button.
     */
    private Button createPlayAgainButton() {
        final Button playAgainButton = new Button(new Sprite(MainApp.MEDIA_URL.concat("media/sprites/buttons/play_again_button.png")), BUTTON_WIDTH, BUTTON_HEIGHT);
        playAgainButton.addListener(new MouseAdapter() {

            @Override
            public void mousePressed(int x, int y, int button) {
                new GameView(EndScreen.this.app, EndScreen.this.player);
            }
        });
        return playAgainButton;
    }

    /**
     * This method creates a button that takes the
     * player back to the menu screen.
     * @return Button a button.
     */
    private Button createMenuButton() {
        final Button createMenuButton = new Button(new Sprite(MainApp.MEDIA_URL.concat("media/sprites/buttons/back_to_menu_button.png")), BUTTON_WIDTH, BUTTON_HEIGHT);
        createMenuButton.addListener(new MouseAdapter() {

            @Override
            public void mousePressed(int x, int y, int button) {
                new MenuScreen(EndScreen.this.app);
            }
        });
        return createMenuButton;
    }

    @Override
    public View createView() {
        View view = new View(this.app.getWidth(), this.app.getHeight());

        PImage backgroundImage = this.app.loadImage(MainApp.MEDIA_URL.concat("media/background/end_screen.jpg"));

        view.setBackground(backgroundImage);

        return view;
    }
}
