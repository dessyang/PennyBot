package com.yjymh.penny.command;

import com.yjymh.penny.entity.CommandProperties;

/**
 * @author yjymh
 */
public interface Command {
    String PERMISSION_DENIED = "权限不足";
    String SECOND_COMMAND_FAULT = "二级指令错误";
    String THIRD_COMMAND_FAULT = "三级指令错误";
    String COMMAND_FAULT = "指令错误";
    String DELETE_SUCCESS = "删除成功";
    String ADD_SUCCESS = "添加成功";
    String ADD_COMMAND_1 = "add";
    String ADD_COMMAND_2 = "a";
    String DELETE_COMMAND_1 = "delete";
    String DELETE_COMMAND_2 = "del";
    String DELETE_COMMAND_3 = "d";
    String UPDATE_COMMAND_1 = "update";
    String UPDATE_COMMAND_2 = "upd";
    String UPDATE_COMMAND_3 = "u";
    String COMMAND_HEAD = ".";
    Integer FIRST_COMMAND = 0;
    Integer SECOND_COMMAND = 1;
    Integer THIRD_COMMAND = 2;

    /**
     * 指令配置文件
     *
     * @return
     */
    CommandProperties properties();
}
