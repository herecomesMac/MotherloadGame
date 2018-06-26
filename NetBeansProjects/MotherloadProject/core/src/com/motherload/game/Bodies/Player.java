package com.motherload.game.Bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


public class Player extends Sprite{
    
    private int life, capacity;
    private float fuel;
    private Sprite sprite;
    private Texture texture;
    private World world;
    public Body body;
    
    
    public Player(World w){
        this.fuel = 20f;
        this.life = 5;
        this.capacity = 100;
        this.world = w;
        texture = new Texture(Gdx.files.internal("actor.png"));
        sprite = new Sprite(texture);        
        //sprite.setPosition(500, 1600);
    }

    public void setPos(int x, int y){
        this.setPosition(x, y);
    }
    
    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public float getFuel() {
        return fuel;
    }

    public void setFuel(float fuel) {
        this.fuel = fuel;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    } 
    
}
