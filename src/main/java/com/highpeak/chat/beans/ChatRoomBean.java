package com.highpeak.chat.beans;

import java.math.BigInteger;
import java.util.List;

public class ChatRoomBean {
    private String name;

    private String type;

    private List<BigInteger> participantIdList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<BigInteger> getParticipantIdList() {
        return participantIdList;
    }

    public void setParticipantIdList(List<BigInteger> participantIdList) {
        this.participantIdList = participantIdList;
    }
}
