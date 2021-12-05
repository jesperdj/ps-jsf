package com.jesperdj.jsf.musicshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LoggingPhaseListener implements PhaseListener {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingPhaseListener.class);

    private static final long serialVersionUID = 1L;

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        LOG.debug("Before phase: {}", event.getPhaseId());
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        LOG.debug("After phase: {}", event.getPhaseId());
    }
}
