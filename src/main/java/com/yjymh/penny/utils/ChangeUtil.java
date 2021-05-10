package com.yjymh.penny.utils;


import java.util.ArrayList;
import java.util.Arrays;

public class ChangeUtil {

    public static ArrayList stringToList(String s) {
        return new ArrayList(Arrays.asList(s.split(",")));
    }

    public static ArrayList<Long> stringToLongList(String s) {
        if (s != null && !s.equals("")) {
            ArrayList<Long> list = new ArrayList<Long>() {{
                for (String num : s.split(",")) {
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
                    s += list.get(i) + ",";
                }
            }
        }
        return s;
    }

}
