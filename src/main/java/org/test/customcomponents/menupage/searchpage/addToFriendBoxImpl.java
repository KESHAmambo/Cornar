package org.test.customcomponents.menupage.searchpage;

import com.vaadin.ui.Button;
import org.test.tamplets.menupage.searchpage.addToFriendBox;

/**
 * Created by Taras on 14.12.2016.
 */
public class addToFriendBoxImpl extends addToFriendBox {
    public Button getAddButton(){
        return addButton;
    }

    public Button getDeclineButton(){
        return decline;
    }
}
