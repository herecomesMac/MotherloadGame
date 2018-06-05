package com.motherload.game.Bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


public abstract class InteractiveBlocks {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;
    
    public InteractiveBlocks(World world, TiledMap map, Rectangle bounds){
        this.world = world;
        this.map = map;
        this.bounds = bounds;   
        
        BodyDef bdef = new BodyDef();
        FixtureDef def = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX()+bounds.getWidth()), (bounds.getY()+bounds.getHeight()));
        body = world.createBody(bdef);
        
        shape.setAsBox(bounds.getWidth(), bounds.getHeight());
        def.shape = shape;
        body.createFixture(def);
        
        fixture = body.createFixture(def);
        
    }
    
   public abstract void onFeetHit();
   
   public void setCategoryFilter(short filterBit){
       Filter filter = new Filter();
       filter.categoryBits = filterBit;
       fixture.setFilterData(filter);
   }
   
   public TiledMapTileLayer.Cell getCell(){
       TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
       return layer.getCell((int)body.getPosition().x/25, (int)body.getPosition().y/25);
   }
}
