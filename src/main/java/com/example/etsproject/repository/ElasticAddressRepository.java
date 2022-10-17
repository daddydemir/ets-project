package com.example.etsproject.repository;

import com.example.etsproject.entity.ElasticAddress;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.annotations.Query;

import java.util.List;

public interface ElasticAddressRepository extends ElasticsearchRepository<ElasticAddress,Integer> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}}]}}")
    List<ElasticAddress> getByCustomQuery(String search);

    List<ElasticAddress> findByNameLike(String name);
}
