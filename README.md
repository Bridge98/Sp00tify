# Sp00tify
A sort of mini-spotify Upload Track service.

* **Backend**: Spring Boot (API + upload handler + metadata)
* **Frontend**: Next.js + TypeScript
* **Storage**: S3-compatible (AWS S3 or MinIO)
* **DB**: PostgreSQL
* **Auth**: JWT (OAuth2 in the future)
* **Processing Async**: (Kafka / RabbitMQ) to audio analysis

<img width="731" height="575" alt="Screenshot 2026-02-19 alle 11 26 13" src="https://github.com/user-attachments/assets/d54c303b-5967-4e54-8cac-f8826e8d86a6" />

How the upload works:
  * **auth user** upload the bounce file of the track
  * **Backend**:
    * save metadata into DB
    * upload the audio file on S3 (MinIO)
    * return track ID + URL streaming

  * **Parameters of the track object**:
    * file (audio)
    * cover (image)
    * title
    * genre

!!! ## Next Updates needed:
  🎚️ **(AAA) Auto Audio Analysis**
    * BPM detection
    * Key detection
    * Loudness normalization (LUFS)
    * Waveform preview
    * Workflow: Upload Evento Kafka Microservizio analizza file Salva metadata audio nel DB
  🎛️ **Loudness normalization**
    * Normalization around -14 LUFS.
    * ffmpeg
    * Essentia
    * librosa (Python microservice)
  🔐 **Security**
    * JWT with Spring Security
    * upload size limitation
    * MIME type validation
    * Scan antivirus (optional)
    * Rate limiting
  🧱 **Evolution (production-grade)**
    * API Gateway
    * Microservices: auth-service, track-service, streaming-service
    * CDN Audio
    * Presigned URLs to direct upload in S3
    * caching with Redis
    * Dockerization
    * Deploy on AWS / Railway / Render...
