package org.test.controllers.menupage.profilepage;

import org.test.customcomponents.menupage.profilepage.MaterialsPageImpl;
import org.test.customcomponents.menupage.profilepage.materialspage.DocumentBoxImpl;
import org.test.dbservice.DatabaseManager;

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
        List<DocumentBoxImpl> documentBoxes = DatabaseManager.pullDocuments();
        documentBoxes.sort((DocumentBoxImpl b1, DocumentBoxImpl b2)-> {
            Date creationDate1 = b1.getCreationDate();
            Date creationDate2 = b2.getCreationDate();
            return creationDate2.compareTo(creationDate1);
        });
        return documentBoxes;
    }
}
