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
    private final String name;

    public EndView(MainApp app, String name) {
        super(app);
        super.render();
        this.name = name;
    }

    @Override
    public void addObjects() {

        this.app.addGameObject(
                playAgainButton(),
                this.app.getWidth() / 2f - playAgainButton().getWidth() * 1.25f,
                this.app.getHeight() - playAgainButton().getHeight() * 1.25f);

        this.app.addGameObject(
                menuButton(),
                this.app.getWidth() / 2f + menuButton().getWidth() * .25f,
                this.app.getHeight() - menuButton().getHeight() * 1.25f);

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
