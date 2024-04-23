import { HttpHeaders } from "@angular/common/http";

/**
 * La classe des utilitaires.
 */
export abstract class Tools {

  /**
   * Contruire des HttpHeaders pour l'appel des APIs.
   * @returns HttpHeaders
   */
  public static getHttpHeaders(): HttpHeaders {
    let httpHeaders = new HttpHeaders();
    httpHeaders.set('Content-Type', 'application/x-www-form-urlencoded');
    httpHeaders.set('Access-Control-Allow-Origin', '*');
    return httpHeaders;
  }
}
