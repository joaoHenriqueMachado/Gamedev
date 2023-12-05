package com.mygdx.mechanictests.paths;

import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.mechanictests.GameScreen;
public abstract class Paths {
    public static Bezier<Vector2> path1;
    public static Bezier<Vector2> path2;
    public static Bezier<Vector2> yLine;
    public static Bezier<Vector2> xLine;
    public static Bezier<Vector2> sine;

    public static void init(){
        Vector2[] path1Points = new Vector2[4];
        path1Points[0] = new Vector2((float)GameScreen.WORLD_WIDTH / 2, GameScreen.WORLD_HEIGHT);
        path1Points[1] = new Vector2((float)GameScreen.WORLD_WIDTH, (float)GameScreen.WORLD_HEIGHT / 2);
        path1Points[2] = new Vector2((float)GameScreen.WORLD_WIDTH / 2, (float)GameScreen.WORLD_HEIGHT / 4);
        path1Points[3] = new Vector2((float)GameScreen.WORLD_WIDTH / 2, 0);
        path1 = new Bezier<>(path1Points);

        Vector2[] path2Points = new Vector2[4];
        path2Points[0] = new Vector2((float)GameScreen.WORLD_WIDTH / 2, GameScreen.WORLD_HEIGHT);
        path2Points[1] = new Vector2(0, (float)GameScreen.WORLD_HEIGHT / 2);
        path2Points[2] = new Vector2((float)GameScreen.WORLD_WIDTH / 2, (float)GameScreen.WORLD_HEIGHT / 4);
        path2Points[3] = new Vector2((float)GameScreen.WORLD_WIDTH / 2, 0);
        path2 = new Bezier<>(path2Points);

        Vector2[] path3Points = new Vector2[2];
        path3Points[0] = new Vector2((float)GameScreen.WORLD_WIDTH / 2, GameScreen.WORLD_HEIGHT);
        path3Points[1] = new Vector2((float)GameScreen.WORLD_WIDTH / 2, 0);
        yLine = new Bezier<>(path3Points);

        Vector2[] path4Points = new Vector2[2];
        path4Points[0] = new Vector2(0, (float)GameScreen.WORLD_HEIGHT / 2);
        path4Points[1] = new Vector2(GameScreen.WORLD_WIDTH, (float)GameScreen.WORLD_HEIGHT / 2);
        xLine = new Bezier<>(path4Points);

        Vector2[] path5Points = new Vector2[4];
        path5Points[0] = new Vector2((float)GameScreen.WORLD_WIDTH / 2, GameScreen.WORLD_HEIGHT);
        path5Points[1] = new Vector2((float)GameScreen.WORLD_WIDTH, (float)GameScreen.WORLD_HEIGHT * 3 / 4);
        path5Points[2] = new Vector2(0, (float)GameScreen.WORLD_HEIGHT * 1 / 4);
        path5Points[3] = new Vector2((float)GameScreen.WORLD_WIDTH / 2, 0);
        sine = new Bezier<>(path5Points);
    }
}
