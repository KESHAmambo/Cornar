package org.test.customcomponents.menupage;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.event.SelectionEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import org.hibernate.SQLQuery;
import org.test.dbservice.DatabaseManager;
import org.test.logic.Profile;
import org.test.tamplets.menupage.SearchPage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by abara on 09.11.2016.
 */
public class SearchPageImpl extends SearchPage implements View {
    List<Profile> profilesList;
    String valueOfBox;
    public SearchPageImpl() {
        searchTable.setStyleName("search-results",true);
        searchButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                profilesList = getSearchResult(searchName.getValue());
                updateTableOfSearchResult(profilesList);
            }
        });
    }
    public List<Profile> getSearchResult(String searchField){
        valueOfBox = (String) searchParametrBox.getValue();
        switch (valueOfBox){
            case "name":{
                List<Profile> usersList = DatabaseManager.getAllUsersWithNameLike(searchField);
                return usersList;
            }
            case "surname":{
                List<Profile> usersList = DatabaseManager.getAllUsersWithSurnameLike(searchField);
                return usersList;
            }
        }
        return new ArrayList<>();
    }

    public void updateTableOfSearchResult(List<Profile> profiles){
        searchTable.getContainerDataSource().removeAllItems();
        if (valueOfBox.equals("name") || valueOfBox.equals("surname"))
            for (Profile user: profiles)
                searchTable.addRow(user.getName() + " " + user.getSurname(), user.getEmail());

//        if (valueOfBox.equals("surname")) {
//            Grid.Column nameColumn = searchTable.getColumn("User Name");
//            searchTable.removeColumn("add person");
//            nameColumn.setHeaderCaption("Surname");
//            for (Profile user: profiles) {
//                searchTable.addRow(user.getSurname(), user.getEmail());
//            }
//        }
        searchTable.setVisible(true); // visible all time todo false in default
        searchTable.setSelectionMode(Grid.SelectionMode.SINGLE);
        searchTable.addSelectionListener(new SelectionEvent.SelectionListener() {

            @Override
            public void select(SelectionEvent event)
            {
                UI.getCurrent().addWindow(customizewindowToAdd(new Window()));
            }
        } );
    }

    //TODO add style
    public Window customizewindowToAdd(Window windowToAdd){
        VerticalLayout content = new VerticalLayout();
        content.setWidth("100%");
        content.setHeight("100%");
        content.addComponentAsFirst(new Label("Do you want to add this person?"));
        HorizontalLayout buttonsLayout = new HorizontalLayout();
        HorizontalLayout layoutAdd = new HorizontalLayout();
        HorizontalLayout layoutDecline = new HorizontalLayout();
        Button addButton = new Button("Add to friend");
        Button decline = new Button("Decline");
        createListenersToAddButton(addButton, windowToAdd);
        createListenersToDeclineButton(decline, windowToAdd);
        //content.setSpacing(true);
        layoutAdd.addComponent(addButton);
        layoutDecline.addComponent(decline);
        buttonsLayout.addComponent(layoutAdd);
        buttonsLayout.addComponent(layoutDecline);
        buttonsLayout.addStyleName("adding_button");
        content.addComponent(buttonsLayout);
        content.addStyleName("add_friend");
        windowToAdd.setContent(content);
        windowToAdd.setClosable(false);
        //windowToAdd.setDraggable(false);
        windowToAdd.setResizable(false);
        windowToAdd.center();
        //windowToAdd.setModal(true);
        windowToAdd.setWidth("30%");
        windowToAdd.setHeight("20%");
        return windowToAdd;
    }

    void createListenersToAddButton(Button addButton, Window windowToAdd){
        addButton.addClickListener(event -> {
            Object rowId = searchTable.getSelectedRow();
            Container container = searchTable.getContainerDataSource();
            String  friendEmail = (String) container.getContainerProperty(rowId,"Email").getValue();
            int userId = Profile.getCurrentProfile().getId();
            DatabaseManager.addToFriends(userId, friendEmail);
            Notification.show("Added to friends " + friendEmail);
            updateFriendListInCurrentSession(userId);
            windowToAdd.close();
        });
    }
    void createListenersToDeclineButton(Button decline, Window windowToAdd){
        decline.addClickListener(event -> {
            windowToAdd.close();
        });
    }

    private void updateFriendListInCurrentSession(int userId){
        List<Profile> friends = DatabaseManager.getAllFriendOfUser(userId);
        Profile.getCurrentProfile().setFriends(friends);
        Profile.getCurrentProfile().getFriends().forEach(System.out::println);
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

}
