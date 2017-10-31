package com.zimo.easymvpdemo;

import android.util.Log;

import com.zimo.easymvpdemo.utils.HttpTask;
import com.zimo.easymvpdemo.utils.HttpUtils;


/**
 * 作者: Dream on 2017/10/16 21:08
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//M层：数据层
public class NewsListModel_9 {

    public void getNewsList(int page, int type, final HttpUtils.OnHttpResultListener onHttpResultListener) {
        HttpTask httpTask = new HttpTask(new HttpUtils.OnHttpResultListener() {
            @Override
            public void onResult(String result) {
                Log.i("main", "服务器返回内容：" + result);
                onHttpResultListener.onResult(result);
                //进行json解析、保存数据库、保存文件等等...
            }
        });
        httpTask.execute("http://api.budejie.com/api/api_open.php?a=list&c=data&page=" + page + "&type=" + type);
    }

}
