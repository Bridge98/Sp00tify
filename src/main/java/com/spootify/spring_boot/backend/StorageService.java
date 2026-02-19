@Service
public class StorageService {

    private final S3Client s3Client;

    public String upload(MultipartFile file) {

        String key = UUID.randomUUID() + "_" + file.getOriginalFilename();

        s3Client.putObject(
            PutObjectRequest.builder()
                .bucket("tracks")
                .key(key)
                .contentType(file.getContentType())
                .build(),
            RequestBody.fromBytes(file.getBytes())
        );

        return "https://your-bucket-url/" + key;
    }
}
