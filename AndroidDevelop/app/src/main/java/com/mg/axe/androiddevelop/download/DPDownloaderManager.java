package com.mg.axe.androiddevelop.download;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/7/26 0026.
 */
public class DPDownloaderManager {

    private String downloadUrl;
    private String notificationTitle;
    private String notificationDescription;
    private HashMap<String,String> mHeaderParam;

    private long id;

    private DownloadManager downloadManager;
    private DownloadManager.Request request;

    public DPDownloaderManager(Context context){
        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
    }

    public DPDownloaderManager initOneRequest(String downloadUrl){
        Uri uri = Uri.parse(downloadUrl);
        request = new DownloadManager.Request(uri);
        return this;
    }

    public DPDownloaderManager start(){
       id = downloadManager.enqueue(request);
        return this;
    }

    public void stop(){
        downloadManager.remove(id);
    }


    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationDescription() {
        return notificationDescription;
    }

    public void setNotificationDescription(String notificationDescription) {
        this.notificationDescription = notificationDescription;
    }

    public HashMap<String, String> getmHeaderParam() {
        return mHeaderParam;
    }

    public void setmHeaderParam(HashMap<String, String> mHeaderParam) {
        this.mHeaderParam = mHeaderParam;
    }
}
