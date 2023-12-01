package com.ldts.mythsmists.model.game.map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

public class MapLoader extends MapBuilder {

    private final int lvl;

    private final List<String> lines;

    public MapLoader(int lvl) throws IOException {
        this.lvl = lvl;

        URL getLevelFile = MapLoader.class.getResource("/maps/map" + lvl + ".asset");
        BufferedReader reader = new BufferedReader(new FileReader(getLevelFile.getFile()));

        lines = readLines(reader);
    }

    private List<String> readLines(BufferedReader reader) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;)
            lines.add(line);

        return lines;
    }

    @Override
    protected int getWidth() {
        int width = 0;
        for (String line : lines) width = Math.max(width, line.length());
        return width;
    }

    @Override
    protected int getHeight() {
        return lines.size();
    }


}