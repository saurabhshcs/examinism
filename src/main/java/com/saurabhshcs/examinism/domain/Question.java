package com.saurabhshcs.examinism.domain;

import lombok.*;
import lombok.experimental.Accessors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Data
@Document(collection = "question")
@Accessors(chain = true)
@Getter
@Builder(toBuilder = true)
public class Question {

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Indexed
    @Field(targetType = FieldType.STRING)
    @NonNull
    private String description;

    private Integer complexity;

    public Object then(Question question) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'then'");
    }

}
