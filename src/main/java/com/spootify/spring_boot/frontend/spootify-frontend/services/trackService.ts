import { api } from "./api";

export const uploadTrack = async (
  file: File,
  cover: File,
  title: string,
  genre: string
) => {
  const formData = new FormData();
  formData.append("file", file);
  formData.append("cover", cover);
  formData.append("title", title);
  formData.append("genre", genre);

  return await api.post("/tracks/upload", formData, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
};

export const getAllTracks = async () => {
  const response = await api.get("/tracks");
  return response.data;
};
