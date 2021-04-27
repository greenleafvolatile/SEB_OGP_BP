package mario.view.game;

import mario.model.Player;
import mario.model.Timer;
import mario.model.score.Highscores;
import mario.model.score.Score;
import mario.view.game.sprites.HeartSprite;
import mario.view.game.sprites.KeySprite;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.view.PGraphicsCreator;
import processing.core.PGraphics;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a dashboard overlay for
 * showing the player's number of lives, the number of
 * keys picked up and the time elapsed since the start of
 * the game.
 */
public class GameDashboard extends Dashboard {

    private static final int X_MARGIN = 40;
    private static final int Y_MARGIN = 20;
    private static final int FONT_SIZE = 32;

    private final Timer timer;
    private final List<HeartSprite> hearts = new ArrayList<>();
    private final PGraphics graphics; // Need a PGraphics object to determine the width of a string.
    private final Player player;
    private TextObject time;

    /**
     * The constructor.
     * @param x the x-position of the dashboard.
     * @param y the y-position of the dashboard.
     * @param width the width of the dashboard.
     * @param height the height of the dashboard.
     * @param player the player.
     */
    public GameDashboard(float x, float y, float width, float height, Player player) {
        super(x, y, width, height);
        this.player = player;
        this.graphics = new PGraphicsCreator().createPGraphics((int) width, (int) height);
        this.timer = new Timer();
        this.init();
    }

    /**
     * This method initializes the dashboard to its starting state.
     */
    private void init() {
        this.graphics.textSize(FONT_SIZE);

        String keysLabelText = "keys:";
        this.addLabel(keysLabelText, (int) (this.width - X_MARGIN - this.graphics.textWidth(keysLabelText)), Y_MARGIN, 255, 255, 255);

        this.addLabel("Player:" + this.player.getName(), X_MARGIN, Y_MARGIN, 255, 255, 255);

        this.createTimeLabel();

        this.initHearts();
        this.addHearts();

        this.timer.startTimer();
    }

    /**
     * This method initializes the hearts array.
     */
    private void initHearts() {
        for (int i = 0; i < this.player.getHealth(); i++) {
            hearts.add(new HeartSprite());
        }
    }

    /**
     * This methods creates a label for showing
     * the time on the dashboard.
     */
    private void createTimeLabel() {
        this.time = new TextObject(this.timer.formatToString(this.timer.getElapsedTime()), FONT_SIZE);
        this.time.setForeColor(255, 255, 255, 255);
        this.addGameObject(this.time, (int) (this.width / 2 - this.graphics.textWidth(this.timer.formatToString(this.timer.getElapsedTime())) / 2), Y_MARGIN);
    }

    @Override
    public void update() {
        removeHeart();
        addKey();
        this.time.setText(this.timer.formatToString(this.timer.getElapsedTime()));
        if (this.player.isSuccessFull()) {
            Highscores.addHighscore(new Score(this.player.getName(), this.timer.formatToString(this.timer.getElapsedTime())));
        }
    }

    /**
     * A generalized method for adding text labels to the dashboard
     * @param text the text to show.
     * @param xPos the x-position of the text.
     * @param yPos the y-position of the text.
     * @param red a red value for the color of the text.
     * @param green a green value for the color of the text.
     * @param blue a blue value for the color of the text.
     */
    @SuppressWarnings("SameParameterValue")
    private void addLabel(String text, int xPos, int yPos, int red, int green, int blue) {
        final int alpha = 255;

        TextObject textObject = new TextObject(text, FONT_SIZE);
        textObject.setForeColor(red, green, blue, alpha);

        this.addGameObject(textObject, xPos, yPos);
    }

    /**
     * This methods adds heart sprites to the dashboard.
     */
    private void addHearts() {
        final int y = Y_MARGIN + FONT_SIZE;
        int x = X_MARGIN;

        for (HeartSprite heartSprite : this.hearts) {
            this.addGameObject(heartSprite, x, y + (int) (heartSprite.getHeight() / 2f));
            x += heartSprite.getWidth();
        }
    }

    /**
     * This method removes a heart sprite from the dashboard.
     */
    public void removeHeart() {
        if (this.player.getHealth() != this.hearts.size()) {
            this.deleteGameObject(hearts.get(hearts.size() - 1));
            this.hearts.remove(this.hearts.size() - 1);
        }
    }

    /**
     * This method adds a key to the dashboard.
     */
    public void addKey() {
        if (this.player.getKeysCollected() > 0) {
            KeySprite key = new KeySprite();
            this.addGameObject(key, (int) (this.width - X_MARGIN - key.getWidth() * this.player.getKeysCollected()), (int) (Y_MARGIN + FONT_SIZE + key.getHeight() / 2f));
        }
    }
}
