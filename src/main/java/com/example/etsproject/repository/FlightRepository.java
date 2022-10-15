package com.example.etsproject.repository;

import com.example.etsproject.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@EnableJpaRepositories
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    @Override
    List<Flight> findAll();

    Flight findById(int id);

    List<Flight> findAllByPlaneId(int id);

    @Query("select f from Flight f where f.departurePoint = :_from and f.destination = :_to and f.departureTime >= :_time and f.departureTime < :_nextTime and f.emptyBusiness > :_business and f.emptyEconomy > :_economy")
    List<Flight> findFlightByDeparturePointAndDestinationAndDepartureTimeAndEmptyBusinessAndEmptyEconomyNamedParams(
            @Param("_from") String from,
            @Param("_to") String to,
            @Param("_time") Date time,
            @Param("_nextTime") Date nTime,
            @Param("_business") int business,
            @Param("_economy") int economy
    );

    @Query("select u from Flight u where u.departurePoint = ?1 ")
    List<Flight> findByFrom(String from);

}
