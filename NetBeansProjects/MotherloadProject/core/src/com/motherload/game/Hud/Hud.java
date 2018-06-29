package com.motherload.game.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud implements Disposable{
    public Stage stage;
    public Stage stage1;
    private Texture texture;
    private Texture tanque;
    private Texture tex;
    private Image image;
    private Image tan;
    private Image fuel;
    private Viewport viewport;
    private int i;
    
    
    public Hud(SpriteBatch sb){
        
        //viewport = new FitViewport(0, 0, new OrthographicCamera());
        stage = new Stage();
        for(i=0; i<120;i+=40){
            texture = new Texture(Gdx.files.internal("heart.png"));
            tex = new Texture(Gdx.files.internal("fueldrop.png"));
            image = new Image(texture);
            fuel = new Image(tex);
            image.setPosition(40+i, 550);
            fuel.setPosition(42+i, 520);
            stage.addActor(image);
            stage.addActor(fuel);
        }
        
    }
    
    public void hudupdate(int h, int f){
        stage.clear();
        int i;
        for(i = 0; i<h*40;i+=40){
            texture = new Texture(Gdx.files.internal("heart.png"));
            image = new Image(texture);
            image.setPosition(40+i, 550);
            stage.addActor(image);
        }
        for(i = 0; i<f*40;i+=40){
            tex = new Texture(Gdx.files.internal("fueldrop.png"));
            fuel = new Image(tex);
            fuel.setPosition(42+i, 520);
            stage.addActor(fuel);
        }
    }
  
    @Override
    public void dispose() {
        stage.dispose();
    }
    
    
}
