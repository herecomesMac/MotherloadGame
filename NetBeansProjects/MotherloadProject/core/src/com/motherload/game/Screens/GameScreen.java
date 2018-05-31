package com.motherload.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.motherload.game.Bodies.Actor;
import com.motherload.game.Bodies.Ground;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.motherload.game.Scenes.Hud;
import com.motherload.game.Motherload;


public class GameScreen implements Screen{
    
    //
    private Stage stage;
    
    //Camera Settings
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Motherload game;    
    private Hud hud;
    
    //Tile Map
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    
    //Box2D World
    private World world;
    private Box2DDebugRenderer b2dr;
    
    //Cria o player
    
    Actor player;
    
    public GameScreen(Motherload g){
        this.game = g;
        
        //Mexendo na câmera
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(800, 600,gamecam);
        
        //Vidas e Óleo
        hud = new Hud(game.batch);
        
        //Carregando o mapa
        maploader = new TmxMapLoader();
        map = maploader.load("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(400,1160,0);
        
        //Fisica do Mundo e seu renderizador
        b2dr = new Box2DDebugRenderer();
        world = new World(new Vector2(0, -2000), true);
        
        //Gdx.input.setInputProcessor(stage);
        stage = new Stage(new ScreenViewport(), game.batch);
        
        
        //Criando o fundo -- Mudar depois para apenas uma imagem estática
        Texture texture0 = new Texture(Gdx.files.internal("img1.png"));
        Texture texture1 = new Texture(Gdx.files.internal("img2.png"));
        Texture texture2 = new Texture(Gdx.files.internal("img3.png"));
        Texture texture3 = new Texture(Gdx.files.internal("img4.png"));
        Texture sun0 = new Texture(Gdx.files.internal("sun.png"));
        
        Image back0 = new Image(texture0);
        Image back1 = new Image(texture1);
        Image back2 = new Image(texture2);        
        Image back3 = new Image(texture3);
        Image sun_big = new Image(sun0);
        Image sun_small = new Image(sun0);
        sun_small.setSize(60, 50);
        sun_small.setPosition(620, 480);
        sun_big.setPosition(650, 500);
        
        stage.addActor(back0);
        stage.addActor(sun_big);
        stage.addActor(sun_small);
        stage.addActor(back1);
        stage.addActor(back2);
        stage.addActor(back3);
        
        player = new Actor(world);
        
        //Criando a física do mundo
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        
        for(MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2), (rect.getY()+rect.getHeight()/2));
            body = world.createBody(bdef);
            
            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
            
        }
        
        
        //Ground ground = new Ground(world, 0);
        //stage.addActor(ground);
        
        //stage.addActor(player);
        
        //player.setTouchable(Touchable.enabled);
        

    }
    
    @Override
    public void show() {
        
    }

    public void handleInput(float dt){
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && player.body.getLinearVelocity().y <=2)
            player.body.applyLinearImpulse( new Vector2(0,0.1f), player.body.getWorldCenter(), true);
        
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && player.body.getLinearVelocity().y <=2)
            player.body.applyLinearImpulse(new Vector2(0, -0.1f), player.body.getWorldCenter(), true);
        
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.body.getLinearVelocity().x >= -200)
            player.body.applyLinearImpulse( new Vector2(50f,0), player.body.getWorldCenter(), true);
        
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.body.getLinearVelocity().x <= 200)
            player.body.applyLinearImpulse( new Vector2(50f,0), player.body.getWorldCenter(), true);
    }
    
    public void update(float dt){
        //Gerencia input do teclado
        handleInput(dt);

        //Calcula o tempo em que a simulação da física é calculada(60/s)
        world.step(1/60f, 6, 2);
        
        //gamecam.position.y = player.body.getPosition().y;
        
        gamecam.update();
        renderer.setView(gamecam);
    }
        
    
    @Override
    public void render(float f) {
        //Separa a atualização da renderização
        update(f);
        
        //Limpa a tela do jogo
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
        
        //Atualiza o palco
        stage.act();
        stage.draw();
        
        //Renderiza o mapa
        renderer.render();

        //Renderiza o Box2DDebugRenderer
        b2dr.render(world, gamecam.combined);
        
        
        //Desenha o batch e o hud
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int i, int i1) {
        //Atualiza a camera
        gamePort.update(i, i1);
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
