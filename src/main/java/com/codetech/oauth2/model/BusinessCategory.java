package com.codetech.oauth2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/23/2019
 * Time: 11:19 AM
 */
@Getter
@Setter
@Entity
public class BusinessCategory extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "business category name cannot be a blank")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=\\S+$).{3,40}+$", message = "Name cannot only contains words, values between 3 to 40 and no spaces")
    @Column(unique = true)
    private String name;


    @ManyToMany(fetch = FetchType.LAZY, mappedBy="businessCategorySet")
    @Getter(onMethod = @__(     @JsonIgnore))
    @Setter
    private Set<BusinessProfile> businessProfile = new HashSet<BusinessProfile>();



}
