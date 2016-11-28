package org.test.controllers.menupage.profilepage;

import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.test.customcomponents.menupage.profilepage.MaterialsPageImpl;
import org.test.customcomponents.menupage.profilepage.materialspage.DocumentBoxImpl;
import org.test.dbservice.DatabaseManager;
import org.test.logic.Profile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by abara on 11.11.2016.
 */
public class MaterialsPageController {
    private MaterialsPageImpl materialsPage;

    public MaterialsPageController(MaterialsPageImpl materialsPage) {
        this.materialsPage = materialsPage;
    }

    public List<DocumentBoxImpl> pullDocumentBoxes() {
        List<DocumentBoxImpl> documentBoxes = DatabaseManager.pullDocumentsBy(Profile.getCurrentProfile().getId());
        documentBoxes.sort((DocumentBoxImpl b1, DocumentBoxImpl b2)-> {
            Date creationDate1 = b1.getCreationDate();
            Date creationDate2 = b2.getCreationDate();
            return creationDate2.compareTo(creationDate1);
        });
        return documentBoxes;
    }
    public void createListenerToAddNewFilesButton(Button addingButton, Window windowToUpload){
        addingButton.addClickListener(event -> UI.getCurrent().addWindow(windowToUpload));
    }
}
