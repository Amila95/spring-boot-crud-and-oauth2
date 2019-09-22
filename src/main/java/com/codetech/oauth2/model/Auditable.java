package com.codetech.oauth2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 3:06 PM
 */

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable <U> {

    @CreatedDate
    @Column(name = "created_date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="IST")
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "modifiedDate")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "IST")
    private Date modifiedDate;

}
