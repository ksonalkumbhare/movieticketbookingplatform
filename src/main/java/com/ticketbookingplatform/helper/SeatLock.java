package com.ticketbookingplatform.helper;


import java.time.Instant;
import java.util.Date;

import com.ticketbookingplatform.dto.ShowDto;
import com.ticketbookingplatform.dto.ShowSeatsDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatLock {
    private ShowSeatsDto seat;
	private ShowDto show;
    private Integer timeoutInSeconds;
    private Date lockTime;
    private long lockedBy;

    public boolean isLockExpired() {
         Instant lockInstant = lockTime.toInstant().plusSeconds(timeoutInSeconds);
         Instant currentInstant = new Date().toInstant();
        return lockInstant.isBefore(currentInstant);
    }
}
