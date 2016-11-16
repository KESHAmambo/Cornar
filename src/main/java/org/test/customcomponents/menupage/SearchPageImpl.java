package org.test.customcomponents.menupage;

import com.vaadin.data.Property;
import com.vaadin.event.SelectionEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Select;
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
        if (valueOfBox.equals("name"))
            for (Profile user: profiles)
                searchTable.addRow(user.getName(), user.getEmail());

        if (valueOfBox.equals("surname")) {
            Grid.Column nameColumn = searchTable.getColumn("User Name");
            nameColumn.setHeaderCaption("Surname");
            for (Profile user: profiles) {
                searchTable.addRow(user.getSurname(), user.getEmail());
            }
        }
        searchTable.setVisible(true);
        searchTable.setSelectionMode(Grid.SelectionMode.SINGLE);
        searchTable.addSelectionListener(new SelectionEvent.SelectionListener() {

            @Override
            public void select(SelectionEvent event) {
                Notification.show("Select row: "+searchTable.getSelectedRow());
            }
        } );
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

}
