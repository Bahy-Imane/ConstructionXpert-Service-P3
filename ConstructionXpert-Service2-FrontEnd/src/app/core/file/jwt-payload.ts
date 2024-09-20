import jwt_decode from 'jwt-decode';

export interface JwtPayload {
  role: string;
}

export function decodeToken(token: string): JwtPayload | null {
  try {
    // @ts-ignore
    const decoded = jwt_decode<JwtPayload>(token);
    return decoded;
  } catch (error) {
    console.error('Failed to decode token', error);
    return null;
  }
}
