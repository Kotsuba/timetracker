package com.touchsoft.timetracker.util;

import java.util.ArrayList;
import java.util.List;

public class UserUtil {
    //|9|16|37|47|77|79|98|104|115|131|
    public static List<Long> getUserId(String viewFilter) {
        viewFilter=viewFilter.substring(1,viewFilter.length());
        String [] viewUserId = viewFilter.split("\\|");
        List<Long> userId=new ArrayList<>(viewUserId.length);
        for (String id : viewUserId){
            userId.add(Long.valueOf(id));
        }
        return userId;
    }
}
