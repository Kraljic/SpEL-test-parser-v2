package hr.kraljic.spel.textparser2.parser;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
class ContextData {
    private String raw;
    private String contextObj;
    private String spelExp;
    private List<ContextData> subContexts;

    public ContextData(String raw) {
        this.raw = raw;
    }
}
