package com.mg.axe.androiddevelop.Utils;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class ImageSplitterUtil {
    /**
     * 传入一个bitmap 返回 一个picec集合
     * @param bitmap
     * @param count
     * @return
     */
    public static List<ImagePiece> splitImage(Bitmap bitmap, int count){

        List<ImagePiece> imagePieces = new ArrayList<>();
        int width = bitmap.getWidth();
        int height= bitmap.getHeight();

        int picWidth = Math.min(width,height)/count;

        for(int i=0;i<count;i++){
            for(int j=0;j<count;j++){
                ImagePiece imagePiece = new ImagePiece();
                imagePiece.setIndex(j+i*count);
                //为createBitmap 切割图片获取xy
                int x = j*picWidth;
                int y = i*picWidth;

                imagePiece.setBitmap(Bitmap.createBitmap(bitmap,x,y,picWidth,picWidth));
                imagePieces.add(imagePiece);
            }
        }

        return imagePieces;
    }
}
