package ru.mastkey.cloudservice.client;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.mastkey.cloudservice.exception.ErrorType;
import ru.mastkey.cloudservice.exception.ServiceException;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3Client {
    private final MinioClient minioClient;

    public void createBucketIfNotExists(String bucketName) {
        try {
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder()
                    .bucket(bucketName)
                    .build());
            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder()
                        .bucket(bucketName)
                        .build());
                log.debug("Корзина с именем  '{}' создана", bucketName);
            }
        } catch (Exception e) {
            throw new ServiceException(ErrorType.INTERNAL_SERVER_ERROR, "Ошибка при создании корзины");
        }
    }
}
