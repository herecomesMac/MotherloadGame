package com.motherload.game.Bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.motherload.game.Motherload;

public class Actor extends Sprite{
    public Body body;
    public World world;
    
    public Actor(World aworld){
        //super(new Texture("actor.png"));
        this.world = aworld;
        defineActor();
        
        /*BodyDef bodyDef = new BodyDef();
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
        this.setOrigin(this.getWidth()/2,this.getHeight()/2);*/
    }
    
    public void defineActor(){
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(200,1400);
        body = world.createBody(bdef);
        
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10);
        fdef.shape = shape;
        body.createFixture(fdef);
    }   
}
