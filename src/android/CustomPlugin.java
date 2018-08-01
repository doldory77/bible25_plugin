package com.sangs.plugin;

import android.content.Intent;
import android.net.Uri;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import com.sangs.plugin.PluginResult;

public class CustomPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        PluginResult result = null;
        if (action.equals("share")) {
            result = share(args);
            if (!result.isSuccess()) {
                callbackContext.error(result.getData().toString());
            }
            
            return true;
        } else if (action.equals("externalApp")) {
            result = externalApp(args);
            if (!result.isSuccess()) {
                callbackContext.error(result.getData().toString());
            }

            return true;
        }
        return false;
    }

    public PluginResult share(JSONArray args) throws JSONException {
        PluginResult result = new PluginResult(true);
        String title = args.getJSONObject(0).getString("title");
        String subject = args.getJSONObject(0).getString("subject");
        String text = args.getJSONObject(0).getString("text");
        if (text != null && text.length() > 0) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, text);
            Intent chooser = Intent.createChooser(intent, title);
            cordova.getContext().startActivity(chooser);
        } else {
            result.setSuccess(false);
            result.setData("인자값을 확인하시기 바랍니다.");
        }

        return result;
    }

    public PluginResult externalApp(JSONArray args) throws JSONException {
        PluginResult result = new PluginResult(true);
        String packageName = args.getJSONObject(0).getString("packageName");
        if (packageName != null && packageName.length() > 0) {
            Intent intent = cordova.getContext().getPackageManager().getLaunchIntentForPackage(packageName);
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            } else {
                String url = "market://details?id=" + packageName;
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            }
            cordova.getContext().startActivity(intent);
        } else {
            result.setSuccess(false);
            result.setData("인자값을 확인하시기 바랍니다.");
        }

        return result;
    }
}