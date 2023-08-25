package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.main.Game;

import java.util.ArrayList;

public class EnemyPlayer extends Player {

    protected Color color;
    protected float controlSpeedAmplifier;
    protected float controlRotateAmplifier;
    protected float controlGeneralAmplifier;
    protected float shootFrequency;
    protected float totalTime;

    public EnemyPlayer(ArrayList<Bullet> bullets) {
        super(bullets);

        this.x = MathUtils.random(0, Game.WIDTH);
        this.y = MathUtils.random(0, Game.HEIGHT);
        this.radians = MathUtils.random(2 * MathUtils.PI);

        this.color = Color.RED;  // Set color directly

        this.controlSpeedAmplifier = MathUtils.random(0.8f, 1.2f);
        this.controlRotateAmplifier = MathUtils.random(0.9f, 2f);
        this.controlGeneralAmplifier = MathUtils.random(1f, 2f);
        this.shootFrequency = MathUtils.random(0.8f, 1.2f);

        this.totalTime = 0;
    }

    @Override
    protected void setShape() {
        super.setShape();
    }

    protected void addShot() {
        bullets.add(new Bullet(x, y, MathUtils.random(2 * MathUtils.PI)));
    }

    @Override
    public void update(float dt) {
        totalTime += dt;

        setUp(MathUtils.sin(totalTime * controlSpeedAmplifier) > 0);
        setLeft(MathUtils.sin(totalTime * controlRotateAmplifier) > 0);
        setRight(MathUtils.sin(totalTime * controlRotateAmplifier) < 0);

        if (MathUtils.randomBoolean(shootFrequency * dt)) {
            addShot();
        }

        super.update(dt);
    }

    @Override
    public void draw(ShapeRenderer sr) {
        sr.setColor(color);
        super.draw(sr);
    }
}
