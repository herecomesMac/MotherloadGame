package com.motherload.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud {
    public Stage stage;
    private Viewport viewport;
    private int quant_lives;
    private int fuel;
    
    
    public Hud(SpriteBatch sb){
        quant_lives = 3;
        fuel = 50;
        Texture heart = new Texture(Gdx.files.internal("heart.png"));
        Texture drop = new Texture(Gdx.files.internal("fueldrop.png"));
        viewport = new FitViewport(800,600, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        
        
        //Lives Bar
        Image life = new Image(heart);
        Image life2 = new Image(heart);
        Image life3 = new Image(heart);
        life.setPosition(50,560);
        life2.setPosition(90,560);
        life3.setPosition(130,560);
        stage.addActor(life);
        stage.addActor(life2);
        stage.addActor(life3);
        
        //Fuel Tank Bar
        Image tank = new Image(drop);
        Image tank1 = new Image(drop);
        Image tank2 = new Image(drop);
        tank.setPosition(50, 525);
        tank1.setPosition(90,525);
        tank2.setPosition(130, 525);
        stage.addActor(tank);
        stage.addActor(tank1);
        stage.addActor(tank2);
        
        
    }
    
    
    
    
}
