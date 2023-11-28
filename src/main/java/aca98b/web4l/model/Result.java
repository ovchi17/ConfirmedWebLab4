package aca98b.web4l.model;

import aca98b.web4l.model.entity.ResultEntity;
import aca98b.web4l.model.request.CheckHitRequest;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;

public class Result implements Serializable {
    private CheckHitRequest request;
    private boolean result;
    private Instant time;
    private long executionTime;

    public Result(){
        super();
    }

    public CheckHitRequest getRequest() {
        return request;
    }

    public void setRequest(CheckHitRequest request) {
        this.request = request;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public String toString() {
        return "CheckArea{" +
                "request=" + request +
                ", result=" + result +
                ", executedAt=" + time +
                ", executionTime=" + executionTime +
                '}';
    }

    public static Result fromEntity (ResultEntity entity) {
        final Result result = new Result();
        final CheckHitRequest checkHitRequest = new CheckHitRequest();
        checkHitRequest.setX(entity.getX());
        checkHitRequest.setY(entity.getY());
        checkHitRequest.setR(entity.getR());
        result.setRequest(checkHitRequest);
        result.setResult(entity.isResult());
        result.setTime(entity.getTime().atZone(ZoneId.systemDefault()).toInstant());
        result.setExecutionTime(entity.getExecutionTime());
        return result;
    }
}
