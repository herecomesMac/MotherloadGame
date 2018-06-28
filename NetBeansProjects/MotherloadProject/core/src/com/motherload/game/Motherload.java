package com.motherload.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.motherload.game.Screens.TitleScreen;

public class Motherload extends Game {
        public SpriteBatch batch;
	

	@Override
	public void create () {
            batch = new SpriteBatch();
            setScreen(new TitleScreen(this));
	}
        

	@Override
	public void render () {
            super.render();
	}
	
	@Override
	public void dispose () {
            
	}
}
