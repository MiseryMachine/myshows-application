package com.rjs.myshows.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rjs.myshows.core.domain.entity.ShowTypeEntity;


//public interface ShowTypeRepository extends MongoRepository<ShowTypeEntity, String> {
public interface ShowTypeRepository extends JpaRepository<ShowTypeEntity, Long> {
	Optional<ShowTypeEntity> findByName(String name);
}
