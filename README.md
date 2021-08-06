# SWT Custom Progress Bar Example.
Welcome to the simpleprogressbar wiki!

## How to
### Basic Usage
   Simply replace SWT ProgressBar with SimpleProgressBar

#### FROM
```
    ProgressBar progressBar = new ProgressBar(shell, SWT.NONE);
    .
    .
    progressBar.setSelection(...)
    progressBar.setSelection()..

```

#### TO
```
    SimpleProgressBar progressBar = new SimpleProgressBar(shell, SET.NONE);
    .
    .
    progressBar.setSelection(...)
    progressBar.setSelection()..
```

### Callback 
SimpleProgressBar notifies these following events:
**started**, **progress** and **completed** with SimpleProgressBarListener
```
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

```

### Callback example
```
SimpleProgressBar simpleProgressBar = new SimpleProgressBar(shell, SWT.NONE, new SimpleProgressBarListener() {
    @Override
    public void completed() {
        System.out.println("Completed: " + simpleProgressBar.hashCode());                
        simpleProgressBar.reset();
    }
});            
```

## Running Demo Application
```
1. Clone this project. (on the Eclipse IDE with WindowBuilderPro plugin) 
2. Run Maven update.
3. Run Java Application : com.keeppy.controls.SimpleProgressBarExample.
```

### Download & Install Eclipse, WindowBuilderPro
[Goto WindowBuilderPro Download](https://www.eclipse.org/windowbuilder/download.php)


If you've already installed **Eclipse IDE for RCP and RAP Developers**, this step isn't required.

### Result Screen

<img src="https://user-images.githubusercontent.com/20672056/128159959-387f9dd0-65c5-4d1e-8a31-7c756cdc5f7d.png" alt="Demo Screen" width="60%" height="60%" />
