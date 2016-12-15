package com.purestation.widgetplus.text;

/**
 * Created by puresprout@sk.com on 2016. 12. 14..
 */
public interface DrawableClickable {
    /**
     * drawable 클릭을 수행한다.
     *
     * @param i 0이면 left, 1이면 top, 2이면 right, 3이면 bottom drawable을 의미
     */
    void performDrawableClick(int i);
}
