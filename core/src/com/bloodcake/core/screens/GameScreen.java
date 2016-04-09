package com.bloodcake.core.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bloodcake.core.utils.ResolutionManager;

/**
 * Created by henke on 3/3/2016.
 */
public abstract class GameScreen implements Screen {
    protected final SpriteBatch batch;
    protected final Stage uiStage;
    protected final ScreenViewport uiViewport;
    protected final GameBase game;
    protected OrthographicCamera gameCamera;
    protected ResolutionManager resolutionManager;
    protected Engine engine;

    protected abstract void initialize();
    protected abstract void preRender(float delta);
    protected abstract void postRender(float delta);
    protected abstract void resizing(int width, int height);
    protected abstract void disposing();

    public GameScreen(GameBase game) {
        this.game = game;
        batch = new SpriteBatch();
        gameCamera = new OrthographicCamera();
        resolutionManager = new ResolutionManager();
        uiViewport = new ScreenViewport();
        engine = new Engine();
        uiStage = new Stage(uiViewport, batch);

        initialize();
/*
        inputController = new InputController(gameCamera, weaponContext);
        hud = new Hud(uiStage, game.assetManager, weaponContext);

        hud.setWeapons(playerWeapons);

*/

        /*
        engine.addSystem(new InputSystem(weaponContext));
        engine.addSystem(new BoundsSystem());
        engine.addSystem(new RemoveOutsideScreenSystem(game.VIRTUAL_WIDTH, getVirtualHeight()));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new WeaponSystem());
        CollisionSystem collisionSystem = new CollisionSystem();
        engine.addSystem(collisionSystem);
        PayloadSystem payloadSystem = new PayloadSystem(new ExplosionFactory(engine, game.assetManager));
        collisionSystem.addListener(payloadSystem);
        collisionSystem.addListener(new DamageSystem());
        engine.addSystem(payloadSystem);
        engine.addSystem(new ExplosionSystem());
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new ParticleSystem());
        engine.addSystem(new LauncherSystem());
        engine.addSystem(new RenderSystem(batch, gameCamera));
        engine.addSystem(new HealthRenderSystem(batch, uiViewport, gameCamera));
        //engine.addSystem(new DebugRenderSystem(batch, gameCamera));

        Entity player = new Entity();
        player.add(new TransformComponent(game.VIRTUAL_WIDTH / 2, getVirtualHeight() - 4, 0));
        player.add(new VisualComponent(new TextureRegion(game.assetManager.get("sprites/ship.png", Texture.class)), 3, 2));
        player.add(new WeaponComponent(playerWeapons));
        player.add(new InputComponent());
        player.add(new PlayerComponent());
        player.add(new HealthComponent(100));
        engine.addEntity(player);

        Entity planet = new Entity();
        planet.add(new TransformComponent(game.VIRTUAL_WIDTH / 2, getVirtualHeight() / 2 - ((getVirtualHeight() / 2) / 3), 0));
        planet.add(new VisualComponent(new TextureRegion(game.assetManager.get("sprites/planet2.png", Texture.class)), game.VIRTUAL_WIDTH, getVirtualHeight() / 1.5f));
        engine.addEntity(planet);

        Entity target1 = new Entity();
        target1.add(new TransformComponent(MathUtils.random(2f, game.VIRTUAL_WIDTH / 2 - 1f), MathUtils.random(2, getVirtualHeight() / 1.5f - 2), 0));
        target1.add(new VisualComponent(new TextureRegion(game.assetManager.get("sprites/planet.png", Texture.class)), 1, 1));
        target1.add(new BoundsComponent(1, 1));
        target1.add(new TargetComponent());
        target1.add(new HealthComponent(100));
        engine.addEntity(target1);

        Entity target2 = new Entity();
        target2.add(new TransformComponent(MathUtils.random(game.VIRTUAL_WIDTH / 2, game.VIRTUAL_WIDTH - 2f), MathUtils.random(2, getVirtualHeight() / 1.5f - 2), 0));
        target2.add(new VisualComponent(new TextureRegion(game.assetManager.get("sprites/planet.png", Texture.class)), 1, 1));
        target2.add(new BoundsComponent(1, 1));
        target2.add(new TargetComponent());
        target2.add(new HealthComponent(100));
        engine.addEntity(target2);


        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(resolutionManager);
        multiplexer.addProcessor(uiStage);
        multiplexer.addProcessor(inputController);
        Gdx.input.setInputProcessor(multiplexer);

        */
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        preRender(delta);
        engine.update(delta);
        postRender(delta);
    }

    @Override
    public void resize (int width, int height) {
        gameCamera.setToOrtho(false, game.virtualWidth, game.virtualWidth * height / (float)width);
        uiViewport.update(width, height);
        resizing(width, height);
    }

    private float getVirtualHeight() {
        return game.virtualWidth * Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
    }

    @Override
    public void dispose () {
        batch.dispose();
        uiStage.dispose();
        disposing();
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
}
