package com.yjymh.penny.sys;

import com.yjymh.penny.command.Command;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AnnotateScanner implements ApplicationListener<ContextRefreshedEvent> {

    List<Command> commandList = new ArrayList<>();

    public List<Command> getCommandList() {
        return commandList;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            Map<String, Object> beans = event.getApplicationContext().getBeansWithAnnotation(com.yjymh.penny.sys.annotate.Command.class);
            for (Object bean : beans.values()) {
                if (bean instanceof Command) {
                    commandList.add((Command) bean);
                }
            }
        }
    }

}
