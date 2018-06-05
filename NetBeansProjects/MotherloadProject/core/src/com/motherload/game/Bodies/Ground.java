package com.motherload.game.Bodies;

import com.badlogic.gdx.Gdx;
import static com.badlogic.gdx.Gdx.input;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.motherload.game.Motherload;

public class Ground extends InteractiveBlocks{
    
    public Ground(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX()+bounds.getWidth()), (bounds.getY()+bounds.getHeight()));
        body = world.createBody(bdef);
        
        shape.setAsBox(bounds.getWidth(), bounds.getHeight());
        fdef.shape = shape;
        body.createFixture(fdef);
        
        fixture.setUserData(this);
        setCategoryFilter(Motherload.GROUND_BIT);
    }

    @Override
    public void onFeetHit() {
        Gdx.app.log("Ground", "Collision");
        setCategoryFilter(Motherload.DESTROYED_BIT);
        getCell().setTile(null);
    }
    
}
