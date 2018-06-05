package com.motherload.game.Tools;

import com.badlogic.gdx.Gdx;
import static com.badlogic.gdx.Gdx.input;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.motherload.game.Bodies.InteractiveBlocks;


public class WorldContactListener implements ContactListener{

    @Override
    public void beginContact(Contact cntct) {
        //Este método usa duas fixtures e testa quais delas é a o pé e qual é o objeto interativo
        Fixture fixA = cntct.getFixtureA();
        Fixture fixB = cntct.getFixtureB();
        Fixture object;
        
        if(fixA.getUserData() == "feet" || fixB.getUserData() == "feet"){
            Fixture feet = fixA.getUserData() == "feet" ? fixA : fixB;
            object = feet == fixA ? fixB : fixA;
            
            //object.getUserData() != null && InteractiveBlocks.class.isAssignableFrom(object.getClass())
            if(object.getUserData() instanceof InteractiveBlocks){
                if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
                    ((InteractiveBlocks) object.getUserData()).onFeetHit();
                //if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            }
        }else if(fixA.getUserData() == "left" || fixB.getUserData() == "left"){
            Fixture left = fixA.getUserData() == "left" ? fixA : fixB;
            object = left == fixA ? fixB : fixA;
            
            //object.getUserData() != null && InteractiveBlocks.class.isAssignableFrom(object.getClass())
            if(object.getUserData() instanceof InteractiveBlocks){
                if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
                    ((InteractiveBlocks) object.getUserData()).onFeetHit();
                //if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            }
        }else if(fixA.getUserData() == "right" || fixB.getUserData() == "right"){
            Fixture right = fixA.getUserData() == "right" ? fixA : fixB;
            object = right == fixA ? fixB : fixA;
            
            //object.getUserData() != null && InteractiveBlocks.class.isAssignableFrom(object.getClass())
            if(object.getUserData() instanceof InteractiveBlocks){
                if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                    ((InteractiveBlocks) object.getUserData()).onFeetHit();
                //if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            }
        }
        
        
    }

    @Override
    public void endContact(Contact cntct) {
        //Este método usa duas fixtures e testa quais delas é a o pé e qual é o objeto interativo
        Fixture fixA = cntct.getFixtureA();
        Fixture fixB = cntct.getFixtureB();
        Fixture object;
        
        if(fixA.getUserData() == "feet" || fixB.getUserData() == "feet"){
            Fixture feet = fixA.getUserData() == "feet" ? fixA : fixB;
            object = feet == fixA ? fixB : fixA;
            
            //object.getUserData() != null && InteractiveBlocks.class.isAssignableFrom(object.getClass())
            if(object.getUserData() instanceof InteractiveBlocks){                
                    ((InteractiveBlocks) object.getUserData()).onFeetHit();
                //if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            }
        }else if(fixA.getUserData() == "left" || fixB.getUserData() == "left"){
            Fixture left = fixA.getUserData() == "left" ? fixA : fixB;
            object = left == fixA ? fixB : fixA;
            
            //object.getUserData() != null && InteractiveBlocks.class.isAssignableFrom(object.getClass())
            if(object.getUserData() instanceof InteractiveBlocks){
                    ((InteractiveBlocks) object.getUserData()).onFeetHit();
                //if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            }
        }else if(fixA.getUserData() == "right" || fixB.getUserData() == "right"){
            Fixture right = fixA.getUserData() == "right" ? fixA : fixB;
            object = right == fixA ? fixB : fixA;
            
            //object.getUserData() != null && InteractiveBlocks.class.isAssignableFrom(object.getClass())
            if(object.getUserData() instanceof InteractiveBlocks){
                    ((InteractiveBlocks) object.getUserData()).onFeetHit();
                //if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            }
        }
    }

    @Override
    public void preSolve(Contact cntct, Manifold mnfld) {
        
    }

    @Override
    public void postSolve(Contact cntct, ContactImpulse ci) {
        
    }
    
}
