package org.vaadin.elements.example
import com.vaadin.ui.AbstractComponent
import com.vaadin.ui.JavaScriptFunction
import org.jsoup.parser.Tag
import org.vaadin.elements.Element
import org.vaadin.elements.ElementIntegration
import org.vaadin.elements.Elements
import org.vaadin.elements.Root

class GroovyElements {

    final Element element

    GroovyElements(Element element) {
        this.element = element
    }

    def propertyMissing(String name, String value) {
        element.setAttribute(name, value)
    }

    def propertyMissing(String name) {
        element.getAttribute(name)
    }

    def methodMissing(String name, args) {
        def elem = Elements.create(name)
        def gelem = new GroovyElements(elem)
        element.appendChild(elem)
        if (!Tag.valueOf(name).isKnownTag()) {
            // TODO: need a resolver for this or at least a config
            elem.root.importHtml("VAADIN/bower_components/${name}/${name}.html")
        }
        args.find{ it instanceof Map}?.with{ map ->
            map.each{ String k, v ->
                switch(k) {
                    case '_events':
                        v.each{ String event, Closure handler ->
                            elem.addEventListener(event, { handler(gelem) } as JavaScriptFunction)
                        }
                        break
                    case '_bindings':
                        v.each{ String attributeName, String eventName ->
                            elem.bindAttribute(attributeName, eventName)
                        }
                        break
                    default:
                        if (v instanceof Boolean) {
                            elem.setAttribute(k, v as Boolean)
                        } else {
                            elem.setAttribute(k, v as String)
                        }
                }
            }
        }
        args.find{ it instanceof String }?.with {
            elem.setInnerText(it as String)
        }
        args.find{ it instanceof Closure }?.with{
            (it.clone() as Closure).with{ c ->
                c.delegate = gelem
                c.resolveStrategy = Closure.DELEGATE_FIRST
                c()
            }
        }
        return gelem
    }

    static Root elements(AbstractComponent root, Closure closure) {
        def rootElement = ElementIntegration.getRoot(root)
        def c = closure.clone() as Closure
        c.delegate = new GroovyElements(rootElement)
        c.resolveStrategy = Closure.DELEGATE_FIRST
        c()
        return rootElement
    }
}
