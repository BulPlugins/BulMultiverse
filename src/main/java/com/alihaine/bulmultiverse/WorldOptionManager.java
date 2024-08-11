package com.alihaine.bulmultiverse;

import com.alihaine.bulmultiverse.options.Environment;
import com.alihaine.bulmultiverse.options.Seed;
import com.alihaine.bulmultiverse.options.Structures;
import com.alihaine.bulmultiverse.options.Type;

import java.util.HashMap;

public class WorldOptionManager {
    protected HashMap<String, WorldOption> availableOptions = new HashMap<>();

    public WorldOptionManager() {
        availableOptions.put("environment", new Environment());
        availableOptions.put("seed", new Seed());
        availableOptions.put("structures", new Structures());
        availableOptions.put("type", new Type());
    }

    public WorldOption getOption(String optionAsString) throws Exception {
        WorldOption option = availableOptions.get(optionAsString);
        if (option == null)
            throw new Exception();
        return option;
    }

    public String buildOptionString(String optionFromCmd) throws Exception {
        switch (optionFromCmd) {
            case "-e":
                return "environment";
            case "-s":
                return "seed";
            case "-b":
                return "structures";
            case "-t":
                return "type";
        }
        throw new Exception();
    }

}
