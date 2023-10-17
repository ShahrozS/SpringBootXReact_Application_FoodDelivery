package com.shahroz.FoodDeliverySBandReact.repository;

import com.shahroz.FoodDeliverySBandReact.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface Userrepository extends JpaRepository<User,Long> {

    public Optional<User> findByEmail(String email);

}
