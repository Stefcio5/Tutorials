package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.myGdxGame.game.MyGdxGame;
import controller.FlyingObjectController;
import entities.Player;
import ui.*;


/**
 * Created by Stefcio on 2017-08-16.
 */
public class GameplayScreen extends AbstractScreen {

    private Skin skin;

    private Image backgroundImage;
    private Player player;
    private PlayerButton playerButton;
    private ResetScoreButton resetScoreButton;
    private BattleScreenButton battleScreenButton;
    private StrengthButton attributeButton;
    private StatsWindow statsWindow;
    private ButtonGroup buttonGroup;
    private StrengthButton one;
    private StrengthButton ten;
    private StrengthButton hundred;
    private StrengthButton strengthButton;
    private StrengthButton dexterityButton;
    private StrengthButton staminaButton;


    private Label levelLabel;
    private Label pointsLabel;
    private Label strengthLabel;
    private Label attributeLabel;
    private FlyingObjectController flyingObjectController;
    private PointsProgressBar pointsProgressBar;



    public GameplayScreen(MyGdxGame game) {
        super(game);
        init();

    }

    private void init() {

        initSkins();
        initBackground();
        initPlayer();
        initPlayerButton();
        initResetScoreButton();
        initGameplayScreenButton();
        initAttributeButton();
        initLevelLabel();
        initPointsLabel();
        initStrengthLabel();
        initAttributeLabel();
        initPointProgressBar();
        initFlyingObjectController();



    }

    private void initSkins() {
       skin = game.assets.manager.get(game.assets.uiSkin);

    }


    private void initAttributeButton() {
        one = new StrengthButton(skin, "radio", new IClickCallback() {
            @Override
            public void onClick() {
                game.getScoreService().setAttributesToAdd(1);

            }
        });

        ten = new StrengthButton(skin, "radio", new IClickCallback() {
            @Override
            public void onClick() {
                game.getScoreService().setAttributesToAdd(10);

            }
        });
        hundred = new StrengthButton(skin, "radio", new IClickCallback() {
            @Override
            public void onClick() {
                game.getScoreService().setAttributesToAdd(100);

            }
        });


          strengthButton = new StrengthButton(skin, "default", new IClickCallback() {
            @Override
            public void onClick() {
                game.getScoreService().addStrength(game.getScoreService().getAttributesToAdd());

            }
        });
            dexterityButton = new StrengthButton(skin, "default", new IClickCallback() {
                @Override
                public void onClick() {
                    game.getScoreService().addDexterity(game.getScoreService().getAttributesToAdd());
                }
            });
              staminaButton = new StrengthButton(skin, "default", new IClickCallback() {
                @Override
                public void onClick() {

                }
            });

        attributeButton = new StrengthButton(skin, "default", new IClickCallback() {
            @Override
            public void onClick() {
                statsWindow = new StatsWindow(skin);
                Table table1 = new Table();
                ScrollPane scrollPane = new ScrollPane(table1);

                buttonGroup = new ButtonGroup<StrengthButton>(one, hundred, ten);
                buttonGroup.setChecked("Checked");
                buttonGroup.setMaxCheckCount(1);
                buttonGroup.setMinCheckCount(1);
                buttonGroup.setUncheckLast(true);

                one.setText("x1");
                one.setChecked(true);
                ten.setText("x10");
                hundred.setText("x100");
                table1.defaults().pad(5);
                table1.add(one);
                table1.add(ten);
                table1.add(hundred);




                Table table = new Table();

                table.defaults().pad(5);
                table.debug();
                table.add(strengthButton).expandX().fillX().top().padTop(10);
                table.row();
                table.add(dexterityButton).expandX().fillX();
                table.row();
                table.add(staminaButton).expandX().fillX();
                table.row();
                table.add(scrollPane).expandX().fillX();



                statsWindow.add(table).expand().fillX().top();
                stage.addActor(statsWindow);

            }
        });
        attributeButton.setWidth(120);
        attributeButton.setHeight(50);
        attributeButton.setX(20);
        attributeButton.setY(540);
        attributeButton.setText("Attributes");
        stage.addActor(attributeButton);

    }

    private void initAttributeLabel() {
        attributeLabel = new Label("Attribute", skin, "subtitle");
        attributeLabel.setX(20);
        attributeLabel.setY(610);
        attributeLabel.setWidth(150);
        stage.addActor(attributeLabel);
    }

    private void initLevelLabel() {
        levelLabel = new Label("Level", skin, "subtitle");
        levelLabel.setX(20);
        levelLabel.setY(670);
        levelLabel.setWidth(100);
        stage.addActor(levelLabel);
    }

    private void initGameplayScreenButton() {
        battleScreenButton = new BattleScreenButton("", skin, "round", new IClickCallback() {
            @Override
            public void onClick() {
                Gdx.app.log("My tag", "BattleScreenButton clicked");
                game.setScreen(new BattleScreen(game));
                dispose();
            }
        });
        stage.addActor(battleScreenButton);

    }

    private void initPointProgressBar() {
        pointsProgressBar = new PointsProgressBar(0, 100, 1, false);
        pointsProgressBar.setAnimateDuration(0.25f);
        stage.addActor(pointsProgressBar);
    }


    private void initFlyingObjectController() {
        flyingObjectController = new FlyingObjectController(game, stage);


    }


    private void initBackground() {
        backgroundImage = new Image(new Texture("background.png"));
        stage.addActor(backgroundImage);
    }

    private void initResetScoreButton() {
        resetScoreButton = new ResetScoreButton(new IClickCallback() {
            @Override
            public void onClick() {
                game.getScoreService().resetGamePoints();
            }
        });
        stage.addActor(resetScoreButton);

    }


    private void initPointsLabel() {
        pointsLabel = new Label("Points", skin, "subtitle");
        pointsLabel.setX(20);
        pointsLabel.setY(630);
        pointsLabel.setWidth(100);
        stage.addActor(pointsLabel);
    }
    private void initStrengthLabel() {
        strengthLabel = new Label("Strength", skin, "subtitle");
        strengthLabel.setX(20);
        strengthLabel.setY(650);
        strengthLabel.setWidth(100);

        stage.addActor(strengthLabel);
    }

    private void initPlayerButton() {
        playerButton = new PlayerButton(new IClickCallback() {
            @Override
            public void onClick() {
                player.reactOnClick();
                game.getScoreService().addPoint();
                game.getScoreService().addAttribute();
            }
        });

        stage.addActor(playerButton);
    }

    private void initPlayer() {
        player = new Player();
        stage.addActor(player);


    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();


        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
        levelLabel.setText("Level: " + game.getScoreService().getLevel());
        pointsLabel.setText("Points: " + game.getScoreService().getPoints());
        strengthLabel.setText("Strength: " +game.getScoreService().getStrength());
        attributeLabel.setText("Attribute points: " + game.getScoreService().getAttributes());
        pointsProgressBar.setValue(game.getScoreService().getPoints());


        //Attribute window settings
        strengthButton.setText("Strength: " +game.getScoreService().getStrength());
        dexterityButton.setText("Dexterity: " + game.getScoreService().getDexterity());
        staminaButton.setText("Stamina: ");

        stage.act();
    }
}
