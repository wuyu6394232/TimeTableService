package com.yg.timetableservice.struct;


public class PositionTurn {
    public Turn turn;
    public int nextStopOrder = -1;
    public int nextDayFlag = 0;//if turn in next day, not divied by 24 hours
    public String cityId;
    public String lineNo;
    public int direction;
    public String getTurnId() {
        return cityId + "#" + lineNo + "#" + direction + "#" + turn.departTime;
    }

}
