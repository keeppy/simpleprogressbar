/*******************************************************************************
 * The MIT License
 * 
 * Copyright (c) 2021 Daniel Hong : keeppy@gmail.com
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 ******************************************************************************/

package com.keeppy.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;

/**
 * 
 * Simple Progress Bar
 *
 */
public class SimpleProgressBar extends Composite implements IProgressBar {

    ProgressData progressData;
    SimpleProgressBarType type = SimpleProgressBarType.HORIZONTAL;
    Color barColor = new Color(getDisplay(), 0, 193, 212);
    
    SimpleProgressBarListener listener = null;

    /**
     * Create the composite.
     * 
     * @param parent
     * @param style
     */
    public SimpleProgressBar(Composite parent, int style) {
        this(parent, style, null);
    }
    
    /**
     * Create the composite with listener.
     * 
     * @param parent
     * @param style
     * @param listener
     */
    public SimpleProgressBar(Composite parent, int style, SimpleProgressBarListener listener) {
        super(parent, SWT.BORDER);
        
        this.listener = listener;
        
        setLayout(null);
        
        progressData = new ProgressData();
        
        if ((style & SWT.VERTICAL) == SWT.VERTICAL) {
            type = SimpleProgressBarType.VERTICAL;
        } else {
            type = SimpleProgressBarType.HORIZONTAL;
        }
                
        addPaintListener(new PaintListener() {
            
            @Override
            public void paintControl(PaintEvent e) {

                paint(e.gc);
                
            }
        });
    }
    
    
    
    @Override
    public void dispose() {
        super.dispose();
        barColor.dispose();
        progressData = null;
        listener = null;
    }
    
    @Override
    public void reset() {
        progressData.min = 0;
        progressData.max = 100;
        progressData.position = 0;
        progressData.started = false;
        progressData.completed = false;

        repaint();
    }


    private void repaint() {
        redraw();
    }

    @Override
    public void increment() {
        
        if (progressData.max > progressData.position) {
            progressData.position ++;
        }

        repaint();
        checkAndFireEvent();
    }
    
    private void checkAndFireEvent() {
        if (this.listener != null) {
            
            if (!progressData.started) {                
            
                progressData.started = true;
                this.listener.started();        
                
            } else {
                
                if (progressData.max == progressData.position) {                    
                    if (!progressData.completed) { 
                        progressData.completed = true;
                        if (this.listener != null) {
                            this.listener.completed();
                        }
                    }
                            
                } else {
                    if (!progressData.completed) { 
                        this.listener.progress(progressData.clone());
                    }
                }
            }
        }    
    }

    @Override
    public void increment(int incVal) {
        int val = incVal + progressData.position;
        if (val < progressData.min) {
            val = progressData.min;
        } else if (val > progressData.max) {
            val = progressData.max;
        }
        progressData.position = val;

        repaint();
        checkAndFireEvent();
    }
    

    @Override
    public void setSelection(int position) {
        int pos = Math.abs(position);
        if (pos > progressData.max) {
            pos = progressData.max;
        } else if (pos < progressData.min) {
            pos = progressData.min;
        }
        progressData.position = pos;
        
        repaint();
        checkAndFireEvent();
    }
    
    @Override
    public int getSelection() {
        return progressData.position;
    }
    
        
    private void paint(GC gc) {
        
        if (type == SimpleProgressBarType.VERTICAL) {
            Rectangle rect = getClientArea();

            gc.setBackground(barColor);

            int height = (int)(rect.height * calcRatio(progressData));
            
            Rectangle drawRect = new Rectangle(0, rect.height - height, rect.width, rect.height);
            gc.fillRectangle(drawRect);
            
            gc.dispose();

        } else {
            
            Rectangle rect = getClientArea();
            
            gc.setBackground(barColor);

            int width = (int)(rect.width * calcRatio(progressData));
            
            Rectangle drawRect = new Rectangle(0, 0, width, rect.height);

            gc.fillRectangle(drawRect);
            
            gc.dispose();
        }
        
    }

    private double calcRatio(ProgressData progressData) {
        if (progressData == null) {
            return 0;
        }
        if (progressData.min >= progressData.max) {
            return 100;
        }
        double percent = ((double)progressData.position / (double)progressData.max);
        return percent;
    }

    @Override
    public void setBarColor(Color color) {

        if (color != null) {
            this.barColor = color;
        }
        
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SimpleProgressBar [progressData=");
        builder.append(progressData);
        builder.append(", type=");
        builder.append(type);
        builder.append(", barColor=");
        builder.append(barColor);
        builder.append("]");
        return builder.toString();
    }

    public void setListener(SimpleProgressBarListener listener) {
        this.listener = listener;
    }
    
}
