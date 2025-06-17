// src/utils/auth.js
import jwt_decode from "jwt-decode";

export function isTokenValid(token) {
  if (!token) return false;

  try {
    const decoded = jwt_decode(token);
    const now = Date.now().valueOf() / 1000; // current time in seconds

    if (decoded.exp && decoded.exp > now) {
      return true; // token is still valid
    } else {
      return false; // token expired
    }
  } catch (error) {
    console.error("Invalid token", error);
    return false;
  }
}
