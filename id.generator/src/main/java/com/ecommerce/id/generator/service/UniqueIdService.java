package com.ecommerce.id.generator.service;

import com.ecommerce.id.generator.common.Constants;
import com.ecommerce.id.generator.exceptions.ClockMovedBackException;
import com.ecommerce.id.generator.exceptions.OutOfBoundNodeIdException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UniqueIdService {

    @Autowired
    private Integer generatingNodeId;

    private final int maxSequenceVal = (int) Math.pow(2, Constants.SEQUENCE_BIT_LEN);
    private final int maxNodeVal = (int) Math.pow(2, Constants.NODE_ID_BIT_LEN);
    private final long EPOCH_START = 1687034278;


    private volatile long currentSequence = -1L;
    private volatile long lastTimestamp = -1L;

    @PostConstruct
    public void checkNodeIdBounds() throws OutOfBoundNodeIdException{
        if(generatingNodeId < 0 || generatingNodeId > maxNodeVal){
            throw new OutOfBoundNodeIdException("Node is either < 0 or >" + maxNodeVal);
        }
    }


    public synchronized long generateId() throws ClockMovedBackException, OutOfBoundNodeIdException {
        checkNodeIdBounds();
        long currentTimeStamp = getTimeStamp();
        if (currentTimeStamp < lastTimestamp) {
            throw new ClockMovedBackException("Clock moved back");
        }
        if (currentTimeStamp == lastTimestamp) {
            // check if current sequence is less than max sequence value
            currentSequence = currentSequence + 1 & maxSequenceVal;
            if (currentSequence != 0) {
                // if current sequence if currentSequence == max sequence then wait for next millisecond timestamp
                currentTimeStamp = waitNextMillis(currentTimeStamp);
            }
        } else {
            currentSequence = 0;
        }
        lastTimestamp = currentTimeStamp;
        long id = currentTimeStamp << (Constants.NODE_ID_BIT_LEN + Constants.SEQUENCE_BIT_LEN);
        id |= (generatingNodeId << Constants.SEQUENCE_BIT_LEN);
        id |= currentSequence;
        return id;
    }

    private long getTimeStamp() {
        return Instant.now().toEpochMilli() - EPOCH_START;
    }

    private long waitNextMillis(long currentTimeStamp) {
        while (currentTimeStamp == lastTimestamp) {
            currentTimeStamp = getTimeStamp();
        }
        return currentTimeStamp;
    }

}
