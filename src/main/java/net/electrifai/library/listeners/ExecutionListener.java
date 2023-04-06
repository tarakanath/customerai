package net.electrifai.library.listeners;


import org.testng.IExecutionListener;

public class ExecutionListener implements IExecutionListener
{

    public void onExecutionStart()
    {
        System.out.println("Execution start ");
    }

    public void onExecutionFinish()
    {

        System.out.println("Execution end ");
    }

}
