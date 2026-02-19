# Sp00tify
A sort of mini-spotify Upload Track service.

* Backend: Spring Boot (API + upload handler + metadata)
* Frontend: Next.js + TypeScript
* Storage: S3-compatible (AWS S3 or MinIO)
* DB: PostgreSQL
* Auth: JWT (OAuth2 in the future)
* Processing Async (Kafka / RabbitMQ) to audio analysis

[ Next.js Frontend ]
        |
        | REST + JWT
        v
[ Spring Boot API ]
        |
        | Metadata
        v
   [ PostgreSQL ]

        |
        | Audio files
        v
     [ S3 / MinIO ]

