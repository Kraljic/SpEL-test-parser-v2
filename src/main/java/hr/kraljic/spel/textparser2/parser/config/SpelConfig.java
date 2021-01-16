package hr.kraljic.spel.textparser2.parser.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpelConfig {
    private boolean filterEmptyLines;
    private String commentPrefix;

    public boolean isFilterEmptyLines() {
        return filterEmptyLines;
    }

    public String getCommentPrefix() {
        return commentPrefix;
    }
}
