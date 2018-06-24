package com.motherload.game.Bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.motherload.game.Motherload;

public class Actor extends Sprite{
    public Body body;
    public World world;
    
    public Actor(World world){
        Texture texture = new Texture("actor.png");
        this.world = world;
        //setBounds(0, 0, 50, 50);
        //setRegion(texture);
        defineActor();

    }
    
    public void update(float dt){
        setPosition(body.getPosition().x - getWidth(), body.getPosition().y + getHeight());
    }
    
    public void defineActor(){
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(200, 1400);
        body = world.createBody(bdef);
        
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(10,10);

        
        fdef.shape = shape;
        body.createFixture(fdef);
        
        //Linha usada para criar as colisões
        /*EdgeShape feet = new EdgeShape();
        feet.set(new Vector2(-5,-10),new Vector2(5,-10));
        fdef.shape = feet;
        fdef.isSensor = true;
        
        body.createFixture(fdef).setUserData("feet");
        
        EdgeShape left = new EdgeShape();
        left.set(new Vector2(10,-6), new Vector2(10,6));
        fdef.shape = left;
        fdef.isSensor = true;
        
        body.createFixture(fdef).setUserData("left");
        
        EdgeShape right = new EdgeShape();
        right.set(new Vector2(-10,-6), new Vector2(-10,6));
        fdef.shape = right;
        fdef.isSensor = true;
        
        body.createFixture(fdef).setUserData("right");*/
        
        
    }
}
