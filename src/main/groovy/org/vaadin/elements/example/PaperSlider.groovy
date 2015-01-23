package org.vaadin.elements.example

import org.vaadin.elements.*

@Tag("paper-slider")
@Import("VAADIN/bower_components/paper-slider/paper-slider.html")
interface PaperSlider extends Element {
    public double setValue(double value)
    @UpdatedBy("core-change")
    public double getValue()
}
