package org.test.customcomponents.menupage.profilepage.materialspage;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import org.test.tamplets.menupage.profilepage.materialspage.DocumentBox;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by abara on 10.11.2016.
 */
public class DocumentBoxImpl extends DocumentBox {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.yyyy HH:mm", Locale.ENGLISH);
    private Date creationDate;

    public DocumentBoxImpl(String docName, Date creationDate) {
        nameLabel.setValue(docName);
        this.creationDate = creationDate;
        dateLabel.setValue(dateFormat.format(creationDate));
        StreamResource streamResource = FileOfUserDownload.getFileStream(docName);
        FileDownloader downloader = new FileDownloader(streamResource);
        downloader.extend(saveDocButton);
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
