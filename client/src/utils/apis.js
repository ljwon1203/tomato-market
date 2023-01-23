import axios from "axios";

axios.defaults.baseURL = "http://localhost:8080";

const http = axios.create();

export const api = {
  default: {
    setHeadersAuthorization(token) {
      http.defaults.headers.common["Authorization"] = "Bearer " + token;
    },
    deleteHeadersAuthorization() {
      delete http.defaults.headers.common["Authorization"];
    },
  },
  login: (payload) => {
    return http.post("/login", payload);
  },
  signup: (payload) => {
    return http.post("/signup", payload);
  },
  // customer
  patchProfile: (userId, payload) => {
    return http.patch(`/users/${userId}`, payload);
  },
  getProfile: () => {
    return http.get("/users");
  },
  getSellers: (params) => {
    return http.get("/sellers", {
      params,
    });
  },
  getMySellerProfile: (userId) => {
    return http.get(`/sellers/users/${userId}`);
  },
  getProducts: ({ params }) => {
    return http.get("/products", {
      params: params,
    });
  },
  postProduct: (payload) => {
    return http.post("/sellers/products/", payload);
  },
  postQuotation: (productId) => {
    return http.post(`/products/${productId}/quotations`);
  },
  postSellerAuth: (payload) => {
    return http.post(`/users/auth/waitings`, payload);
  },
  patchSellerAuth: (waitingId) => {
    return http.patch(`/admin/auth/waitings/${waitingId}`);
  },
  getSellerAuth: () => {
    return http.get("/admin/auth/waitings");
  },
  patchSellerProfile: (userId, payload) => {
    return http.patch(`/sellers/${userId}`, payload);
  },
  postSellerProduct: (payload) => {
    return http.post("/sellers/product", payload);
  },
  getMyProducts: () => {
    return http.get("/sellers/products");
  },
  deleteMyProduct: (productId) => {
    return http.delete(`/sellers/products/${productId}`);
  },
  patchProduct: (productId, payload) => {
    return http.patch(`/sellers/products/${productId}`, payload);
  },
  getQuotations: (params) => {
    return http.get(`/sellers/quotations`, { params });
  },
  patchQuotation: (requestId) => {
    return http.patch(`/sellers/quotations/${requestId}`);
  },
  deleteSellerAuth: (sellerId) => {
    return http.delete(`/admin/sellers/${sellerId}/`);
  },
  getAllCustomers: (params) => {
    return http.get(`/admin/users`, { params });
  },
};
