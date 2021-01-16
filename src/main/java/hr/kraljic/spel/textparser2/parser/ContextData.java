package hr.kraljic.spel.textparser2.parser;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
class ContextData {
    private String raw;
    private String contextObj;
    private String spelExp;
    private List<ContextData> subContexts;

    public ContextData(String raw) {
        this.raw = raw;
    }

    /** Copy constructor */
    public ContextData(ContextData old) {
        this.raw = old.raw;
        this.contextObj = old.contextObj;
        this.spelExp = old.spelExp;
        this.subContexts = new ArrayList<>(old.subContexts);
    }
}
