package pl.edu.ug.aib.firstApp;

import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import pl.edu.ug.aib.firstApp.adapter.PersonListAdapter;
import pl.edu.ug.aib.firstApp.data.FacebookPage;
import pl.edu.ug.aib.firstApp.data.Person;

@EActivity(R.layout.activity_my)
@OptionsMenu(R.menu.my)
public class FirstActivity extends ActionBarActivity {

    @ViewById
    public EditText username;

    @ViewById
    public EditText password;

    @ViewById
    ListView list;

    @Bean
    PersonListAdapter adapter;

    @NonConfigurationInstance
    @Bean
    MyBackgroundTask myBackgroundTask;

    @AfterViews
    void init() {
        list.setAdapter(adapter);
    }

    @Click
    void loginClicked()    {
        if (username.getText().length() < 3) {
            Toast.makeText(this, getString(R.string.uernameTooShort), Toast.LENGTH_SHORT).show();
            return;
        }
        myBackgroundTask.doInBackground(username.getText().toString());
    }

    void goToSecondActivity(FacebookPage page) {
        String text = username.getText().toString();
        SecondActivity_.intent(this).username(page.name).start();
    }

    @ItemClick
    void listItemClicked(Person item) {
        Toast.makeText(this, item.name, Toast.LENGTH_SHORT).show();
    }

    @OptionsItem
    void settingsSelected() {
    }
    public void showError(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }
}
