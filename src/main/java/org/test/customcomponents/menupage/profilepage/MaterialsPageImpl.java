package org.test.customcomponents.menupage.profilepage;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import org.test.controllers.menupage.profilepage.MaterialsPageController;
import com.vaadin.ui.*;
import org.test.controllers.menupage.materilaspage.MaterialsPageController;
import org.test.customcomponents.menupage.ProfilePageImpl;
import org.test.customcomponents.menupage.profilepage.materialspage.DocumentBoxImpl;
import org.test.customcomponents.menupage.profilepage.materialspage.FileReceiver;
import org.test.customcomponents.menupage.profilepage.materialspage.UploadBoxImpl;
import org.test.logic.Cleanable;
import org.test.logic.Profile;
import org.test.tamplets.menupage.ProfilePage;
import org.test.tamplets.menupage.profilepage.MaterialsPage;

import java.util.List;

/**
 * Created by abara on 10.11.2016.
 */
public class MaterialsPageImpl extends MaterialsPage implements View, Cleanable {
    private final MaterialsPageController controller;
    Window windowToUpload;
    public MaterialsPageImpl() {
        controller = new MaterialsPageController(this);
        Profile.registerCleanable(this);
        createWindowToUpload();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        addingButton.addClickListener(event -> UI.getCurrent().addWindow(windowToUpload));
        forDocsLayout.removeAllComponents();
        List<DocumentBoxImpl> documentBoxes = controller.pullDocumentBoxes();
        documentBoxes.forEach(forDocsLayout::addComponent);
    }

    public void createWindowToUpload(){
        windowToUpload = new Window();
        windowToUpload.setContent(new UploadBoxImpl());
        windowToUpload.setWidth("200px");
        windowToUpload.setHeight("350px");
    }

    @Override
    public void cleanInformation() {
        mainLayout.removeAllComponents();
    }

}
