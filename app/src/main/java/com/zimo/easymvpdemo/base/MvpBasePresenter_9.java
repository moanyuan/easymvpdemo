package com.zimo.easymvpdemo.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 作者: Dream on 2017/10/16 21:33
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//抽象类
public abstract class MvpBasePresenter_9<V extends MvpView_9> {

    private V view;
    private V proxyView;

    public V getView() {
        return proxyView;
    }

    private boolean isNull(){
        if (this.view == null){
            return true;
        }
        return false;
    }

    public void attachView(V view){
        this.view = view;
        //参数一：类加载器
        ClassLoader loader = view.getClass().getClassLoader();
        //参数二：代理接口
        Class<?>[] interfaces = view.getClass().getInterfaces();
        //参数三：方法回调
        InvocationHandler handler = new MvpViewInvocationHandler(this.view);
        proxyView = (V) Proxy.newProxyInstance(loader, interfaces, handler);
    }

    //RxJava->300个类->将近10万行代码量->分析设计理念?很强框架设计功底?
    //教你？将近设计，而不是应用(技术 + 思想)
    //阿里云栖大会->为什么要搞？
    //第一个方面：商业性价格->阿里云做推广
    //第二个方面：学习交流价值(掌握数据和技术进行分享，大家一起来学习交流)
    //46个国家从事技术人才聚集
    //200元(开发者交流会)
    //课程方向：架构设计(设计模式 + 主流框架设计)
    //NDK技术（基于安卓平台C/C++：OpenCV + OpenGL + VR/AR技术）
    //VR/AR：购物、游戏等等...
    //总共学费：5880元（学习周期：1年学习周期）->最新一起班级10月31日开班
    //你学习NDK技术 + 架构（NDK技术里面，分析架构，C++框架设计），一个个学习
    //NDK是每周：2、4、6上课
    //NDK从0开始，架构从0开始，一步步深入
    //补课学习：补框架设计（6个月）
    //1年之内，每一期是6个月
    //补齐学费：找Grace老师、Anna老师
    //架构设计：跨语言、跨平台
    //NDK技术：跨语言、跨平台
    //上一期NDK：上了1.5个月，最新一期，10月31日开班
    //长期在这个行业优势
    //复习？
    //框架设计：Dream老师全程讲解
    //NDK技术：6个月

    //学习周期：1年，在一年内，新技术学院所有的课程都是免费学习
    //开设：小课、大课都是免费学习，不断更新新的技术（福利课程）
    //NDK技术
    // 2个月：opencv->尧金老师（跨平台OpenCV开发：Dream）
    // 2个月：OpenGL->青岩老师
    // 2个月：VR/AR技术->尧金老师
    //上一期: NDK技术 + 架构 + 企业级开发（核心技术）
    // 510278658
    //系统VIP课程：每周2、4、6上课，每天晚上8：30-11：30
    public void detachView(){
        this.view = null;
    }

    private class MvpViewInvocationHandler implements InvocationHandler {

        private MvpView_9 view;

        MvpViewInvocationHandler(MvpView_9 view){
            this.view = view;
        }

        //统一判断->控制对象访问权限
        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            //检查是不是为null
            if (isNull()){
                //不用回调
                return null;
            }
            //执行回调
            return method.invoke(view, objects);
        }
    }

}

//学习对自己投资
