package com.zimo.easymvpdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zimo.easymvpdemo.base.MvpActivity_9;

//123456789
public class MainActivity extends MvpActivity_9<NewsListView_9, NewsListPresenter_9> implements NewsListView_9 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //普通代码结构->入门级别代码
//    public void clickGetData(View v){
//        HttpTask httpTask = new HttpTask(new HttpUtils.OnHttpResultListener() {
//            @Override
//            public void onResult(String result) {
//                Log.i("main", "服务器返回内容：" + result);
//            }
//        });
//        httpTask.execute("http://api.budejie.com/api/api_open.php?a=list&c=data&page=0&type=29");
//    }

    //    第一步：MVP设计->代码优化第1步->代码结构优化
//    发现问题：功能扩展时候：模块划分，版本迭代，非常麻烦->发现这个网络框架不好用，那么我更新起来你会发现很烦->需要修改UI层代码
//    网络代码和UI代码逻辑耦合（数据库操作，网络操作等等...）
//    解决问题：MVP设计
//    M：NewsListModel（新闻列表）
//    V：NewsListView（UI层交互接口）
//    本身UI层已经是存在的，不需要实现，需要交互接口
//    P：NewsListPresenter（用于将UI层和数据层进行关联）
    //UI层有一个特点：持有P层引用
//    private NewsListPresenter presenter;
//    public void clickGetData(View v) {
//        presenter = new NewsListPresenter(new NewsListView() {
//            @Override
//            public void onResultNewsList(String result) {
//                //更新UI层
//                Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
//            }
//        });
//        presenter.getNewsList(0, 29);
//    }

    //    第二步：MVP设计->代码优化第2步->方法绑定解决
//    发现问题：如果我的请求没有执行完成，这个时候我的Activity关闭了，导致内存泄漏？隐藏问题？
//    解决方案：
//    attachView方法->绑定
//    detachView方法->解绑
//    private NewsListPresenter_2 presenter;
//
//    public void clickGetData(View v) {
//        presenter = new NewsListPresenter_2();
//        //关联->绑定
//        presenter.attachView(this);
//        presenter.getNewsList(0, 29);
//    }
//
//    @Override
//    public void onResultNewsList(String result) {
//        //更新UI层
//        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (presenter != null) {
//            //解绑
//            this.presenter.detachView();
//        }
//    }


    //    第三步：MVP设计->代码优化第3步
//    发现问题：我们写了一个Presenter，没什么感觉，那如果我写100个Presenter，你发现代码冗余？
//    这样会导致绑定和解绑重复写100次
//    解决方案：父类抽象设计->MvpBasePresenter
//    private NewsListPresenter_3 presenter;
//
//    public void clickGetData(View v) {
//        presenter = new NewsListPresenter_3();
//        //关联->绑定
//        presenter.attachView(this);
//        presenter.getNewsList(0, 29);
//    }
//
//    @Override
//    public void onResultNewsList(String result) {
//        //更新UI层
//        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (presenter != null) {
//            //解绑
//            this.presenter.detachView();
//        }
//    }


    //    第四步：MVP设计->代码优化第4步
//    发现问题：我们的父类写死啦？不是动态配置
//    解决方案：UI回调接口抽象
//    private NewsListPresenter_4 presenter;
//
//    public void clickGetData(View v) {
//        presenter = new NewsListPresenter_4();
//        //关联->绑定
//        presenter.attachView(this);
//        presenter.getNewsList(0, 29);
//    }
//
//    @Override
//    public void onResultNewsList(String result) {
//        //更新UI层
//        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (presenter != null) {
//            //解绑
//            this.presenter.detachView();
//        }
//    }


    //    第五步：MVP设计->代码优化第5步
//    发现问题：每一次你都需要进行类型转换，很麻烦
//    解决方案：泛型设计->动态指定类型（定义的时候不需要指定类型，使用的时候指定具体类型）
//    private NewsListPresenter_5 presenter;
//
//    public void clickGetData(View v) {
//        presenter = new NewsListPresenter_5();
//        //关联->绑定
//        presenter.attachView(this);
//        presenter.getNewsList(0, 29);
//    }
//
//    @Override
//    public void onResultNewsList(String result) {
//        //更新UI层
//        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (presenter != null) {
//            //解绑
//            this.presenter.detachView();
//        }
//    }


    //    第六步：MVP设计->代码优化第6步
//    发现问题：你会发现一个Activity，没有什么影响，如果是50个Activity或者50个Fragment，你发现反复绑定和解绑？
//    解决方案：父类抽象->MvpActivity
//    public void clickGetData(View v) {
//        getPresenter().getNewsList(0, 29);
//    }
//
//    @Override
//    public void onResultNewsList(String result) {
//        super.onResultNewsList(result);
//        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
//    }


    //    第七步：MVP设计->代码优化第7步
//    发现问题：Activity抽象写死了，不是动态的，适合一个模块，然而我们需要适应所有的模块（所有项目模块）？
//    解决方案：抽象 + 泛型设计
//    public void clickGetData(View v) {
//        getPresenter().getNewsList(0, 29);
//    }
//
//    @Override
//    public NewsListPresenter_7 createPresenter() {
//        return new NewsListPresenter_7();
//    }
//
//    @Override
//    public NewsListView_7 createView() {
//        return this;
//    }
//
//    @Override
//    public void onResultNewsList(String result) {
//        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
//    }


    //    第八步：MVP设计->代码优化第8步
//    发现问题：多定义了一个变量，其实这个变量没有什么作用，导致代码冗余？（框架内步优化）
//    解决方案：Java特性（继承特性）
    public void clickGetData(View v) {
        getPresenter().getNewsList(0, 29);
    }

    @Override
    public NewsListPresenter_9 createPresenter() {
        return new NewsListPresenter_9();
    }

    @Override
    public void onResultNewsList(String result) {
        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
    }
}
