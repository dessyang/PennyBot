package com.yjymh.penny.utils;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.MemberPermission;

/**
 * @author YJYMH
 */
public class GroupUtil {


    /**
     * 判断群员的等级
     *
     * @param qq         用户id
     * @param group      群组id
     * @param permission 需要判断的权限(群主，管理员，一般群员)
     * @return true:拥有改权限 false:没有该权限
     * @since 1.1.2
     */
    private static boolean getPerMission(long qq, Group group, MemberPermission permission) {
        MemberPermission memberPerMission = group.get(qq).getPermission();
        boolean flag = false;
        if (memberPerMission == permission) {
            flag = true;
        }
        return flag;
    }

    /**
     * 该用户是管理员
     *
     * @param qq    用户id
     * @param group 群组id
     * @return boolean
     * @since 1.1.2
     */
    public static boolean isAdmin(long qq, Group group) {
        return getPerMission(qq, group, MemberPermission.ADMINISTRATOR);
    }

    /**
     * 该用户是一般群员
     *
     * @param qq    用户id
     * @param group 群组id
     * @return boolean
     * @since 1.1.2
     */
    public static boolean isMember(long qq, Group group) {
        return getPerMission(qq, group, MemberPermission.MEMBER);
    }

    /**
     * 该用户是群主或者管理员
     *
     * @param qq    用户id
     * @param group 群组id
     * @return boolean
     * @since 1.1.2
     */
    public static boolean isNotMember(long qq, Group group) {
        return !isMember(qq, group);
    }

    /**
     * 该用户是群主
     *
     * @param qq    用户id
     * @param group 群组id
     * @return boolean
     * @since 1.1.2
     */
    public static boolean isOwner(long qq, Group group) {
        return getPerMission(qq, group, MemberPermission.OWNER);
    }

    /**
     * 该用户不是群主
     *
     * @param qq    用户id
     * @param group 群组id
     * @return boolean
     * @since 1.1.2
     */
    public static boolean isNotOwner(long qq, Group group) {
        return !isOwner(qq, group);
    }
}
