package com.zimo.easymvpdemo;


import com.zimo.easymvpdemo.base.MvpBasePresenter_9;
import com.zimo.easymvpdemo.utils.HttpUtils;

/**
 * 作者: Dream on 2017/10/16 21:11
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//P层：中介->媒婆
//P层有两个特点
//特点一：持有M层引用
//特点二：持有V层引用
public class NewsListPresenter_9 extends MvpBasePresenter_9<NewsListView_9> {

    private NewsListModel_9 model;

    public NewsListPresenter_9(){
        this.model = new NewsListModel_9();
    }

    public void getNewsList(int page, int type){
        this.model.getNewsList(page, type, new HttpUtils.OnHttpResultListener() {
            @Override
            public void onResult(String result) {
                //返回结果->更新UI层
                getView().onResultNewsList(result);
            }
        });
    }

}
