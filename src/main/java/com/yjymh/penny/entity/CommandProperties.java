package com.yjymh.penny.entity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author yjymh
 */
public class CommandProperties {
    // TODO: 2021/5/10
    /**
     * 指令名称
     */
    public String name;
    /**
     * 指令别名
     */
    public ArrayList<String> alias;

    public CommandProperties(String name, ArrayList<String> alias) {
        this.name = name;
        this.alias = alias;
    }

    public CommandProperties(String name) {
        this(name, new ArrayList<>());
    }

    public CommandProperties(String name, String... alias) {
        this(name, new ArrayList<>(Arrays.asList(alias)));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getAlias() {
        return alias;
    }

    public void setAlias(ArrayList<String> alias) {
        this.alias = alias;
    }
}
