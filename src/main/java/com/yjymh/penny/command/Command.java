package com.yjymh.penny.command;

import com.yjymh.penny.entity.CommandProperties;

/**
 * @author yjymh
 */
public interface Command {

    String COMMAND_HEAD = ".";
    
    /**
     * 指令配置文件
     *
     * @return
     */
    CommandProperties properties();
}
