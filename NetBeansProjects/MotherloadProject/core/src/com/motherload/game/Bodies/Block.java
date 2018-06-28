package com.motherload.game.Bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.Random;

public class Block {
    private Texture texture;
    private Sprite sprite;
    private int id, valor, x, y, weight;
    
    public Block(boolean firstLine, int x, int y){
        this.id = 0;
        this.valor = 0;
        this.weight = 0;
        texture = new Texture(Gdx.files.internal("Tiles/ground.png"));
        sprite = new Sprite(texture);
        this.x = x*50;
        this.y = y*50;
    }
    
    public Block(int x, int y){
        Random rand = new Random();
        if(rand.nextInt(14) == 0){
            int r = rand.nextInt(99);
            if(r<=5){
                this.id = 1;
            }else if(r>6 && r<60){ 
                this.id = 2;
            }else if(r>59 && r<80){
                this.id = 3;
            }else if(r>79 && r<95){
                this.id = 4;
            }else if(r>94 && r<98){
                this.id = 5;
            }else{
                this.id = 6;
            }
            
            switch(this.id){
                case 1:
                    texture = new Texture(Gdx.files.internal("collectables/copper.png"));
                    sprite = new Sprite(texture);
                    this.weight = 10;
                    this.valor = 5;
                    break;
                case 2:
                    texture = new Texture(Gdx.files.internal("lava.png"));
                    this.weight = 0;
                    this.valor = -1;
                    break;
                case 3:
                    texture = new Texture(Gdx.files.internal("collectables/silver.png"));
                    sprite = new Sprite(texture);
                    this.weight = 20;
                    this.valor = 25;
                    break;
                case 4:
                    texture = new Texture(Gdx.files.internal("collectables/gold.png"));
                    sprite = new Sprite(texture);
                    this.weight = 25;
                    this.valor = 200;
                    break;
                case 5:
                    texture = new Texture(Gdx.files.internal("collectables/diamond.png"));
                    sprite = new Sprite(texture);
                    this.weight = 10;
                    this.valor = 1000;
                    break;
                case 6:
                    texture = new Texture(Gdx.files.internal("collectables/diamond.png"));
                    sprite = new Sprite(texture);
                    this.weight = 10;
                    this.valor = 1;
                    break;
            }
        }else{
            this.id = 0;
            this.valor = 0;
            texture = new Texture(Gdx.files.internal("Tiles/ground.png"));
            sprite = new Sprite(texture);
        }
        this.x = x*50;
        this.y = y*50;
    }
    
    public Texture getTexture(){
        return this.texture;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public int getValue(){
        return this.valor;
    }
    
    public int getID(){
        return this.id;
    }
    
    
}
