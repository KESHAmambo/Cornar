package org.test.customcomponents.menupage;

import com.vaadin.data.Container;
import com.vaadin.event.SelectionEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.test.customcomponents.menupage.searchpage.addToFriendBoxImpl;
import org.test.dbservice.DatabaseManager;
import org.test.logic.Profile;
import org.test.tamplets.menupage.SearchPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abara on 09.11.2016.
 */
public class SearchPageImpl extends SearchPage implements View {
    List<Profile> profilesList;
    String valueOfBox;
    private final addToFriendBoxImpl addToFriendBox;
    public SearchPageImpl() {
        searchTable.setStyleName("search-results",true);
        searchButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                profilesList = getSearchResult(searchName.getValue());
                updateTableOfSearchResult(profilesList);
            }
        });
        addToFriendBox = new addToFriendBoxImpl();
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

    public Window customizeWindowToAdd(Window windowToAdd){
        createListenersToAddButton(addToFriendBox.getAddButton(), windowToAdd);
        createListenersToDeclineButton(addToFriendBox.getDeclineButton(), windowToAdd);
        windowToAdd.setContent(addToFriendBox);
        windowToAdd.setClosable(false);
        windowToAdd.setResizable(false);
        windowToAdd.center();
        windowToAdd.setModal(true);
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
