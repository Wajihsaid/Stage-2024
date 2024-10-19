package com.Farmer.Farm4U.Entities.Farm;

import com.Farmer.Farm4U.Entities.Motel.Motel;
import com.Farmer.Farm4U.Entities.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Farmer")
@DiscriminatorValue("Farmer")
public final class Farmer extends User {

    @OneToMany(mappedBy = "farmer")
    private List<Motel> motels;

}
