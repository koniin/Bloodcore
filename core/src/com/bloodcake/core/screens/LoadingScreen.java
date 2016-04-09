package com.bloodcake.core.screens;

import com.badlogic.gdx.Screen;

/**
 * Created by henke on 3/6/2016.
 */
public abstract class LoadingScreen implements Screen {
    protected GameBase game;

    public LoadingScreen(GameBase game) {
        this.game = game;
    }

    public abstract void loadAssets();
    public abstract void loadComplete();

    @Override
    public void show() {
        loadAssets();
        /*
        game.assetManager.load("sprites/text.png", Texture.class);
        game.assetManager.load("sprites/ship.png", Texture.class);
        game.assetManager.load("sprites/planet.png", Texture.class);
        game.assetManager.load("sprites/planet2.png", Texture.class);
        game.assetManager.load("sprites/bullet.png", Texture.class);
        game.assetManager.load("sprites/missile.png", Texture.class);
        game.assetManager.load("sprites/explosions/explosion-1.png", Texture.class);
        game.assetManager.load("sprites/explosions/explosion-2.png", Texture.class);
        game.assetManager.load("sprites/explosions/explosion-3.png", Texture.class);
        game.assetManager.load("sprites/explosions/explosion-4.png", Texture.class);
        game.assetManager.load("sprites/explosions/explosion-5.png", Texture.class);
        game.assetManager.load("sprites/bullet.png", Texture.class);
        game.assetManager.load("sprites/redplasma.png", Texture.class);
        game.assetManager.load("sprites/lazer.png", Texture.class);
        game.assetManager.load("data/uiskin.json", Skin.class);

        game.assetManager.load("particles/greysmoke.party", ParticleEffect.class);
        /*
        game.assetManager.load("particles/greysmokefire.party", ParticleEmitter.Particle.class);
        */
    }

    @Override
    public void render(float delta) {
        if(game.assetManager.update()) {
            loadComplete();
        }
    }

    @Override
    public void resize(int width, int height) {

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

    }
}
