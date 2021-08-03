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

/**
 * Progress data class.
 */
public class ProgressData implements Cloneable {
    
    int min;
    int max;
    int position;
    
    boolean started;
    boolean completed;
    
    public ProgressData() {
        min = 0;
        max = 100;
        position = 0;
        started = false;
        completed = false;
    }
    
    @Override
    protected ProgressData clone() throws RuntimeException {
        ProgressData cloned = new ProgressData();
        cloned.min = min;
        cloned.max = max;
        cloned.position = position;
        cloned.started = started;
        cloned.completed = completed;
        return cloned;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ProgressData [min=");
        builder.append(min);
        builder.append(", max=");
        builder.append(max);
        builder.append(", position=");
        builder.append(position);
        builder.append(", started=");
        builder.append(started);
        builder.append(", completed=");
        builder.append(completed);
        builder.append("]");
        return builder.toString();
    }


}
