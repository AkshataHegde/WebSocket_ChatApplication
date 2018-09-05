package com.highpeak.chat.beans;

import lombok.Data;

import java.util.List;

@Data
public class GroupBean {

    private String groupName;

    private List<Long> participantIdList;
}
