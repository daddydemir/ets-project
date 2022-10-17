package com.example.etsproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

@Data
@Document(indexName = "address")
@AllArgsConstructor
@NoArgsConstructor
public class ElasticAddress {

    @Id
    private String id;

    @Field(name = "name", type = FieldType.Text)
    private String name;

    public ElasticAddress(String name){
        this.name = name;
    }

}
