package com.bokade.festec;


import androidx.annotation.Nullable;
import androidx.multidex.MultiDexApplication;

import com.bokade.festec.event.ShareEvent;
import com.bokade.festec.event.TestEvent;
import com.bokade.latte.app.Latte;
import com.bokade.latte.ec.database.DatabaseManager;
import com.bokade.latte.ec.icon.FontEcModule;
import com.bokade.latte.net.interceptors.DebugInterceptor;
import com.bokade.latte.util.callback.CallbackManager;
import com.bokade.latte.util.callback.CallbackType;
import com.bokade.latte.util.callback.IGlobalCallback;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by 傅令杰 on 2017/3/29
 */
public class ExampleApp extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withLoaderDelayed(1000)
                .withApiHost("你的本地服务器地址")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withWeChatAppId("你的微信AppKey")
                .withWeChatAppSecret("你的微信AppSecret")
                .withJavascriptInterface("latte")
                .withWebEvent("test", new TestEvent())
                .withWebEvent("share", new ShareEvent())
                .configure();

        //initStetho();
        DatabaseManager.getInstance().init(this);

        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                            //开启极光推送
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(Latte.getApplicationContext());
                        }
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (!JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                            JPushInterface.stopPush(Latte.getApplicationContext());
                        }
                    }
                });
    }

//    private void initStetho() {
//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//                        .build());
//    }
}
