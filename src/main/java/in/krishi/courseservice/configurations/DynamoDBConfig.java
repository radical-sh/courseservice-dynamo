package in.krishi.courseservice.configurations;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import in.krishi.courseservice.repositories.CourseItemRepository;
import in.krishi.courseservice.repositories.CourseRepository;
import java.net.URI;
import java.util.Objects;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
@EnableDynamoDBRepositories(
        basePackageClasses = { CourseRepository.class, CourseItemRepository.class }
)
public class DynamoDBConfig {

    @Value(value = "${amazon.aws.accesskey}")
    String amazonAWSAccessKey;

    @Value(value = "${amazon.aws.secretkey}")
    String amazonAWSSecretKey;

    @Value(value = "${amazon.dynamodb.endpoint}")
    String amazonDynamoDbEndpointUrl;

    @Value(value = "${app.environment}")
    String environment;

    @Value(value = "${amazon.dynamodb.region}")
    String awsRegion;

    public AWSCredentialsProvider amazonAWSCredentialsProvider() {
        return new AWSStaticCredentialsProvider(amazonAWSCredentials());
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    }

    @Bean
    @Primary
    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        return DynamoDBMapperConfig.DEFAULT;
    }

    @Bean
    @Primary
    public DynamoDBMapper dynamoDBMapper(
            AmazonDynamoDB amazonDynamoDB,
            DynamoDBMapperConfig config
    ) {
        return new DynamoDBMapper(amazonDynamoDB, config);
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient() {
        Region region = Region.of(awsRegion);

        var builder = DynamoDbClient.builder().region(region);

        if (Objects.equals(environment, "local")) {
            builder =
                    builder.endpointOverride(URI.create(amazonDynamoDbEndpointUrl));
        }

        DynamoDbClient ddb = builder
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(
                                        amazonAWSAccessKey,
                                        amazonAWSSecretKey
                                )
                        )
                )
                .build();

        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient
                .builder()
                .dynamoDbClient(ddb)
                .build();
        return enhancedClient;
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        var builder = AmazonDynamoDBClientBuilder.standard();

        if (Objects.equals(environment, "local")) {
            builder =
                    builder.withEndpointConfiguration(
                            new AwsClientBuilder.EndpointConfiguration(
                                    amazonDynamoDbEndpointUrl,
                                    awsRegion
                            )
                    );
        }

        return builder.withCredentials(amazonAWSCredentialsProvider()).build();
    }
}