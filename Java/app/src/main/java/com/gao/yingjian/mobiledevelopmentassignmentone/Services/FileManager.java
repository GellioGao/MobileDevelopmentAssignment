package com.gao.yingjian.mobiledevelopmentassignmentone.Services;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

public class FileManager {
    private static final FileManager ourInstance = new FileManager();

    public static FileManager getInstance() {
        return ourInstance;
    }

    private FileManager() {
    }

    public String getAssetJSONFile (String filename, Context context) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(filename);
        byte[] formArray = new byte[file.available()];
        file.read(formArray);
        file.close();

        return new String(formArray);
    }

    public <TResult> TResult getObjectFromJson(String file, Context context, Class<TResult> type) {
        return this.getObjectFromJson(file,context,(Type)type);
    }

    public <TResult> TResult getObjectFromJson(String file, Context context, Type type){
        TResult result = null;
        try {
            String json = getAssetJSONFile(file, context);
            result = new Gson().fromJson(json, type);
        }
        catch (IOException ioe){
            Log.e("FileManager", ioe.toString());
        }
        return result;
    }
}
