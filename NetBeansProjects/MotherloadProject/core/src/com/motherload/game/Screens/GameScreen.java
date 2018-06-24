package com.motherload.game.Screens;

import com.motherload.game.Bodies.Block;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.motherload.game.Motherload;
import com.motherload.game.Bodies.Player;
import java.util.Random;

public class GameScreen implements Screen{
    
    private Motherload game;
    private final Block[][] map = new Block[16][12];
    private SpriteBatch batch;
    private Block block;
    private Player player;
    private OrthographicCamera camera;
    private float playerX=500, playerY=500, playerSpeed = 100.0f;
    private World world;
    private Texture texture;
    private Image background;
    private Stage stage;
    
    public GameScreen(Motherload g){
        this.game = g;
        world = new World(new Vector2(0, -2000), true);
        player = new Player(world);
        
        
        Random rand = new Random();
        for(int i=0; i<16;i++){
            map[i][12-5] = new Block(true, i, 12-5);
        }
        
        batch = new SpriteBatch();
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 12-5; j++){
                if(rand.nextInt(4)==0){
                    map[i][j] = null;                    
                }else{
                    map[i][j] = new Block(i, j);}
            }
        }
        
        texture = new Texture(Gdx.files.internal("background.png"));
        background = new Image(texture);
        stage = new Stage();
        stage.addActor(background);
    
    }
    
    
    public void handleInput(float dt){
        int x = (int) Math.floor(playerX)/50;
        int y = (int) Math.floor(playerY)/50;
        if(map[x][y]==null || y>350){
            playerY -= Gdx.graphics.getDeltaTime() * playerSpeed*2;}
        if(Gdx.input.isKeyPressed(Keys.DOWN)){
            if(map[x][y]!=null){
                map[x][y] = null;
            }
        }
        if(Gdx.input.isKeyPressed(Keys.LEFT)){
            if(map[x][y+1]!=null){
                map[x][y+1] = null;
            }
            if(playerX > -0.1){
                playerX -= Gdx.graphics.getDeltaTime() * playerSpeed;
            }
        }
        if(Gdx.input.isKeyPressed(Keys.RIGHT)){ 
           if(map[x+1][y+1]!=null){
                map[x+1][y+1] = null;
           }
           if(playerX < 748){
             Gdx.app.log( "X:", String.valueOf(playerX));
             playerX += Gdx.graphics.getDeltaTime() * playerSpeed;
           }
        }
        if(Gdx.input.isKeyPressed(Keys.UP)){
            if(map[x][y+1]==null){
                if(playerY <Gdx.graphics.getHeight()){
                    playerY += Gdx.graphics.getDeltaTime() * playerSpeed * 3;
                }
            }
        }
        
    }
    
    @Override
    public void show() {
        
    }

    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        batch.begin();
        for(int x = 0; x < 16; x++){
            for(int y = 0; y < 12; y++){
                if(map[x][y] != null){
                    batch.draw(map[x][y].getTexture(), (float) map[x][y].getX(), (float) map[x][y].getY());
                }
            }
        }
        
        world.step(1/60f, 6, 2);
        handleInput(dt);
        batch.draw(player.getTexture(), (int)playerX, (int)playerY);
        
        batch.end();
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
