package com.motherload.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.motherload.game.Screens.TitleScreen;

public class Motherload extends Game {
	static public Skin skin;
	static public TextureAtlas textureAtlas;

	@Override
	public void create () {
            skin = new Skin(Gdx.files.internal("glassy-ui.json"));
            textureAtlas = new TextureAtlas();
            textureAtlas.addRegion("actor",new TextureRegion(new Texture("actor.png")));
            this.setScreen(new TitleScreen(this));
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
