package com.motherload.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.motherload.game.Screens.TitleScreen;

public class Motherload extends Game {
        public SpriteBatch batch;
	static public Skin skin;
        
        public static final int V_WIDTH = 800;
        public static final int V_HEIGHT = 600;
        public static final short DEFAULT_BIT = 1;
        public static final short ACTOR_BIT = 2;
        public static final short GROUND_BIT = 4;
        public static final short DESTROYED_BIT = 8;
        
        //Pixel per meter
        public static final float PPM = 100;

	@Override
	public void create () {
            batch = new SpriteBatch();
            skin = new Skin(Gdx.files.internal("glassy-ui.json"));
            setScreen(new TitleScreen(this));
	}
        

	@Override
	public void render () {
            super.render();
	}
	
	@Override
	public void dispose () {
		skin.dispose();
	}
}
