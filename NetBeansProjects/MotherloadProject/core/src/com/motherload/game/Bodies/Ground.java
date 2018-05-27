package com.motherload.game.Bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Ground extends Image{
    private Body body;
    private World world;
    
    public Ground(World w, float pos_y){
        super(new Texture(Gdx.files.internal("img4.png")));
        this.setOrigin(this.getWidth()/2,this.getHeight()/2);
        this.setPosition(0,pos_y);
        world = w;
        
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0, pos_y));
        
        body = world.createBody(groundBodyDef);

        
        PolygonShape ground = new PolygonShape();
        ground.setAsBox(Gdx.graphics.getWidth(), 393);
        

        body.createFixture(ground, 1.0f);
        ground.dispose();
        
    }
    
    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
    
    
}
