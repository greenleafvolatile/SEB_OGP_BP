package mario.view.end;

import mario.MainApp;
import mario.ui.Button;
import mario.ui.MouseListener;
import mario.view.Screen;
import mario.view.game.GameView;
import mario.view.menu.MenuView;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.view.View;

public class EndView extends Screen {

    private final int buttonWidth = 350;
    private final int buttonHeight = 100;

    public EndView(MainApp app) {
        super(app);
        super.render();
    }

    @Override
    public void addObjects() {

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

    }

    @Override
    public View createView() {
        return null;
    }

    private Button playButton() {

        Button playButton = new Button(new Sprite(MainApp.MEDIA_URL.concat("/sprites/buttons/play_button.png")), buttonWidth, buttonHeight);
        playButton.addListener(new MouseListener() {

            @Override
            public void mousePressed(int x, int y, int button) {
                System.out.println("Check 1");
            }
        });
        return playButton;
    }

    private Button exitButton() {

        Button exitButton = new Button(new Sprite(MainApp.MEDIA_URL.concat("/sprites/buttons/exit_button.png")), buttonWidth, buttonHeight);
        exitButton.addListener(new MouseListener() {

            @Override
            public void mousePressed(int x, int y, int button) {
                System.out.println("Check 2");
            }
        });
        return exitButton;
    }
}
