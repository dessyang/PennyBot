package com.yjymh.penny.utils;


import com.yjymh.penny.constant.Const;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author yjymh
 */
public class ChangeUtil {

    public static ArrayList stringToList(String s) {
        return new ArrayList(Arrays.asList(s.split(Const.COMMA)));
    }

    public static ArrayList<Long> stringToLongList(String s) {
        if (s != null && !s.equals(Const.EMPTY)) {
            ArrayList<Long> list = new ArrayList<Long>() {{
                for (String num : s.split(Const.COMMA)) {
                    add(Long.parseLong(num));
                }
            }};
            return list;
        }
        return new ArrayList<Long>();
    }

    public static String listToString(ArrayList list) {
        String s = "";

        for (int i = 0; i < list.size(); i++) {
            if (list.size() == 1) {
                s = list.get(i).toString();
            } else {
                if (i == (list.size() - 1)) {
                    s += list.get(i);
                } else {
                    s += list.get(i) + Const.COMMA;
                }
            }
        }
        return s;
    }

}
