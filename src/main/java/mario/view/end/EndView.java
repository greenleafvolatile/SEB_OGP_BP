package mario.view.end;

import mario.MainApp;
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

import java.util.List;

public class EndView extends Screen {

    private final int buttonWidth = 350;
    private final int buttonHeight = 100;

    private final String name;
    private float highscoresWidth;

    public EndView(MainApp app, String name) {
        super(app);
        super.render();
        this.name = name;
    }

    @Override
    public void addObjects() {

        this.app.addGameObject(
                menuButton(),
                this.app.getWidth() / 2f - playAgainButton().getWidth() * 1.05f,
                this.app.getHeight() - playAgainButton().getHeight() * 2.25f);

        this.app.addGameObject(
                playAgainButton(),
                this.app.getWidth() / 2f + menuButton().getWidth() * .05f,
                this.app.getHeight() - menuButton().getHeight() * 2.25f);


        addHighscoresLabel();
        addHighScores();

    }

    private void addHighscoresLabel() {

        final int fontSize = 50;

        this.app.textSize(50);

        String text = "Highscores:";
        highscoresWidth = this.app.textWidth(text);

        TextObject label = new TextObject(text, fontSize);
        label.setForeColor(0, 255, 0, 255);

        this.app.addGameObject(label, this.app.getWidth() / 2f - highscoresWidth / 2f, 200);

    }

    private void addHighScores() {

        final List<Score> highscores = Highscores.loadHighscores();
        final int fontSize = 32;

        int yPos = 275;

        for (Score score : highscores) {

            TextObject nameObject = new TextObject(score.getName(), fontSize);
            nameObject.setForeColor(255, 0, 0, 255);

            this.app.addGameObject(nameObject, this.app.getWidth() / 2f - highscoresWidth / 2f, yPos);

            TextObject timeObject = new TextObject(score.getTime(), fontSize);
            timeObject.setForeColor(0, 0, 255, 255);

            this.app.addGameObject(timeObject, this.app.getWidth() / 2f + 50, yPos);

            yPos += fontSize * 1.5f;

        }
    }


    private Button playAgainButton() {

        Button play_again_button = new Button(new Sprite(MainApp.MEDIA_URL.concat("/sprites/buttons/play_again_button.png")), buttonWidth, buttonHeight);
        play_again_button.addListener(new MouseListener() {

            @Override
            public void mousePressed(int x, int y, int button) {
                new GameView(EndView.this.app, EndView.this.name);
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
        return view;
    }
}
