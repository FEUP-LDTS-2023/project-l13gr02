package com.ldts.mythsmists.model.game.map;

import com.ldts.mythsmists.model.Elements.Dracma;
import com.ldts.mythsmists.model.Elements.Enemy;
import com.ldts.mythsmists.model.Elements.Orpheus;
import com.ldts.mythsmists.model.Elements.Wall;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

public class MapLoader extends MapBuilder {

    private final int number;
    private final List<String> lines;

    public MapLoader(int number) throws IOException {
        this.number = number;

        URL getMapFile = MapLoader.class.getResource("/maps/map" + number + ".asset");
        assert getMapFile != null;
        BufferedReader reader = new BufferedReader(new FileReader(getMapFile.getFile()));

        lines = readLines(reader);
    }

    private List<String> readLines(BufferedReader reader) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;)
            lines.add(line);

        return lines;
    }

    protected int getWidth() {
        int width = 0;
        for (String line : lines) width = Math.max(width, line.length());
        return width;
    }

    protected int getHeight() {
        return lines.size();
    }

    @Override
    protected Orpheus createOrpheus() {
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'O') return new Orpheus(x, y);
        }
        return null;
    }

    @Override
    protected List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == '#') walls.add(new Wall(x, y));
        }

        return walls;
    }

    @Override
    protected List<Dracma> createDracmas() {
        List<Dracma> dracmas = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == '$') dracmas.add(new Dracma(x, y));
        }
        return dracmas;
    }

    @Override
    protected List<Enemy> createEnemys() {
        List<Enemy> enemys = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'E') enemys.add(new Enemy(x, y));
        }

        return enemys;
    }


}
