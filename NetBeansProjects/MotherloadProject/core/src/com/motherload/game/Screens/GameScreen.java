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
import com.motherload.game.Tools.WorldContactListener;
import com.motherload.game.Tools.WorldCreator;


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
        gamePort = new FitViewport(Motherload.V_WIDTH, Motherload.V_HEIGHT,gamecam);
        
        //Vidas e Óleo
        hud = new Hud(game.batch);
        
        //Carregando o mapa
        maploader = new TmxMapLoader();
        map = maploader.load("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(400,1210,0);
        
        //Fisica do Mundo e seu renderizador
        b2dr = new Box2DDebugRenderer();
        world = new World(new Vector2(0, -2000), true);
        
        //Gdx.input.setInputProcessor(stage);
        stage = new Stage(new ScreenViewport(), game.batch);
        
        
        //Criando o fundo
        Texture texture = new Texture(Gdx.files.internal("background.png"));
        Image background = new Image(texture);
        stage.addActor(background);
        
        
        //Criando um blocos do TitledMap
        new WorldCreator(world, map);
        
       //Criando o jogador
        player = new Actor(world);
        
       //Criando a lógica das colisões
       world.setContactListener(new WorldContactListener());
        
        
        
    }
    
    @Override
    public void show() {
        
    }

    public void handleInput(float dt){
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            player.body.applyLinearImpulse(new Vector2(0,50f), player.body.getWorldCenter(), true);
        
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && player.body.getLinearVelocity().y >=-200)
            player.body.applyLinearImpulse(new Vector2(0, -50f), player.body.getWorldCenter(), true);
        
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.body.getLinearVelocity().x >= -200)
            player.body.applyLinearImpulse(new Vector2(-50f,0), player.body.getWorldCenter(), true);
        
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            player.body.applyLinearImpulse(new Vector2(50f,0), player.body.getWorldCenter(), true);
    }
    
    public void update(float dt){
        //Gerencia input do teclado
        handleInput(dt);

        //Calcula o tempo em que a simulação da física é calculada(60/s)
        player.update(dt);
        world.step(1/60f, 6, 2);
        
        
        gamecam.position.y = player.getY()-100;
   
        
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
        //game.batch.begin();
        //player.draw(game.batch);
        //game.batch.end();
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
        //stage.dispose();
        map.dispose();
        renderer.dispose();
        world.dispose();
        hud.dispose();
    }

}