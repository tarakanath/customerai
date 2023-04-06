package net.electrifai.library.listeners;

import com.aventstack.extentreports.GherkinKeyword;
import net.electrifai.library.utils.ThreadLocalManager;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.HookTestStep;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestStepStarted;

public class CustomEventListener extends ExtentReportListener implements ConcurrentEventListener
{


    @Override
    public void setEventPublisher(EventPublisher eventPublisher)
    {
        eventPublisher.registerHandlerFor(TestStepStarted.class, this::stepStarted);
    }


//    // This event is triggered when feature file is read
//    // here we create the feature node
//    private void featureRead(TestSourceRead event) {
//        String featureSource = event.getUri().toString();
//        String featureName = featureSource.split(".*/")[1];
//
////        if (feature.get(featureSource) == null)
////        {
////            try
////            {
////                feature.putIfAbsent(featureSource, extent.createTest(new GherkinKeyword("Feature"),featureName));
////            } catch (ClassNotFoundException e)
////            {
////                e.printStackTrace();
////            }
////        }
//    };


    // step started event
    // here we creates the test node
    private void stepStarted(TestStepStarted event)
    {


        String stepName = " ";
        String keyword = "Triggered the hook :";


        // We checks whether the event is from a hook or step
        if (event.getTestStep() instanceof PickleStepTestStep)
        {
            // TestStepStarted event implements PickleStepTestStep interface
            // WHich have additional methods to interact with the event object
            // So we have to cast TestCase object to get those methods
            PickleStepTestStep steps = (PickleStepTestStep) event.getTestStep();
            stepName = steps.getStep().getText();
            keyword = steps.getStep().getKeyword();

            try {
                ThreadLocalManager.getScenario().createNode(new GherkinKeyword(keyword), stepName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            // Same with HoojTestStep
            HookTestStep hoo = (HookTestStep) event.getTestStep();
            stepName = hoo.getHookType().name();
        }



    };


}
