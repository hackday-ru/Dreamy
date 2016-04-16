package org.kedzo.dreamy.models;

/**
 * Created by woodman on 16.04.16.
 * мечты,кошмары,сознательные,предсказывающие
 */
public enum DreamType {
    DREAM("мечта"),
    NIGHTMARE("кошмар"),
    CONSCIOUSNESS("осознаный"),
    PREDICTION("веший"),
    EROTICA("эротичный"),
    CYCLE("повторяюшийся"),
    CREATIVE("творческий"),
    LETHARGY("летаргичкский");

    private final String text;

    private DreamType(final String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return text;
    }

}
