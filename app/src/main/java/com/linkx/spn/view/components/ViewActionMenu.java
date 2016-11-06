package com.linkx.spn.view.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.linkx.spn.R;

public class ViewActionMenu extends FrameLayout {

    @Bind(R.id.action_left_panel)
    ImageView leftPanel;
    @Bind(R.id.action_upload_to_cloud)
    ImageView uploadToCloud;
    @Bind(R.id.action_add_project)
    ImageView addProject;

    public ViewActionMenu(Context context) {
        super(context);
        setup(null);
    }

    public ViewActionMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(attrs);
    }

    public ViewActionMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(attrs);
    }

    private void setup(AttributeSet attrs) {
        inflate(getContext(), R.layout.view_action_menu, this);
        ButterKnife.bind(this);
    }

    public ImageView getLeftPanel() {
        return leftPanel;
    }

    public ImageView getUploadToCloud() {
        return uploadToCloud;
    }

    public ImageView getAddProject() {
        return addProject;
    }


}
