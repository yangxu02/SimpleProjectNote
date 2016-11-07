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


}
