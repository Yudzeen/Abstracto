package ics.yudzeen.abstracto.screens.queue.games.customercashier;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Spawn area actor
 */

class SpawnArea extends Actor {

    static final String TAG = SpawnArea.class.getName();

    private static final int WIDTH = 200;
    private static final int HEIGHT = 300;

    private CustomerCashierScreen gameScreen;
    private GameRenderer gameRenderer;
    private Texture texture;

    private Random random = new Random();

    private List<Customer> customerList;
    private boolean spawnProcess;
    private float spawnTimer;

    SpawnArea(CustomerCashierScreen gameScreen, GameRenderer gameRenderer) {
        this.gameScreen = gameScreen;
        this.gameRenderer = gameRenderer;
        init();
    }

    private void init() {
        customerList = new ArrayList<>();
        setWidth(WIDTH);
        setHeight(HEIGHT);
        initTexture();
        initCustomers();
    }

    private void initTexture() {
        Pixmap pixmap = new Pixmap(WIDTH, HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(245/255.0f, 245/255.0f, 245/255.0f, 1));
        pixmap.fill();
        pixmap.setColor(Color.BLACK);
        for (int i = 0; i < pixmap.getWidth(); i++) {
            for (int j = 0; j < pixmap.getHeight(); j++) {
                if(i < 3 || j < 3 || i >= pixmap.getWidth()-3 || j >= pixmap.getHeight()-3) {
                    pixmap.drawPixel(i, j);
                }
            }
        }
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    private void initCustomers() {
        for (int i = 0; i < 8; i++) {
            int type = random.nextInt(3) + 1;
            Customer customer = new Customer(gameScreen, gameRenderer, type);
            int randomX = random.nextInt(((int) (50 + WIDTH - customer.getWidth())) - 55) + 50 + 5;
            int randomY = random.nextInt(((int) (45 + HEIGHT - customer.getHeight())) - 55) + 45 + 5;
            customer.setPosition(randomX, randomY);
            customer.setOriginalX(randomX);
            customer.setOriginalY(randomY);
            customerList.add(customer);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(spawnProcess) {
            spawnTimer += delta;
            if(spawnTimer > 5 && gameScreen.gameController.timer > 25) {
                spawnProcess();
                spawnTimer = 0;
            }
        }
    }

    public void spawnProcess() {
        int type = random.nextInt(3) + 1;
        Customer customer = new Customer(gameScreen, gameRenderer, type);
        int randomX = random.nextInt(((int) (getX() + WIDTH - customer.getWidth())) - 55) + ((int) getX()) + 5;
        int randomY = random.nextInt(((int) (getY() + HEIGHT - customer.getHeight())) - 55) + ((int) getY()) + 5;
        customer.setPosition(randomX, randomY);
        customer.setOriginalX(randomX);
        customer.setOriginalY(randomY);
        gameScreen.getStage().addActor(customer);
        customerList.add(customer);
    }

    public void setSpawnProcess(boolean spawnProcess) {
        this.spawnProcess = spawnProcess;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }
}
