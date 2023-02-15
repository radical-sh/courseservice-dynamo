package sh.radical.courseservice.mappers;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.Instant;

public class LocalDateTimeToStringTypeConverter implements DynamoDBTypeConverter<String, Instant> {

    @Override
    public String convert(Instant instant) {
        return instant.toString();
    }

    @Override
    public Instant unconvert(String s) {
        return Instant.parse(s);
    }
}
