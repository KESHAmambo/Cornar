package org.test.customcomponents.menupage;

import com.vaadin.data.Property;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;
import org.test.controllers.menupage.ProfilePageController;
import org.test.customcomponents.MenuPageImpl;
import org.test.customcomponents.menupage.profilepage.InboxPageImpl;
import org.test.customcomponents.menupage.profilepage.MaterialsPageImpl;
import org.test.customcomponents.menupage.profilepage.SchedulePageImpl;
import org.test.logic.Profile;
import org.test.tamplets.menupage.ProfilePage;
import org.test.tamplets.menupage.profilepage.MaterialsPage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.test.logic.PageName.*;

/**
 * Created by abara on 06.11.2016.
 */
public class ProfilePageImpl extends ProfilePage implements View {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH);

    private final ProfilePageController controller;

    public ProfilePageImpl() {
        controller = new ProfilePageController(this);

        provideNavigation();
        bindLabelsToProfileData();
    }

    private void provideNavigation() {
        Navigator menuButtonsNavigator = createNavigatorForMenuButtons();
        createListenersForMenuButtons(menuButtonsNavigator);
    }

    private Navigator createNavigatorForMenuButtons() {
        Navigator menuButtonsNavigator = new Navigator(
                UI.getCurrent(), layoutForInnerPages);

        menuButtonsNavigator.addView("", new InboxPageImpl());
        menuButtonsNavigator.addView(MENU_PAGE.toString(), new InboxPageImpl());
        menuButtonsNavigator.addView(MENU_PAGE + "/" + PROFILE_PAGE, new InboxPageImpl());

        menuButtonsNavigator.addView(
                MENU_PAGE + "/" + PROFILE_PAGE + "/" + INBOX, new InboxPageImpl());
        menuButtonsNavigator.addView(
                MENU_PAGE + "/" + PROFILE_PAGE + "/" + MATERIALS, new MaterialsPageImpl());
        menuButtonsNavigator.addView(
                MENU_PAGE + "/" + PROFILE_PAGE + "/" + SCHEDULE, new SchedulePageImpl());
        return menuButtonsNavigator;
    }

    private void createListenersForMenuButtons(Navigator menuButtonsNavigator) {
        controller.createListenerForMenuButton(
                inboxButton, menuButtonsNavigator, MENU_PAGE + "/" + PROFILE_PAGE + "/" + INBOX);
        controller.createListenerForMenuButton(
                scheduleButton, menuButtonsNavigator, MENU_PAGE + "/" + PROFILE_PAGE + "/" + SCHEDULE);
        controller.createListenerForMenuButton(
                materialsButton, menuButtonsNavigator, MENU_PAGE + "/" + PROFILE_PAGE + "/" + MATERIALS);
    }

    private void bindLabelsToProfileData() {
        nameLabel.setPropertyDataSource(new Property() {
            @Override
            public Object getValue() {
                Profile profile = Profile.getCurrentProfile();
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
