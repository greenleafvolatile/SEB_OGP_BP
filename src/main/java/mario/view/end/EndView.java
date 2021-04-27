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

public class EndView extends Screen {

    private final int buttonWidth = 350;
    private final int buttonHeight = 100;
    private final Player player;

    public EndView(MainApp app, Player player) {
        super(app);
        this.player = player;
        super.render();
    }

    @Override
    public void addObjects() {

        final String endText = this.player.isSucessfull() ? "You win!" : "You lose!";
        final int endTextFontSize = 100;
        addText(endText, endTextFontSize, this.app.getWidth() / 2f - getTextWidth(endText, endTextFontSize) / 2f, endTextFontSize * .5f);

        final String highscores = "Highscores:";
        final int highscoresFontFize = 50;
        addText(highscores, highscoresFontFize, this.app.getWidth() / 2f - getTextWidth(highscores, highscoresFontFize) / 2f, 200);

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


    private void addText(String text, int fontSize, float xPos, float yPos) {

        TextObject label = new TextObject(text, fontSize);
        label.setForeColor(0, 0, 0, 255);

        this.app.addGameObject(label, xPos, yPos);
    }



    private float getTextWidth(String string, int fontSize) {
        this.app.textSize(fontSize);
        return this.app.textWidth(string);
    }

    private void addHighScores() {

        final List<Score> highscores = Highscores.loadHighscores();
        final int fontSize = 32;
        final float padding = getMaxNameWidth(highscores, fontSize) / 10f;

        int yPos = 275;

        for (Score score : highscores) {

            TextObject nameObject = new TextObject(score.getName(), fontSize);
            nameObject.setForeColor(0, 0, 0, 255);
            this.app.addGameObject(nameObject, this.app.getWidth() / 2f - getMaxNameWidth(highscores, fontSize), yPos);

            TextObject timeObject = new TextObject(score.getTime(), fontSize);
            timeObject.setForeColor(0, 0, 0, 255);
            this.app.addGameObject(timeObject, this.app.getWidth() / 2f + padding, yPos);

            yPos += fontSize * 1.5f;
        }
    }

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


    private Button createPlayAgainButton() {

        Button play_again_button = new Button(new Sprite(MainApp.MEDIA_URL.concat("media/sprites/buttons/play_again_button.png")), buttonWidth, buttonHeight);
        play_again_button.addListener(new MouseAdapter() {

            @Override
            public void mousePressed(int x, int y, int button) {
                new GameView(EndView.this.app, EndView.this.player);
            }
        });
        return play_again_button;
    }

    private Button createMenuButton() {

        Button createMenuButton = new Button(new Sprite(MainApp.MEDIA_URL.concat("media/sprites/buttons/back_to_menu_button.png")), buttonWidth, buttonHeight);
        createMenuButton.addListener(new MouseAdapter() {

            @Override
            public void mousePressed(int x, int y, int button) {
                new MenuScreen(EndView.this.app);
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
