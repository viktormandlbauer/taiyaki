function base64UrlToUtf8(base64Url) {
  let base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
  const pad = base64.length % 4;
  if (pad) base64 += "=".repeat(4 - pad);

  const binary = atob(base64);
  const bytes = Uint8Array.from(binary, (c) => c.charCodeAt(0));
  return new TextDecoder().decode(bytes);
}

export function decodeJwtPayload(token) {
  if (!token) return null;

  const raw = token.startsWith("Bearer ") ? token.slice(7) : token;
  const parts = raw.split(".");
  if (parts.length !== 3) return null;

  try {
    return JSON.parse(base64UrlToUtf8(parts[1]));
  } catch {
    return null;
  }
}

export function getAccessToken() {
  return localStorage.getItem("token");
}

export function getSub() {
  const token = getAccessToken();
  return decodeJwtPayload(token)?.sub ?? null;
}
