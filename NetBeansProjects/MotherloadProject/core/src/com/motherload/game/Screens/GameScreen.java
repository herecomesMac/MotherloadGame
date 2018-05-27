package com.motherload.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.motherload.game.Bodies.Actor;
import com.motherload.game.Bodies.Ground;
import com.motherload.game.Motherload;
import com.motherload.game.Screens.TitleScreen;

public class GameScreen implements Screen{
    private Stage stage;
    private Game game;    
    private World world;
    private Box2DDebugRenderer debugRenderer;
    
    public GameScreen(Game agame){
        game = agame;
        Gdx.input.setInputProcessor(stage);
        stage = new Stage(new ScreenViewport());
        debugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, -1000), true);
        
        Texture texture1 = new Texture(Gdx.files.internal("img1.png"));
        Texture texture2 = new Texture(Gdx.files.internal("img2.png"));
        Texture texture3 = new Texture(Gdx.files.internal("img3.png"));
        Texture sun1 = new Texture(Gdx.files.internal("sun.png"));
        Texture sun2 = new Texture(Gdx.files.internal("sun.png"));
        
        Image Image1 = new Image(texture1);
        Image Image2 = new Image(texture2);
        Image Image3 = new Image(texture3);
        Image sun01 = new Image(sun1);
        sun01.setSize(60, 50);
        sun01.setPosition(170, 490);
        Image sun02 = new Image(sun2);
        sun02.setPosition(60, 500);
        
        
        stage.addActor(Image1);
        stage.addActor(sun02);
        stage.addActor(Image2);
        stage.addActor(Image3);
        stage.addActor(sun01);
        
        Ground ground = new Ground(world, 0);
        stage.addActor(ground);
        
        Actor player = new Actor(world, Gdx.graphics.getWidth()/4,390);
        stage.addActor(player);
        
        player.setTouchable(Touchable.enabled);
        
        
        if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
            game.setScreen(new TitleScreen(game));
        }
    }
    
    @Override
    public void show() {
        Gdx.app.log("MainScreen","show");
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
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
        stage.dispose();
    }
    
    
}
