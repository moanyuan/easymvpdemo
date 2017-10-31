package com.zimo.easymvpdemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * 作者: Dream on 2017/10/16 22:14
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//使用泛型设计
public abstract class MvpActivity_9<V extends MvpView_9, P extends MvpBasePresenter_9<V>> extends Activity implements MvpView_9 {

    private P presenter;

    public P getPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.presenter == null){
            this.presenter = this.createPresenter();
        }
        if (this.presenter != null){
            //关联->绑定（父类引用指向子类实例对象）
            presenter.attachView((V)this);
        }
    }

    public abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            //解绑
            this.presenter.detachView();
        }
    }

}
