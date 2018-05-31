package com.motherload.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.motherload.game.Screens.TitleScreen;

public class Motherload extends Game {
        public SpriteBatch batch;
	static public Skin skin;
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
