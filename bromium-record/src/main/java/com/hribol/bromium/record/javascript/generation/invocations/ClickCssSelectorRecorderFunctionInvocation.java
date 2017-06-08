package com.hribol.bromium.record.javascript.generation.invocations;

import java.text.MessageFormat;

/**
 * Created by hvrigazov on 08.06.17.
 */
public class ClickCssSelectorRecorderFunctionInvocation implements RecorderFunctionInvocation {
    private String cssSelector;
    private String eventName;

    public ClickCssSelectorRecorderFunctionInvocation(String cssSelector, String eventName) {
        this.cssSelector = cssSelector;
        this.eventName = eventName;
    }

    @Override
    public String getJavascriptCode() {
        return MessageFormat.format(
                "\n" +
                        "clickCssSelector(\"{0}\", \"{1}\")" +
                        "\n",
                cssSelector,
                eventName
        );
    }
}
