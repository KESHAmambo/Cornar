package org.test.customcomponents.menupage;

import com.vaadin.event.SelectionEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import org.test.dbservice.DatabaseManager;
import org.test.logic.Profile;
import org.test.tamplets.menupage.SearchPage;

import java.util.List;

/**
 * Created by abara on 09.11.2016.
 */
public class SearchPageImpl extends SearchPage implements View {
    List<Profile> profilesList;

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
        List<Profile> usersList = DatabaseManager.getAllUsersWithNameLike(searchField);
        return usersList;
    }

    public void updateTableOfSearchResult(List<Profile> profiles){
        searchTable.getContainerDataSource().removeAllItems();
        for (Profile user: profiles) {
            searchTable.addRow(user.getName(), user.getEmail());
        }
        searchTable.setVisible(true);
        searchTable.setSelectionMode(Grid.SelectionMode.SINGLE);
        searchTable.addSelectionListener(new SelectionEvent.SelectionListener() {

            @Override
            public void select(SelectionEvent event) {
                Notification.show("Select row: "+searchTable.getSelectedRow());
            }
        });
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
