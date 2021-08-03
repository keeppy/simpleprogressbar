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
 * ProgressBar callback class.
 */
public abstract class SimpleProgressBarListener {
    
    private String className = SimpleProgressBarListener.class.getSimpleName();
    
    public SimpleProgressBarListener() {
        
    };
    
    public void started() {
        System.out.format("# Started: %s@%s\n", className,  hashCode());                
    }
    
    public void progress(ProgressData progressData) {
        System.out.format("# Progress: %s@%s %s\n", className, hashCode(), progressData);                
    }
    
    public void completed() {
        System.out.format("# Completed: %s@%s\n", className,  hashCode());                
    }
}
