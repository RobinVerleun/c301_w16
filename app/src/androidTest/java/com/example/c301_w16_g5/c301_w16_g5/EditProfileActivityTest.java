package com.example.c301_w16_g5.c301_w16_g5;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Hailey on 2016-02-11.
 */
public class EditProfileActivityTest extends ActivityInstrumentationTestCase2 {
    public EditProfileActivityTest() {
        super(EditProfileActivity.class);
    }

    public void testStart() throws Exception {
        ChickBidsApplication.getUserController().setUser(new User("un", "f", "l", "abc@email.com", "780-123-4567", "some"));
        Activity activity = getActivity();
    }

//    public void testSendProfile() {
//        Intent intent = new Intent();
//        UserProfile profile = new UserProfile("unique_user123", "First",
//                "Last", "user@ualberta.ca", "780-123-4567",
//                "I am a dedicated chicken enthusiast");
//
//        intent.putExtra(AddProfileActivity.TEXT_SPECIFYING_PROFILE, profile);
//        setActivityIntent(intent);
//        AddProfileActivity epa = (AddProfileActivity) getActivity();
//
//        assertEquals("AddProfileActivity should get the user from the intent",
//                profile, epa.getUserProfile());
//    }
}