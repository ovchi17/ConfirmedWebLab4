package aca98b.web4l.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="aca98b_result", schema = "s367845")
public class ResultEntity implements Serializable {
    private long id;
    private UserEntity ownerID;
    private float x;
    private float y;
    private float r;
    private boolean result;
    private LocalDateTime time;
    private long executionTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "owner_id")
    public UserEntity getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(UserEntity ownerID) {
        this.ownerID = ownerID;
    }

    @Column
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    @Column
    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Column
    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    @Column
    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Column(name = "time")
    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Column(name = "execution_time")
    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultEntity that)) return false;
        return getId() == that.getId() && getOwnerID() == that.getOwnerID() && Double.compare(getX(), that.getX()) == 0 && Double.compare(getY(), that.getY()) == 0 && Double.compare(getR(), that.getR()) == 0 && isResult() == that.isResult() && getExecutionTime() == that.getExecutionTime() && Objects.equals(getTime(), that.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOwnerID(), getX(), getY(), getR(), isResult(), getTime(), getExecutionTime());
    }

    @Override
    public String toString() {
        return "CheckAreaEntity{" +
                "id=" + id +
                ", ownerID=" + ownerID +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", result=" + result +
                ", executedAt=" + time +
                ", executionTime=" + executionTime +
                '}';
    }
}