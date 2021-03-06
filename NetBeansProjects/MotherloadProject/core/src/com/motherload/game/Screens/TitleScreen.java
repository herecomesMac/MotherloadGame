package com.motherload.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.motherload.game.Motherload;


public class TitleScreen implements Screen{

    private Stage stage;
    private Motherload game;
    Texture texture0, texture1, texture2, texture3, sun0, name, press, instru, pressenter;
    Image back0, back1, back2, back3, sun_big, sun_small, title, start, info, enter;
    
    
    public TitleScreen(Motherload g){
        this.game = g;
        stage = new Stage(new ScreenViewport(), game.batch);
        
        texture0 = new Texture(Gdx.files.internal("img1.png"));
        texture1 = new Texture(Gdx.files.internal("img2.png"));
        texture2 = new Texture(Gdx.files.internal("img3.png"));
        texture3 = new Texture(Gdx.files.internal("img4.png"));
        sun0 = new Texture(Gdx.files.internal("sun.png"));
        name = new Texture(Gdx.files.internal("motherloadname.png"));
        press = new Texture(Gdx.files.internal("press.png"));
        instru = new Texture(Gdx.files.internal("howtoplay.png"));
        pressenter = new Texture(Gdx.files.internal("pressenter.png"));
        
        back0 = new Image(texture0);
        back1 = new Image(texture1);
        back1.setPosition(0,-150);
        back2 = new Image(texture2);
        back2.setPosition(0, -150);
        back3 = new Image(texture3);
        back3.setPosition(0, -150);
        sun_big = new Image(sun0);
        sun_small = new Image(sun0);
        sun_small.setSize(60, 50);
        sun_small.setPosition(620, 480);
        sun_big.setPosition(650, 500);
        title = new Image(name);
        start = new Image(press);
        info = new Image(instru);
        info.setPosition(0, -600);
        enter = new Image(pressenter);
        enter.setPosition(0, 900);
       
        
        stage.addActor(back0);
        stage.addActor(sun_big);
        stage.addActor(sun_small);
        stage.addActor(back1);
        stage.addActor(back2);
        stage.addActor(back3);
        stage.addActor(title);
        stage.addActor(start);
        stage.addActor(enter);
        stage.addActor(info);
blink(start);
        
        //Checa se a tecla espaço foi digitada para começar a animação
        stage.addListener(new InputListener(){
            @Override
            public boolean keyDown (InputEvent event, int keycode) {
                if (keycode == Keys.SPACE){                    
                    //Animação do fundo
                    back1.addAction(Actions.moveTo(0, 0, 0.5f));
                    back2.addAction(Actions.moveTo(0, 0, 0.5f));
                    back3.addAction(Actions.moveTo(0, 0, 0.5f));
                    title.addAction(Actions.moveTo(0, 1000, 0.8f));
                    start.addAction(Actions.moveTo(0, -600, 0.8f)); 
                    enter.addAction(Actions.moveTo(0, 0, 0.5f));
                    info.addAction(Actions.moveTo(0,0, 0.5f));
                    blink(enter);
                    return true;
                }else if(keycode == Keys.ENTER){
                    game.setScreen(new GameScreen(game));
                    return true;
                }else if(keycode == Keys.ESCAPE){
                    game.setScreen(new TitleScreen(game));
                    return true;
                }
                return true;
            }
        });
   
        
    }
    
    private void blink(final Image image){
        
        Texture texture = new Texture(Gdx.files.internal("blank.png"));
        final Image image1 = new Image(texture);
        
        SequenceAction actions=Actions.sequence(Actions.run(new Runnable() {
                @Override
                public void run() {
                    image.setVisible(true);
                    image1.setVisible(false);
                }
            }),Actions.delay(.2f),Actions.run(new Runnable() {
                @Override
                public void run() {
                    image.setVisible(false);
                    image1.setVisible(true);

                }
            }),Actions.delay(.2f));

        Action myAction=Actions.forever(actions);  //or not forever
        stage.addAction(myAction);
        
        
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
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
    
