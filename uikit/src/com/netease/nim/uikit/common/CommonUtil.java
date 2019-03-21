package com.netease.nim.uikit.common;

import android.view.View;

import com.netease.nimlib.sdk.msg.model.RecentContact;

public class CommonUtil {

    //TODO Base URL 加载头像时使用
    public static String BaseUrl = "http://www.classbro.com/";

    public static String classbroRobot = "1008611";

    public static int TEAC = 1;  //教师
    public static int STUD = 2;  //学生
    public static int SELLER= 3;//销售
    //当前已发送消息数量
    public static int sendMessageCount = 0;
    //是否开启留言  0-未开启   1-开启
    public static int isOPeenMessage = 0;

    public static int role = 0;  //角色  1--教师   2--学生  3--销售

    public static void checkRole (int lable) {
        role = lable;
    }

    public static String getAvatarUrl(String url){
        if(null == url){
            return url;
        }
        boolean sta = url.startsWith("http://");
        if(sta){
            return url;
        }
        return CommonUtil.BaseUrl + url;
    }


    public static void addTag(RecentContact recent, long tag) {
        tag = recent.getTag() | tag;
        recent.setTag(tag);
    }

    public static void removeTag(RecentContact recent, long tag) {
        tag = recent.getTag() & ~tag;
        recent.setTag(tag);
    }

    public static boolean isTagSet(RecentContact recent, long tag) {
        return (recent.getTag() & tag) == tag;
    }


    public static ChatItemOnClicklistener clicklistener;

    public static void setChatItemOnClicklistener(ChatItemOnClicklistener listener){
        clicklistener = listener;
    }

    public interface ChatItemOnClicklistener{
        void onClick(View view , Object o);
    }


    //排课点击事件回调
    public static ScheduleClassOnClicklistener scheduleClassOnClicklistener;
    public static void setScheduleClassOnClicklistener(ScheduleClassOnClicklistener listener){
        scheduleClassOnClicklistener = listener;
    }
    public interface ScheduleClassOnClicklistener{
        void onClick(long courseId);
    }

}
