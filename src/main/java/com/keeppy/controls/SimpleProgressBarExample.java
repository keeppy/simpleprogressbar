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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Example
 */
public class SimpleProgressBarExample {

	protected Shell shell;
	private SimpleProgressBar simpleProgressBar;
	private SimpleProgressBar simpleProgressBarVert;
	private Button btnStop;

	private Thread exampleThread;
	private boolean stopped = false;
	private SimpleProgressBar simpleProgressBar2;
	private SimpleProgressBar simpleProgressBar3;
	private SimpleProgressBar simpleProgressBarVert2;
	private SimpleProgressBar simpleProgressBarVert3;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SimpleProgressBarExample window = new SimpleProgressBarExample();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		exampleThread.interrupt();
		
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		
		shell.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent e) {
				exampleThread.interrupt();
			}
		});
		
		shell.setSize(836, 316);
		shell.setText("SWT SimpleProgressBar Example Application");
		
		simpleProgressBar = new SimpleProgressBar(shell, SWT.NONE, new SimpleProgressBarListener() {
			@Override
			public void completed() {
				System.out.println("Completed: " + simpleProgressBar.hashCode());				
				simpleProgressBar.reset();
			}
		});
		
		simpleProgressBar.setBounds(27, 36, 545, 41);
		
		Button btnStart = new Button(shell, SWT.NONE);
		btnStart.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				stopped = false;
				
				if (exampleThread != null) {
					exampleThread.interrupt();
					exampleThread = null;
				}

				exampleThread = new Thread(new Runnable() {
					
					@Override
					public void run() {
												
						Display.getDefault().syncExec(new Runnable() {
							@Override
							public void run() {								
								simpleProgressBar.reset();
								simpleProgressBar2.reset();
								simpleProgressBar3.reset();
								simpleProgressBarVert.reset();
								simpleProgressBarVert2.reset();
								simpleProgressBarVert3.reset();
							}							
						});
						
						try {
							
							
														
							while (!stopped) {
	
								Display.getDefault().asyncExec(new Runnable() {
	
									@Override
									public void run() {										
										simpleProgressBar.increment(4);
										simpleProgressBar2.increment(9);
										simpleProgressBar3.increment(13);
										simpleProgressBarVert.increment(3);
										simpleProgressBarVert2.increment(5);
										simpleProgressBarVert3.increment(7);
									}
									
								});
								
								Thread.sleep(100);
							}
							
						} catch (InterruptedException x) {
							System.err.println("Thread interrupted!");
						}
						
						System.out.println("UI Thread Terminated!!");	
					}
					
				});
				
				exampleThread.start();
	
			}
		});
		btnStart.setBounds(27, 189, 109, 35);
		btnStart.setText("Start");
		
		simpleProgressBarVert = new SimpleProgressBar(shell, SWT.VERTICAL, new SimpleProgressBarListener() {			
			@Override
			public void completed() {
				simpleProgressBarVert.reset();
			}
		});
		
		simpleProgressBarVert.setBounds(601, 36, 44, 188);
		simpleProgressBarVert.setBarColor(new Color(shell.getDisplay(), 233, 59, 129));
		simpleProgressBarVert.setBackground(new Color(shell.getDisplay(), 128, 128, 128));

		btnStop = new Button(shell, SWT.NONE);
		btnStop.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				stopped = true;
			}
			
		});
		btnStop.setBounds(153, 189, 109, 35);
		btnStop.setText("Stop");		
		
		simpleProgressBar2 = new SimpleProgressBar(shell, SWT.NONE, new SimpleProgressBarListener() {	
			@Override
			public void completed() {
				super.completed();
				simpleProgressBar2.reset();
			}			
		});
		
		simpleProgressBar2.setLocation(27, 83);
		simpleProgressBar2.setSize(545, 41);				
		simpleProgressBar2.setBarColor(new Color(shell.getDisplay(), 3, 63, 255));

		
		simpleProgressBar3 = new SimpleProgressBar(shell, SWT.NONE, new SimpleProgressBarListener() {
			@Override
			public void completed() {
				super.completed();
				simpleProgressBar3.reset();
			}	
		});
		
		simpleProgressBar3.setBounds(27, 129, 545, 41);
		simpleProgressBar3.setBarColor(new Color(shell.getDisplay(), 90, 90, 90));
		
		simpleProgressBarVert2 = new SimpleProgressBar(shell, SWT.V_SCROLL, new SimpleProgressBarListener() {
			@Override
			public void completed() {
				super.completed();
				simpleProgressBarVert2.reset();
			}	
		});
		
		simpleProgressBarVert2.setBounds(665, 36, 44, 188);
		simpleProgressBarVert2.setBarColor(new Color(shell.getDisplay(), 247, 253, 4));
		simpleProgressBarVert2.setBackground(new Color(shell.getDisplay(), 128, 128, 128));
	
		simpleProgressBarVert3 = new SimpleProgressBar(shell, SWT.V_SCROLL, new SimpleProgressBarListener() {
			
			@Override
			public void completed() {
				super.completed();
				simpleProgressBarVert3.reset();
			}	
			
		});
		
		simpleProgressBarVert3.setBounds(733, 36, 44, 188);
		simpleProgressBarVert3.setBarColor(new Color(shell.getDisplay(), 255, 103, 1));
		simpleProgressBarVert3.setBackground(new Color(shell.getDisplay(), 128, 128, 128));

	}
}
