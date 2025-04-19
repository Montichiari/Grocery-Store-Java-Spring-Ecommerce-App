import axios, {
  AxiosRequestConfig,
  AxiosResponse,
  RawAxiosRequestHeaders,
} from "axios";
interface ApiResponse<T> {
  data?: T;
  status?: number;
  error?: string;
}

const API_BASE_URL = "http://localhost:8080";
const DEFAULT_HEADERS: RawAxiosRequestHeaders = {
  "Content-Type": "application/json",
};
const DEFAULT_SETTINGS: AxiosRequestConfig = {
  withCredentials: true,
  headers: DEFAULT_HEADERS,
};

const api = {
  async get<T>(
    endpoint: string,
    params?: Record<string, any>
  ): Promise<ApiResponse<T>> {
    try {
      const response: AxiosResponse<T> = await axios.get(
        `${API_BASE_URL}/${endpoint}`,
        { ...DEFAULT_SETTINGS, params }
      );
      return { data: response.data, status: response.status };
    } catch (error: any) {
      console.log(error.response.status);
      return { error: error.response };
    }
  },
  async post<T, K>(
    endpoint: string,
    params?: Record<string, any>,
    body?: K
  ): Promise<ApiResponse<T>> {
    try {
      const response: AxiosResponse<T> = await axios.post(
        `${API_BASE_URL}/${endpoint}`,
        body,
        { ...DEFAULT_SETTINGS, params }
      );
      return { data: response.data, status: response.status };
    } catch (error: any) {
      console.log(error.response.status);
      return { error: error.response };
    }
  },
  async put<T, K>(
    endpoint: string,
    params?: Record<string, any>,
    body?: K
  ): Promise<ApiResponse<T>> {
    try {
      const response: AxiosResponse<T> = await axios.put(
        `${API_BASE_URL}/${endpoint}`,
        body,
        { ...DEFAULT_SETTINGS, params }
      );
      return { data: response.data, status: response.status };
    } catch (error: any) {
      console.log(error.response.status);
      return { error: error.response };
    }
  },
  async patch<T, K>(
    endpoint: string,
    params?: Record<string, any>,
    body?: K
  ): Promise<ApiResponse<T>> {
    try {
      const response: AxiosResponse<T> = await axios.patch(
        `${API_BASE_URL}/${endpoint}`,
        body,
        { ...DEFAULT_SETTINGS, params }
      );
      return { data: response.data, status: response.status };
    } catch (error: any) {
      console.log(error.response.status);
      return { error: error.response };
    }
  },
  async delete<T>(
    endpoint: string,
    params?: Record<string, any>
  ): Promise<ApiResponse<T>> {
    try {
      const response: AxiosResponse<T> = await axios.delete(
        `${API_BASE_URL}/${endpoint}`,
        { ...DEFAULT_SETTINGS, params }
      );
      return { data: response.data, status: response.status };
    } catch (error: any) {
      console.log(error.response.status);
      return { error: error.response };
    }
  },
};

export default api;
