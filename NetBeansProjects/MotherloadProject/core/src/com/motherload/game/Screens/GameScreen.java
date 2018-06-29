package com.motherload.game.Screens;

import com.motherload.game.Bodies.Block;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.motherload.game.Motherload;
import com.motherload.game.Bodies.Player;
import com.motherload.game.Hud.Hud;
import java.util.Random;

public class GameScreen implements Screen{
    
    private Motherload game;
    private final Block[][] map = new Block[16][25];
    private SpriteBatch batch;
    private Block block;
    private Hud hud;
    private Player player;
    private OrthographicCamera camera;
    private Box2DDebugRenderer b2dr;    
    private float playerX=500, playerY=1100, playerSpeed = 100.0f;
    private World world;
    private Texture texture;
    private Image background;
    private Stage stage;
    private Texture Tplataforma;
    private Sprite Splataforma;
    private boolean recarregou = false;
    
    public GameScreen(Motherload g){
        this.game = g;
         
        world = new World(new Vector2(0, -2000), true);
        hud = new Hud(game.batch);
        player = new Player(world);
        camera = new OrthographicCamera(800 ,600);
        
        camera.position.set(400, 1300,0);
        stage = new Stage();
        stage.getViewport().setCamera(camera);
        b2dr = new Box2DDebugRenderer();
        
        
        Random rand = new Random();
        for(int i=0; i<16;i++){
            map[i][25-5] = new Block(true, i, 25-5);
        }
        batch = new SpriteBatch();
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 25-5; j++){
                if(rand.nextInt(4)==0){
                    map[i][j] = null;                    
                }else{
                    map[i][j] = new Block(i, j);}
            }
        }
        
        texture = new Texture(Gdx.files.internal("bg.png"));
        background = new Image(texture);
        stage.addActor(background);
        
        Tplataforma = new Texture(Gdx.files.internal("plataform.png"));
        Splataforma = new Sprite(Tplataforma);
        Splataforma.setPosition(790-Splataforma.getWidth(), 1063-Splataforma.getHeight());
    }
    
    public void handleInput(float dt){
        int x = (int) Math.floor(playerX)/50;
        int y = (int) Math.floor(playerY)/50;
        if(map[x][y]==null || y>1000){
            playerY -= Gdx.graphics.getDeltaTime() * playerSpeed*2;
        }
        
        if(playerX >= 780-Splataforma.getWidth() && playerY >= 1063-Splataforma.getHeight()){
            System.out.println(player.getLucro());
            player.somaTotal(player.getValue());
            player.somaLucro(player.getValue());
            player.setValue(0);
            player.setCarga(0);
            if(player.getFuel() < 19 && player.getLucro() >= 50 && !recarregou){
                player.setFuel(20f);
                player.diminuiLucro(50);
                recarregou = true;
            }
        }else{
            recarregou = false;
        }
        
        if(Gdx.input.isKeyPressed(Keys.DOWN)){
            if(playerY > 0){
                if(map[x][y] != null && player.getCarga() < player.getCapacityTotal()){
                    player.diminuiFuel(0.05f);
                    if(map[x][y].getValue() == -1){
                        if (player.getLife() > 0){
                            player.setLife(player.getLife()-1);
                            map[x][y] = null;
                        }
                    }else{
                        if((player.getCarga() + map[x][y].getWeight()) < player.getCapacityTotal()){
                            player.somaCarga(map[x][y].getWeight());
                            player.somaValue(map[x][y].getValue());
                            map[x][y] = null;
                        }
                    }
                }
            }
        }
        
        if(Gdx.input.isKeyPressed(Keys.LEFT)){
            if(playerX > -0.1){
                if(map[x][y+1] != null && player.getCarga() < player.getCapacityTotal()){
                    player.diminuiFuel(0.05f);
                    if(map[x][y+1].getValue() == -1){
                        playerX -= Gdx.graphics.getDeltaTime() * playerSpeed;
                        if (player.getLife() > 0){
                            player.setLife(player.getLife()-1);
                            map[x][y+1] = null;
                        }
                    }else{
                        if((player.getCarga() + map[x][y+1].getWeight()) < player.getCapacityTotal()){
                            playerX -= Gdx.graphics.getDeltaTime() * playerSpeed;
                            player.somaCarga(map[x][y+1].getWeight());
                            player.somaValue(map[x][y+1].getValue());
                            map[x][y+1] = null;
                        }
                    }
                }else{
                    playerX -= Gdx.graphics.getDeltaTime() * playerSpeed;
                    player.diminuiFuel(0.005f);
                }
            }
        }
        
        if(Gdx.input.isKeyPressed(Keys.RIGHT)){
           if(playerX < 748){
                if(map[x+1][y+1] != null && player.getCarga() < player.getCapacityTotal()){
                    player.diminuiFuel(0.05f);
                    if(map[x+1][y+1].getValue() == -1){
                        playerX += Gdx.graphics.getDeltaTime() * playerSpeed;
                        if (player.getLife() > 0){
                            player.setLife(player.getLife()-1);
                            map[x+1][y+1] = null;
                        }
                    }else{
                        if((player.getCarga() + map[x+1][y+1].getWeight()) < player.getCapacityTotal()){
                            playerX += Gdx.graphics.getDeltaTime() * playerSpeed;
                            player.somaCarga(map[x+1][y+1].getWeight());
                            player.somaValue(map[x+1][y+1].getValue());
                            map[x+1][y+1] = null;
                        }
                    }
                }else{
                    playerX += Gdx.graphics.getDeltaTime() * playerSpeed;
                    player.diminuiFuel(0.005f);
                }
            }
        }
        
        if(Gdx.input.isKeyPressed(Keys.UP)){
            player.diminuiFuel(0.005f + (float)(player.getCarga()/500));
            if(map[x][y+1] == null){
                if(playerY < 1600){
                    playerY += Gdx.graphics.getDeltaTime() * playerSpeed * 3 - (player.getCarga()/100);
                }
            }
        }
        if(playerY < 0){
            playerY = 0;
        }
        System.out.println(player.getFuel());
    }
    
        
    @Override
    public void show() {
        
    }

    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(255/255f,0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for(int x = 0; x < 16; x++){
            for(int y = 0; y < 25; y++){
                if(map[x][y] != null){
                    batch.draw(map[x][y].getTexture(), (float) map[x][y].getX(), (float) map[x][y].getY());
                }
            }
        }
        handleInput(dt);
        if(player.getLife() <= 0 || player.getFuel() <= 0){
            System.out.println(player.getTotal());
            game.setScreen(new GameOver(game));
        }
        if(playerY <= 0){
            System.out.println(player.getTotal());
            game.setScreen(new WinScreen(game));
        }
        batch.draw(Splataforma.getTexture(), Splataforma.getX(), Splataforma.getY());
        batch.draw(player.getTexture(), (int)playerX, (int)playerY);
        camera.position.y = playerY;
        camera.update();
        batch.end();
        hud.hudupdate(player.getLife(), (int)player.getFuel()/5);
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        world.step(1/60f, 6, 2);
     
    }

    @Override
    public void resize(int i, int i1) {
        
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
    
}
