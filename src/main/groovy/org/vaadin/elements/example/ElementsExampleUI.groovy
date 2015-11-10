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
        final slider
        final input
        elements(layout) {
            div {
                slider = 'paper-slider'(value: '60', _bindings: [value: 'change'], _events: [
                        change: { Notification.show("Slided to ${it.value}") }
                ])
            }
            div {
                input = 'paper-input'(label: "Input", _bindings: [value: 'change'])
            }
            div {
                'paper-button'([_events: [
                        click: { Notification.show("Slider: $slider.value / input $input.value")}
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
