package ics.yudzeen.abstracto.screens.queue.games;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.queue.QueueMapScreen;
import ics.yudzeen.abstracto.screens.queue.games.customercashier.CustomerCashierScreen;
import ics.yudzeen.abstracto.screens.queue.games.processscheduling.ProcessSchedulingGameScreen;
import ics.yudzeen.abstracto.screens.stack.StackMapScreen;
import ics.yudzeen.abstracto.screens.stack.games.balancingsymbols.InstructionScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GameConstants;
import ics.yudzeen.abstracto.utils.GamePreferences;

/**
 * Screen for applications
 */

public class ArcadeMapScreen extends AbstractoScreen {

    public static final String TAG = ArcadeMapScreen.class.getName();

    private Image backgroundImage;
    private Label titleLabel;
    private Image personImage;

    private ImageButton customerCashierButton;
    private ImageButton cpuSchedulingButton;

    public ArcadeMapScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initBackgroundImage();
        initTitleLabel();
        initPersonImage();
        initCustomerCashierButton();
        initCpuSchedulingButton();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(backgroundImage);
        stage.addActor(titleLabel);
        stage.addActor(personImage);

        stage.addActor(customerCashierButton);
        stage.addActor(cpuSchedulingButton);
    }

    private void initBackgroundImage() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, GameConstants.HEIGHT, Pixmap.Format.RGBA8888);
        //pixmap.setColor(74/255.0f,143/255.0f,231/255.0f,1);
        //pixmap.setColor(0,0,0,1);
        pixmap.setColor(QueueMapScreen.THEME);
        pixmap.fill();
        backgroundImage = new Image(new Texture(pixmap));
        backgroundImage.setPosition(0,0);
        pixmap.dispose();
    }

    private void initTitleLabel() {
        titleLabel = LabelFactory.createLabel("ARCADE", assets.fonts.verdana_40, QueueMapScreen.LABEL_COLOR);
        titleLabel.setPosition(GameConstants.WIDTH/2 - titleLabel.getWidth()/2,
                GameConstants.HEIGHT - titleLabel.getHeight() - 30);
    }

    private void initPersonImage() {
        GamePreferences gamePreferences = game.getGamePreferences();
        gamePreferences.load();
        TextureRegion region = new TextureRegion(gamePreferences.character.equals("MALE") ? assets.images.male : assets.images.female);
        if(!region.isFlipX()) {
            region.flip(true, false);
        }
        personImage = new Image(region);
        personImage.setPosition(GameConstants.WIDTH/4 - personImage.getWidth()/2,
                GameConstants.HEIGHT/2 - personImage.getHeight()/2);
    }
    private void initCustomerCashierButton() {
        customerCashierButton = ButtonFactory.createImageButton(assets.buttons.cashier_game);
        customerCashierButton.setPosition(GameConstants.WIDTH*3/4 - customerCashierButton.getWidth()/2 - 80,
                GameConstants.HEIGHT - customerCashierButton.getHeight() - 150);
        customerCashierButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new CustomerCashierScreen.InstructionScreen(game));
            }
        });
    }

    private void initCpuSchedulingButton() {
        cpuSchedulingButton = ButtonFactory.createImageButton(assets.buttons.process_game);
        cpuSchedulingButton.setPosition(customerCashierButton.getX(),
                customerCashierButton.getY() - 60 - cpuSchedulingButton.getHeight());
        cpuSchedulingButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ProcessSchedulingGameScreen.InstructionScreen(game));
            }
        });
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new QueueMapScreen(game));
    }
}
