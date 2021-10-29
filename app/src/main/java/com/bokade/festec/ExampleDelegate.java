package com.bokade.festec;

import android.os.Bundle;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bokade.latte.delegates.LatteDelegate;

import butterknife.OnClick;

/**
 * Created by 傅令杰 on 2017/4/2
 */

public class ExampleDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return com.bokade.fastec.example.R.layout.delegate_example;
    }

    @OnClick(com.bokade.fastec.example.R.id.btn_test)
    void onClickTest() {
        testWX();

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
//        testRestClient();
    }

    private void testWX() {
    }
}
