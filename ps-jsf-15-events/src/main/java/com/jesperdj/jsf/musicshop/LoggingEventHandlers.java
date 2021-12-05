package com.jesperdj.jsf.musicshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIInput;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

@Named
@ApplicationScoped
public class LoggingEventHandlers {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingEventHandlers.class);

    public void valueChanged(ValueChangeEvent event) {
        LOG.debug("Value of component {} was changed: [{}] => [{}]", event.getComponent().getId(),
                event.getOldValue(), event.getNewValue());
    }

    public void actionPerformed(ActionEvent event) {
        LOG.debug("Action performed for component: {}", event.getComponent().getId());
    }

    public void beforeValidation(ComponentSystemEvent event) {
        LOG.debug("Before validation of component: {}", event.getComponent().getId());
    }

    public void afterValidation(ComponentSystemEvent event) {
        UIInput inputComponent = (UIInput) event.getComponent();
        LOG.debug("After validation of component: {}, valid: {}", inputComponent.getId(), inputComponent.isValid());
    }

    public void addedToView(ComponentSystemEvent event) {
        LOG.debug("Component added to view: {}", event.getComponent().getId());
    }

    public void beforeRenderComponent(ComponentSystemEvent event) {
        LOG.debug("Before rendering of component: {}", event.getComponent().getId());
    }
}
