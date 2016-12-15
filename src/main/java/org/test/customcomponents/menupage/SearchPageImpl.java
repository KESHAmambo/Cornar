package org.test.customcomponents.menupage;

import com.vaadin.data.Container;
import com.vaadin.event.SelectionEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.test.customcomponents.menupage.searchpage.addToFriendBoxImpl;
import org.test.dbservice.DatabaseManager;
import org.test.logic.Profile;
import org.test.msgservice.UIAlterationManager;
import org.test.tamplets.menupage.SearchPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abara on 09.11.2016.
 */
public class SearchPageImpl extends SearchPage implements View {
    private List<Profile> profilesList;
    private String valueOfBox;
    private final addToFriendBoxImpl addToFriendButton;

    public SearchPageImpl() {
        searchTable.setStyleName("search-results",true);
        searchButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                profilesList = getSearchResult(searchName.getValue());
                updateTableOfSearchResult(profilesList);
            }
        });
        addToFriendButton = new addToFriendBoxImpl();
    }

    private List<Profile> getSearchResult(String searchField){
        valueOfBox = (String) searchParametrBox.getValue();
        switch (valueOfBox){
            case "name":{
                return DatabaseManager.getAllUsersWithNameLike(searchField);
            }
            case "surname":{
                return DatabaseManager.getAllUsersWithSurnameLike(searchField);
            }
        }
        return new ArrayList<>();
    }

    private void updateTableOfSearchResult(List<Profile> profiles){
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
        searchTable.setVisible(true); // visible all time
        searchTable.setSelectionMode(Grid.SelectionMode.SINGLE);
        searchTable.addSelectionListener(new SelectionEvent.SelectionListener() {

            @Override
            public void select(SelectionEvent event)
            {
                UI.getCurrent().addWindow(customizeWindowToAdd(new Window()));
            }
        } );
    }

    private Window customizeWindowToAdd(Window windowToAdd){
        createListenersToAddButton(addToFriendButton.getAddButton(), windowToAdd);
        createListenersToDeclineButton(addToFriendButton.getDeclineButton(), windowToAdd);
        windowToAdd.setContent(addToFriendButton);
        windowToAdd.setClosable(false);
        //windowToAdd.setDraggable(false);
        windowToAdd.setResizable(false);
        windowToAdd.center();
        windowToAdd.setModal(true);
        windowToAdd.setWidth("-1px");
        windowToAdd.setHeight("");
        return windowToAdd;
    }

    private void createListenersToAddButton(Button addButton, Window windowToAdd){
        addButton.addClickListener(event -> {
            Object rowId = searchTable.getSelectedRow();
            Container container = searchTable.getContainerDataSource();
            String  friendEmail = (String) container.getContainerProperty(rowId,"Email").getValue();
            int userId = Profile.getCurrentProfile().getId();

            DatabaseManager.addToFriends(userId, friendEmail);
            showAddedFriendInAllUIs(friendEmail);

            Notification.show("Added to friends " + friendEmail);
//            updateFriendListInCurrentSession(userId);
            windowToAdd.close();
        });
    }

    private void showAddedFriendInAllUIs(String friendEmail) {
        Profile newFriend = DatabaseManager.getProfile(friendEmail);
        UIAlterationManager.showAddedFriendInAllUIs(newFriend);
    }

    private void createListenersToDeclineButton(Button decline, Window windowToAdd){
        decline.addClickListener(event -> {
            windowToAdd.close();
        });
    }

    private void updateFriendListInCurrentSession(int userId){
        List<Profile> friends = DatabaseManager.getAllFriendOfUser(userId);
        Profile.getCurrentProfile().setFriends(friends);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

}
