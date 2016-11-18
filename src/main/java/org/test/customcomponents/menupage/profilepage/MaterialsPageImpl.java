package org.test.customcomponents.menupage.profilepage;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import org.test.controllers.menupage.profilepage.MaterialsPageController;
import org.test.customcomponents.menupage.profilepage.materialspage.DocumentBoxImpl;
import org.test.tamplets.menupage.profilepage.MaterialsPage;

import java.util.List;

/**
 * Created by abara on 10.11.2016.
 */
public class MaterialsPageImpl extends MaterialsPage implements View{
    private final MaterialsPageController controller;

    public MaterialsPageImpl() {
        controller = new MaterialsPageController(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        mainLayout.removeAllComponents();
        List<DocumentBoxImpl> documentBoxes = controller.pullDocumentBoxes();
        documentBoxes.forEach(mainLayout::addComponent);
    }
}
