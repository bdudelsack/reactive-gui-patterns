package de.hs_flensburg.mobilecomputing.mvvm_reactive_graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.wifi.ScanResult;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.*;

public class GraphView extends View {
    protected Paint axisPaint;
    protected Paint gridPaint;
    protected Paint barPaint;

    protected int numChannels = 13;
    protected int numVerticalSegments = 10;

    protected Map<Integer,List<ScanResult>> scanResultsByChannel;

    public GraphView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        axisPaint = new Paint();
        axisPaint.setStyle(Paint.Style.STROKE);
        axisPaint.setColor(Color.BLACK);
        axisPaint.setStrokeWidth(3.0f);

        gridPaint = new Paint();
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setColor(Color.GRAY);

        barPaint = new Paint();
        barPaint.setStyle(Paint.Style.FILL);
        barPaint.setARGB(75,255,0,0);
        barPaint.setStrokeWidth(50);

        scanResultsByChannel = new HashMap<>();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.scale(1.0f, -1.0f, getWidth() / 2.0f, getHeight() / 2.0f); // Flip the Y axis

        repaintStaticContent(canvas);
        repaintDynamicContent(canvas);
    }

    protected void repaintStaticContent(Canvas canvas) {
        drawGrid(canvas, gridPaint);
        drawAxis(canvas, axisPaint);
    }

    protected void repaintDynamicContent(Canvas canvas) {
        drawScanResults(canvas);
    }

    protected void drawAxis(Canvas canvas, Paint paint) {
        canvas.drawLine(0f, 1f, 0f, getHeight(), paint);
        canvas.drawLine(0f,1f,getWidth(),1f,paint);
    }

    protected void drawGrid(Canvas canvas, Paint paint) {
        float segmentHeight = (float)getHeight() / (float)numVerticalSegments;
        for(int n =0; n < numVerticalSegments; n++) {
            float y = segmentHeight * (float)n;
            canvas.drawLine(0,y,getWidth(),y,gridPaint);
        }
    }

    protected void drawScanResults(Canvas canvas) {
        for(int n = 1; n <= numChannels; n++) {
            List<ScanResult> results = scanResultsByChannel.get(n);
            int x = ((getWidth() - 100) / numChannels) * n;
            if(results != null && results.size() > 0) {
                for(ScanResult res : results) {
                    int y = getHeight() - (getHeight() / 100 * Math.abs(res.level));
                    canvas.drawLine(x,0,x,y, barPaint);
                }
            }
        }
    }

    public void setScanResultList(List<ScanResult> scanResultList) {
        scanResultsByChannel = new HashMap<>();

        for(ScanResult result : scanResultList) {
            int channel = convertFrequencyToChannel(result.frequency);

            if(channel == -1 || channel > numChannels)
                continue;

            if(scanResultsByChannel.get(channel) == null){
                scanResultsByChannel.put(channel, new LinkedList<>());
            }

            scanResultsByChannel.get(channel).add(result);
        }

        invalidate();
    }


    protected int convertFrequencyToChannel(int freq) {
        if (freq >= 2412 && freq <= 2484) {
            return (freq - 2412) / 5 + 1;
        } else if (freq >= 5170 && freq <= 5825) {
            return (freq - 5170) / 5 + 34;
        } else {
            return -1;
        }
    }
}
