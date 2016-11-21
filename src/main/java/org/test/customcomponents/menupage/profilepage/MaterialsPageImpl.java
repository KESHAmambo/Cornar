package org.test.customcomponents.menupage.profilepage;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import org.test.controllers.menupage.profilepage.MaterialsPageController;
import com.vaadin.ui.*;
import org.test.customcomponents.menupage.ProfilePageImpl;
import org.test.customcomponents.menupage.profilepage.materialspage.DocumentBoxImpl;
import org.test.customcomponents.menupage.profilepage.materialspage.FileReceiver;
import org.test.customcomponents.menupage.profilepage.materialspage.UploadBoxImpl;
import org.test.logic.Profile;
import org.test.tamplets.menupage.ProfilePage;
import org.test.tamplets.menupage.profilepage.MaterialsPage;

import java.util.List;

/**
 * Created by abara on 10.11.2016.
 */
public class MaterialsPageImpl extends MaterialsPage implements View {
    private final MaterialsPageController controller;
    private final UploadBoxImpl uploadBox;
    private final Window windowToUpload;

    public MaterialsPageImpl() {
        controller = new MaterialsPageController(this);
        uploadBox = new UploadBoxImpl();
        windowToUpload = new Window();
        customizeWindowToUpload();
        createListenerToAddNewFilesButton();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        forDocsLayout.removeAllComponents();
        List<DocumentBoxImpl> documentBoxes = controller.pullDocumentBoxes();
        documentBoxes.forEach(forDocsLayout::addComponent);
    }

    public void customizeWindowToUpload(){
        windowToUpload.setContent(uploadBox);
        windowToUpload.center();
        windowToUpload.setModal(true);
        windowToUpload.setWidth("30%");
        windowToUpload.setHeight("60%");
    }

    private void createListenerToAddNewFilesButton(){
        addingButton.addClickListener(event -> UI.getCurrent().addWindow(windowToUpload));
    }

}
