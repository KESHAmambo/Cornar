package org.test.customcomponents;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import org.test.tamplets.MenuPage;
import org.vaadin.sliderpanel.SliderPanel;
import org.vaadin.sliderpanel.SliderPanelBuilder;
import org.vaadin.sliderpanel.client.SliderMode;
import org.vaadin.sliderpanel.client.SliderTabPosition;

import java.io.File;

/**
 * Created by Аркадий on 26.10.2016.
 */
public class MenuPageImpl extends MenuPage {
    public MenuPageImpl() {
        SliderPanel chatSlidePanel = new SliderPanelBuilder(getChatPanelContent())
                .expanded(false)
                .caption("Dialogs")
                .mode(SliderMode.RIGHT)
                .style("chatPanelMajorLayout")
                .tabPosition(SliderTabPosition.MIDDLE)
                .build();
        majorHorizontalLayout.addComponent(chatSlidePanel);

        String basePath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        System.out.println(basePath);
//        FileResource avatarResource = new FileResource(new File(basePath + "VAADIN/themes/images/test.png"));
        FileResource avatarResource = new FileResource(new File(basePath + "/images/test.png"));
        avatarImage.setSource(avatarResource);

//        Image avatar = new Image("", avatarResource);

//        avatarLayout.addComponent(avatar, Alignment_M);
    }

    private Component getChatPanelContent() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setHeightUndefined();
//        verticalLayout.setStyleName("chatPanelMajorLayout");
        verticalLayout.setWidth("400");
        verticalLayout.setHeight("100%");
        return verticalLayout;
    }


}
