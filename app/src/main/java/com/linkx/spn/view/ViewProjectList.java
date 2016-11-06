package com.linkx.spn.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.linkx.spn.R;

public class ViewProjectList extends FrameLayout {

    @Bind(R.id.star_list)
    ViewGroup starList;

    private int count = 5;

    public ViewProjectList(Context context) {
        super(context);
        setup(null);
    }

    public ViewProjectList(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(attrs);
    }

    public ViewProjectList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(attrs);
    }

    private void setup(AttributeSet attrs) {
        inflate(getContext(), R.layout.view_project_list_panel, this);
        ButterKnife.bind(this);
    }

    public com.linkx.spn.view.components.ViewStarList setWeight(float weight, int base) {
        Context context = getContext();
        int width = this.getLayoutParams().width;
        int height = this.getLayoutParams().height;
        int starWidth = (int) (0.9 * height);
        int starHeight = (int) (0.9 * height);
        int scale = base / count;
        float weightScaled = weight / scale;
        starList.removeAllViews();
        for (int i = 0; i < count; ++i) {
            ViewStarShape starShape = new ViewStarShape(context);
            starShape.setSize(starWidth, starHeight);
            ViewStarShape.FillStyle fillStyle;
            if (weightScaled >= 1) {
                fillStyle = ViewStarShape.FillStyle.Full;
            } else if (weightScaled < 1 && weightScaled > 0) {
                fillStyle = ViewStarShape.FillStyle.Half;
            } else {
                fillStyle = ViewStarShape.FillStyle.None;
            }
            starList.addView(starShape.setFillStyle(fillStyle));
            weightScaled -= 1;
//            Log.d("Trends", "i=" + i + ",width=" + width + ",height=" + height
//                    + ",starWidth=" + starWidth + ",starHeight=" + starHeight + ",fill=" + fillStyle
//                    + ",weight=" + weight + ",scaledWeight=" + weightScaled
//            );
        }
        return this;
    }


}