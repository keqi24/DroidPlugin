package android.exmaple.plugin.core;

import android.content.Context;
import android.exmaple.plugin.hook.HookFactory;

/**
 * Created by Derek on 20/10/15.
 */
public class PluginProcessManager {

    public static void installHook(Context hostContext) throws Throwable {
        HookFactory.getInstance().installHook(hostContext, null);
    }
}
