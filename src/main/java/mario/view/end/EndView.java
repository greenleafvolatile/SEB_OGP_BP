package mario.view.end;

import mario.MainApp;
import mario.model.Player;
import mario.model.score.Highscores;
import mario.model.score.Score;
import mario.ui.Button;
import mario.ui.MouseListener;
import mario.view.Screen;
import mario.view.game.GameView;
import mario.view.menu.MenuView;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.view.View;
import processing.core.PImage;

import java.util.List;

public class EndView extends Screen {

    private final int buttonWidth = 350;
    private final int buttonHeight = 100;

    private Player player;
    private float highscoresWidth;


    public EndView(MainApp app, Player player) {
        super(app);
        this.player = player;
        super.render();
    }

    @Override
    public void addObjects() {

        addTitle();
        addHighscoresLabel();
        addHighScores();

        this.app.addGameObject(
                menuButton(),
                this.app.getWidth() / 2f - playAgainButton().getWidth() * 1.05f,
                this.app.getHeight() - playAgainButton().getHeight() * 2.25f);

        this.app.addGameObject(
                playAgainButton(),
                this.app.getWidth() / 2f + menuButton().getWidth() * .05f,
                this.app.getHeight() - menuButton().getHeight() * 2.25f);


    }

    private void addTitle() {

        final int fontSize = 100;

        this.app.textSize(fontSize);

        String text = this.player.isSucessfull() ? "You win!" : "You lose!";

        TextObject label = new TextObject(text, fontSize);
        label.setForeColor(0, 0, 0, 255);

        this.app.addGameObject(label, this.app.getWidth() / 2f - this.app.textWidth(text) / 2, fontSize * .5f);

    }

    private void addHighscoresLabel() {

        final int fontSize = 50;

        this.app.textSize(50);

        String text = "Highscores:";
        highscoresWidth = this.app.textWidth(text);

        TextObject label = new TextObject(text, fontSize);
        label.setForeColor(0, 0, 0, 255);

        this.app.addGameObject(label, this.app.getWidth() / 2f - highscoresWidth / 2f, 200);

    }

    private void addHighScores() {

        final List<Score> highscores = Highscores.loadHighscores();
        final int fontSize = 32;

        int yPos = 275;

        for (Score score : highscores) {

            TextObject nameObject = new TextObject(score.getName(), fontSize);
            nameObject.setForeColor(0, 0, 0, 255);
            this.app.addGameObject(nameObject, this.app.getWidth() / 2f - highscoresWidth / 2f, yPos);

            TextObject timeObject = new TextObject(score.getTime(), fontSize);
            timeObject.setForeColor(0, 0, 0, 255);
            this.app.addGameObject(timeObject, this.app.getWidth() / 2f - highscoresWidth / 2f + 200, yPos); // 200 is the widh of the textField in MenuView en dus ook de maximale width of playerName.

            yPos += fontSize * 1.5f;

        }
    }


    private Button playAgainButton() {

        Button play_again_button = new Button(new Sprite(MainApp.MEDIA_URL.concat("/sprites/buttons/play_again_button.png")), buttonWidth, buttonHeight);
        play_again_button.addListener(new MouseListener() {

            @Override
            public void mousePressed(int x, int y, int button) {
                new GameView(EndView.this.app, EndView.this.player);
            }
        });
        return play_again_button;
    }

    private Button menuButton() {

        Button menuButton = new Button(new Sprite(MainApp.MEDIA_URL.concat("/sprites/buttons/back_to_menu_button.png")), buttonWidth, buttonHeight);
        menuButton.addListener(new MouseListener() {

            @Override
            public void mousePressed(int x, int y, int button) {
                new MenuView(EndView.this.app);
            }
        });

        return menuButton;
    }

    @Override
    public View createView() {

        View view = new View(this.app.getWidth(), this.app.getHeight());

        PImage backgroundImage = this.app.loadImage(MainApp.MEDIA_URL.concat("/background/end_screen.jpg"));
        view.setBackground(backgroundImage);

        return view;
    }
}
