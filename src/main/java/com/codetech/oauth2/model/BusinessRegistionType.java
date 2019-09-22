package com.codetech.oauth2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/26/2019
 * Time: 12:29 PM
 */

@Getter
@Setter
@Entity
public class BusinessRegistionType extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "business registation name cannot be a blank")
    @Size(min = 3, max = 200, message
            = "business category name must be between 3 and 200 characters")
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "businessRegistionTypes")
    @JsonIgnore
    Set<BusinessProfile> businessProfiles;
}
