package org.vaadin.elements.example

import com.vaadin.annotations.JavaScript
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.CssLayout
import com.vaadin.ui.Notification
import com.vaadin.ui.UI
import org.vaadin.elements.ElementIntegration
import org.vaadin.elements.Elements
import org.vaadin.spring.annotation.VaadinUI

@VaadinUI
@JavaScript("vaadin://bower_components/webcomponentsjs/webcomponents.js")
class ElementsExampleUI extends UI {

    protected void init(VaadinRequest request) {
        final elemRoot = new CssLayout()
        final textRoot = new CssLayout()
        setContent(new CssLayout(elemRoot,textRoot))
        ElementIntegration.getRoot(elemRoot).with {
            appendChild(
                    Elements.create(PaperSlider).with {
                        value = 50.0d
                        addEventListener('change', { e ->
                            Notification.show("Value Changed to: ${it.getAttribute('value')}")
                        })
                        return it
                    }
            )
        }
        ElementIntegration.getRoot(textRoot).with {
            appendChild(
                    Elements.create('paper-slider').with {
                        setAttribute('value', "66")
                        addEventListener('change', { e ->
                            Notification.show("Value Changed to: ${it.getAttribute('value')}")
                        })
                        return it
                    }
            )
        }
    }

}
