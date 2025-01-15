import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class StockService {
  private apiKey = 'FJL70HW455HWR84O'; // Replace with your Alpha Vantage API key
  private baseUrl = 'https://www.alphavantage.co/query';

  constructor(private http: HttpClient) {}

  fetchStockData(symbol: string, interval: string = '5min'): Observable<any> {
    const url = `${this.baseUrl}?function=TIME_SERIES_INTRADAY&symbol=${symbol}&interval=${interval}&apikey=${this.apiKey}`;
    return this.http.get<any>(url);
  }
}
