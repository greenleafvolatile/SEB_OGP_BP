package mario;

import mario.ui.Button;
import mario.ui.MouseListener;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.view.View;

import java.util.Vector;

public class EndScreen {

    private final MainApp app;
    private final String playerName;

    public EndScreen(MainApp app, String playerName) {

        this.app = app;
        this.playerName = playerName;
        this.init();

    }

    private void init() {


        this.createView();
        final int buttonWidth = 350;
        final int buttonHeight = 100;

        Button playButton = new Button(new Sprite(MainApp.MEDIA_URL.concat("/sprites/buttons/play_button.png")), buttonWidth, buttonHeight);
        playButton.addListener(new MouseListener() {

            @Override
            public void mousePressed(int x, int y, int button)  {

                EndScreen.this.app.deleteAllGameOBjects();

                new Game(EndScreen.this.app, EndScreen.this.playerName);


            }
        });

        Button startMenuButton = new Button(new Sprite(MainApp.MEDIA_URL.concat("/sprites/buttons/exit_button.png")), buttonWidth, buttonHeight);

        startMenuButton.addListener(new MouseListener() {

            @Override
            public void mousePressed(int x, int y, int button) {

                EndScreen.this.app.deleteAllGameOBjects();

                new StartMenu(EndScreen.this.app);
            }

        });

        this.app.addGameObject(playButton, this.app.getWidth() / 2f - playButton.getWidth() / 2f, 300);
        this.app.addGameObject(startMenuButton, this.app.getWidth() / 2f - startMenuButton.getWidth() / 2f, 430);
    }





    private void createView() {
        View view = new View(this.app.getWidth() , this.app.getHeight());

        // Insert background here.

        this.app.setView(view);

    }



}
