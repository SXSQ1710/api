package com.sxsq.sxinterface.ai;

import lombok.Data;

@Data
public class Choices {
    private String text;
    private Integer index;
    private String logprobs;
    private String finish_reason;
}
