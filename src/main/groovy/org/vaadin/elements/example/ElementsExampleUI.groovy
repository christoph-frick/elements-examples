package org.vaadin.elements.example
import com.vaadin.annotations.JavaScript
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.CssLayout
import com.vaadin.ui.Notification
import com.vaadin.ui.UI
import org.vaadin.spring.annotation.VaadinUI

import static org.vaadin.elements.example.GroovyElements.elements

@VaadinUI
@JavaScript("vaadin://bower_components/webcomponentsjs/webcomponents.js")
class ElementsExampleUI extends UI {

    protected void init(VaadinRequest request) {
        final layout = new CssLayout()
        elements(layout) {
            div {
                'paper-slider'(value: '60', _bindings: [value: 'change'], _events: [
                        change: { Notification.show("Clicked for ${it.value}") }
                ])
            }
            div {
                'paper-button'([_events: [
                        click: { Notification.show("Clicked the button")}
                ]]) {
                    'core-icon'(icon: 'check')
                    b('Bold')
                    i('Italic')
                    span('Click me')
                }
            }
        }
        setContent(layout)
    }

}
