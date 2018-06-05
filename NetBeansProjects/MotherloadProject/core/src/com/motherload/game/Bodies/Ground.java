package com.motherload.game.Bodies;

import com.badlogic.gdx.Gdx;
import static com.badlogic.gdx.Gdx.input;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.motherload.game.Motherload;

public class Ground extends InteractiveBlocks{
    
    public Ground(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        
        fixture.setUserData(this);
        setCategoryFilter(Motherload.GROUND_BIT);
    }

    @Override
    public void onFeetHit() {
        Gdx.app.log("Ground", "Collision");
        setCategoryFilter(Motherload.DESTROYED_BIT);
        //getCell().setTile(null);
    }
    
}
