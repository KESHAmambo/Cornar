package org.test.customcomponents;

import com.vaadin.data.Property;
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
    public ProfilePageImpl() {
        bindLabelsToProfileData();
    }

    public ProfilePageImpl(Profile currentProfile) {
        bindLabelsToProfileData();
    }

    private void bindLabelsToProfileData() {
        System.out.println(Profile.getCurrentProfile() + "binding");
        nameLabel.setPropertyDataSource(new Property() {
            @Override
            public Object getValue() {
                Profile profile = Profile.getCurrentProfile();
                System.out.println(profile);
                String name = profile.getName();
                String surname = profile.getSurname();
                return name + " " + surname;
            }

            @Override
            public void setValue(Object o) throws ReadOnlyException {

            }

            @Override
            public Class getType() {
                return String.class;
            }

            @Override
            public boolean isReadOnly() {
                return false;
            }

            @Override
            public void setReadOnly(boolean b) {

            }
        });
        birthdayLabel.setPropertyDataSource(new Property() {
            @Override
            public Object getValue() {
                Date birthDate = Profile.getCurrentProfile().getBirthDate();
                if(birthDate == null) {
                    return "No information";
                } else {
                    return dateFormat.format(birthDate);
                }
            }

            @Override
            public void setValue(Object o) throws ReadOnlyException {

            }

            @Override
            public Class getType() {
                return String.class;
            }

            @Override
            public boolean isReadOnly() {
                return false;
            }

            @Override
            public void setReadOnly(boolean b) {

            }
        });
        emailLabel.setPropertyDataSource(new Property() {
            @Override
            public Object getValue() {
                return Profile.getCurrentProfile().getEmail();
            }

            @Override
            public void setValue(Object o) throws ReadOnlyException {

            }

            @Override
            public Class getType() {
                return String.class;
            }

            @Override
            public boolean isReadOnly() {
                return false;
            }

            @Override
            public void setReadOnly(boolean b) {

            }
        });
        educationLabel.setPropertyDataSource(new Property() {
            @Override
            public Object getValue() {
                String education = Profile.getCurrentProfile().getEducation();
                if(education == null || education.equals("")) {
                    return "No information";
                } else {
                    return education;
                }
            }

            @Override
            public void setValue(Object o) throws ReadOnlyException {

            }

            @Override
            public Class getType() {
                return String.class;
            }

            @Override
            public boolean isReadOnly() {
                return false;
            }

            @Override
            public void setReadOnly(boolean b) {

            }
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
