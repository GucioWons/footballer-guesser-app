package com.guciowons.footballer_guesser_app.game.requests;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.guciowons.footballer_guesser_app.R;

public class ImageRequestManager {
    public StringRequest getSVGRequest(String url, ImageView imageView){
        return new StringRequest(url,
                response -> convertSVGToBitmap(response, imageView, url),
                error -> {

        });
    }
    private void convertSVGToBitmap(String response, ImageView imageView, String url){
        try {
            SVG svg = SVG.getFromString(response);
            if(!url.endsWith("/6.svg")) {
                Picture picture = svg.renderToPicture();
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                    Bitmap bitmap = Bitmap.createBitmap(picture);
                    imageView.setImageBitmap(bitmap);
                }
            }else{
                imageView.setImageDrawable(imageView.getContext().getDrawable(R.drawable.badge));
            }
        } catch (SVGParseException e) {
        }
    }

    public ImageRequest getPngRequest(String url, ImageView imageView, Integer min, Integer max){
        return new ImageRequest(url,
                response -> imageView.setImageBitmap(response),
                min, max, ImageView.ScaleType.CENTER, null,
                error -> {

        });
    }
}
