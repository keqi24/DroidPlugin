package android.exmaple.plugin;

import android.content.Context;
import android.exmaple.plugin.core.PluginProcessManager;
import android.util.Log;

/**
 * Created by Derek on 20/10/15.
 */
public class PluginHelper {

    private static final String TAG = PluginHelper.class.getSimpleName();

    private static PluginHelper sInstance = null;

    private PluginHelper() {
    }

    public static final PluginHelper getInstance() {
        if (sInstance == null) {
            sInstance = new PluginHelper();
        }
        return sInstance;
    }

    public void applicationOnCreate(final Context baseContext) {
        initPlugin(baseContext);
    }

    private void initPlugin(Context baseContext) {
        long b = System.currentTimeMillis();
        try {
            try {
                PluginProcessManager.installHook(baseContext);
            } catch (Throwable e) {
                Log.e(TAG, "installHook has error", e);
            }

            try {
                if (PluginProcessManager.isPluginProcess(baseContext)) {
                    PluginProcessManager.setHookEnable(true);
                } else {
                    PluginProcessManager.setHookEnable(false);
                }
            } catch (Throwable e) {
                Log.e(TAG, "setHookEnable has error", e);
            }

            try {
                PluginManager.getInstance().addServiceConnection(PluginHelper.this);
                PluginManager.getInstance().init(baseContext);
            } catch (Throwable e) {
                Log.e(TAG, "installHook has error", e);
            }


        } finally {
            Log.i(TAG, "Init plugin in process cost %s ms" +  (System.currentTimeMillis() - b));
        }
    }



    public void applicationAttachBaseContext(Context baseContext) {
    }
}
