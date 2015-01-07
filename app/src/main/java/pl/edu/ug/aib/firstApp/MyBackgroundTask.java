package pl.edu.ug.aib.firstApp;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

import pl.edu.ug.aib.firstApp.data.FacebookPage;
import pl.edu.ug.aib.firstApp.interfaces.FacebookPageRestClient;

@EBean
public class MyBackgroundTask {

    @RootContext
    FirstActivity activity;

    @RestService
    FacebookPageRestClient restClient;

    @Background
    void doInBackground(String name) {
        FacebookPage fbPage = restClient.getPage(name);
    }

    @UiThread
    void publishResult() {
        activity.goToSecondActivity(fbPage);
    }

}
