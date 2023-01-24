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
    console.log("[URL] /login");
    return http.post("/login", payload);
  },
  signup: (payload) => {
    console.log(`[URL] /signup`);
    return http.post("/signup", payload);
  },
  // customer
  patchProfile: (userId, payload) => {
    console.log(`[URL] /users/${userId}`);
    return http.patch(`/users/${userId}`, payload);
  },
  getProfile: () => {
    console.log(`[URL] /users`);
    return http.get("/users");
  },
  getSellers: (params) => {
    console.log(`[URL] /sellers`);
    return http.get("/sellers", {
      params,
    });
  },
  getMySellerProfile: (userId) => {
    console.log(`[URL] /sellers/users/${userId}`);
    return http.get(`/sellers/users/${userId}`);
  },
  getProducts: ({ params }) => {
    console.log(`[URL] /products`);
    return http.get("/products", {
      params: params,
    });
  },
  postProduct: (payload) => {
    console.log(`[URL] /sellers/products/`);
    return http.post("/sellers/products/", payload);
  },
  postQuotation: (productId) => {
    console.log(`[URL] /products/${productId}/quotations`);
    return http.post(`/products/${productId}/quotations`);
  },
  postSellerAuth: (payload) => {
    console.log(`[URL] /users/auth/waitings`);
    return http.post(`/users/auth/waitings`, payload);
  },
  patchSellerAuth: (waitingId) => {
    console.log(`[URL] /admin/auth/waitings/${waitingId}`);
    return http.patch(`/admin/auth/waitings/${waitingId}`);
  },
  getSellerAuth: () => {
    console.log(`[URL] /admin/auth/waitings`);
    return http.get("/admin/auth/waitings");
  },
  patchSellerProfile: (userId, payload) => {
    console.log(`[URL] /sellers/${userId}`);
    return http.patch(`/sellers/${userId}`, payload);
  },
  postSellerProduct: (payload) => {
    console.log(`[URL] /sellers/products`);
    return http.post("/sellers/products", payload);
  },
  getMyProducts: () => {
    console.log(`[URL] /sellers/products`);
    return http.get("/sellers/products");
  },
  deleteMyProduct: (productId) => {
    console.log(`[URL] /sellers/products/${productId}`);
    return http.delete(`/sellers/products/${productId}`);
  },
  patchProduct: (productId, payload) => {
    console.log(`[URL] /sellers/products/${productId}`);
    return http.patch(`/sellers/products/${productId}`, payload);
  },
  getQuotations: (params) => {
    console.log(`[URL] /sellers/quotations`);
    return http.get(`/sellers/quotations`, { params });
  },
  patchQuotation: (requestId) => {
    console.log(`[URL] /sellers/quotations/${requestId}`);
    return http.patch(`/sellers/quotations/${requestId}`);
  },
  deleteSellerAuth: (sellerId) => {
    console.log(`[URL] /admin/sellers/${sellerId}`);
    return http.delete(`/admin/sellers/${sellerId}`);
  },
  getAllCustomers: (params) => {
    console.log(`[URL] /admin/users`);
    return http.get(`/admin/users`, { params });
  },
};
