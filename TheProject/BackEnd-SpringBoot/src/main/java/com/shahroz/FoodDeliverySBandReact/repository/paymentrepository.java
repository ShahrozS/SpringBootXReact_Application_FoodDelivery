package com.shahroz.FoodDeliverySBandReact.repository;

import com.shahroz.FoodDeliverySBandReact.entities.payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface paymentrepository extends JpaRepository<payment,Long> {
}
