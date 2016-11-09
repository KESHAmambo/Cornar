package org.test.customcomponents;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import org.test.logic.Profile;
import org.test.tamplets.ProfilePage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by abara on 06.11.2016.
 */
public class ProfilePageImpl extends ProfilePage implements View {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH);

    public ProfilePageImpl(Profile profile) {
        fulfillProfileLabels(profile);
    }

    private void fulfillProfileLabels(Profile profile) {
        String name = profile.getName();
        String surname = profile.getSurname();
        nameLabel.setValue(name + " " + surname);

        Date birthDate = profile.getBirthDate();
        if(birthDate == null) {
            birthdayLabel.setValue("No information");
        } else {
            birthdayLabel.setValue(dateFormat.format(birthDate));
        }

        String email = profile.getEmail();
        emailLabel.setValue(email);

        String education = profile.getEducation();
        if(education == null || education.equals("")) {
            educationLabel.setValue("No information");
        } else {
            educationLabel.setValue(education);
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
