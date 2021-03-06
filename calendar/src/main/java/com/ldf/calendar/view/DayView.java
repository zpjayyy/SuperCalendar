package com.ldf.calendar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.ldf.calendar.component.State;
import com.ldf.calendar.interf.IDayRenderer;
import com.ldf.calendar.model.CalendarDate;

/**
 * Created by ldf on 16/10/19.
 */

public abstract class DayView extends RelativeLayout implements IDayRenderer {

    protected Day day;
    protected Context context;
    protected int layoutResource;

    /**
     * Constructor. Sets up the DayView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the DayView
     */
    public DayView(Context context, int layoutResource) {
        super(context);
        setupLayoutResource(layoutResource);
        this.context = context;
        this.layoutResource = layoutResource;
    }

    /**
     * Sets the layout resource for a custom DayView.
     *
     * @param layoutResource
     */
    private void setupLayoutResource(int layoutResource) {
        View inflated = LayoutInflater.from(getContext()).inflate(layoutResource, this);
        inflated.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        inflated.layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    @Override
    public void refreshContent() {
        measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    @Override
    public void drawDay(Canvas canvas , Day day) {
        this.day = day;
        refreshContent();
        int saveId = canvas.save();
        canvas.translate(day.getPosCol() * getMeasuredWidth(),
                day.getPosRow() * getMeasuredHeight());
        draw(canvas);
        canvas.restoreToCount(saveId);
    }
}