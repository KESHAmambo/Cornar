package org.test.customcomponents.menupage.profilepage.materialspage;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.*;
import org.test.tamplets.menupage.profilepage.materialspage.DocumentBox;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by abara on 10.11.2016.
 */
public class DocumentBoxImpl extends DocumentBox {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.yyyy HH:mm", Locale.ENGLISH);
    private Date creationDate;
    private HorizontalLayout layout;

    public DocumentBoxImpl(String docName, Date creationDate) {
        nameLabel.setValue(docName);
        this.creationDate = creationDate;
        dateLabel.setValue(dateFormat.format(creationDate));
        StreamResource streamResource = FileOfUserDownload.getFileStream(docName);
        FileDownloader downloader = new FileDownloader(streamResource);
        downloader.extend(saveDocButton);
        createListenersToViewButton(docName);
    }

    private void createListenersToViewButton(String docName){
        viewDocButton.addClickListener(event -> {
            if(!docName.matches(".*\\.pdf$")) {
                Notification.show("It's  not a pdf format");
                return;
            }
            layout = new HorizontalLayout();
            File pdfFile = new File(docName);
            Embedded pdf = new Embedded("", new FileResource(pdfFile));
            pdf.setMimeType("application/pdf");
            pdf.setType(Embedded.TYPE_BROWSER);
            Window w = new Window();
            layout.addComponent(pdf);
            pdf.setSizeFull();
            layout.setSizeFull();
            customizeWindowToPDF(w);
            UI.getCurrent().addWindow(w);
        });

    }
    private void customizeWindowToPDF(Window pdfWindow){
        pdfWindow.setContent(layout);
        pdfWindow.center();
        pdfWindow.setModal(true);
        pdfWindow.setWidth("60%");
        pdfWindow.setHeight("100%");
    }
    public Date getCreationDate() {
        return creationDate;
    }
}
