package com.codetech.oauth2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/28/2019
 * Time: 8:58 AM
 */
@Getter
@Setter
@Entity
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "interest name cannot be a blank")
    @Size(min = 3, max = 200, message
            = "interest name must be between 3 and 200 characters")
    @Column(unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH
            },
            mappedBy = "interests")
      @Getter(onMethod = @__( @JsonIgnore))
    private Set<User> users = new HashSet<User>();

}
