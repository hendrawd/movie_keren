package keren.movie.moviekeren.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import keren.movie.moviekeren.network.UrlComposer;

/**
 * Contoh CompoundView
 *
 * @author hendrawd on 8/13/17
 */

public class VideoView extends LinearLayout {

    public VideoView(Context context) {
        super(context);
        init();
    }

    public VideoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VideoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);

        ImageView ivLeftPreview = new ImageView(getContext());

        LinearLayout llRightTexts = new LinearLayout(getContext());
        LinearLayout.LayoutParams defaultParams = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        llRightTexts.setLayoutParams(defaultParams);
        llRightTexts.setOrientation(VERTICAL);

        TextView tvTitle = new TextView(getContext());
        TextView tvType = new TextView(getContext());

        tvTitle.setLayoutParams(defaultParams);
        tvType.setLayoutParams(defaultParams);

        llRightTexts.addView(tvTitle);
        llRightTexts.addView(tvType);

        addView(ivLeftPreview);
        addView(llRightTexts);
    }

    public void setVideoThumbnail(String videoId) {
        ImageView imageView = (ImageView) getChildAt(0);
        Picasso.with(getContext())
                .load(
                        UrlComposer.getYoutubeThumbnail(
                                videoId
                        )
                )
                .into(imageView);
    }

    public void setTitle(String title) {
        LinearLayout llRightTexts = (LinearLayout) getChildAt(1);
        TextView tvTitle = (TextView) llRightTexts.getChildAt(0);
        tvTitle.setText(title);
    }

    public void setType(String type) {
        LinearLayout llRightTexts = (LinearLayout) getChildAt(1);
        TextView tvTitle = (TextView) llRightTexts.getChildAt(1);
        tvTitle.setText(type);
    }
}
