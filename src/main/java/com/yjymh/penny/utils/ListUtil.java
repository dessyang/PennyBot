package com.yjymh.penny.utils;

import com.yjymh.penny.constant.Const;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author yjymh
 */
public class ListUtil {

    public static ArrayList stringToList(String s) {
        return new ArrayList(Arrays.asList(s.split(Const.COMMA)));
    }

    public static ArrayList<Long> stringToLongList(String s) {
        ArrayList<Long> list = new ArrayList<Long>();
        if (s != null && !s.equals(Const.EMPTY)) {
            for (String num : s.split(Const.COMMA)) {
                list.add(Long.parseLong(num));
            }
        }
        return list;
    }

    public static String listToString(ArrayList list) {
        StringBuffer s = new StringBuffer();
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.size() == 1) {
                    s.append(list.get(i).toString());
                } else {
                    if (i == (list.size() - 1)) {
                        s.append(list.get(i));
                    } else {
                        s.append(list.get(i) + Const.COMMA);
                    }
                }
            }
        }
        return s.toString();
    }

}
