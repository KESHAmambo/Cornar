package org.test.customcomponents.menupage;

import com.vaadin.ui.Window;
import org.test.controllers.menupage.UploadImageBoxController;
import org.test.controllers.menupage.profilepage.materials.UploadBoxController;
import org.test.customcomponents.menupage.profilepage.materialspage.FileReceiver;
import org.test.customcomponents.menupage.profilepage.materialspage.ImageReceiver;
import org.test.tamplets.menupage.UploadImageBox;

/**
 * Created by Taras on 15.12.2016.
 */
public class UploadImageBoxImpl extends UploadImageBox {
    private final UploadImageBoxController controller;
    public Window window;

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public UploadImageBoxImpl(Window window) {
        controller = new UploadImageBoxController(this);
        this.window = window;
        //TODO imageReceiver
        ImageReceiver receiver = new ImageReceiver();
        image.setVisible(false);
        receiver.setImage(image);
        controller.setReceiverToUpload(uploadImageButton, receiver);
        controller.createListenersForUploads(uploadImageButton);
    }
}
