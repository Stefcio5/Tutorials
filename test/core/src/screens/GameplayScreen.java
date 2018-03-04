package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
    private Label dexterityLabel;
    private Label staminaLabel;
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
        initDexterityLabel();
        initStrengthLabel();
        initStaminaLabel();
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
                    game.getScoreService().addStamina(game.getScoreService().getAttributesToAdd());

                }
            });

        attributeButton = new StrengthButton(skin, "default", new IClickCallback() {
            @Override
            public void onClick() {
                statsWindow = new StatsWindow(skin);
                statsWindow.setPosition(camera.viewportWidth/2 - statsWindow.getWidth()/2, camera.viewportHeight/2 - statsWindow.getHeight()/2);
                Table table1 = new Table();
                ScrollPane scrollPane = new ScrollPane(table1);

                buttonGroup = new ButtonGroup<StrengthButton>(one, hundred, ten);
                buttonGroup.setChecked("Checked");
                buttonGroup.setMaxCheckCount(1);
                buttonGroup.setMinCheckCount(1);
                buttonGroup.setUncheckLast(true);

                one.setText("x1");
                one.setChecked(true);
                game.getScoreService().setAttributesToAdd(1);
                ten.setText("x10");
                ten.setSize(25,25);
                hundred.setText("x100");
                hundred.setSize(20,20);
                table1.defaults().pad(5).width(50).height(20);
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

    private void initLevelLabel() {
        levelLabel = new Label("Level",skin, "subtitle");
        levelLabel.setPosition(20, 670);
        levelLabel.setSize(100, 20);
        stage.addActor(levelLabel);
    }
    private void initStrengthLabel() {
        strengthLabel = new Label("Strength",skin, "subtitle");
        strengthLabel.setPosition(20, 650);
        strengthLabel.setSize(100, 20);
        stage.addActor(strengthLabel);
    }
    private void initDexterityLabel() {
        dexterityLabel = new Label("Dexterity",skin, "subtitle");
        dexterityLabel.setPosition(20, 630);
        dexterityLabel.setSize(100, 20);
        stage.addActor(dexterityLabel);
    }
    private void initStaminaLabel(){
        staminaLabel = new Label("Stamina",skin, "subtitle");
        staminaLabel.setPosition(20, 610);
        staminaLabel.setSize(100, 20);
        stage.addActor(staminaLabel);
    }
    private void initAttributeLabel() {
        attributeLabel = new Label("Attribute", skin, "subtitle");
        attributeLabel.setPosition(20, 590);
        attributeLabel.setSize(150, 20);
        stage.addActor(attributeLabel);
    }

    private void initPointProgressBar() {
        pointsProgressBar = new PointsProgressBar(0, 100, 1, false);
        pointsProgressBar.setAnimateDuration(0.25f);
        stage.addActor(pointsProgressBar);
    }
    private void initPointsLabel() {
        pointsLabel = new Label("Points", skin, "subtitle");
        pointsLabel.setPosition(165, 650);
        pointsLabel.setSize(100, 20);
        stage.addActor(pointsLabel);
    }

    private void initGameplayScreenButton() {
        battleScreenButton = new BattleScreenButton("", skin, "round", new IClickCallback() {
            @Override
            public void onClick() {
                Gdx.app.log("My tag", "BattleScreenButton clicked");
                dispose();
                game.setScreen(new BattleScreen(game));
            }
        });
        stage.addActor(battleScreenButton);

    }



    private void initFlyingObjectController() {
        flyingObjectController = new FlyingObjectController(game, stage);


    }


    private void initBackground() {
        backgroundImage = new Image(new Texture("background.png"));
        backgroundImage.setSize(game.WIDTH, game.HEIGHT);
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

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    private void update() {
        levelLabel.setText("Level: " + game.getScoreService().getLevel());
        pointsLabel.setText("Points: " + game.getScoreService().getPoints());
        strengthLabel.setText("Strength: " +game.getScoreService().getStrength());
        dexterityLabel.setText("Dexterity: " +game.getScoreService().getDexterity());
        staminaLabel.setText("Stamina: " +game.getScoreService().getStamina());
        attributeLabel.setText("Attribute points: " + game.getScoreService().getAttributes());
        pointsProgressBar.setValue(game.getScoreService().getPoints());


        //Attribute window settings
        strengthButton.setText("Strength: " +game.getScoreService().getStrength());
        dexterityButton.setText("Dexterity: " + game.getScoreService().getDexterity());
        staminaButton.setText("Stamina: " + game.getScoreService().getStamina());


        stage.act();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
