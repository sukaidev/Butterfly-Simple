package com.sukai.butterfly_simple.runtime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by sukaidev on 2019/10/18.
 */
public class ActivityBuilder {

    public final static ActivityBuilder INSTANCE = new ActivityBuilder();

    public void startActivity(Context context, Intent intent) {
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }
}
