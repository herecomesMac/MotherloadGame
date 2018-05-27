package com.motherload.game.Bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.motherload.game.Screens.TitleScreen;

public class Actor extends Image{
    private Body body;
    private World world;
    final float STEP = 5f;
    
    public Actor(World aworld, float pos_x,float pos_y){
        super(new Texture("actor.png"));
        world = aworld;
        this.setPosition(pos_x, pos_y);
        
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(pos_x, pos_y);
        body = world.createBody(bodyDef);

        
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.getWidth()/2, this.getHeight()/2);

        
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 5f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution= 0f;
        Fixture fixture = body.createFixture(fixtureDef);

        
        shape.dispose();
        this.setOrigin(this.getWidth()/2,this.getHeight()/2);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);        
        
        if(Gdx.input.isKeyPressed(Keys.UP))
            this.setPosition(getX(), getY() + STEP);
        if(Gdx.input.isKeyPressed(Keys.LEFT)){
            if(this.getX() == 0){
                this.setPosition(0, getY());
            } else{
                this.setPosition(getX() - STEP, getY());}
        }
        if(Gdx.input.isKeyPressed(Keys.RIGHT)){
            if(this.getX() == 740){
                this.setPosition(740, getY());
            }else{
                this.setPosition(getX() + STEP, getY());}
        }
        if(Gdx.input.isKeyPressed(Keys.DOWN))
            this.setPosition(getX(), getY() - STEP);
    }
    
  
    
}
