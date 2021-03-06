package com.example.c301_w16_g5.c301_w16_g5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This is the view the user sees when they are updating their profile information.
 * This will be done in editable text fields.
 *
 * @author  Hailey
 * @version 1.4, 03/02/2016
 * @see     User
 * @see     DisplayProfileActivity
 * @see     UserController
 */
public class EditProfileActivity extends ChickBidActivity {

    public static final String PROFILE_EXTRA_KEY = "PROFILE_REQUEST";

    public static final String CREATE_USER_EXTRA_KEY = "create_user_username_extra_key";
    public static final String UPDATE_USER_EXTRA_KEY = "update_user_extra_key";

    public static final String CREATE_USER_USERNAME_EXTRA_KEY = "create_user_username_extra_key";

    private EditText usernameEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private EditText experienceEditText;

    private User user;

    private String activityType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.nav_toolbar);
        setSupportActionBar(toolbar);

        // show back arrow at top left
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        usernameEditText = (EditText) findViewById(R.id.username);
        firstNameEditText = (EditText) findViewById(R.id.firstName);
        lastNameEditText = (EditText) findViewById(R.id.lastName);
        emailEditText = (EditText) findViewById(R.id.email);
        phoneEditText = (EditText) findViewById(R.id.phone);
        experienceEditText = (EditText) findViewById(R.id.experience);

        Intent intent = getIntent();
        activityType = intent.getStringExtra(PROFILE_EXTRA_KEY);
        if (activityType != null && activityType.equals(CREATE_USER_EXTRA_KEY)) {
            // source:
            // http://stackoverflow.com/questions/3438276/change-title-bar-text-in-android
            // answered by Paresh Mayani on Aug 9 '10
            // accessed by Hailey on Mar 13 '16
            user = new User();
            getSupportActionBar().setTitle(R.string.title_activity_add_profile);
            usernameEditText.setText(intent.getStringExtra(CREATE_USER_USERNAME_EXTRA_KEY));
        } else if (activityType != null && activityType.equals(UPDATE_USER_EXTRA_KEY)) {
            user = ChickBidsApplication.getUserController().getCurrentUser();
            fillUserInformation();

            // 2016-03-15 : http://stackoverflow.com/questions/6731017/how-to-make-an-edittext-uneditable-disabled
            usernameEditText.setEnabled(false);
            usernameEditText.setClickable(false);
        }
    }

    private void fillUserInformation() {
        usernameEditText.setText(user.getUsername());
        firstNameEditText.setText(user.getFirstName());
        lastNameEditText.setText(user.getLastName());
        emailEditText.setText(user.getEmail());
        phoneEditText.setText(user.getPhoneNumber());
        experienceEditText.setText(user.getChickenExperience());
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (activityType != null && activityType.equals(CREATE_USER_EXTRA_KEY)) {
            menu.clear();
            onCreateOptionsMenu(menu);
            menu.removeItem(R.id.home_button);
            menu.removeItem(R.id.notifications_button);
        }
        return true;
    }

    public void updateUser(View view) {
        if (validateUpdatedUserInfo() == true) {
            updateUserInfo();
        } else {
            return;
        }

        String message = "";
        if (activityType.equals(UPDATE_USER_EXTRA_KEY)) {
            message = getString(R.string.account_updated_message);
            ChickBidsApplication.getUserController().updateUser(user);
        } else if (activityType.equals(CREATE_USER_EXTRA_KEY)) {
            message = getString(R.string.account_created_message);
            ChickBidsApplication.getUserController().saveUser(user);
        }

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

        finish();
    }

    private boolean validateUpdatedUserInfo() {
        String username = ((EditText) findViewById(R.id.username)).getText().toString();
        String firstName = ((EditText) findViewById(R.id.firstName)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.lastName)).getText().toString();
        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        String phone = ((EditText) findViewById(R.id.phone)).getText().toString();
        String experience = ((EditText) findViewById(R.id.experience)).getText().toString();

        String error_message = "";
        boolean valid = false;

        if (UserController.usernameInUse(username)) {
            error_message = "Username already in use";
        }

        if (!UserController.validateNames(username)) {
            error_message = "Invalid username";
        }

        if (!UserController.validateNames(firstName)) {
            error_message = "Invalid first name";
        }

        if (!UserController.validateNames(lastName)) {
            error_message = "Invalid last name";
        }

        if (!UserController.validateEmail(email)) {
            error_message = "Invalid email";
        }

        if (!UserController.validatePhoneNumber(phone)) {
            error_message = "Invalid phone number";
        }

        if (!UserController.validateChickenExperience(experience)) {
            error_message = "Invalid chicken experience description";
        }

        if (error_message.isEmpty()) {
            valid = true;
        } else {
            Toast.makeText(getApplicationContext(), error_message, Toast.LENGTH_SHORT).show();
        }

        return valid;
    }

    private void updateUserInfo() {
        String username = ((EditText) findViewById(R.id.username)).getText().toString();
        String firstName = ((EditText) findViewById(R.id.firstName)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.lastName)).getText().toString();
        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        String phone = ((EditText) findViewById(R.id.phone)).getText().toString();
        String experience = ((EditText) findViewById(R.id.experience)).getText().toString();

        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phone);
        user.setChickenExperience(experience);
    }

}
